/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cekGiziApp;
import Model.Connector;
import Model.Pengguna;
import View.FormPengguna;
import Controller.PenggunaController;
/**
 *
 * @author ucul
 */
public class cekGiziApp {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Pengguna data = new Pengguna();
        FormPengguna form = new FormPengguna();
        PenggunaController ctrl = new PenggunaController(data, form);
        
        ctrl.KosongFormPengguna();
        ctrl.TampilDataFormPengguna();
        form.setVisible(true);
    }
    
}
