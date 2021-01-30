package sistemacontactos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Contactos {

    private int Idcontacto;
    private String NombreContacto;
    private String ApellidoContacto;
    private int Telefono;
    private String CreateAt;

    public Contactos(int Idcontacto, String NombreContacto, String ApellidoContacto, int Telefono, String CreateAt) {
        this.Idcontacto = Idcontacto;
        this.NombreContacto = NombreContacto;
        this.ApellidoContacto = ApellidoContacto;
        this.Telefono = Telefono;
        this.CreateAt = CreateAt;
    }

    public int getIdcontacto() {
        return Idcontacto;
    }

    public String getNombreContacto() {
        return NombreContacto;
    }

    public String getApellidoContacto() {
        return ApellidoContacto;
    }

    public int getTelefono() {
        return Telefono;
    }

    public String getCreateAt() {
        return CreateAt;
    }

    public static ArrayList<Contactos> getListFromDatabase() throws SQLException {
        Connection db = Database.getConn();
        ArrayList<Contactos> Lista = new ArrayList<>();

        String q = "SELECT * FROM Contactos";
        PreparedStatement stmt = db.prepareStatement(q);
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            Contactos contacto = new Contactos(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getString(5));
            Lista.add(contacto);
        }
        return Lista;
    }

    public static Contactos getIdFromDatabase(int id) throws SQLException {
        Connection db = Database.getConn();
        String q = "SELECT * FROM Contactos WHERE IdContacto = ?";
        PreparedStatement stmt = db.prepareStatement(q);
        stmt.setInt(1, id);
        ResultSet rs = stmt.executeQuery();
        rs.first();

        Contactos resultado = new Contactos(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getString(5));
        return resultado;
    }

    public static void deleteIdFromDatabase(int id) throws SQLException {
        Connection db = Database.getConn();
        String q = "DELETE FROM Contactos WHERE IdContacto = ?";
        PreparedStatement stmt = db.prepareStatement(q);
        stmt.setInt(1, id);
        stmt.execute();
    }

    public static void registreNew(String nombre, String apellido, int telefono, String direccion) throws SQLException {
        Connection db = Database.getConn();
        String q = "INSERT INTO Contactos (NombreContacto, ApellidoContacto, Telefono, Direccion) VALUES (?, ?, ?, ?)";
        PreparedStatement stmt = db.prepareStatement(q);
        stmt.setString(1, nombre);
        stmt.setString(2, apellido);
        stmt.setInt(3, telefono);
        stmt.setString(4, direccion);
        stmt.execute();
    }

}
