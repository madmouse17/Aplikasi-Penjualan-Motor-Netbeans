/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package penjualan_motor;

/**
 *
 * @author Mad Silver
 */
import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import net.proteanit.sql.DbUtils;
public class Ftransaksi extends javax.swing.JFrame {

    /**
     * Creates new form Ftransaksi
     */
      Statement st;
    ResultSet rs;
    PreparedStatement ps;
      koneksi k;
    String idPenjualan, id_motor,ubah;
    int count ,jml_awal;
    public Ftransaksi() {
        initComponents();
        setTanggal();
        GetData();
        tampilcombo();
        otomatis();
        jTextField1.disable();
        jTextField6.disable();
        jTextField7.disable();
        jTextField8.disable();
        jTextField9.disable();
    }
private void setTanggal(){
    java.util.Date skrg = new java.util.Date();
    java.text.SimpleDateFormat kal = new
    java.text.SimpleDateFormat("dd/MM/yyyy");
    jLabel14.setText(kal.format(skrg));
    }

 private void GetData(){ // menampilkan data dari database
    try {
        Connection conn =(Connection)penjualan_motor.koneksi.configDB();
        java.sql.Statement stm = conn.createStatement();
        java.sql.ResultSet sql = stm.executeQuery("select * from tbjual");
        jTable1.setModel(DbUtils.resultSetToTableModel(sql));
    }
    catch (SQLException | HeadlessException e) {
    }
     }
     
     private void tampilcombo()
    {
        try {
        Connection conn =penjualan_motor.koneksi.configDB();
        java.sql.Statement stm = conn.createStatement();
        String sql = "select id_motor from tbstock order by id_motor asc";  
         rs = stm.executeQuery(sql);
        
        while(rs.next()){
            Object[] ob = new Object[3];
            ob[0]=  rs.getString(1);
            
           
        jComboBox1.addItem(ob[0]);
        }
        rs.close(); stm.close();
         
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }              
    }
     private void tampil()
    {
        try {
        Connection conn =penjualan_motor.koneksi.configDB();
        java.sql.Statement stm = conn.createStatement();
        String sql = "select type,merk,warna,harga from tbstock where id_motor='"+jComboBox1.getSelectedItem()+"'";  
        rs = stm.executeQuery(sql);
        
        while(rs.next()){
            Object[] ob = new Object[5];
            ob[0]=  rs.getString(1);
            ob[1]=  rs.getString(2);
            ob[2]=  rs.getString(3);
            ob[3]=  rs.getString(4);
            
            
            
            jTextField6.setText((String) ob[0]);
            jTextField7.setText((String) ob[1]);
            jTextField8.setText((String) ob[2]);
            jTextField9.setText((String) ob[3]);
            
            
            
        }
        rs.close(); stm.close();
         
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }              
    }
     private void baru(){
         jTextField1.setText("");
         jTextField2.setText("");
         jTextField3.setText("");
         jTextField4.setText("");
         jTextField5.setText("");
         jTextField6.setText("");
         jTextField7.setText("");
         jTextField8.setText("");
         jTextField9.setText("");
         jTextField10.setText("");
         jTextField11.setText("");
           
          jTextField1.requestFocus();
     }

      private void otomatis()
    {
       try {
            String sql ="select * from tbjual order by no_fak desc";
            java.sql.Connection conn = (java.sql.Connection)penjualan_motor.koneksi.configDB();
            st= conn.createStatement();
            rs=st.executeQuery(sql);
            if (rs.next()) {
                String id = rs.getString("no_fak").substring(3);
                String AN = "" + (Integer.parseInt(id) + 1);
                String Nol = "";
    
               if(AN.length()==1)
                {Nol = "0";}
                else if(AN.length()==2)
                {Nol = "";}

               jTextField1.setText("FA-" + Nol + AN);
            } else {
               jTextField1.setText("FA-01");
            }

           }catch(SQLException | NumberFormatException e){
           JOptionPane.showMessageDialog(null, e);
           }
     }
      
      
        private int cekStok() {
        int stok = 0;
        String total = "SELECT jumlah from tbstock where id_motor ='" + jComboBox1.getSelectedItem()+ "'";
        try {
            Connection conn =penjualan_motor.koneksi.configDB();
            st = conn.createStatement();
            rs = st.executeQuery(total);

            while (rs.next()) {
                stok = Integer.parseInt(rs.getString(1));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            System.out.println("" + e.getMessage());
        }
        return stok;
    }
        private void simpan(){
                   try {
              
            String sql = "insert into tbjual values('"+jTextField1.getText()+"','"+jLabel14.getText()+"','"+jTextField2.getText()+"','"+jTextField3.getText()+"','"+jTextField4.getText()+"','"+jTextField5.getText()+"','"+jComboBox1.getSelectedItem()+"','"+jTextField6.getText()+"','"+jTextField7.getText()+"','"+jTextField8.getText()+"','"+jTextField9.getText()+"','"+jTextField10.getText()+"','"+jTextField11.getText()+"')";
            java.sql.Connection conn = (java.sql.Connection)penjualan_motor.koneksi.configDB();
            java.sql.PreparedStatement pst = conn.prepareStatement(sql);
            pst.execute();
            
            JOptionPane.showMessageDialog(null, "berhasil disimpan");
            
                        
        } catch (SQLException | HeadlessException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    
       
    GetData();   
    baru();
    otomatis();
        }
        
      private void UpdateStok(String ubah) {
       int stokBaru = 0;
       try {
           switch (ubah) {
               case "insert":
                   stokBaru = cekStok() - Integer.parseInt(jTextField10.getText());
                   break;
               case "delete":
                   stokBaru=cekStok()+Integer.parseInt(jTextField10.getText());
                   break;
           }
           String total = "UPDATE tbstock set jumlah ='" + stokBaru + "' where id_motor = '" + jComboBox1.getSelectedItem() + "'";
           st.executeUpdate(total);
       } catch (Exception e) {
           JOptionPane.showMessageDialog(null, e.getMessage());
           System.out.println("" + e.getMessage());
       }
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
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jTextField3 = new javax.swing.JTextField();
        jTextField4 = new javax.swing.JTextField();
        jTextField5 = new javax.swing.JTextField();
        jTextField6 = new javax.swing.JTextField();
        jTextField7 = new javax.swing.JTextField();
        jTextField8 = new javax.swing.JTextField();
        jTextField9 = new javax.swing.JTextField();
        jTextField10 = new javax.swing.JTextField();
        jTextField11 = new javax.swing.JTextField();
        jComboBox1 = new javax.swing.JComboBox();
        jLabel14 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setIconImage(new javax.swing.ImageIcon(getClass().getResource("images/logo.png")).getImage());
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setText("No Faktur");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 120, -1, -1));

        jLabel3.setText("NIK");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 150, -1, -1));

        jLabel4.setText("Nama");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 180, -1, -1));

        jLabel5.setText("Alamat");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 210, -1, -1));

        jLabel6.setText("No Hp");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 250, -1, -1));

        jLabel7.setText("ID Motor");
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 110, -1, -1));

        jLabel8.setText("Type");
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 140, -1, -1));

        jLabel9.setText("Merk");
        getContentPane().add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 170, -1, -1));

        jLabel10.setText("Warna");
        getContentPane().add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 200, -1, -1));

        jLabel11.setText("Harga");
        getContentPane().add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 230, -1, -1));

        jLabel12.setText("Jumlah");
        getContentPane().add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 260, -1, -1));

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel13.setText("Total");
        getContentPane().add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 580, -1, -1));
        getContentPane().add(jTextField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 120, 70, -1));
        getContentPane().add(jTextField2, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 150, 110, -1));
        getContentPane().add(jTextField3, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 180, 130, -1));
        getContentPane().add(jTextField4, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 210, 140, 30));
        getContentPane().add(jTextField5, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 250, 140, 30));
        getContentPane().add(jTextField6, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 140, 80, -1));
        getContentPane().add(jTextField7, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 170, 70, -1));
        getContentPane().add(jTextField8, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 200, 60, -1));
        getContentPane().add(jTextField9, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 230, 100, -1));

        jTextField10.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField10KeyReleased(evt);
            }
        });
        getContentPane().add(jTextField10, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 260, 60, -1));

        jTextField11.setBackground(new java.awt.Color(255, 0, 0));
        jTextField11.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jTextField11.setForeground(new java.awt.Color(255, 255, 255));
        jTextField11.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        getContentPane().add(jTextField11, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 560, 210, 60));

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "--PILIH--" }));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });
        getContentPane().add(jComboBox1, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 110, 80, -1));

        jLabel14.setBackground(new java.awt.Color(255, 255, 255));
        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("jLabel14");
        getContentPane().add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 50, -1, -1));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4", "Title 5", "Title 6", "Title 7", "Title 8", "Title 9", "Title 10", "Title 11", "Title 12", "Title 13"
            }
        ));
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);
        if (jTable1.getColumnModel().getColumnCount() > 0) {
            jTable1.getColumnModel().getColumn(0).setHeaderValue("Title 1");
            jTable1.getColumnModel().getColumn(1).setHeaderValue("Title 2");
            jTable1.getColumnModel().getColumn(2).setHeaderValue("Title 3");
            jTable1.getColumnModel().getColumn(3).setHeaderValue("Title 4");
            jTable1.getColumnModel().getColumn(4).setHeaderValue("Title 5");
            jTable1.getColumnModel().getColumn(5).setHeaderValue("Title 6");
            jTable1.getColumnModel().getColumn(6).setHeaderValue("Title 7");
            jTable1.getColumnModel().getColumn(7).setHeaderValue("Title 8");
            jTable1.getColumnModel().getColumn(8).setHeaderValue("Title 9");
            jTable1.getColumnModel().getColumn(9).setHeaderValue("Title 10");
            jTable1.getColumnModel().getColumn(10).setHeaderValue("Title 11");
            jTable1.getColumnModel().getColumn(11).setHeaderValue("Title 12");
            jTable1.getColumnModel().getColumn(12).setHeaderValue("Title 13");
        }

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 340, 710, 190));

        jButton1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jButton1.setText("SIMPAN");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 300, 90, 30));

        jButton2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jButton2.setText("BARU");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 300, 90, 30));

        jButton3.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jButton3.setText("EDIT");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 300, 80, 30));

        jButton4.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jButton4.setText("HAPUS");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 300, 80, 30));

        jButton5.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jButton5.setText("KELUAR");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 300, 100, 30));

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel15.setText("..................................................");
        getContentPane().add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 610, 210, -1));

        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel16.setText("..................................................");
        getContentPane().add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 530, 210, -1));

        jLabel1.setBackground(new java.awt.Color(0, 0, 0));
        jLabel1.setFont(new java.awt.Font("Sylfaen", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Transaksi Penjualan");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 20, -1, -1));

        jLabel17.setIcon(new javax.swing.ImageIcon(getClass().getResource("/penjualan_motor/images/logo.png"))); // NOI18N
        getContentPane().add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 0, -1, -1));

        jPanel1.setBackground(new java.awt.Color(51, 204, 0));
        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 720, 80));

        jPanel2.setBackground(new java.awt.Color(0, 0, 0));
        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 80, 720, 10));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        getContentPane().add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 110, 230, 180));

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        getContentPane().add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 100, 190, 190));

        jPanel5.setBackground(new java.awt.Color(204, 204, 204));
        getContentPane().add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 90, 730, 580));

        setSize(new java.awt.Dimension(733, 702));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
try {
             
            String sql = "update tbjual SET tanggal='"+jLabel14.getText()+"',nik='"+jTextField2.getText()+"',nama='"+jTextField3.getText()+"',alamat='"+jTextField4.getText()+"',hp='"+jTextField5.getText()+"',id_motor='"+jComboBox1.getSelectedItem()+"',type='"+jTextField6.getText()+"',merk='"+jTextField7.getText()+"',warna='"+jTextField8.getText()+"',harga='"+jTextField9.getText()+"',jumlah='"+jTextField10.getText()+"',total='"+jTextField11.getText()+"' where no_fak='"+jTextField1.getText()+"'";
            java.sql.Connection conn = (java.sql.Connection)penjualan_motor.koneksi.configDB();
            java.sql.PreparedStatement pst = conn.prepareStatement(sql);
            pst.execute();
            JOptionPane.showMessageDialog(null, "berhasil diubah");
        } catch (SQLException | HeadlessException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    GetData();
    baru();// TODO add your handling code here:
     otomatis();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
tampil();        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
  ubah="insert";
         if (Integer.parseInt(jTextField10.getText()) <= cekStok()) {
                  UpdateStok(ubah);
                  simpan();
             }else {
            JOptionPane.showMessageDialog(null, "Stok barang tidak cukup!!");
        }
 
    
   
// TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
 ubah="delete";
        try { // hapus data
            String sql ="delete from tbjual where no_fak='"+jTextField1.getText()+"'";
            java.sql.Connection conn = (java.sql.Connection)penjualan_motor.koneksi.configDB();
            java.sql.PreparedStatement pst = conn.prepareStatement(sql);
            pst.execute();
            JOptionPane.showMessageDialog(null, "Data akan dihapus?");
           
                UpdateStok(ubah);
               

        } catch (SQLException | HeadlessException e) {}

        GetData();        // TODO add your handling code here:
        baru();  
        otomatis();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
this.dispose();        // TODO add your handling code here:
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
  try {
        int row =jTable1.getSelectedRow();
        String tabel_klik=(jTable1.getModel().getValueAt(row, 0).toString());
        java.sql.Connection conn =(java.sql.Connection)penjualan_motor.koneksi.configDB();
        java.sql.Statement stm = conn.createStatement();
        java.sql.ResultSet sql = stm.executeQuery("select * from tbjual where no_fak='"+tabel_klik+"'");
        if(sql.next()){
            String no_fak = sql.getString("no_fak");
            jTextField1.setText(no_fak);
            String tanggal = sql.getString("tanggal");
            jLabel14.setText(tanggal);
            String nik = sql.getString("nik");
            jTextField2.setText(nik);
            String nama = sql.getString("nama");
            jTextField3.setText(nama);
            String alamat = sql.getString("alamat");
            jTextField4.setText(alamat);
            String hp = sql.getString("hp");
            jTextField5.setText(hp);
            String id_motor = sql.getString("id_motor");
            jComboBox1.setSelectedItem(id_motor);
            String type = sql.getString("type");
            jTextField6.setText(type);
            String merk = sql.getString("merk");
            jTextField7.setText(merk);
            String warna = sql.getString("warna");
            jTextField8.setText(warna);
            String harga = sql.getString("harga");
            jTextField9.setText(harga);
           String jumlah = sql.getString("jumlah");
            jTextField10.setText(jumlah);
            String total = sql.getString("total");
            jTextField11.setText(total);
           
        }
    } catch (SQLException e) {}        
                    // TODO add your handling code here:
    }//GEN-LAST:event_jTable1MouseClicked

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
baru();
otomatis();// TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jTextField10KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField10KeyReleased
 int harga,jumlah,total;
 harga=Integer.parseInt(jTextField9.getText());
 jumlah=Integer.parseInt(jTextField10.getText());
 total=harga*jumlah;
 jTextField11.setText(String.valueOf(total));         // TODO add your handling code here:
    }//GEN-LAST:event_jTextField10KeyReleased

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
            java.util.logging.Logger.getLogger(Ftransaksi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Ftransaksi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Ftransaksi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Ftransaksi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Ftransaksi().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField10;
    private javax.swing.JTextField jTextField11;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JTextField jTextField7;
    private javax.swing.JTextField jTextField8;
    private javax.swing.JTextField jTextField9;
    // End of variables declaration//GEN-END:variables
}
