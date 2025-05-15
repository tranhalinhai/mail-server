package mail;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.*;

public class Server {
    private static final int PORT = 12345;
    private static final String DB_URL = "jdbc:sqlite:emails.db";

    public static void main(String[] args) {
        createDatabase();
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("✅ Server is running on port " + PORT);
            while (true) {
                Socket socket = serverSocket.accept();
                new ClientHandler(socket).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static class ClientHandler extends Thread {
        private Socket socket;

        public ClientHandler(Socket socket) {
            this.socket = socket;
            
        }

        @Override
        public void run() {
            try (DataInputStream in = new DataInputStream(socket.getInputStream());
                 DataOutputStream out = new DataOutputStream(socket.getOutputStream())) {

                String requestType = in.readUTF();

                if ("login".equals(requestType)) {
                    String email = in.readUTF();
                    boolean exists = isEmailExists(email);
                    out.writeUTF(exists ? "success" : "fail");

                } else if ("signup".equals(requestType)) {
                    String email = in.readUTF();
                    String password = in.readUTF();
                    boolean success = registerUser(email, password);
                    out.writeUTF(success ? "Signup successful" : "Signup failed: Email already exists");

                } else if ("sendmail".equals(requestType)) {
    String sender = in.readUTF();
    String recipient = in.readUTF();
    String subject = in.readUTF();
    String content = in.readUTF();
    String attachFiles = in.readUTF(); // Nhận đường dẫn file đính kèm

    if (!isEmailExists(recipient)) {
        out.writeUTF("Lỗi: Email người nhận không tồn tại!");
        return;
    }

boolean saved = saveEmail(sender, recipient, subject, content, attachFiles);
System.out.println("Server phản hồi: " + (saved ? "Email đã gửi thành công!" : "Lỗi khi gửi email!"));
out.writeUTF(saved ? "Email đã gửi thành công!" : "Lỗi khi gửi email!");
}else if ("saveSMTPMail".equals(requestType)) {
    String sender = in.readUTF();
    String recipient = in.readUTF();
    String subject = in.readUTF();
    String content = in.readUTF();
    String attachFiles = in.readUTF();

    boolean saved = saveEmail(sender, recipient, subject, content, attachFiles);
    out.writeUTF(saved ? "Email SMTP đã lưu!" : "Lỗi khi lưu email SMTP!");
}


                else if ("checkemail".equals(requestType)) {
    String email = in.readUTF();
    boolean exists = isEmailExists(email);
    out.writeUTF(exists ? "exists" : "not_found");
}
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        private boolean isEmailExists(String email) {
            String sql = "SELECT email FROM users WHERE email = ?";
            try (Connection conn = DriverManager.getConnection(DB_URL);
                 PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, email);
                ResultSet rs = pstmt.executeQuery();
                return rs.next();
            } catch (SQLException e) {
                e.printStackTrace();
                return false;
            }
        }

        private boolean registerUser(String email, String password) {
            if (isEmailExists(email)) return false;

            String sql = "INSERT INTO users (email, password) VALUES (?, ?)";
            try (Connection conn = DriverManager.getConnection(DB_URL);
                 PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, email);
                pstmt.setString(2, password);
                pstmt.executeUpdate();
                return true;
            } catch (SQLException e) {
                e.printStackTrace();
                return false;
            }
        }

        private boolean saveEmail(String sender, String recipient, String subject, String content, String attachFiles) {
    String sql = "INSERT INTO emails (sender, recipient, subject, content, timestamp, attachFiles) VALUES (?, ?, ?, ?, datetime('now'), ?)";


    try (Connection conn = DriverManager.getConnection(DB_URL);
         PreparedStatement pstmt = conn.prepareStatement(sql)) {
        pstmt.setString(1, sender);
        pstmt.setString(2, recipient);
        pstmt.setString(3, subject);
        pstmt.setString(4, content);
        pstmt.setString(5, attachFiles);
        pstmt.executeUpdate();
        return true;
    } catch (SQLException e) {
        e.printStackTrace();
        return false;
    }
}

    }

    private static void createDatabase() {
        String createUsersTable = "CREATE TABLE IF NOT EXISTS users (email TEXT PRIMARY KEY, password TEXT NOT NULL);";
        String createEmailsTable = "CREATE TABLE IF NOT EXISTS emails ("
            + "id INTEGER PRIMARY KEY AUTOINCREMENT, "
            + "sender TEXT, "
            + "recipient TEXT, "
            + "subject TEXT, "
            + "content TEXT, "
            + "timestamp DATETIME DEFAULT CURRENT_TIMESTAMP, "
            + "attachment TEXT"
            + ");";

        try (Connection conn = DriverManager.getConnection(DB_URL);
             Statement stmt = conn.createStatement()) {
            stmt.execute(createUsersTable);
            stmt.execute(createEmailsTable);
            System.out.println("📌 Database checked/created successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
