package Model;

import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class Pengguna extends Connector{
    private String namaOrtu;
    private String alamatOrtu;
    private String noHpOrtu;
    private String namaAnak;
    
    public String getnamaOrtu(){
        return namaOrtu;
    }
    public void setnamaOrtu(String namaOrtu){
        this.namaOrtu = namaOrtu;
    }
    
    public String getalamatOrtu(){
     return alamatOrtu;   
    }
    public void setalamatOrtu(String alamatOrtu){
        this.alamatOrtu = alamatOrtu;
    }
    
    public String getnoHpOrtu(){
        return noHpOrtu;
    }
    public void setnoHpOrtu(String noHpOrtu){
        this.noHpOrtu = noHpOrtu;
    }
    
    public String getnamaAnak(){
        return namaAnak;
    }
    public void setnamaAnak(String namaAnak){
        this.namaAnak = namaAnak;
    }
    
    public boolean SimpanPengguna(Pengguna pengguna) throws SQLException {
        PreparedStatement pstm = null;
        Connection conn = (Connection) Connector.configDB();
        
        String sql = "INSERT INTO `pengguna`(`nama_ortu`, `alamat_ortu`, `no_hp_ortu`, `nama_anak`) VALUES (?,?,?,?)";
        try{
            pstm = conn.prepareStatement(sql);
            pstm.setString(1, getnamaOrtu());
            pstm.setString(2, getalamatOrtu());
            pstm.setString(3, getnoHpOrtu());
            pstm.setString(4, getnamaAnak());
            pstm.execute();
            return true;
        } catch (HeadlessException | SQLException e){
            System.err.println(e);
            return false;
        }
    }
    
    public boolean UpdatePengguna(Pengguna pengguna) throws SQLException {
        PreparedStatement pstm = null;
        Connection conn = (Connection) Connector.configDB();
        
        String sql = "UPDATE pengguna SET nama_ortu=?, alamat_ortu=?, no_hp_ortu=? WHERE nama_anak=?";
        try{
            pstm = conn.prepareStatement(sql);
            pstm.setString(1, getnamaOrtu());
            pstm.setString(2, getalamatOrtu());
            pstm.setString(3, getnoHpOrtu());
            pstm.setString(4, getnamaAnak());
            pstm.execute();
            return true;
        } catch (HeadlessException | SQLException e){
            System.err.println(e);
            return false;
        }
    }
    
    public boolean DeletePengguna(Pengguna pengguna) throws SQLException {
        PreparedStatement pstm = null;
        Connection conn = (Connection) Connector.configDB();
        
        String sql = "DELETE FROM pengguna WHERE nama_anak=?";
        try{
            pstm = conn.prepareStatement(sql);
            pstm.setString(1, getnamaAnak());
            pstm.execute();
            return true;
        } catch (HeadlessException | SQLException e){
            System.err.println(e);
            return false;
        }
    }
}
