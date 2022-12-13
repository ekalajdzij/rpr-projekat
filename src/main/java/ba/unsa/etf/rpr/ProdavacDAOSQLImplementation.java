package ba.unsa.etf.rpr;

import javax.xml.crypto.Data;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProdavacDAOSQLImplementation implements ProdavacDAO{
    @Override
    public Prodavac getById(int id) throws SQLException {
        Connection con = Database.getConnection();      //povezemo se sa bazom
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
        return prodavac;
    }

    @Override
    public List<Prodavac> getAll() throws SQLException {
        Connection con = Database.getConnection();                //povezemo se sa bazom
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
        return prodavci;
    }

    @Override
    public int add(Prodavac prodavac) throws SQLException {
        Connection con = Database.getConnection();
        String sql = "INSERT INTO Prodavac (id, ime, telefon, mail) VALUES (?,?, ?, ?)";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1,prodavac.getId());
        ps.setString(2, prodavac.getIme());
        ps.setString(3, prodavac.getTelefon());
        ps.setString(4, prodavac.getMail());

        int rez = ps.executeUpdate();

        Database.closePreparedStatement(ps);
        Database.closeConnection(con);
        return rez;
    }

    @Override
    public int update(Prodavac prodavac) throws SQLException {
        Connection connection = Database.getConnection();
        String sql = "UPDATE Prodavac set ime = ?, telefon = ?, mail = ? WHERE id = ?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, prodavac.getIme());
        ps.setString(2, prodavac.getTelefon());
        ps.setString(3, prodavac.getMail());
        ps.setInt(4,prodavac.getId());
        int rez = ps.executeUpdate();
        Database.closePreparedStatement(ps);
        Database.closeConnection(connection);
        return rez;

    }

    @Override
    public int delete(Prodavac prodavac) throws SQLException {
        Connection connection = Database.getConnection();
        String sql = "DELETE FROM Prodavac WHERE id = ?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setInt(1,prodavac.getId());
        int rez = ps.executeUpdate();
        Database.closePreparedStatement(ps);
        Database.closeConnection(connection);
        return rez;


    }
}
