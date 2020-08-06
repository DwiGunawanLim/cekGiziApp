package Controller;

import Model.Connector;
import Model.Pengguna;
import Model.Hasil;
import View.FormPengguna;
import View.MENU;
import View.FormHasil;
import Controller.HasilController;
import cekGiziApp.cekGiziApp;
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
/**
 *
 * @author ucul
 */
public class PenggunaController implements ActionListener, MouseListener{

    private Pengguna model;
    private FormPengguna form;
    
    
    public PenggunaController(Pengguna model, FormPengguna form){
        this.model = model;
        this.form = form;
        this.form.tablePengguna.addMouseListener(this);
        this.form.btnTambah.addActionListener(this);
        this.form.btnSimpan.addActionListener(this);
        this.form.btnHapus.addActionListener(this);
        this.form.btnEdit.addActionListener(this);
        this.form.btnCekGizi.addActionListener(this);
        this.form.btnBack.addActionListener(this);
    }
    
    public void KosongFormPengguna(){
        form.txtnamaAnak.setEditable(true);
        form.txtnamaOrtu.setText(null);
        form.txtalamatOrtu.setText(null);
        form.txtnoHpOrtu.setText(null);
        form.txtnamaAnak.setText(null);
    }
    
    public void TampilDataFormPengguna(){
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("No");
        model.addColumn("Nama Ortu");
        model.addColumn("Alamat Ortu");
        model.addColumn("No Hp Ortu");
        model.addColumn("Nama Anak");
        try {
            int no=1;
            String sql = "SELECT * FROM pengguna";
            java.sql.Connection conn = (Connection) Connector.configDB();
            java.sql.Statement stm = conn.createStatement();
            java.sql.ResultSet res = stm.executeQuery(sql);
            while (res.next()) {
                model.addRow(new Object[]{
                    no++,
                    res.getString(1),
                    res.getString(2),
                    res.getString(3),
                    res.getString(4)});
            }
            form.tablePengguna.setModel(model);
        } catch (SQLException e) {
            System.out.println("Error "+e.getMessage());
        }
    }
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        
        if(ae.getSource() == form.btnTambah){
            //UNTUK MENAMBAH USER ( KOSONGKAN FORM )
            KosongFormPengguna();
        } else if(ae.getSource() == form.btnSimpan){
            // SIMPAN DATA USER YANG ADA DI KOLOM TEXT AREA
            model.setnamaOrtu(form.txtnamaOrtu.getText());
            model.setalamatOrtu(form.txtalamatOrtu.getText());
            model.setnoHpOrtu(form.txtnoHpOrtu.getText());
            model.setnamaAnak(form.txtnamaAnak.getText());
            
            try {
                if (model.SimpanPengguna(model)) {
                    JOptionPane.showMessageDialog(null, "Simpan Data Berhasil!");
                    KosongFormPengguna();
                    TampilDataFormPengguna();
                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, e);
            }
        } else if (ae.getSource() == form.btnEdit) {
            // EDIT DATA USER YANG ADA DI KOLOM TEXT AREA
            model.setnamaOrtu(form.txtnamaOrtu.getText());
            model.setalamatOrtu(form.txtalamatOrtu.getText());
            model.setnoHpOrtu(form.txtnoHpOrtu.getText());
            model.setnamaAnak(form.txtnamaAnak.getText());
            
            try {
                if (model.UpdatePengguna(model)) {
                    JOptionPane.showMessageDialog(null, "Update Data berhasil!");
                    KosongFormPengguna();
                    TampilDataFormPengguna();
                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, e);
            }
        } else if (ae.getSource() == form.btnCekGizi) {
            //MENUJU KE CEK GIZI
            Hasil modelHasil = new Hasil();
            FormHasil formHasil = new FormHasil();
            HasilController ctrlHasil = new HasilController(modelHasil, formHasil);
            ctrlHasil.KosongFormHasil();
            ctrlHasil.TampilDataFormHasil();
            ctrlHasil.ComboNamaAnak();
            formHasil.setVisible(true);
            form.setVisible(false);
        } else if (ae.getSource() == form.btnBack) {
            //MENUJU KE MENU UTAMA
            MENU menu = new MENU();
            MenuController ctrl = new MenuController(menu);
            menu.setVisible(true);
            form.setVisible(false);
        } else {
            // HAPUS DATA USER YANG ADA DI KOLOM TEXT AREA
            model.setnamaAnak(form.txtnamaAnak.getText());
            
            try {
                if(model.DeletePengguna(model)){
                    JOptionPane.showMessageDialog(null, "Hapus Data Berhasil!");
                    KosongFormPengguna();
                    TampilDataFormPengguna();
                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, e);
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent me) {
        if (me.getSource()==form.tablePengguna) {
            form.txtnamaAnak.setEditable(false);
            
            int baris = form.tablePengguna.rowAtPoint(me.getPoint());
            String namaOrtu = form.tablePengguna.getValueAt(baris, 1).toString();
            form.txtnamaOrtu.setText(namaOrtu);
            String alamatOrtu = form.tablePengguna.getValueAt(baris, 2).toString();
            form.txtalamatOrtu.setText(alamatOrtu);
            String noHpOrtu = form.tablePengguna.getValueAt(baris, 3).toString();
            form.txtnoHpOrtu.setText(noHpOrtu);
            String namaAnak = form.tablePengguna.getValueAt(baris, 4).toString();
            form.txtnamaAnak.setText(namaAnak);
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
