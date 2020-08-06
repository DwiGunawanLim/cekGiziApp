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
import Model.Hasil;
import View.FormHasil;
import Controller.HasilController;
import View.MENU;
import Controller.MenuController;
/**
 *
 * @author ucul
 */
public class cekGiziApp {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // PENGGUNA
        Pengguna dataPengguna = new Pengguna();
        FormPengguna formPengguna = new FormPengguna();
        PenggunaController ctrlPengguna = new PenggunaController(dataPengguna, formPengguna);
        
        ctrlPengguna.KosongFormPengguna();
        ctrlPengguna.TampilDataFormPengguna();
        formPengguna.setVisible(false);
        
        // HASIL
        Hasil dataHasil = new Hasil();
        FormHasil formHasil = new FormHasil();
        HasilController ctrlHasil = new HasilController(dataHasil, formHasil);
        
        ctrlHasil.KosongFormHasil();
        ctrlHasil.TampilDataFormHasil();
        ctrlHasil.ComboNamaAnak();
        formHasil.setVisible(false);
        
        // MENU
        MENU menu = new MENU();
        MenuController ctrlMenu = new MenuController(menu);
        menu.setVisible(true);
    }
}
