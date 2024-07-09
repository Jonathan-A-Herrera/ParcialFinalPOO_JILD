package org.example.parcialfinalpoo;

import EntidadesBD.Cliente;
import EntidadesBD.Tarjeta;
import EntidadesBD.Transaccion;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

public class HelloController implements Initializable {

    // 00085720 Campos de texto para ingresar datos
    @FXML
    private TextField idField; // 00085720 Campo de texto para el ID
    @FXML
    private TextField nameField; // 00085720 Campo de texto para el nombre
    @FXML

    private TextField descriptionField; // 00085720 Campo de texto para la descripción
    @FXML
    private TextField idClienteA; //00013423: Campo de texto que guardara el id del cliente a buscar
    @FXML
    private TextField fechaInicioA; //00013423: Fecha de inicio de la busqueda de compras
    @FXML
    private TextField fechaFinA; //00013423: Fecha de fin de la busqueda de compras
    @FXML
    private TableView<Cliente> reporteTableViewA; //00013423: Table view usada para el reporte A
    @FXML
    private TableColumn<Cliente, Integer> idColumnA; //00013423: Columna de la TVA que contiene el id del cliente
    @FXML
    private TableColumn<Cliente, String> idNameA; //00013423: Columna de la TVA que contiene el nombre del cliente
    @FXML
    private TableColumn<Cliente, Double> idMontoA; //00013423: Columna de la TVA que contiene el monto de la compra del cliente
    @FXML
    private TableColumn<Cliente, String> idFechaCompraA; //00013423: Columna de la TVA que contiene la fecha en la que se realizo la compra

    @FXML 
    private TextField idClienteC; //00379823: Campo de texto que va a almacenar el id del cliente a buscar
    @FXML
    private TableView<Tarjeta> reporteTableViewC; //00379823: Table view usada para el reporte C
    @FXML
    private TableColumn<Tarjeta, String> numTarjetaC;
    @FXML
    private TableColumn<Tarjeta,String> tipoTarjetaC;
    @FXML
    private TableColumn<Tarjeta, String> fechaExpTarjetaC;

    // 00085720 Tabla y sus columnas para mostrar los datos
    @FXML
    private TableView<Item> dataTableView; // 00085720 Tabla para mostrar los elementos
    @FXML
    private TableColumn<Item, String> idColumn; // 00085720 Columna para el ID
    @FXML
    private TableColumn<Item, String> nameColumn; // 00085720 Columna para el nombre
    @FXML
    private TableColumn<Item, String> descriptionColumn; // 00085720 Columna para la descripción

    // 00085720 Lista observable para almacenar los elementos
    private final ObservableList<Item> itemList = FXCollections.observableArrayList();

    // 00085720 Metodo que se llama al inicializar el controlador
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        connectToDatabase();
        // Configurar las columnas de la tabla
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id")); // 00085720 Configura la columna 'idColumn'
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name")); // 00085720 Configura la columna 'nameColumn'
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description")); // 00085720 Configura la columna 'descriptionColumn'
        dataTableView.setItems(itemList); // 00085720 Vincular la lista a la tabla

        //00013423: Configurar las columnas de la tabla de reporte
        idColumnA.setCellValueFactory(new PropertyValueFactory<>("ID_Cliente")); //00013423: Configura la columna id
        idNameA.setCellValueFactory(new PropertyValueFactory<>("nombre")); //00013423: Configura la columna de nombre
        idMontoA.setCellValueFactory(new PropertyValueFactory<>("monto")); //00013423: Configura la columna del monto total de la compra
        idFechaCompraA.setCellValueFactory(new PropertyValueFactory<>("fechaCompra")); //00013423: //00013423: Configura la columna con la fecha de la compra

        numTarjetaC.setCellValueFactory(new PropertyValueFactory<>("numeroTarjetaCensurado"));
        tipoTarjetaC.setCellValueFactory(new PropertyValueFactory<>("tipo"));
        fechaExpTarjetaC.setCellValueFactory(new PropertyValueFactory<>("fechaExpiracion"));
    }

    //00085720 Metodo para crear un nuevo elemento
    @FXML
    private void create() {
        //00085720 Obtener los valores de los campos de texto
        String id = idField.getText(); //00085720 Obtiene el texto del campo 'idField' y lo almacena en la variable 'id'
        String name = nameField.getText(); //00085720 Obtiene el texto del campo 'nameField' y lo almacena en la variable 'name'
        String description = descriptionField.getText(); //00085720 Obtiene el texto del campo 'descriptionField' y lo almacena en la variable 'description'

        // 00085720 Validar que todos los campos esten llenos
        if (id.isEmpty() || name.isEmpty() || description.isEmpty()) { //00085720 Si id, name o description estan vacios
            showAlert(Alert.AlertType.ERROR, "Error", "Todos los campos son obligatorios."); //00085720 Muestra alerta
            return;
        }

        // 00085720 Crear un nuevo objeto Item y agregarlo a la lista
        Item item = new Item(id, name, description); //00085720 Crea un nuevo objeto Item
        itemList.add(item); //00085720 Agrega el objeto Item a la lista
        clearFields(); // 00085720 Limpiar los campos de texto
    }

    // 00085720 Metodo para leer un elemento por su ID
    @FXML
    private void read() {
        String id = idField.getText(); // 00085720 Obtener el ID del campo de texto

        // 00085720 Validar que el campo de ID no este vacío
        if (id.isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Error", "ID es obligatorio."); //00085720 muestra alerta
            return;
        }

        // 00085720 Buscar el elemento por su ID
        Item item = findItemById(id); //00085720 Busca el elemento por su ID
        if (item != null) { //00085720 Si item diferente a nulo
            // 00085720 Mostrar los datos en los campos de texto
            nameField.setText(item.getName()); //00085720 Asigna el nombre del elemento al campo de texto
            descriptionField.setText(item.getDescription()); //00085720 Asigna la descripcion del elemento
        } else {
            showAlert(Alert.AlertType.INFORMATION, "Informacion", "Elemento no encontrado."); // 00085720 muestra alerta
        }
    }

    // 00085720 Metodo para actualizar un elemento
    @FXML
    private void update() {
        // 00085720 Obtener los valores de los campos de texto
        String id = idField.getText(); //00085720 Obtiene el texto del campo 'idField' y lo almacena en la variable 'id'
        String name = nameField.getText(); // 00085720 Obtiene el texto del campo 'nameField' y lo almacena en la variable 'name'
        String description = descriptionField.getText(); // 00085720 Obtiene el texto del campo 'description' y lo almacena en la variable 'description'

        // Validar que todos los campos esten llenos
        if (id.isEmpty() || name.isEmpty() || description.isEmpty()) { // 00085720 Si id, name o description estan vacios
            showAlert(Alert.AlertType.ERROR, "Error", "Todos los campos son obligatorios."); // 00085720 Mostrar alerta
            return;
        }

        //00085720 Buscar el elemento por su ID
        Item item = findItemById(id);
        if (item != null) { //00085720 Si item diferente a nulo
            //00085720 Actualizar los valores del elemento
            item.setName(name); // 00085720 Actualizar el nombre del elemento
            item.setDescription(description); //00085720 Actualizar la descripcion del elemento
            dataTableView.refresh(); // 00085720 Refrescar la tabla para mostrar los cambios
            clearFields(); // 00085720 Limpiar los campos de texto
        } else { //00085720 Si no
            showAlert(Alert.AlertType.INFORMATION, "Informacion", "Elemento no encontrado."); //00085720 Mostrar alerta
        }
    }

    // 00085720 Metodo para eliminar un elemento
    @FXML
    private void delete() {
        String id = idField.getText(); // 00085720 Obtener el ID del campo de texto

        // Validar que el campo de ID no este vacío
        if (id.isEmpty()) { //00085720 Si id esta vacio
            showAlert(Alert.AlertType.ERROR, "Error", "ID es obligatorio."); // 00085720 Mostrar alerta
            return;
        }

        // 00085720 Buscar el elemento por su ID
        Item item = findItemById(id);
        if (item != null) { // 00085720 Item diferente a nulo
            itemList.remove(item); //00085720 Eliminar el elemento de la lista
            clearFields(); // 00085720 Limpiar los campos de texto
        } else { // 00085720 Si no
            showAlert(Alert.AlertType.INFORMATION, "Informacion", "Elemento no encontrado."); //00085720 Mostrar alerta
        }
    }

    // 00085720 Metodo para buscar un elemento por su ID
    private Item findItemById(String id) {
        for (Item item : itemList) { // 00085720 Recorrer la lista de elementos
            if (item.getId().equals(id)) { // 00085720 Comparar el ID del elemento con el ID del campo de texto
                return item; // 00085720 Retornar el elemento si se encuentra
            }
        }
        return null; // 00085720 Retornar null si no se encuentra
    }

    // 00085720 Metodo para limpiar los campos de texto
    private void clearFields() {
        idField.clear(); // 00085720 Limpia campos
        nameField.clear(); // 00085720 Limpia campos
        descriptionField.clear(); // 00085720 Limpia campos
    }

    // 00085720 Metodo para mostrar alertas
    private void showAlert(Alert.AlertType alertType, String title, String content) {
        Alert alert = new Alert(alertType, content, ButtonType.OK); // 00085720 Crea una alerta
        alert.setTitle(title); // 00085720 Titulo de la alerta
        alert.setHeaderText(null); // 00085720 No tiene encabezado
        alert.showAndWait(); // 00085720 Mostrar la alerta y esperar a que se cierre
    }

    // Metodos para generar reportes

    @FXML
    private void onGenerarReporteAButtonClick(ActionEvent event) { //00013423: Boton que genera el reporte A
        if (idClienteA.getText().isEmpty() || fechaInicioA.getText().isEmpty() || fechaFinA.getText().isEmpty()){ //00013423: Mostrara una alerta en caso de que no se haya llenado uno de estos campos
            showAlert(Alert.AlertType.WARNING,"Fallo","Llene todos los campos"); //00013423: Mostrara una alerta
        } else {
            ObservableList<Cliente> datos = getClientes(); //00013423: Se instancia un objeto datos que guardara la lista de clientes que retorna el netodo getClientes();
            reporteTableViewA.setItems(datos); //00013423: Mostrara los datos en el TableViewA del reporte A en sus casillas correspondientes.
            File file = new File(System.getProperty("user.dir") + "/src/main/java/Reportes/","ReporteA.txt"); //00013423: Se crea un archivo file en la ruta actual, con el nombre ReporteA.txt
            generarReporteA(file); //00013423: Se llama al metodo que genera los reportes de la consulta A
        }
    }



    @FXML
    private void onGenerarReporteBButtonClick(ActionEvent event) {
        // Codigo para generar reporte B
        showAlert(Alert.AlertType.INFORMATION, "Reporte B", "Generar Reporte B");
    }

  @FXML
private void onGenerarReporteCButtonClick(ActionEvent event) { //00379823: Boton que genera el reporte C
    if (idClienteC.getText().isEmpty()) { //00379823: Mostrará error en caso que este campo no se haya llenado adecuadamente
        showAlert(Alert.AlertType.WARNING, "Fallo", "El ID del cliente es inválido"); //00379823: Mostrara la alerta 
    } else {
        ObservableList<Tarjeta> datos = getTarjetasCliente(); //00379823: Se instancia un objeto de datos que guarda la lista de tarjetas por cliente que retorna el metodo getTarjetasCliente();  
        reporteTableViewC.setItems(datos); //00379823: Mostrara los datos en el TableViewC del reporte C como corresponde
        File file = new File(System.getProperty("user.dir") + "/src/main/java/Reportes/", "ReporteC.txt"); //00379823: Se crea un archivo tipo file en la ruta acutal, con el nombre ReporteC.txt
        generarReporteC(file); //00379823: Se llama al metodo que genera los reportes de la consulta C
    }
}

    @FXML
    private void onGenerarReporteDButtonClick(ActionEvent event) {
        // Codigo para generar reporte D
        showAlert(Alert.AlertType.INFORMATION, "Reporte D", "Generar Reporte D");
    }

    @FXML
    private void onGenerarReporteEButtonClick(ActionEvent event) {
        // Codigo para generar reporte E
        showAlert(Alert.AlertType.INFORMATION, "Reporte E", "Generar Reporte E");
    }


    public void connectToDatabase() { //00013423: Creando una funcion para establecer conexion con la base de datos
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver"); //00013423: Cargando el controlador para la base de datos
            String url = "jdbc:sqlserver://localhost:1433;databaseName=PARCIALFINAL;encrypt=false"; //00013423: Variable String la cual se inicializa con la URL de la conexion a la BD
            String user = "poo"; //00013423: Usuario que se le pasara como parametro al metodo .getConnection(url,user,password)
            String password = "ParcialFinal"; //00013423: Contraseña del usuario que se le pasara como parametro al metodo .getConnection(url,user,password)
            Connection conn = DriverManager.getConnection(url, user, password); //00013423: Estableciendo conexion con la base de datos
            System.out.println("Se establecio la conexion correctamente"); //00013423: Mensaje para saber si la conexion se establecio correctamente
        } catch (Exception e) { //00013423: Control para el manejo de excepciones
            e.printStackTrace(); //00013423: Imprime mensajes de errores estandar en caso de que haya habido algun error
        }
    }

    public ObservableList<Cliente> getClientes() { //00013423: Crea metodo que devolvera una lista de instancias de la clase Cliente
        ObservableList<Cliente> clientes = FXCollections.observableArrayList(); //00013423: Crea una lista que se usara para guardar los valores que iran en el TableViewA
        try {
            int id = Integer.parseInt(idClienteA.getText()); //00013423: Linea que convierte el valor del campo ID en un entero
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver"); //00013423: Cargando el controlador para la base de datos
            String url = "jdbc:sqlserver://localhost:1433;databaseName=PARCIALFINAL;encrypt=false"; //00013423: Variable String la cual se inicializa con la URL de la conexion a la BD
            String user = "poo"; //00013423: Usuario que se le pasara como parametro al metodo .getConnection(url,user,password)
            String password = "ParcialFinal"; //00013423: Contraseña del usuario que se le pasara como parametro al metodo .getConnection(url,user,password)
            Connection conn = DriverManager.getConnection(url, user, password); //00013423: Estableciendo conexion con la base de datos
            String reporteA = "SELECT cl.ID_Cliente, cl.Nombre, trns.Monto_Total as 'Monto Total', trns.Fecha_Compra as 'Fecha de compra' from Cliente cl\n" +
                    "inner join Tarjeta trj ON trj.ID_Cliente = cl.ID_Cliente\n" +
                    "inner join Transacción trns ON trns.Número_Tarjeta = trj.Número_Tarjeta\n" +
                    "where cl.ID_Cliente =" + id +  "AND\n" + //00013423: Se hace uso de la variable id declarada arriba como parametro de busqueda
                    "trns.Fecha_Compra BETWEEN " + "'"+fechaInicioA.getText()+"'" + " and " + "'"+fechaFinA.getText()+"'"  +  "\n" +
                    "order by trns.Fecha_Compra";
            Statement stmt = conn.createStatement(); ///00013423: Se crea un objeto de tipo statement que ayudara a mandar consultas a la BD
            ResultSet rs = stmt.executeQuery(reporteA); //00013423: Se crea un objeto rs que a su vez ejecuta la consulta a la BD, al metodo .executeQuery se le pasa como parametro la variable string de antes.
            while (rs.next()) { //00013423: Mientras rs.next() sea verdadero, se ira iterando sobre los resultados de la consulta
                Cliente cliente = new Cliente(); //00013423: Crea una instancia de cliente
                cliente.setID_Cliente(rs.getInt("ID_Cliente")); //00013423: Define los valores de la variable ID_Cliente, pasando como parametro el nombre de la columna de la BD
                cliente.setNombre(rs.getString("Nombre")); //00013423: Define los valores de la variable nombre, pasando como parametro el nombre de la columna de la BD
                cliente.setMonto(rs.getDouble("Monto Total")); //00013423: Define los valores de la variable monto, pasando como parametro el nombre de la columna de la BD
                cliente.setFechaCompra(rs.getString("Fecha de Compra")); //00013423: Define los valores de la variable fechaCompra, pasando como parametro el nombre de la columna de la BD
                Cliente client = new Cliente(cliente.getID_Cliente(),cliente.getNombre(),cliente.getMonto(),cliente.getFechaCompra()); //00013423: Se hace uso del metodo Constructor creado especificamente para la consulta A
                clientes.add(client); //00013423: Se añaden los resultados a la lista que se le pasara como parametro al metodo .setItems(clientes) en el boton onGenerarReporteAButtonClick
            }
        } catch (Exception e) { //00013423: Control para el manejo de excepciones
            e.printStackTrace(); //00013423: Imprime mensajes de errores estandar en caso de que haya habido algun error
        }
        return clientes; //00013423: Retorna los elementos en la lista
    }

    public void generarReporteA(File file){
        ObservableList<Cliente> datos = getClientes(); //00013423: Carga los datos de la lista
        if (!file.exists()){ //00013423: Verifica si existe el archivo
            try {
                FileWriter writer = new FileWriter(file); //00013423: Crea una instancia de la clase writer que permitira escribir en el archivo de nombre especificado
                for(Cliente cliente : datos){ //00013423: ciclo for que permitira recorrer los elementos de la lista.
                    String info = "ID: " + cliente.getID_Cliente() //0013423: variable que ira guardando los datos de la lista a medida que realiza iteraciones
                            +"    Nombre: " + cliente.getNombre() //00013423: recupera el nombre del cliente
                            +"    Monto total: " + cliente.getMonto() //00013423: recupera el monto de la compra del cliente
                            +"    Fecha de compra: " + cliente.getFechaCompra() + "\n"; //00013423: recupera la fecha de la compra
                    writer.write(info); //00013423: metodo de la instancia writer que escribira los datos correspondientes a cada iteracion
                }
                writer.close(); //00013423: Cierra el flujo de escritura en el archivo reporteA
            } catch (Exception e) {
                e.printStackTrace(); //00013423: Imprime mensajes de errores estandar en caso de que haya habido algun error
            }
        } else {
            try {
                FileWriter writer = new FileWriter(file,true); //00013423: Crea una instancia de la clase writer que permitira escribir en el archivo de nombre especificado
                for(Cliente cliente : datos){ //00013423: ciclo for que permitira recorrer los elementos de la lista.
                    String info = "ID: " + cliente.getID_Cliente() //0013423: variable que ira guardando los datos de la lista a medida que realiza iteraciones
                            +"    Nombre: " + cliente.getNombre() //00013423: recupera el nombre del cliente
                            +"    Monto total: " + cliente.getMonto() //00013423: recupera el monto de la compra del cliente
                            +"    Fecha de compra: " + cliente.getFechaCompra() + "\n"; //00013423: recupera la fecha de la compra
                    writer.write(info); //00013423: metodo de la instancia writer que escribira los datos correspondientes a cada iteracion
                }
                writer.close(); //00013423: Cierra el flujo de escritura en el archivo reporteA
            } catch (Exception e) { //00013423: Control para el manejo de excepciones
                e.printStackTrace(); //00013423: Imprime mensajes de errores estandar en caso de que haya habido algun error
            }
        }
    }

    public ObservableList<Tarjeta> getTarjetasCliente() {
        ObservableList<Tarjeta> tarjetas = FXCollections.observableArrayList();
        try {
            int id = Integer.parseInt(idClienteC.getText());
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String url = "jdbc:sqlserver://localhost:1433;databaseName=PARCIALFINAL;encrypt=false";
            String user = "poo";
            String password = "ParcialFinal";
            Connection conn = DriverManager.getConnection(url, user, password);
            String query = "SELECT Número_Tarjeta, Tipo, Fecha_Expiración FROM Tarjeta WHERE ID_Cliente = " + id;
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                Tarjeta tarjeta = new Tarjeta();
                tarjeta.setNumeroTarjeta(rs.getString("Número_Tarjeta"));
                tarjeta.setTipo(rs.getString("Tipo"));
                tarjeta.setFechaExpiracion(rs.getString("Fecha_Expiración"));
                tarjetas.add(tarjeta);
            }
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tarjetas;
    }

public void generarReporteC(File file) {
    ObservableList<Tarjeta> datos = getTarjetasCliente(); //00379823: Carga los datos de la lista

    if (!file.exists()) { //00379823: Verifica si existe este archivo
        try {
            FileWriter writer = new FileWriter(file); //00379823: Crea una instancia de la clase writer que permitira escribir en el archivo de nombre especificado
            for (Tarjeta tarjeta : datos) { //00379823: ciclo for que permitira recorrer los elementos de la lista
                String info = "Número de Tarjeta: " + tarjeta.getNumeroTarjeta() + "    Tipo: " + tarjeta.getTipo() + "\n"; //00379823: variable que ira guardando los datos de la lista a medida que realiza iteraciones
                writer.write(info); //00379823: metodo de la instancia tipo writer que escribira los datos correspondientes a cada iteracion
            }
            writer.close(); //00379823: Cierra el flujo de escritura en el archivo reporteC
        } catch (Exception e) { //00379823: Control para el manejo de excepciones
            e.printStackTrace(); //00379823: Imprime mensajes de errores estandar en caso de que haya habido algun error
        }
    } else {
        try {
            FileWriter writer = new FileWriter(file, true);
            for (Tarjeta tarjeta : datos) { //00379823: ciclo for que permitira recorrer los elementos de la lista
                String info = "Número de Tarjeta: " + tarjeta.getNumeroTarjeta() + "    Tipo: " + tarjeta.getTipo() + "\n"; //00379823: variable que ira guardando los datos de la lista a medida que realiza iteraciones
                writer.write(info); //00379823: metodo de la instancia tipo writer que escribira los datos correspondientes a cada iteracion
            }
            writer.close(); //00379823: Cierra el flujo de escritura en el archivo reporteC
        } catch (Exception e) {  //00379823: Control para el manejo de excepciones
            e.printStackTrace(); //00379823: Imprime mensajes de errores estandar en caso de que haya habido algun error
        }
    }
}

}
