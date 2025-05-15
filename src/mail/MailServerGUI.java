package mail;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;
import com.formdev.flatlaf.FlatLightLaf;
import java.awt.font.TextAttribute;
import java.util.Map;
import javax.swing.plaf.basic.BasicTableUI;
import java.awt.Font;
import java.awt.Color;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import javax.swing.border.Border;
import java.sql.*;
import javax.swing.table.DefaultTableModel;

public class MailServerGUI extends javax.swing.JFrame {

    public MailServerGUI() {
        initComponents();
        alignTableColumnsCenter(User_Table);
        addButtonColumn();
        customizeTable();
        loadUserData(); // Gọi hàm load dữ liệu vào bảng khi khởi động
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        User_Table = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 235, 235));
        setPreferredSize(new java.awt.Dimension(1152, 648));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 204, 204));
        jPanel1.setAlignmentX(0.0F);
        jPanel1.setAlignmentY(0.0F);
        jPanel1.setPreferredSize(new java.awt.Dimension(180, 648));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("SVN-Billy", 0, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(235, 56, 116));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Mail Server");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 13, 168, 50));

        jButton1.setBackground(new java.awt.Color(255, 189, 189));
        jButton1.setFont(new java.awt.Font("SVN-Amsi Narw", 0, 14)); // NOI18N
        jButton1.setText("User Management");
        jButton1.setAlignmentY(0.0F);
        jButton1.setBorder(null);
        jButton1.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 69, 180, 39));

        jButton2.setBackground(new java.awt.Color(255, 204, 204));
        jButton2.setFont(new java.awt.Font("SVN-Amsi Narw", 0, 14)); // NOI18N
        jButton2.setText("Mail Logs");
        jButton2.setAlignmentY(0.0F);
        jButton2.setBorder(null);
        jButton2.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 114, 180, 39));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1520, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 660, Short.MAX_VALUE)
        );

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(186, 0, 1520, 660));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, -8, -1, 660));

        jPanel3.setBackground(new java.awt.Color(255, 241, 241));

        jLabel2.setFont(new java.awt.Font("SVN-Billy", 0, 36)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(235, 56, 116));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("User Management");

        User_Table.setBackground(new java.awt.Color(255, 231, 231));
        User_Table.setFont(new java.awt.Font("SVN-Amsi Narw", 0, 14)); // NOI18N
        User_Table.setForeground(new java.awt.Color(102, 102, 102));
        User_Table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "ID", "Username (Mail)", "Password", "Number of sent emails", "Function"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        User_Table.setGridColor(new java.awt.Color(255, 186, 186));
        User_Table.setShowGrid(true);
        jScrollPane1.setViewportView(User_Table);
        User_Table.getAccessibleContext().setAccessibleDescription("d");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(335, 335, 335)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 322, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(42, 42, 42)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 877, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(201, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 427, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(169, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 0, 1120, 670));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void addButtonColumn() {
        System.out.println("Thêm cột chức năng vào bảng");
    TableColumn functionColumn = User_Table.getColumnModel().getColumn(4);
functionColumn.setCellRenderer(new ButtonRenderer());
functionColumn.setCellEditor(new ButtonEditor(new JCheckBox()));
}
    
public class ButtonRenderer extends JButton implements TableCellRenderer {
    public ButtonRenderer() {
        setOpaque(true);
        setBackground(new Color(255, 231, 231)); // Màu hồng nhạt
        setForeground(Color.BLACK);
        setBorder(BorderFactory.createEmptyBorder());

        // Gạch chân chữ
        Font font = new Font("SVN-Amsi Narw", Font.BOLD, 14);
        Map<TextAttribute, Object> attributes = (Map<TextAttribute, Object>) font.getAttributes();
        attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
        setFont(font.deriveFont(attributes));
    }

    @Override
public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        setText("Xóa"); // Gán nhãn "Xóa" cho nút

        // Đồng bộ viền với bảng nếu cần
        Color tableBorderColor = table.getGridColor();
        setBorder(BorderFactory.createLineBorder(tableBorderColor, 1));

        return this;
    }
}

class ButtonEditor extends DefaultCellEditor {
    private JButton button;
    private boolean isClicked;
    private int row;
    
    public ButtonEditor(JCheckBox checkBox) {
        super(checkBox);
        button = new JButton("Xóa");
        button.setOpaque(true);
        button.setBackground(new Color(255, 102, 102)); // Đổi màu nút xóa
        button.setForeground(Color.WHITE);
        button.addActionListener(e -> fireEditingStopped());
    }

   @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        this.row = row;
        isClicked = true;
        return button;
    }

    @Override
    public Object getCellEditorValue() {
        if (isClicked) {
            deleteUser(row);
        }
        isClicked = false;
        return "Xóa";
    }

    @Override
    public boolean stopCellEditing() {
        isClicked = false;
        return super.stopCellEditing();
    }

    private void deleteUser(int row) {
        DefaultTableModel model = (DefaultTableModel) User_Table.getModel();
        String emailToDelete = (String) model.getValueAt(row, 1); // Lấy email để xóa

        int confirm = JOptionPane.showConfirmDialog(null, "Bạn có chắc muốn xóa tài khoản: " + emailToDelete + "?", "Xác nhận xóa", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            try (Connection conn = DriverManager.getConnection("jdbc:sqlite:emails.db");
                 PreparedStatement pstmt = conn.prepareStatement("DELETE FROM users WHERE email = ?")) {
                pstmt.setString(1, emailToDelete);
                int affectedRows = pstmt.executeUpdate();

                if (affectedRows > 0) {
                    model.removeRow(row); // Xóa khỏi bảng
                    JOptionPane.showMessageDialog(null, "Xóa thành công!");
                } else {
                    JOptionPane.showMessageDialog(null, "Không thể xóa tài khoản!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Lỗi khi kết nối với cơ sở dữ liệu!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}

private void customizeTable() {
    JTableHeader header = User_Table.getTableHeader();
    header.setFont(new Font("SVN-Amsi Narw", Font.PLAIN, 14));
    header.setForeground(Color.BLACK);
    header.setBackground(new Color(255, 231, 231));
    
    // Đặt viền bảng chỉ một lần để tránh chỗ dày chỗ mỏng
    Color borderColor = new Color(255,169,169); // Màu viền nhẹ
    Border uniformBorder = BorderFactory.createLineBorder(borderColor, 1);
    
    // Áp dụng viền cho tiêu đề bảng
    header.setBorder(uniformBorder);

    // Cấu hình màu bảng
    User_Table.setGridColor(borderColor);
    User_Table.setShowGrid(true);
    User_Table.setBackground(new Color(255, 231, 231));

    // Nếu bảng nằm trong JScrollPane, đặt viền đồng nhất
    if (User_Table.getParent() instanceof JViewport) {
        Container scrollPane = User_Table.getParent().getParent();
        if (scrollPane instanceof JScrollPane) {
            ((JScrollPane) scrollPane).setBorder(uniformBorder);
        }
    }
    
    
header.setDefaultRenderer(new DefaultTableCellRenderer() {
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        JLabel label = new JLabel(value.toString(), JLabel.CENTER);
        label.setFont(new Font("SVN-Amsi Narw", Font.PLAIN, 14));
        label.setForeground(Color.BLACK);
        label.setBackground(new Color(255, 231, 231));
        label.setOpaque(true);
        label.setBorder(BorderFactory.createLineBorder(new Color(255,169,169), 1)); // Viền mỏng hơn
        return label;
    }
});
DefaultTableCellRenderer renderer = new DefaultTableCellRenderer() {
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        JLabel label = new JLabel(value.toString(), JLabel.CENTER);
        label.setFont(new Font("Arial", Font.PLAIN, 14));
        label.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, new Color(255,169,169))); // Viền dày 2px
        return label;
    }
};

}

private void alignTableColumnsCenter(JTable table) {
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        for (int i = 0; i < table.getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
    }

private void loadUserData() {
        DefaultTableModel model = (DefaultTableModel) User_Table.getModel();
        model.setRowCount(0); // Xóa dữ liệu cũ

        String dbUrl = "jdbc:sqlite:emails.db";
        String sql = "SELECT users.email, users.password, COUNT(emails.id) AS sent_count " +
                     "FROM users LEFT JOIN emails ON users.email = emails.sender " +
                     "GROUP BY users.email";

        try (Connection conn = DriverManager.getConnection(dbUrl);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            int id = 1;
            while (rs.next()) {
                String username = rs.getString("email");
                String password = rs.getString("password");
                int sentEmails = rs.getInt("sent_count");

                model.addRow(new Object[]{id++, username, password, sentEmails, "Xóa"});
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
try {
            UIManager.setLookAndFeel(new FlatLightLaf());
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }

        java.awt.EventQueue.invokeLater(() -> new MailServerGUI().setVisible(true));
    }
    
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable User_Table;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
