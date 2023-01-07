package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Kupac;
import ba.unsa.etf.rpr.exceptions.KarteException;

import java.sql.*;
import java.util.Map;
import java.util.TreeMap;

public class KupacDAOSQLImplementation extends AbstractDAO<Kupac> implements KupacDAO {

    private static KupacDAOSQLImplementation instance = null;

    public KupacDAOSQLImplementation() {
        super("Kupac");
    }

    public static KupacDAOSQLImplementation getInstance() {
        if(instance == null) instance = new KupacDAOSQLImplementation();
        return instance;
    }

    @Override
    public int getId(String ime) throws KarteException {
        try {Connection connection = Database.getConnection();
            String sql = "SELECT id FROM Kupac WHERE ime = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1,ime);
            ResultSet rs = ps.executeQuery();
            if(rs.next()) {
                int id = rs.getInt("id");
                return id;
            }}
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
        return row;
    }




    /* @Override
    public Kupac getById(int id) throws KarteException {
        try {Connection connection = Database.getConnection();
        Kupac kupac = null;
        String sql = "SELECT id, ime, mail, adresa, telefon, Prodavac_id, Karta_id FROM Kupac WHERE id = ?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setInt(1,id);
        ResultSet rs =  ps.executeQuery();
        if (rs.next()) {
            int oid = rs.getInt("id");
            String ime = rs.getString("ime");
            String mail = rs.getString("mail");
            String adresa = rs.getString("adresa");
            String telefon = rs.getString("telefon");
            ProdavacDAO prodavacDAO = new ProdavacDAOSQLImplementation();
            Prodavac prodavac = prodavacDAO.getById(rs.getInt("Prodavac_id"));
            KarteDAO karteDAO = new KarteDAOSQLImplementation();
            Karte karta = karteDAO.getById(rs.getInt("Karta_id"));
            return new Kupac(oid,ime,mail,adresa,telefon,prodavac,karta);
        }} catch(SQLException e) {
            throw new KarteException(e.getMessage(),e);
        }
        return null;
    }

    @Override
    public List<Kupac> getAll() throws KarteException {
       try { Connection connection = Database.getConnection();
        String sql = "SELECT id, ime, mail, adresa, telefon, Prodavac_id FROM Kupac";
        List<Kupac> kupci = new ArrayList<>();
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        while (rs.next()) {
            int id = rs.getInt("id");
            String ime = rs.getString("ime");
            String mail = rs.getString("mail");
            String adresa = rs.getString("adresa");
            String telefon = rs.getString("telefon");
            ProdavacDAO prodavacDAO = new ProdavacDAOSQLImplementation();
            Prodavac prodavac = prodavacDAO.getById(rs.getInt("Prodavac_id"));
            KarteDAO karteDAO = new KarteDAOSQLImplementation();
            Karte karta = karteDAO.getById(rs.getInt("Karta_id"));
            Kupac kupac = new Kupac(id,ime,mail,adresa,telefon,prodavac,karta);
            kupci.add(kupac);
        }
        return kupci;}
       catch(SQLException e) {
           throw new KarteException(e.getMessage(),e);
       }
    }


    @Override
    public int add(Kupac kupac) throws KarteException {
        try {Connection connection = Database.getConnection();
        String sql = "INSERT INTO Kupac (id,ime,mail,adresa,telefon,Prodavac_id,Karta_id) VALUES (?,?,?,?,?,?,?)";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setInt(1,kupac.getId());
        ps.setString(2,kupac.getIme());
        ps.setString(3,kupac.getMail());
        ps.setString(4,kupac.getAdresa());
        ps.setString(5, kupac.getTelefon());
        ps.setInt(6,kupac.getProdavac().getId());
        ps.setInt(7,kupac.getKarta().getId());

        int rez = ps.executeUpdate();
        Database.closePreparedStatement(ps);
        Database.closeConnection(connection);

        return rez;}
        catch(SQLException e) {
            throw new KarteException(e.getMessage(),e);
        }
    }

    @Override
    public int update(Kupac kupac) throws KarteException {
        try {Connection connection = Database.getConnection();
        String sql = "UPDATE Kupac set ime = ?, mail = ?, adresa = ?, telefon = ?, Prodavac_id = ?, Karta_id = ? WHERE id = ?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1,kupac.getIme());
        ps.setString(2,kupac.getMail());
        ps.setString(3,kupac.getAdresa());
        ps.setString(4,kupac.getTelefon());
        ps.setInt(5,kupac.getProdavac().getId());
        ps.setInt(6,kupac.getKarta().getId());
        ps.setInt(6,kupac.getId());
        int rez = ps.executeUpdate();
        Database.closePreparedStatement(ps);
        Database.closeConnection(connection);
        return rez;}
        catch(SQLException e) {
            throw new KarteException(e.getMessage(),e);
        }
    }

    @Override
    public int delete(Kupac kupac) throws KarteException {
        try {
            Connection connection = Database.getConnection();

        String sql = "DELETE FROM Kupac WHERE id = ?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setInt(1,kupac.getId());
        int rez = ps.executeUpdate();
        Database.closePreparedStatement(ps);
        Database.closeConnection(connection);
        return rez; }
        catch(SQLException e) {
            throw new KarteException(e.getMessage(),e);
        }

    } */

}
