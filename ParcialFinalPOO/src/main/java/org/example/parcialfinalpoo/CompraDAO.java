package org.example.parcialfinalpoo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CompraDAO {
    private Connection connection; // 00085720 Conexion a la base de datos

    public CompraDAO() {
        String url = "jdbc:sqlserver://localhost:1433;databaseName=PARCIALFINAL;encrypt=false"; // 00085720 URL de la base de datos
        String user = "poo"; // 00085720 Usuario de la base de datos
        String password = "ParcialFinal"; // 00085720 Contraseña de la base de datos
        // 00085720 Conectar a la base de datos
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver"); // 00085720 Carga el controlador JDBC para SQL Server
            this.connection = (Connection) DriverManager.getConnection(url, user, password); // 00085720 Establece la conexión con la base de datos
            // this.connection = (Connection) DriverManager.getConnection("jdbc:sqlite:tu_basededatos.db"); // 00085720 Alternativa para SQLite
        } catch (ClassNotFoundException | SQLException e) { // 00085720 Maneja las excepciones de clase no encontrada y SQL
            e.printStackTrace(); // 00085720 Imprime la traza de la excepción
        }
    }

    public void crearCompra(Compra compra) {
        String sql = "INSERT INTO compras (idCliente, idTarjeta, fechaCompra, monto, descripcion) VALUES (?, ?, ?, ?, ?)"; // 00085720 Sentencia SQL para insertar una compra
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) { // 00085720 Prepara la sentencia SQL
            pstmt.setInt(1, compra.getIdCliente()); // 00085720 Establece el ID del cliente
            pstmt.setInt(2, compra.getIdTarjeta()); // 00085720 Establece el ID de la tarjeta
            pstmt.setString(3, compra.getFechaCompra()); // 00085720 Establece la fecha de compra
            pstmt.setDouble(4, compra.getMonto()); // 00085720 Establece el monto de la compra
            pstmt.setString(5, compra.getDescripcion()); // 00085720 Establece la descripción de la compra
            pstmt.executeUpdate(); // 00085720 Ejecuta la actualización
        } catch (SQLException e) { // 00085720 Maneja la excepción SQL
            e.printStackTrace(); // 00085720 Imprime la traza de la excepción
        }
    }

    public List<Compra> obtenerCompras() {
        List<Compra> compras = new ArrayList<>(); // 00085720 Lista para almacenar las compras
        String sql = "SELECT * FROM compras"; // 00085720 Sentencia SQL para seleccionar todas las compras
        try (Statement stmt = connection.createStatement(); // 00085720 Crea la sentencia
             ResultSet rs = stmt.executeQuery(sql)) { // 00085720 Ejecuta la consulta y obtiene el resultado
            while (rs.next()) { // 00085720 Itera sobre los resultados
                Compra compra = new Compra( // 00085720 Crea un objeto Compra con los datos del resultado
                        rs.getInt("idCompra"), // 00085720 Obtiene el ID de la compra
                        rs.getInt("idCliente"), // 00085720 Obtiene el ID del cliente
                        rs.getInt("idTarjeta"), // 00085720 Obtiene el ID de la tarjeta
                        rs.getString("fechaCompra"), // 00085720 Obtiene la fecha de compra
                        rs.getDouble("monto"), // 00085720 Obtiene el monto de la compra
                        rs.getString("descripcion") // 00085720 Obtiene la descripcion de la compra
                );
                compras.add(compra); // 00085720 Añade la compra a la lista
            }
        } catch (SQLException e) { // 00085720 Maneja la excepción SQL
            e.printStackTrace(); // 00085720 Imprime la traza de la excepción
        }
        return compras; // 00085720 Retorna la lista de compras
    }

    public void actualizarCompra(Compra compra) {
        String sql = "UPDATE compras SET idCliente = ?, idTarjeta = ?, fechaCompra = ?, monto = ?, descripcion = ? WHERE idCompra = ?"; // 00085720 Sentencia SQL para actualizar una compra
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) { // 00085720 Prepara la sentencia SQL
            pstmt.setInt(1, compra.getIdCliente()); // 00085720 Establece el ID del cliente
            pstmt.setInt(2, compra.getIdTarjeta()); // 00085720 Establece el ID de la tarjeta
            pstmt.setString(3, compra.getFechaCompra()); // 00085720 Establece la fecha de compra
            pstmt.setDouble(4, compra.getMonto()); // 00085720 Establece el monto de la compra
            pstmt.setString(5, compra.getDescripcion()); // 00085720 Establece la descripcion de la compra
            pstmt.setInt(6, compra.getIdCompra()); // 00085720 Establece el ID de la compra
            pstmt.executeUpdate(); // 00085720 Ejecuta la actualización
        } catch (SQLException e) { // 00085720 Maneja la excepción SQL
            e.printStackTrace(); // 00085720 Imprime la traza de la excepción
        }
    }

    public void eliminarCompra(int idCompra) {
        String sql = "DELETE FROM compras WHERE idCompra = ?"; // 00085720 Sentencia SQL para eliminar una compra
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) { // 00085720 Prepara la sentencia SQL
            pstmt.setInt(1, idCompra); // 00085720 Establece el ID de la compra a eliminar
            pstmt.executeUpdate(); // 00085720 Ejecuta la eliminación
        } catch (SQLException e) { // 00085720 Maneja la excepción SQL
            e.printStackTrace(); // 00085720 Imprime la traza de la excepción
        }
    }
}