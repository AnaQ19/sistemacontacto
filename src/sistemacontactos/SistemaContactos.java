package sistemacontactos;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SistemaContactos {

    public static void main(String[] args) {

        try {
            Database.conectar();
            ArrayList<Contactos> lista = Contactos.getListFromDatabase();
            for (Contactos Contacto : lista) {
                System.out.printf("El nombre es: %s\r\n", Contacto.getNombreContacto());
            }
            Database.cerrar();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(SistemaContactos.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(SistemaContactos.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private static void insterTest(Connection conn) throws SQLException {
        String q = "INSERT INTO contactos (NombreContacto, ApellidoContacto, Direccion) VALUESES(?,?,?)";
        PreparedStatement stmt = conn.prepareCall(q);
        stmt.setString(1, "Jair");
        stmt.setString(2, "Espinales");
        stmt.setString(3, "Node");
        stmt.execute();
    }

    private static void call_prepare(Connection conn) throws SQLException {
        String query = "{CALL get_nombre_contacto(?)}";
        CallableStatement stmt = conn.prepareCall(query);
        stmt.setInt(1, 1);

        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            System.out.printf("El nombre es: %s\r\n", rs.getString(1));
        }
    }
}
