package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.bussines.ProdavacManager;
import ba.unsa.etf.rpr.domain.Prodavac;
import ba.unsa.etf.rpr.exceptions.KarteException;

import java.sql.*;
import java.util.Map;
import java.util.TreeMap;

public class ProdavacDAOSQLImplementation extends AbstractDAO<Prodavac> implements ProdavacDAO {

    private static ProdavacDAOSQLImplementation instance = null;
    private ProdavacManager manager = new ProdavacManager();
    public ProdavacDAOSQLImplementation() {
        super("Prodavac");
    }

    public static ProdavacDAOSQLImplementation getInstance() {
        if (instance == null) instance = new ProdavacDAOSQLImplementation();
        return instance;
    }

    public static void removeInstance() {
        if(instance != null) instance = null;
    }

    public int getId(String ime) throws KarteException {
        String sql = "SELECT id FROM Prodavac WHERE ime = ?";
        try {
            PreparedStatement ps = this.getConnection().prepareStatement(sql);
            ps.setObject(1,ime);
            ResultSet rs = ps.executeQuery();
            if(rs.next()) {
                return rs.getInt("id");
            }
        }
        catch(SQLException e) {
            throw new KarteException(e.getMessage(),e);
        }
        return -1;
    }

    @Override
    public Prodavac row2object(ResultSet rs) throws KarteException {
        try {
            Prodavac p = new Prodavac();
            p.setId(rs.getInt("id"));
            p.setIme(rs.getString("ime"));
            p.setTelefon(rs.getString("telefon"));
            p.setMail(rs.getString("mail"));
            return p;
        } catch (SQLException e) {
            throw new KarteException(e.getMessage(), e);
        }
    }

    @Override
    public Map<String, Object> object2row(Prodavac object) {
        Map<String, Object> row = new TreeMap<>();
        row.put("id", object.getId());
        row.put("ime", object.getIme());
        row.put("telefon", object.getTelefon());
        row.put("mail",object.getMail());
        return row;
    }
     @Override
    public Prodavac getById(int id) throws KarteException {
         Prodavac prodavac = null;
         String sql = "SELECT id, ime, telefon, mail FROM Prodavac WHERE id = ? ";
        try {
            PreparedStatement ps = this.getConnection().prepareStatement(sql);
            ps.setObject(1,id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int oid = rs.getInt("id");
                String ime = rs.getString("ime");
                String telefon = rs.getString("telefon");
                String mail = rs.getString("mail");
                prodavac = new Prodavac(oid,ime,telefon,mail);
            }
            return prodavac;
        }
        catch(SQLException e) {
            throw new KarteException(e.getMessage(),e);
        }
    }
    @Override
    public Prodavac add(Prodavac prodavac) throws KarteException {
        String sql = "INSERT INTO Prodavac (id, ime, telefon, mail) VALUES (?,?, ?, ?)";
        try {
            PreparedStatement ps = this.getConnection().prepareStatement(sql);
            ps.setObject(1,prodavac.getId());
            ps.setObject(2, prodavac.getIme());
            ps.setObject(3, prodavac.getTelefon());
            ps.setObject(4, prodavac.getMail());

            ps.executeUpdate();
            return prodavac;
        }
        catch(SQLException e) {
            throw new KarteException(e.getMessage(),e);
        }
    }

}
