/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package penjualan_motor;

import java.awt.Color;
import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 *
 * @author Mad Silver
 */
public class Flogin extends javax.swing.JFrame {
int xmouse;
int ymouse;
 koneksi koneksi;
Statement statement;
ResultSet resultSet;
public static int kondisiLogin=3;
public static String userLogin="tidak terdeteksi";
    /**
     * Creates new form Flogin
     */
    public Flogin() {
        initComponents();
        setBackground(new Color(0,0,0,0));
    }
     private void clear(){
    jTextField1.setText("");
    jPasswordField1.setText("");
    
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jPasswordField1 = new javax.swing.JPasswordField();
        jButton1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(null);

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/penjualan_motor/images/close_1.png"))); // NOI18N
        jLabel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel2MouseClicked(evt);
            }
        });
        getContentPane().add(jLabel2);
        jLabel2.setBounds(600, 20, 40, 40);

        jTextField1.setFont(new java.awt.Font("Tw Cen MT", 1, 36)); // NOI18N
        jTextField1.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jTextField1.setCaretColor(new java.awt.Color(255, 255, 255));
        getContentPane().add(jTextField1);
        jTextField1.setBounds(140, 160, 330, 80);

        jPasswordField1.setFont(new java.awt.Font("Tw Cen MT", 1, 36)); // NOI18N
        jPasswordField1.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jPasswordField1.setCaretColor(new java.awt.Color(255, 255, 255));
        jPasswordField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jPasswordField1ActionPerformed(evt);
            }
        });
        getContentPane().add(jPasswordField1);
        jPasswordField1.setBounds(140, 270, 330, 80);

        jButton1.setBorder(null);
        jButton1.setContentAreaFilled(false);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1);
        jButton1.setBounds(140, 380, 400, 80);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/penjualan_motor/images/login.png"))); // NOI18N
        jLabel1.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                jLabel1MouseDragged(evt);
            }
        });
        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel1MousePressed(evt);
            }
        });
        getContentPane().add(jLabel1);
        jLabel1.setBounds(0, 0, 660, 550);

        setSize(new java.awt.Dimension(658, 553));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MouseClicked
System.exit(0);        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel2MouseClicked

    private void jPasswordField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jPasswordField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jPasswordField1ActionPerformed

    private void jLabel1MouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseDragged
int x = evt.getXOnScreen();
        int y = evt.getYOnScreen();
        
        this.setLocation(x - xmouse, y - ymouse);
        System.out.println(x+","+y);        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel1MouseDragged

    private void jLabel1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MousePressed
 xmouse = evt.getX();
        ymouse = evt.getY();         // TODO add your handling code here:
    }//GEN-LAST:event_jLabel1MousePressed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
   String hak_akses = "";
 
        try {
            Connection c = koneksi.configDB();
            Statement s = c.createStatement();
            String sql = "SELECT username,password,hak_akses FROM tblogin where username='"+jTextField1.getText() + "' AND password=md5('"+ jPasswordField1.getText() +"')";

            ResultSet r = s.executeQuery(sql);

            while (r.next()) {
                hak_akses = r.getString(3);
            }
            if (hak_akses.equals("")) {
                JOptionPane.showMessageDialog(null, "USERNAME atau PASSWORD SALAH");
                clear();
            }
            else {
                if (hak_akses.equals("admin")) {
                    new Fmenu(0).setVisible(true);
                    kondisiLogin = 0;

                    this.dispose();
                } else if (hak_akses.equals("user")) {
                    new Fmenu(1).setVisible(true);

                    kondisiLogin = 1;
                    this.dispose();
                } else {
                    new Fmenu(3).setVisible(true);
                    kondisiLogin = 3;
                    this.dispose();
                }
                userLogin = jTextField1.getText();
            }

        } catch (SQLException | HeadlessException  e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }     // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Flogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Flogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Flogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Flogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Flogin().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPasswordField jPasswordField1;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
}
