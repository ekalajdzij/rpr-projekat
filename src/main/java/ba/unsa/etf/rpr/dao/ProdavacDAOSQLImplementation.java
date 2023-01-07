package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Prodavac;
import ba.unsa.etf.rpr.exceptions.KarteException;

import java.sql.*;
import java.util.Map;
import java.util.TreeMap;

public class ProdavacDAOSQLImplementation extends AbstractDAO<Prodavac> implements ProdavacDAO {

    private static ProdavacDAOSQLImplementation instance = null;
    public ProdavacDAOSQLImplementation() {
        super("Prodavac");
    }



    public int getId(String ime) throws KarteException {
        try {Connection connection = Database.getConnection();
            String sql = "SELECT id FROM Prodavac WHERE ime = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1,ime);
            ResultSet rs = ps.executeQuery();
            if(rs.next()) {
                int id = rs.getInt("id");
                return id;
            } }
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
        return row;
    }
    /*@Override
    public Prodavac getById(int id) throws KarteException {
        try {Connection con = Database.getConnection();      //povezemo se sa bazom
        Prodavac prodavac = null;                       //promjenjiva tipa Prodavac
        String sql = "SELECT id, ime, telefon, mail FROM Prodavac WHERE id = ? ";   //nas sql upit
        PreparedStatement ps = con.prepareStatement(sql);   //formiramo preparedstatement
        ps.setInt(1,id);                        //placeholdere u upitu zamijenimo konkretnim vrijednostima
        ResultSet rs = ps.executeQuery();                   //izvrsimo nas upit
        if (rs.next()) {
            int oid = rs.getInt("id");
            String ime = rs.getString("ime");
            String telefon = rs.getString("telefon");
            String mail = rs.getString("mail");
            prodavac = new Prodavac(oid,ime,telefon,mail);
        }
        return prodavac;}
        catch(SQLException e) {
            throw new KarteException(e.getMessage(),e);
        }
    }

    @Override
    public List<Prodavac> getAll() throws KarteException {
        try {Connection con = Database.getConnection();                //povezemo se sa bazom
        String sql = "SELECT id, ime, telefon, mail FROM Prodavac";//nas sql upit
        List<Prodavac> prodavci = new ArrayList<>();                //napravimo listu tipa Prodavac
        Statement stmt = con.createStatement();                     //formiramo statement & izvrsimo upit
        ResultSet rs = stmt.executeQuery(sql);

        while (rs.next()) {
            int id = rs.getInt("id");
            String ime = rs.getString("ime");
            String telefon = rs.getString("telefon");       //ubacujemo u listu
            String mail = rs.getString("mail");
            Prodavac prod = new Prodavac(id,ime,telefon,mail);
            prodavci.add(prod);
        }
        return prodavci;}
        catch(SQLException e) {
            throw new KarteException(e.getMessage(),e);
        }
    }

    @Override
    public int add(Prodavac prodavac) throws KarteException {
        try {Connection con = Database.getConnection();
        String sql = "INSERT INTO Prodavac (id, ime, telefon, mail) VALUES (?,?, ?, ?)";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1,prodavac.getId());
        ps.setString(2, prodavac.getIme());
        ps.setString(3, prodavac.getTelefon());
        ps.setString(4, prodavac.getMail());

        int rez = ps.executeUpdate();

        Database.closePreparedStatement(ps);
        Database.closeConnection(con);
        return rez;}
        catch(SQLException e) {
            throw new KarteException(e.getMessage(),e);
        }
    }

    @Override
    public int update(Prodavac prodavac) throws KarteException {
        try {Connection connection = Database.getConnection();
        String sql = "UPDATE Prodavac set ime = ?, telefon = ?, mail = ? WHERE id = ?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, prodavac.getIme());
        ps.setString(2, prodavac.getTelefon());
        ps.setString(3, prodavac.getMail());
        ps.setInt(4,prodavac.getId());
        int rez = ps.executeUpdate();
        Database.closePreparedStatement(ps);
        Database.closeConnection(connection);
        return rez;}
        catch(SQLException e) {
            throw new KarteException(e.getMessage(),e);
        }

    }

    @Override
    public int delete(Prodavac prodavac) throws KarteException {
       try { Connection connection = Database.getConnection();
        String sql = "DELETE FROM Prodavac WHERE id = ?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setInt(1,prodavac.getId());
        int rez = ps.executeUpdate();
        Database.closePreparedStatement(ps);
        Database.closeConnection(connection);
        return rez;}
       catch(SQLException e) {
           throw new KarteException(e.getMessage(),e);
       }


    } */

}
