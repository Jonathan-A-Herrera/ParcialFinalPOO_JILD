package org.example.parcialfinalpoo;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

public class ReporteD {

    // 00085720 Genera un reporte basado en el facilitador proporcionado
    public void generateReport(String facilitator) {
        Map<String, ReportData> reportDataMap = new HashMap<>(); // 00085720 Mapa para almacenar los datos del reporte

        try {
            // 00085720 Conectar a la base de datos
            String url = "jdbc:mysql://localhost:3306/PARCIALFINAL";
            String user = "poo";
            String password = "ParcialFinal";
            Connection conn = DriverManager.getConnection(url, user, password);

            // 00085720 Consulta SQL para obtener datos de clientes
            String query = "SELECT c.id, c.name, COUNT(*) as purchase_count, SUM(p.amount) as total_spent " +
                    "FROM customers c " +
                    "JOIN purchases p ON c.id = p.customer_id " +
                    "JOIN cards cr ON p.card_id = cr.id " +
                    "WHERE cr.facilitator = ? " +
                    "GROUP BY c.id, c.name";

            PreparedStatement stmt = conn.prepareStatement(query); // 00085720 Preparar la declaración SQL
            stmt.setString(1, facilitator); // 00085720 Establecer el parametro facilitador
            ResultSet rs = stmt.executeQuery(); // 00085720 Ejecutar la consulta

            // 00085720 Procesar los resultados de la consulta
            while (rs.next()) {
                String customerId = rs.getString("id"); // 00085720 Obtener el ID del cliente
                String customerName = rs.getString("name"); // 00085720 Obtener el nombre del cliente
                int purchaseCount = rs.getInt("purchase_count"); // 00085720 Obtener la cantidad de compras
                double totalSpent = rs.getDouble("total_spent"); // 00085720 Obtener el total gastado

                reportDataMap.put(customerId, new ReportData(customerName, purchaseCount, totalSpent)); // 00085720 Almacenar los datos en el mapa
            }

            rs.close(); // 00085720 Cerrar el conjunto de resultados
            stmt.close(); // 00085720 Cerrar la declaracion
            conn.close(); // 00085720 Cerrar la conexion

            saveReportToFile(reportDataMap, facilitator); // 00085720 Guardar el reporte en un archivo

        } catch (Exception e) {
            e.printStackTrace(); // 00085720 Manejar excepciones
        }
    }

    // 00085720 Guardar los datos del reporte en un archivo
    private void saveReportToFile(Map<String, ReportData> reportData, String facilitator) {
        String fileName = "Reportes/ReporteD-" + System.currentTimeMillis() + ".txt"; // 00085720 Nombre del archivo

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) { // 00085720 Crear el escritor de archivos
            writer.write("Reporte D: Clientes que han realizado compras con " + facilitator + "\n\n"); // 00085720 Escribir el encabezado del reporte
            for (Map.Entry<String, ReportData> entry : reportData.entrySet()) { // 00085720 Iterar sobre los datos del reporte
                String customerId = entry.getKey(); // 00085720 Obtener el ID del cliente
                ReportData data = entry.getValue(); // 00085720 Obtener los datos del cliente
                writer.write("Cliente ID: " + customerId + "\n"); // 00085720 Escribir el ID del cliente
                writer.write("Nombre: " + data.name + "\n"); // 00085720 Escribir el nombre del cliente
                writer.write("Cantidad de Compras: " + data.purchaseCount + "\n"); // 00085720 Escribir la cantidad de compras
                writer.write("Total Gastado: $" + data.totalSpent + "\n\n"); // 00085720 Escribir el total gastado
            }
        } catch (IOException e) {
            e.printStackTrace(); // 00085720 Manejar excepciones de IO
        }
    }

    // 00085720 Método placeholder para generar un reporte sin parámetros
    public void generateReport() {
    }

    // 00085720 Clase interna para almacenar los datos del reporte
    private static class ReportData {
        String name; // 00085720 Nombre del cliente
        int purchaseCount; // 00085720 Cantidad de compras del cliente
        double totalSpent; // 00085720 Total gastado por el cliente

        ReportData(String name, int purchaseCount, double totalSpent) { // 00085720 Constructor de la clase
            this.name = name; // 00085720 Asignar el nombre
            this.purchaseCount = purchaseCount; // 00085720 Asignar la cantidad de compras
            this.totalSpent = totalSpent; // 00085720 Asignar el total gastado
        }
    }
}
