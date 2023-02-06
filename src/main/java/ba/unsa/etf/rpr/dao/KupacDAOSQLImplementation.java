package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.bussines.KarteManager;
import ba.unsa.etf.rpr.bussines.KupacManager;
import ba.unsa.etf.rpr.bussines.ProdavacManager;
import ba.unsa.etf.rpr.domain.Karte;
import ba.unsa.etf.rpr.domain.Kupac;
import ba.unsa.etf.rpr.domain.Prodavac;
import ba.unsa.etf.rpr.exceptions.KarteException;

import java.sql.*;
import java.util.Map;
import java.util.TreeMap;

/**
 * The type of Kupac dao sql implementation.
 */
public class KupacDAOSQLImplementation extends AbstractDAO<Kupac> implements KupacDAO {

    private static KupacDAOSQLImplementation instance = null;
    private KupacManager manager = new KupacManager();

    /**
     * Instantiates a new KupacDAOSql object.
     */
    public KupacDAOSQLImplementation() {
        super("Kupac");
    }

    /**
     * Gets instance
     *
     * @return the instance
     */
    public static KupacDAOSQLImplementation getInstance() {
        if(instance == null) instance = new KupacDAOSQLImplementation();
        return instance;
    }

    /**
     * Remove the instance
     */
    public static void removeInstance() {
        if (instance != null) instance = null;
    }

    @Override
    public int getId(String ime) throws KarteException {
        String sql = "SELECT id FROM Kupac WHERE ime = ?";
        try {
            PreparedStatement ps = this.getConnection().prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
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
    public Kupac row2object(ResultSet rs) throws KarteException {
        try {
            Kupac k = new Kupac();
            k.setId(rs.getInt("id"));
            k.setIme(rs.getString("ime"));
            k.setMail(rs.getString("mail"));
            k.setAdresa(rs.getString("adresa"));
            k.setTelefon(rs.getString("telefon"));
            return k;
        } catch (SQLException e) {
            throw new KarteException(e.getMessage(), e);
        }
    }

    @Override
    public Map<String, Object> object2row(Kupac object) {
        Map<String, Object> row = new TreeMap<>();
        row.put("id", object.getId());
        row.put("ime", object.getIme());
        row.put("mail", object.getMail());
        row.put("adresa", object.getAdresa());
        row.put("telefon", object.getTelefon());
        return row;
    }




     @Override
    public Kupac getById(int id) throws KarteException {
         Kupac kupac = null;
         String sql = "SELECT id, ime, mail, adresa, telefon, Prodavac_id, Karta_id FROM Kupac WHERE id = ?";
        try {
            PreparedStatement ps = this.getConnection().prepareStatement(sql);
            ps.setObject(1,id);
            ResultSet rs =  ps.executeQuery();
            if (rs.next()) {
                int oid = rs.getInt("id");
                String ime = rs.getString("ime");
                String mail = rs.getString("mail");
                String adresa = rs.getString("adresa");
                String telefon = rs.getString("telefon");

                ProdavacManager managerZaProdavca = new ProdavacManager();
                Prodavac prodavac = managerZaProdavca.getById(rs.getInt("Prodavac_id"));
                KarteManager managerZaKarte = new KarteManager();
                Karte karta = managerZaKarte.getById(rs.getInt("Karta_id"));
                return new Kupac(oid,ime,mail,adresa,telefon,prodavac,karta);
            }
        } catch(SQLException e) {
            throw new KarteException(e.getMessage(),e);
        }
        return null;
    }

    @Override
    public Kupac add(Kupac kupac) throws KarteException {
        String sql = "INSERT INTO Kupac (id,ime,mail,adresa,telefon,Prodavac_id,Karta_id) VALUES (?,?,?,?,?,?,?)";
        try {
            PreparedStatement ps = this.getConnection().prepareStatement(sql);
            ps.setObject(1,kupac.getId());
            ps.setObject(2,kupac.getIme());
            ps.setObject(3,kupac.getMail());
            ps.setObject(4,kupac.getAdresa());
            ps.setObject(5, kupac.getTelefon());
            ps.setObject(6,kupac.getProdavac().getId());
            ps.setObject(7,kupac.getKarta().getId());

            ps.executeUpdate();

            return kupac;
        }
        catch(SQLException e) {
            throw new KarteException(e.getMessage(),e);
        }
    }


}
