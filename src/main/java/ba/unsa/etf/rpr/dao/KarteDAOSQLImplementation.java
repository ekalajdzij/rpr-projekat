package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.bussines.KarteManager;
import ba.unsa.etf.rpr.bussines.ProdavacManager;
import ba.unsa.etf.rpr.domain.Karte;
import ba.unsa.etf.rpr.domain.Prodavac;
import ba.unsa.etf.rpr.exceptions.KarteException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * The type of Karte dao sql implementation.
 */

public class KarteDAOSQLImplementation extends AbstractDAO<Karte> implements KarteDAO {
    private static KarteDAOSQLImplementation instance = null;
    private KarteManager manager = new KarteManager();

    /**
     * Instantiates a new KarteDAOSql object
     */
    public KarteDAOSQLImplementation() {super("Karte");}

    /**
     * Gets instance
     *
     * @return the instance
     */
    public static KarteDAOSQLImplementation getInstance() {
        if(instance == null) instance = new KarteDAOSQLImplementation();
        return instance;
    }

    /**
     * Remove instance
     */
    public static void removeInstance() {
        if (instance != null) instance = null;
    }
    @Override
    public Karte row2object(ResultSet rs) throws KarteException {
        try {
            Karte k = new Karte();
            k.setId(rs.getInt("id"));
            k.setVrsta(rs.getString("vrsta"));
            k.setDatum(rs.getString("datum"));
            k.setAdresa(rs.getString("adresa"));
            k.setCijena(rs.getDouble("cijena"));
            return k;
        } catch (SQLException e) {
            throw new KarteException(e.getMessage(), e);
        } }
    @Override
    public Map<String, Object> object2row(Karte object) {
        Map<String, Object> row = new TreeMap<>();
        row.put("id", object.getId());
        row.put("vrsta", object.getVrsta());
        row.put("datum",object.getDatum());
        row.put("adresa",object.getAdresa());
        row.put("cijena",object.getCijena());
        return row;
    }


    @Override
    public Karte getById(int id) throws KarteException{
        Karte karta = null;
        String sql = "SELECT id, vrsta, datum, adresa, cijena, Prodavac_id FROM Karte WHERE id = ?";
        try {
            PreparedStatement ps = this.getConnection().prepareStatement(sql);
            ps.setObject(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int oid = rs.getInt("id");
                String vrsta = rs.getString("vrsta");
                String datum = rs.getString("datum");
                String adresa = rs.getString("adresa");
                Double cijena = rs.getDouble("cijena");
                ProdavacDAO prodavacDAO = new ProdavacDAOSQLImplementation();
                Prodavac prodavac = prodavacDAO.getById(rs.getInt("Prodavac_id"));
                karta = new Karte(oid, vrsta, datum, adresa, prodavac, cijena);
            }
            return karta;
        } catch(SQLException e) {
            throw new KarteException(e.getMessage(),e);
        }
    }

    @Override
    public List<Karte> getAll() throws KarteException{
        List<Karte> karte = new ArrayList<>();
        String sql = "SELECT id,vrsta,datum,adresa,cijena,Prodavac_id FROM Karte";
        try {
            PreparedStatement stmt = this.getConnection().prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                int oid = rs.getInt("id");
                String vrsta = rs.getString("vrsta");
                String datum = rs.getString("datum");
                String adresa = rs.getString("adresa");
                Double cijena = rs.getDouble("cijena");
                ProdavacManager prodavacManager = new ProdavacManager();
                Prodavac prodavac = prodavacManager.getById(rs.getInt("Prodavac_id"));
                Karte karta = new Karte(oid, vrsta, datum, adresa, prodavac, cijena);
                karte.add(karta);
            }
            return karte;
        } catch(SQLException e) {
            throw new KarteException(e.getMessage(),e);
        }
    }

    @Override
    public Karte add(Karte karte) throws KarteException {
        String sql = "INSERT INTO Karte (id, vrsta, datum, adresa, cijena, Prodavac_id) VALUES (?,?,?,?,?,?)";
        try {
            PreparedStatement ps = this.getConnection().prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
            ps.setObject(1,karte.getId());
            ps.setObject(2,karte.getVrsta());
            ps.setObject(3,karte.getDatum());
            ps.setObject(4,karte.getAdresa());
            ps.setObject(5, karte.getCijena());
            ps.setObject(6,karte.getProdavac().getId());
            ps.executeUpdate();
            return karte;
        }
        catch(SQLException e) {
            throw new KarteException(e.getMessage(),e);
        }
    }

    @Override
    public List<String> getAllKarte() throws KarteException{
        List<String> lista = new ArrayList<>();
        List<Karte> list = manager.getAll();
        for (Karte x : list)
            lista.add(x.getVrsta());
        return lista;
    }

    @Override
    public int dajIdKarte(String vrsta) throws KarteException {
        String sql = "SELECT id FROM Karte WHERE vrsta = ?";
        try {
            PreparedStatement ps = this.getConnection().prepareStatement(sql);
            ps.setObject(1,vrsta);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt("id");
            }
        } catch(SQLException e) {
            throw new KarteException(e.getMessage(),e);
        }
        return -1;
    }

    @Override
    public int dajIdProdavcaKarte(String vrsta) throws KarteException {
        String sql = "SELECT Prodavac_id FROM Karte WHERE vrsta = ?";
        try {
            PreparedStatement ps = this.getConnection().prepareStatement(sql);
            ps.setObject(1,vrsta);
            ResultSet rs = ps.executeQuery();
            if(rs.next()) {
                return rs.getInt("Prodavac_id");
            }
        }
        catch(SQLException e) {
            throw new KarteException(e.getMessage(),e);
        }
        return -1;
    }
    @Override
    public Double dajCijenu(int id) throws KarteException {
        String sql = "SELECT cijena FROM Karte WHERE id = ?";
        try {
            PreparedStatement ps = this.getConnection().prepareStatement(sql);
            ps.setObject(1,id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getDouble("cijena");
            }
        }
        catch(SQLException e) {
            throw new KarteException(e.getMessage(),e);
        }
        return (double) -1;
    }
}

