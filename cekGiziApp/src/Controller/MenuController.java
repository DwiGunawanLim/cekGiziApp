/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Connector;
import Model.Hasil;
import Model.Pengguna;
import Controller.HasilController;
import Controller.MenuController;
import Controller.PenggunaController;
import View.FormHasil;
import View.FormPengguna;
import View.MENU;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import org.w3c.dom.events.MouseEvent;

/**
 *
 * @author ucul
 */
public class MenuController implements ActionListener,MouseListener{

    private MENU menu;
    Pengguna data = new Pengguna();
    FormPengguna form = new FormPengguna();
    
    Hasil data2 = new Hasil();
    FormHasil form2 = new FormHasil();
    
    public MenuController (MENU menu){
        this.menu = menu;
        this.menu.btnDaftar.addActionListener(this);
        this.menu.btnCekGizi.addActionListener(this);
        this.menu.btnExit.addMouseListener(this);
    }
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        if(ae.getSource() == menu.btnDaftar){
            
            PenggunaController ctrl = new PenggunaController(data, form);
            ctrl.KosongFormPengguna();
            ctrl.TampilDataFormPengguna();
            form.setVisible(true);
            menu.setVisible(false);
        } else if(ae.getSource() == menu.btnCekGizi){
            
            HasilController ctrl2 = new HasilController(data2, form2);
            ctrl2.KosongFormHasil();
            ctrl2.TampilDataFormHasil();
            ctrl2.ComboNamaAnak();
            form2.setVisible(true);
            menu.setVisible(false);
        } else if(ae.getSource() == menu.btnExit){
            
        }
    }

    @Override
    public void mouseClicked(java.awt.event.MouseEvent me) {
        if (me.getSource() == menu.btnExit) {
            System.exit(0);
        }
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mousePressed(java.awt.event.MouseEvent me) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseReleased(java.awt.event.MouseEvent me) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseEntered(java.awt.event.MouseEvent me) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseExited(java.awt.event.MouseEvent me) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
      
}
