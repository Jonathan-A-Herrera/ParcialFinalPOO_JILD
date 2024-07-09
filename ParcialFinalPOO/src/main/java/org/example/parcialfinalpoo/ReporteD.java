package org.example.parcialfinalpoo;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.util.Duration;

import java.io.*;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ReporteD {

    @FXML
    private ComboBox<String> cbFacilitador; // 00085720 Combobox para seleccionar el facilitador
    @FXML
    private Label facilitadorTextField; // 00085720 Etiqueta para mostrar mensajes

    // 00085720 Genera un reporte basado en el facilitador proporcionado
    @FXML
    public void crearReporteD() { // 00085720 Metodo para crear reporte D
        try {
            Connection conn = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=PARCIALFINAL;encrypt=false", "poo", "ParcialFinal"); // 00085720 conecta a la base de datos

            String facilitador = cbFacilitador.getValue(); // 00085720 Obtiene el facilitador

            String query = "SELECT " +
                    "c.id AS cliente_id, " +
                    "c.nombre_completo, " +
                    "tar.facilitador, " +
                    "COUNT(t.id) AS cantidad_compras, " +
                    "SUM(t.monto) AS total_gastado " +
                    "FROM " +
                    "Cliente c " +
                    "JOIN " +
                    "Tarjeta tar ON c.id = tar.cliente_id " +
                    "JOIN " +
                    "Transaccion t ON tar.id = t.tarjeta_id " +
                    "WHERE " +
                    "tar.facilitador = ? " +
                    "GROUP BY " +
                    "c.id, c.nombre_completo, tar.facilitador"; // 00085720 Se define la consulta SQL para obtener los clientes que han realizado compras con cierto facilitador

            PreparedStatement pstmt = conn.prepareStatement(query); // 00085720 Prepara la consulta SQL
            pstmt.setString(1, facilitador); // 00085720 Establece el facilitador en la consulta
            ResultSet rs = pstmt.executeQuery(); // 00085720 Ejecuta la consulta

            LocalDateTime now = LocalDateTime.now(); // 00085720 Crea un objeto LocalDateTime con la hora actual
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss"); //00085720 Le da formato a la hora
            String hora = now.format(formatter); // 00085720 Saca la hora actual

            String nombreArchivo = "Reporte D - " + hora + " - " + LocalDate.now() + ".txt"; // 00085720 Nombre del archivo
            String rutaArchivo = "Reportes/" + nombreArchivo; // 00085720 Ruta del archivo
            File file = new File(rutaArchivo); // 00085720 Crea un archivo con la ruta especificada
            Writer writer = new FileWriter(file); // 00085720 Crea un escritor para el archivo

            if (rs.isBeforeFirst()) { // 00085720 Verifica si hay resultados en la consulta
                writer.write("""
                        Reporte D

                        id_cliente\tnombre_completo\tfacilitador\tcantidad_compras\ttotal_gastado
                        """);
                while (rs.next()) { // 00085720 Itera sobre los resultados de la consulta
                    int clienteId = rs.getInt("cliente_id"); // 00085720 Obtiene el ID del cliente
                    String nombreCompleto = rs.getString("nombre_completo"); // 0085720 Obtiene el nombre completo del cliente
                    String facilitadorConsulta = rs.getString("facilitador"); // 00085720 Obtiene el facilitador
                    int cantidadCompras = rs.getInt("cantidad_compras"); // 00085720 Obtiene la cantidad de compras
                    double totalGastado = rs.getDouble("total_gastado"); // 00085720 Obtiene el total gastado

                    writer.write(clienteId + "\t\t\t" + nombreCompleto + "\t\t\t" + facilitadorConsulta + "\t\t\t" + cantidadCompras + "\t\t\t" + totalGastado + "\n"); // 00085720 Escribe en el archivo
                }
            } else {
                writer.write("Reporte D: No existe ningun cliente con el facilitador: " + facilitador); // 00085720 Escribe en el archivo
            }
            writer.flush(); // 00085720 Guarda los cambios en el archivo
            writer.close(); // 00085720 Cierra el archivo

            facilitadorTextField.setText("Reporte creado correctamente"); // 00085720 Muestra un mensaje de exito en la etiqueta
            limpiarD(); // 00085720 limpia los campos de entrada
            tiempoLabel(facilitadorTextField); // 00085720 Muestra facilitadorTextField por 2 segundos
            System.out.println("Se genero reporte D");

            conn.close(); // 00085720 Cierra la conexión a la base de datos
        } catch (SQLException e) { // 00085720 Maneja excepciones SQL
            e.printStackTrace(); // 00085720 Imprime la traza de la excepción
        } catch (IOException e) { // 00085720 Maneja excepciones de entrada/salida
            e.printStackTrace();
        }
    }

    private void limpiarD() {
        cbFacilitador.setValue(null); // 00085720 Limpia el ComboBox del facilitador
    }

    private void tiempoLabel(Label label) {
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(2), event -> label.setText("")));
        timeline.setCycleCount(1);
        timeline.play();
    }
}