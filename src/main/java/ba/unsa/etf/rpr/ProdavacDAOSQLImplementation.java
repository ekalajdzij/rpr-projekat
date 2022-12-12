package ba.unsa.etf.rpr;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class ProdavacDAOSQLImplementation implements ProdavacDAO{
    @Override
    public Prodavac getById(int id) throws SQLException {
        Connection con = Database.getConnection();
        Prodavac prodavac = null;
        String sql = "SELECT id, ime, telefon, mail FROM Prodavac WHERE id = ? ";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1,id);
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

    @Override
    public List<Prodavac> getAll() throws SQLException {
        return null;
    }

    @Override
    public int add(Prodavac prodavac) throws SQLException {
        return 0;
    }

    @Override
    public int update(Prodavac prodavac) throws SQLException {
        return 0;
    }

    @Override
    public int delete(Prodavac prodavac) {
        return 0;
    }
}
