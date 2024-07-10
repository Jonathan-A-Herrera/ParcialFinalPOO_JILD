package org.example.parcialfinalpoo;

import javafx.scene.control.Alert;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CompraDAO {
    private Connection connection;

    public CompraDAO() {
        String url = "jdbc:sqlserver://localhost:1433;databaseName=PARCIALFINAL;encrypt=false"; // 00085720 URL de la base de datos
        String user = "poo"; // 00085720 Usuario de la base de datos
        String password = "ParcialFinal"; // 00085720 Contraseña de la base de datos
        // Conectar a la base de datos
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver"); // 00085720 Carga el controlador JDBC para SQL Server
            this.connection = (Connection) DriverManager.getConnection(url, user, password); // 00085720 Establece la conexión con la base de datos
            //this.connection = (Connection) DriverManager.getConnection("jdbc:sqlite:tu_basededatos.db");
        } catch (ClassNotFoundException | SQLException e) { // 00085720 Maneja las excepciones de clase no encontrada y SQL
            e.printStackTrace(); // 00085720 Imprime la traza de la excepcion
        }
    }

    public void crearCompra(Compra compra) {
        String sql = "INSERT INTO compras (idCliente, idTarjeta, fechaCompra, monto, descripcion) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, compra.getIdCliente());
            pstmt.setInt(2, compra.getIdTarjeta());
            pstmt.setString(3, compra.getFechaCompra());
            pstmt.setDouble(4, compra.getMonto());
            pstmt.setString(5, compra.getDescripcion());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Compra> obtenerCompras() {
        List<Compra> compras = new ArrayList<>();
        String sql = "SELECT * FROM compras";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Compra compra = new Compra(
                        rs.getInt("idCompra"),
                        rs.getInt("idCliente"),
                        rs.getInt("idTarjeta"),
                        rs.getString("fechaCompra"),
                        rs.getDouble("monto"),
                        rs.getString("descripcion")
                );
                compras.add(compra);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return compras;
    }

    public void actualizarCompra(Compra compra) {
        String sql = "UPDATE compras SET idCliente = ?, idTarjeta = ?, fechaCompra = ?, monto = ?, descripcion = ? WHERE idCompra = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, compra.getIdCliente());
            pstmt.setInt(2, compra.getIdTarjeta());
            pstmt.setString(3, compra.getFechaCompra());
            pstmt.setDouble(4, compra.getMonto());
            pstmt.setString(5, compra.getDescripcion());
            pstmt.setInt(6, compra.getIdCompra());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void eliminarCompra(int idCompra) {
        String sql = "DELETE FROM compras WHERE idCompra = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, idCompra);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
