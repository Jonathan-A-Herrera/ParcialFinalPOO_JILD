package org.example.parcialfinalpoo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CompraDAO {
    private Connection connection;

    public CompraDAO() {
        // Conectar a la base de datos
        try {
            this.connection = (Connection) DriverManager.getConnection("jdbc:sqlite:tu_basededatos.db");
        } catch (SQLException e) {
            e.printStackTrace();
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
