package mail;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;

public class Client {
    private JFrame frame;
    private JTextField emailField, recipientField, subjectField;
    private JPasswordField passwordField;
    private JTextArea messageArea;
    private File attachment; // Biến lưu file đính kèm
    private JLabel attachmentLabel; // Hiển thị tên file

    public Client() {
        frame = new JFrame("Mail Client");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 600);
        frame.setLocationRelativeTo(null);
        showMailPanel();
        frame.setVisible(true);
    }

    private void showMailPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JLabel titleLabel = new JLabel("Compose Email", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));

        emailField = createTextField("Your Email");
        passwordField = createPasswordField("Your App Password");
        recipientField = createTextField("Recipient Email");
        subjectField = createTextField("Subject");

        messageArea = new JTextArea(10, 30);
        messageArea.setLineWrap(true);
        JScrollPane scrollPane = new JScrollPane(messageArea);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Message"));

        JButton attachButton = createButton("Attach File", e -> attachFile());
        attachmentLabel = new JLabel("No file attached");

        JButton sendButton = createButton("Send Email", e -> sendEmailSMTP());

        panel.add(titleLabel);
        panel.add(emailField);
        panel.add(passwordField);
        panel.add(recipientField);
        panel.add(subjectField);
        panel.add(scrollPane);
        panel.add(attachButton);
        panel.add(attachmentLabel);
        panel.add(sendButton);

        frame.setContentPane(panel);
        frame.revalidate();
    }

    private void attachFile() {
        JFileChooser fileChooser = new JFileChooser();
        int option = fileChooser.showOpenDialog(frame);
        if (option == JFileChooser.APPROVE_OPTION) {
            attachment = fileChooser.getSelectedFile();
            attachmentLabel.setText("Attached: " + attachment.getName());
        }
    }

    private void sendEmailSMTP() {
        String username = emailField.getText();
        String password = new String(passwordField.getPassword());
        String recipient = recipientField.getText();
        String subject = subjectField.getText();
        String messageContent = messageArea.getText();

        if (username.isEmpty() || password.isEmpty() || recipient.isEmpty() || subject.isEmpty() || messageContent.isEmpty()) {
            showMessage("Please fill in all fields.");
            return;
        }

        Properties props = new Properties();
props.put("mail.smtp.auth", "true");
props.put("mail.smtp.starttls.enable", "true");
props.put("mail.smtp.host", "smtp.gmail.com");
props.put("mail.smtp.port", "587");
props.put("mail.smtp.ssl.protocols", "TLSv1.2");
props.put("mail.smtp.ssl.trust", "smtp.gmail.com");


        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipient));
            message.setSubject(subject);

            // Kiểm tra có file đính kèm không
            if (attachment != null) {
                Multipart multipart = new MimeMultipart();

                // Nội dung email
                MimeBodyPart textPart = new MimeBodyPart();
                textPart.setText(messageContent);
                multipart.addBodyPart(textPart);

                // File đính kèm
                MimeBodyPart filePart = new MimeBodyPart();
                filePart.attachFile(attachment);
                multipart.addBodyPart(filePart);

                message.setContent(multipart);
            } else {
                message.setText(messageContent);
            }

            Transport.send(message);
            showMessage("Email sent successfully.");

            // Gửi thông tin tới Server
            sendToServer(username, recipient, subject);
        } catch (Exception e) {
            showMessage("Failed to send email: " + e.getMessage());
        }
    }

    private void sendToServer(String sender, String recipient, String subject) {
        try {
            java.net.Socket socket = new java.net.Socket("localhost", 12345);
            java.io.PrintWriter out = new java.io.PrintWriter(socket.getOutputStream(), true);
            out.println(sender + ";" + recipient + ";" + subject + ";" + new java.util.Date());
            socket.close();
        } catch (Exception e) {
            showMessage("Failed to send data to server.");
        }
    }

    private void showMessage(String message) {
        JOptionPane.showMessageDialog(frame, message);
    }

    private JTextField createTextField(String title) {
        JTextField textField = new JTextField();
        textField.setBorder(BorderFactory.createTitledBorder(title));
        return textField;
    }

    private JPasswordField createPasswordField(String title) {
        JPasswordField passwordField = new JPasswordField();
        passwordField.setBorder(BorderFactory.createTitledBorder(title));
        return passwordField;
    }

    private JButton createButton(String text, ActionListener action) {
        JButton button = new JButton(text);
        button.addActionListener(action);
        return button;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Client::new);
    }
}
