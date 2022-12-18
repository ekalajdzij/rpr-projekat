package ba.unsa.etf.rpr;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class KarteDAOSQLImplementation implements KarteDAO{
    @Override
    public Karte getById(int id) throws SQLException {
        Connection con = Database.getConnection();
        Karte karta = null;
        String sql = "SELECT id, vrsta, datum, adresa, cijena, Prodavac_id FROM Karte WHERE id = ?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1,id);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            int oid = rs.getInt("id");
            String vrsta = rs.getString("vrsta");
            String datum = rs.getString("datum");
            String adresa = rs.getString("adresa");
            Double cijena = rs.getDouble("cijena");
            ProdavacDAO prodavacDAO = new ProdavacDAOSQLImplementation();
            Prodavac prodavac = prodavacDAO.getById(rs.getInt("Prodavac_id"));
            karta = new Karte(oid,vrsta,datum,adresa,prodavac,cijena);
        }
        return karta;
    }

    @Override
    public List<Karte> getAll() throws SQLException {
        Connection con = Database.getConnection();
        String sql = "SELECT id,vrsta,datum,adresa,cijena,Prodavac_id FROM Karte";
        List<Karte>karte = new ArrayList<>();
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        while (rs.next()) {
            int oid = rs.getInt("id");
            String vrsta = rs.getString("vrsta");
            String datum = rs.getString("datum");
            String adresa = rs.getString("adresa");
            Double cijena = rs.getDouble("cijena");
            ProdavacDAO prodavacDAO = new ProdavacDAOSQLImplementation();
            Prodavac prodavac = prodavacDAO.getById(rs.getInt("Prodavac_id"));
            Karte karta = new Karte(oid,vrsta,datum,adresa,prodavac,cijena);
            karte.add(karta);
        }
        return karte;
    }

    @Override
    public int add(Karte karte) throws SQLException {
        Connection con = Database.getConnection();
        String sql = "INSERT INTO Karte (id, vrsta, datum, adresa, cijena, Prodavac_id) VALUES (?,?,?,?,?,?)";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1,karte.getId());
        ps.setString(2,karte.getVrsta());
        ps.setString(3,karte.getDatum());
        ps.setString(4,karte.getAdresa());
        ps.setDouble(5, karte.getCijena());
        ps.setInt(6,karte.getProdavac().getId());
        int rez = ps.executeUpdate();
        Database.closePreparedStatement(ps);
        Database.closeConnection(con);
        return rez;
    }

    @Override
    public int update(Karte karte) throws SQLException {
        Connection con = Database.getConnection();
        String sql = "UPDATE Karte set vrsta = ?, datum = ?, adresa = ?, cijena = ?, Prodavac_id = ? WHERE id = ?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1,karte.getVrsta());
        ps.setString(2,karte.getDatum());
        ps.setString(3,karte.getAdresa());
        ps.setDouble(4, karte.getCijena());
        ps.setInt(5,karte.getProdavac().getId());
        ps.setInt(6,karte.getId());
        int rez = ps.executeUpdate();
        Database.closePreparedStatement(ps);
        Database.closeConnection(con);
        return rez;
    }

    @Override
    public int delete(Karte karte) throws SQLException {
        Connection con = Database.getConnection();
        String sql = "DELETE FROM Karte WHERE id = ?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1,karte.getId());
        int rez = ps.executeUpdate();
        Database.closePreparedStatement(ps);
        Database.closeConnection(con);
        return rez;


    }
    @Override
    public List<String> getAllKarte() throws SQLException {
        KarteDAO kDA0 = new KarteDAOSQLImplementation();
        List<String> lista = new ArrayList<>();
        List<Karte> list = kDA0.getAll();
        for (Karte x : list)
            lista.add(x.getVrsta());
        return lista;
    }

    @Override
    public int dajIdKarte(String vrsta) throws SQLException {
        Connection connection = Database.getConnection();
        String sql = "SELECT id FROM Karte WHERE vrsta = ?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1,vrsta);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            int id = rs.getInt("id");
            return id;
        }
        return -1;
    }

    @Override
    public int dajIdProdavcaKarte(String vrsta) throws SQLException {
        Connection connection = Database.getConnection();
        String sql = "SELECT Prodavac_id FROM Karte WHERE vrsta = ?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1,vrsta);
        ResultSet rs = ps.executeQuery();
        if(rs.next()) {
            int id = rs.getInt("Prodavac_id");
            return id;
        }
        return -1;
    }
    @Override
    public Double dajCijenu(int id) throws SQLException {
        Connection connection = Database.getConnection();
        String sql = "SELECT cijena FROM Karte WHERE id = ?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setInt(1,id);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            Double ciena = rs.getDouble("cijena");
            return ciena;
        }
        return Double.valueOf(-1);
    }
}
