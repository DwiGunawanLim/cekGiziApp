/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Connector;
import Model.Hasil;
import Model.Pengguna;
import View.FormHasil;
import View.FormPengguna;
import View.MENU;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class HasilController implements ActionListener, MouseListener{

    private Hasil model;
    private FormHasil form;

    public HasilController(Hasil model, FormHasil form){
        this.model = model;
        this.form = form;
        this.form.tableHasil.addMouseListener(this);
        this.form.btnTambah.addActionListener(this);
        this.form.btnSimpan.addActionListener(this);
        this.form.btnHapus.addActionListener(this);
        this.form.btnEdit.addActionListener(this);
        this.form.btnKembali.addActionListener(this);
        this.form.btnDaftar.addActionListener(this);
    }
    
    public void KosongFormHasil(){
        form.btnComboAnak.setEditable(true);
        form.txtjenisKelamin.setText(null);
        form.txtBerat.setText(null);
        form.txtTinggi.setText(null);
        form.txtUmur.setText(null);
    }
    
    public void TampilDataFormHasil(){
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("No");
        model.addColumn("Nama Anak");
        model.addColumn("Jenis Kelamin");
        model.addColumn("Berat");
        model.addColumn("Tinggi");
        model.addColumn("Umur");
        model.addColumn("Hasil");
        try {
            int no=1;
            String sql = "SELECT * FROM hasil";
            java.sql.Connection conn = (Connection) Connector.configDB();
            java.sql.Statement stm = conn.createStatement();
            java.sql.ResultSet res = stm.executeQuery(sql);
            while (res.next()) {
                model.addRow(new Object[]{
                    no++,
                    res.getString(1),
                    res.getString(2),
                    res.getString(3),
                    res.getString(4),
                    res.getString(5),
                    res.getString(6)});
            }
            form.tableHasil.setModel(model);
        } catch (SQLException e){
            System.err.println("Error "+e.getMessage());        
        }
    }
    
    public void ComboNamaAnak() {
        try {
            String sql = "SELECT nama_anak FROM pengguna";      
            java.sql.Connection conn = (Connection) Connector.configDB();
            java.sql.Statement stm = conn.createStatement();
            java.sql.ResultSet res = stm.executeQuery(sql);    

            while (res.next()) {
                Object[] ob = new Object[3];
                ob[0] = res.getString(1);

                form.btnComboAnak.addItem((String)ob[0]);
            }
            res.close();
            stm.close();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        if(ae.getSource() == form.btnTambah){
            KosongFormHasil();
        } else if(ae.getSource() == form.btnSimpan){
            model.setnamaAnak(form.btnComboAnak.getSelectedItem().toString());
            model.setjenisKelamin(form.txtjenisKelamin.getText());
            model.setBerat(Integer.parseInt(form.txtBerat.getText()));
            model.setTinggi(Integer.parseInt(form.txtTinggi.getText()));
            model.setUmur(Integer.parseInt(form.txtUmur.getText()));
            
            // PENGAMBILAN DATA GIZI
            int berat = Integer.parseInt(form.txtBerat.getText());
            int tinggi = Integer.parseInt(form.txtTinggi.getText());
            int umur = Integer.parseInt(form.txtUmur.getText());
            float hasilberat = berat/umur;
            float hasiltinggi = tinggi/umur;
            float beratberdasarkanumur = berat/tinggi;
            
            //PERHITUNGAN GIZI
            String hasil = null;
            
            //PERHITUNGAN BERAT BERDASARKAN UMUR
            if ( hasilberat >= -2 && hasilberat <= 3 ) {
                hasil = "Berat Badan Anak Normal. ";
            } else if ( hasilberat < -2 && hasilberat >= -3 ) {
                hasil = "Berat Badan Anak Kurang. ";
            } else if ( hasilberat < -3) {
                hasil = "Berat Badan Anak Sangat Kurang. ";
            } else {
                hasil = "";
            }
            
            //PERHITUNGAN TINGGI BERDASARKAN UMUR
            if ( hasiltinggi > 2 ) {
                hasil += "Tinggi Badan Anak Diatas Normal. ";
            } else if ( hasiltinggi > -2 && hasilberat <= 2 ) {
                hasil += "Tinggi Badan Anak Normal. ";
            } else if ( hasiltinggi >= -3 && hasiltinggi <= -2) {
                hasil += "Anak Pendek. ";
            } else if ( hasiltinggi < -3 ) {
                hasil += "Anak Sangat Pendek. ";
            } else {
                hasil += "";
            }
            
            //PERHITUNGAN BERAT BADAN BERDASARKAN TINGGI BADAN
            if ( beratberdasarkanumur > 3 ) {
                hasil += "Anak Sangat Gemuk. ";
            } else if ( beratberdasarkanumur > 2 && beratberdasarkanumur <= 3 ) {
                hasil += "Anak Gemuk. ";
            } else if ( beratberdasarkanumur > -2 && beratberdasarkanumur <= 2) {
                hasil += "Anak Normal. ";
            } else if ( beratberdasarkanumur > -3 && beratberdasarkanumur <= -2) {
                hasil += "Anak Kurus. ";
            } else {
                hasil += "";
            }
            
            // PERMINTAAN SIMPAN DATA
            model.setHasil(hasil);
            int tanya = JOptionPane.showConfirmDialog(null, hasil + "\n\nApakah Data Ingin Disimpan?");
            
            if (tanya == 0) {
                
                try {
                    if (model.SimpanHasil(model)) {
                        JOptionPane.showMessageDialog(null, "Simpan Data Berhasil!");
                        KosongFormHasil();
                        TampilDataFormHasil();
                    }
                } catch (SQLException e) {
                    JOptionPane.showMessageDialog(null, e);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Terimakasih Sudah Menggunakan Sistem ini. Sampai Jumpa Di Lain Waktu");
            }
            
        } else if (ae.getSource() == form.btnEdit) {
            model.setnamaAnak(form.btnComboAnak.getSelectedItem().toString());
            model.setjenisKelamin(form.txtjenisKelamin.getText());
            model.setBerat(Integer.parseInt(form.txtBerat.getText()));
            model.setTinggi(Integer.parseInt(form.txtTinggi.getText()));
            model.setUmur(Integer.parseInt(form.txtUmur.getText()));
            
            // PENGAMBILAN DATA GIZI
            int berat = Integer.parseInt(form.txtBerat.getText());
            int tinggi = Integer.parseInt(form.txtTinggi.getText());
            int umur = Integer.parseInt(form.txtUmur.getText());
            float hasilberat = berat/umur;
            float hasiltinggi = tinggi/umur;
            float beratberdasarkanumur = berat/tinggi;
            
            //PERHITUNGAN GIZI
            String hasil = null;
            
            //PERHITUNGAN BERAT BERDASARKAN UMUR
            if ( hasilberat >= -2 && hasilberat <= 3 ) {
                hasil = "Berat Badan Anak Normal. ";
            } else if ( hasilberat < -2 && hasilberat >= -3 ) {
                hasil = "Berat Badan Anak Kurang. ";
            } else if ( hasilberat < -3) {
                hasil = "Berat Badan Anak Sangat Kurang. ";
            } else {
                hasil = "";
            }
            
            //PERHITUNGAN TINGGI BERDASARKAN UMUR
            if ( hasiltinggi > 2 ) {
                hasil += "Tinggi Badan Anak Diatas Normal. ";
            } else if ( hasiltinggi > -2 && hasilberat <= 2 ) {
                hasil += "Tinggi Badan Anak Normal. ";
            } else if ( hasiltinggi >= -3 && hasiltinggi <= -2) {
                hasil += "Anak Pendek. ";
            } else if ( hasiltinggi < -3 ) {
                hasil += "Anak Sangat Pendek. ";
            } else {
                hasil += "";
            }
            
            //PERHITUNGAN BERAT BADAN BERDASARKAN TINGGI BADAN
            if ( beratberdasarkanumur > 3 ) {
                hasil += "Anak Sangat Gemuk. ";
            } else if ( beratberdasarkanumur > 2 && beratberdasarkanumur <= 3 ) {
                hasil += "Anak Gemuk. ";
            } else if ( beratberdasarkanumur > -2 && beratberdasarkanumur <= 2) {
                hasil += "Anak Normal. ";
            } else if ( beratberdasarkanumur > -3 && beratberdasarkanumur <= -2) {
                hasil += "Anak Kurus. ";
            } else {
                hasil += "";
            }
            
            // PERMINTAAN SIMPAN DATA
            model.setHasil(hasil);
            int tanya = JOptionPane.showConfirmDialog(null, hasil + "\n\nApakah Data Ingin Disimpan?");
            
            if (tanya == 0) {
                try {
                    if (model.SimpanHasil(model)) {
                        JOptionPane.showMessageDialog(null, "Update Data berhasil!");
                        KosongFormHasil();
                        TampilDataFormHasil();
                    }
                } catch (SQLException e) {
                    JOptionPane.showMessageDialog(null, e);
                }
            }
        } else if (ae.getSource() == form.btnDaftar) {
            // MENUJU KE DAFTAR
            Pengguna modelPengguna = new Pengguna();
            FormPengguna formPengguna = new FormPengguna();
            PenggunaController ctrl = new PenggunaController(modelPengguna, formPengguna);
            ctrl.KosongFormPengguna();
            ctrl.TampilDataFormPengguna();
            formPengguna.setVisible(true);
            form.setVisible(false);
        } else if (ae.getSource() == form.btnKembali) {
            MENU menu = new MENU();
            MenuController menuctrl = new MenuController(menu);
            menu.setVisible(true);
            form.setVisible(false);
        } else {
            model.setnamaAnak(form.btnComboAnak.getSelectedItem().toString());
            
            try {
                if(model.DeleteHasil(model)){
                    JOptionPane.showMessageDialog(null, "Hapus Data Berhasil!");
                    KosongFormHasil();
                    TampilDataFormHasil();
                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, e);
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent me) {
        if (me.getSource()==form.tableHasil) {
            form.btnComboAnak.setEditable(false);
            
            int baris = form.tableHasil.rowAtPoint(me.getPoint());
            String namaAnak = form.tableHasil.getValueAt(baris, 1).toString();
            form.btnComboAnak.setSelectedItem(namaAnak);
            String jenisKelamin = form.tableHasil.getValueAt(baris, 2).toString();
            form.txtjenisKelamin.setText(jenisKelamin);
            String berat = form.tableHasil.getValueAt(baris, 3).toString();
            form.txtBerat.setText(berat);
            String tinggi = form.tableHasil.getValueAt(baris, 4).toString();
            form.txtTinggi.setText(tinggi);
            String umur = form.tableHasil.getValueAt(baris, 5).toString();
            form.txtUmur.setText(umur);
            
        }
    }

    @Override
    public void mousePressed(MouseEvent me) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseReleased(MouseEvent me) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseEntered(MouseEvent me) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseExited(MouseEvent me) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
