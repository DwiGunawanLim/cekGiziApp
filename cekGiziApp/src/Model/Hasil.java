/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class Hasil extends Connector{
    private String namaAnak;
    private String jenisKelamin;
    private int berat;
    private int tinggi;
    private int umur;
    private String hasil;
    
    public String getnamaAnak(){
        return namaAnak;
    }
    public void setnamaAnak(String namaAnak){
        this.namaAnak = namaAnak;
    }
    
    public String getjenisKelamin(){
        return jenisKelamin;
    }
    public void setjenisKelamin(String jenisKelamin){
        this.jenisKelamin = jenisKelamin;
    }
    
    public int getBerat(){
        return berat;
    }
    public void setBerat(int Berat){
        this.berat = Berat;
    }
    
    public int getTinggi(){
        return tinggi;
    }
    public void setTinggi(int Tinggi){
        this.tinggi = Tinggi;
    }
    
    public int getUmur(){
        return umur;
    }
    public void setUmur(int Umur){
        this.umur = Umur;
    }
    
    public String getHasil(){
        return hasil;
    }
    public void setHasil(String Hasil){
        this.hasil = Hasil;
    }
    
    public boolean SimpanHasil(Hasil hasil) throws SQLException {
        PreparedStatement pstm = null;
        Connection conn = (Connection) Connector.configDB();
        
        String sql = "INSERT INTO `hasil`(`nama_anak`,`jenis_kelamin_anak`,`berat_anak`,`tinggi_anak`,`umur_anak`,`hasil_cek_anak`) VALUES (?,?,?,?,?,?)";
        try {
            pstm = conn.prepareStatement(sql);
            pstm.setString(1, getnamaAnak());
            pstm.setString(2, getjenisKelamin());
            pstm.setInt(3, getBerat());
            pstm.setInt(4, getTinggi());
            pstm.setInt(5, getUmur());
            pstm.setString(6, getHasil());
            pstm.execute();
            return true;
        } catch (HeadlessException | SQLException e) {
            System.err.println(e);
            return false;
        }
    }
    
    public boolean UpdateHasil(Hasil hasil) throws SQLException {
        PreparedStatement pstm = null;
        Connection conn = (Connection) Connector.configDB();
        
        String sql = "UPDATE hasil SET jenis_kelamin_anak=?, berat_anak=?, tinggi_anak=?, umur_anak=?, hasil_cek_anak=? WHERE nama_anak=?";
        try {
            pstm = conn.prepareStatement(sql);
            pstm.setString(1, getnamaAnak());
            pstm.setString(2, getjenisKelamin());
            pstm.setInt(3, getBerat());
            pstm.setInt(4, getTinggi());
            pstm.setInt(5, getUmur());
            pstm.setString(6, getHasil());
            pstm.execute();
            return true;
        } catch (HeadlessException | SQLException e) {
            System.err.println(e);
            return false;
        }
    }
    
    public boolean DeleteHasil(Hasil hasil) throws SQLException {
        PreparedStatement pstm = null;
        Connection conn = (Connection) Connector.configDB();
        
        String sql = "DELETE FROM hasil WHERE nama_anak=?";
        try {
            pstm = conn.prepareStatement(sql);
            pstm.setString(1, getnamaAnak());
            pstm.execute();
            return true;
        } catch (HeadlessException | SQLException e) {
            System.err.println(e);
            return false;
        }
    }
}
