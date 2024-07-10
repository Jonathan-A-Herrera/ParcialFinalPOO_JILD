package org.example.parcialfinalpoo;
import EntidadesBD.Cliente;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.io.File;
import java.io.FileWriter;
import java.sql.*;
public class ReporteB {
    public ObservableList<Cliente> getClientes(int idCliente, int mes, int anio) { //00095123 crea metodo que devolvera una lista de instancias de la clase cliente
        ObservableList<Cliente> clientes = FXCollections.observableArrayList();//00095123 crea una lista que se usara para guardar los valores que iran en TableviewB
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");//00095123 Carga el controlador para la BD
            String url = "jdbc:sqlserver://localhost:1433;databaseName=PARCIALFINAL;encrypt=false";//00095123 variable String para la conexion a la BD
            String user = "poo";//00095123 usuario para la conexion a la bd
            String password = "ParcialFinal";//00095123 contraseña para la conexion a la bd
            Connection conn = DriverManager.getConnection(url, user, password); //00095123 Estableciendo conexion con la base de datos
            String query = "SELECT cl.ID_Cliente, cl.Nombre, SUM(trns.Monto_Total) AS 'Total Gastado' " +
                    "FROM Cliente cl " +
                    "INNER JOIN Tarjeta trj ON trj.ID_Cliente = cl.ID_Cliente " +
                    "INNER JOIN Transacción trns ON trns.Número_Tarjeta = trj.Número_Tarjeta " +
                    "WHERE cl.ID_Cliente = ? " +
                    "AND YEAR(trns.Fecha_Compra) = ? " +
                    "AND MONTH(trns.Fecha_Compra) = ? " +
                    "GROUP BY cl.ID_Cliente, cl.Nombre";//00095123 Consulta para el reporteB que es el total gastado de un mes

            PreparedStatement pstmt = conn.prepareStatement(query);//00095123 se crea un objeto de tipo statement que ayudara a mandar consultas a la bd
            pstmt.setInt(1, idCliente);//00095123 establece el parametro idCliente para la consulta
            pstmt.setInt(2, anio);//00095123 establece el parametro anio para la consulta
            pstmt.setInt(3, mes);//00095123 establece el paremetro mes para la consulta
            ResultSet rs = pstmt.executeQuery();//00095123 se crea un objeto rs que ejecuta una consulta a la bd
            while (rs.next()) {
                Cliente cliente = new Cliente();
                cliente.setID_Cliente(rs.getInt("ID_Cliente"));//00095123 Define los valores de la variable id_Cliente, pasando como parametro el nombre de la columna de la bd
                cliente.setNombre(rs.getString("Nombre"));//00095123 Define los valores de la variable nombre, pasando como parametro el nombre de la columna de la bd
                cliente.setMonto(rs.getDouble("Total Gastado"));//00095123 Define los valores de la variable monto_total, pasando como parametro el nombre de la columna de la bd
                clientes.add(cliente);//00095123 se añaden los resultados a la lista que se les pasara como parametro al metodo .setItems(clientes) en el boton onGenerarReporteBButtonclick
            }

        } catch (Exception e) {//00095123 Control para el manejo de excepciones
            e.printStackTrace();//00095123 Imprime mensajes de errores standard en caso que haya habido algun error
        }
        return clientes;//00095123 retorna los elementos en la lista
    }
    public void generarReporteB(File file, int idCliente, int mes, int anio) {
        ObservableList<Cliente> datos = getClientes(idCliente, mes, anio); //00095123: Carga los datos de la lista
        if (!file.exists()) { //00095123: Verifica si existe el archivo
            try {
                FileWriter writer = new FileWriter(file); //00095123: Crea una instancia de la clase writer que permitirá escribir en el archivo de nombre especificado
                for (Cliente cliente : datos) { //00095123: ciclo for que permitira recorrer los elementos de la lista
                    String info = "ID: " + cliente.getID_Cliente() //00095123: variable que guarda los datos de la lista a medida que realiza iteraciones
                            + "    Nombre: " + cliente.getNombre() //00095123: recupera el nombre del cliente
                            + "    Gasto total en el mes: " + cliente.getMonto() //00095123: recupera el monto de la compra del cliente
                            + "\n";
                    writer.write(info); //00095123: metodo de la instancia writer que escribira los datos correspondientes a cada iteracion
                }
                writer.close(); //00095123: cierra el flujo de escritura en el archivo reporteB
            } catch (Exception e) {
                e.printStackTrace(); //00095123: imprime mensajes de errores estandar en caso de que haya habido algun error
            }
        } else {
            try {
                FileWriter writer = new FileWriter(file, true); //00095123: crea una instancia de la clase writer que permitira escribir en el archivo de nombre especificado
                for (Cliente cliente : datos) { //00095123: ciclo for que permitira recorrer los elementos de la lista
                    String info = "ID: " + cliente.getID_Cliente() //00095123: variable que ira guardando los datos de la lista a medida que realiza iteraciones
                            + "    Nombre: " + cliente.getNombre() //00095123: recupera el nombre del cliente
                            + "    Gasto total en el mes: " + cliente.getMonto() //00095123: recupera el monto de la compra del cliente
                            + "\n";
                    writer.write(info); //00095123: metodo de la instancia writer que escribirá los datos correspondientes a cada iteracion
                }
                writer.close(); //00095123: cierra el flujo de escritura en el archivo reporteB
            } catch (Exception e) { //00095123: control para el manejo de excepciones
                e.printStackTrace(); //00095123: imprime mensajes de errores estándar en caso de que haya habido algun error
            }
        }
    }
}
