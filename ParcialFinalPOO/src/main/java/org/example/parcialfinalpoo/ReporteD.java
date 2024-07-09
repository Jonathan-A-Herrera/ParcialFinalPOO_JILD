package org.example.parcialfinalpoo;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class ReporteD {
    private final ObservableList<Cliente> clientes = FXCollections.observableArrayList();

    public ReporteD(String facilitador) {
        // Conectar a la base de datos y obtener los datos
        connectToDatabase(facilitador);
    }

    private void connectToDatabase(String facilitador) {
        String url = "jdbc:mysql://localhost:3306/tienda";
        String user = "root";
        String password = "password";

        try {
            Connection connection = DriverManager.getConnection(url, user, password);
            String query = "SELECT ID_Cliente, nombre, cantidadCompras, totalGastado FROM clientes WHERE facilitador = '" + facilitador + "'";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                int id = resultSet.getInt("ID_Cliente");
                String name = resultSet.getString("nombre");
                int compras = resultSet.getInt("cantidadCompras");
                double totalGastado = resultSet.getDouble("totalGastado");

                Cliente cliente = new Cliente(id, name, compras, totalGastado);
                clientes.add(cliente);
            }

            resultSet.close();
            statement.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ObservableList<Cliente> getClientes() {
        return clientes;
    }
}
