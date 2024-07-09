package org.example.parcialfinalpoo;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.fxml.Initializable;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
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
    private TextField facilitadorField; // 00085720 Campo de texto para el facilitador

    @FXML
    private TableView<Item> dataTableView; // 00085720 Tabla para mostrar los elementos
    @FXML
    private TableColumn<Item, String> idColumn; // 00085720 Columna para el ID
    @FXML
    private TableColumn<Item, String> nameColumn; // 00085720 Columna para el nombre
    @FXML
    private TableColumn<Item, String> descriptionColumn; // 00085720 Columna para la descripción
    @FXML
    private TableColumn<Item, String> facilitadorColumn; // 00085720 Columna para el facilitador
    @FXML
    private ComboBox<String> cbFacilitador; // 00085720 ComboBox para buscar el facilitador

    // 00085720 Lista observable para almacenar los elementos
    private final ObservableList<Item> itemList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Configurar las columnas de la tabla
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id")); // 00085720 Configura la columna 'idColumn'
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name")); // 00085720 Configura la columna 'nameColumn'
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description")); // 00085720 Configura la columna 'descriptionColumn'
        facilitadorColumn.setCellValueFactory(new PropertyValueFactory<>("facilitador")); // 00085720 Configura la columna 'facilitadorColumn'
        dataTableView.setItems(itemList); // 00085720 Vincular la lista a la tabla
        connectToDatabase(); // Inicializar la conexión a la base de datos
    }
    ObservableList<String> facilitadores = FXCollections.observableArrayList(
            "Visa", "MasterCard", "American Express"
    ); // 00085720 Lista de facilitadores

    // 00085720 Metodo para crear un nuevo elemento
    @FXML
    private void create() {
        // 00085720 Obtener los valores de los campos de texto
        String id = idField.getText(); // 00085720 Obtiene el texto del campo 'idField' y lo almacena en la variable 'id'
        String name = nameField.getText(); // 00085720 Obtiene el texto del campo 'nameField' y lo almacena en la variable 'name'
        String description = descriptionField.getText(); // 00085720 Obtiene el texto del campo 'descriptionField' y lo almacena en la variable 'description'
        String facilitador = facilitadorField.getText(); // 00085720 Obtiene el texto del campo 'facilitadorField' y lo almacena en la variable 'facilitador'

        // 00085720 Validar que todos los campos esten llenos
        if (id.isEmpty() || name.isEmpty() || description.isEmpty() || facilitador.isEmpty()) { // 00085720 Si id, name o description estan vacios
            showAlert(Alert.AlertType.ERROR, "Error", "Todos los campos son obligatorios."); // 00085720 Muestra alerta
            return;
        }

        // 00085720 Crear un nuevo objeto Item y agregarlo a la lista
        Item item = new Item(id, name, description, facilitador); // 00085720 Crea un nuevo objeto Item
        itemList.add(item); // 00085720 Agrega el objeto Item a la lista
        clearFields(); // 00085720 Limpiar los campos de texto
    }

    // 00085720 Metodo para leer un elemento por su ID
    @FXML
    private void read() {
        String id = idField.getText(); // 00085720 Obtener el ID del campo de texto
        if (id.isEmpty()) { // 00085720 Validar que el campo de ID no este vacío
            showAlert(Alert.AlertType.ERROR, "Error", "ID es obligatorio."); // 00085720 muestra alerta
            return;
        }

        // 00085720 Buscar el elemento por su ID
        Item item = findItemById(id); // 00085720 Busca el elemento por su ID
        if (item != null) { // 00085720 Si item diferente a nulo
            // 00085720 Mostrar los datos en los campos de texto
            nameField.setText(item.getName()); // 00085720 Asigna el nombre del elemento al campo de texto
            descriptionField.setText(item.getDescription()); // 00085720 Asigna la descripcion del elemento
        } else { // 00085720 Si no
            showAlert(Alert.AlertType.INFORMATION, "Informacion", "Elemento no encontrado."); // 00085720 muestra alerta
        }
    }

    // 00085720 Metodo para actualizar un elemento
    @FXML
    private void update() {
        // 00085720 Obtener los valores de los campos de texto
        String id = idField.getText(); // 00085720 Obtiene el texto del campo 'idField' y lo almacena en la variable 'id'
        String name = nameField.getText(); // 00085720 Obtiene el texto del campo 'nameField' y lo almacena en la variable 'name'
        String description = descriptionField.getText(); // 00085720 Obtiene el texto del campo 'description' y lo almacena en la variable 'description'
        String facilitador = facilitadorField.getText(); // 00085720 Obtiene el texto del campo 'facilitadorField' y lo almacena en la variable 'facilitador'

        // 00085720 Validar que todos los campos esten llenos
        if (id.isEmpty() || name.isEmpty() || description.isEmpty() || facilitador.isEmpty()) { // 00085720 Si id, name o description estan vacios
            showAlert(Alert.AlertType.ERROR, "Error", "Todos los campos son obligatorios."); // 00085720 Mostrar alerta
            return;
        }

        // 00085720 Buscar el elemento por su ID
        Item item = findItemById(id);
        if (item != null) { // 00085720 Si item diferente a nulo
            // 00085720 Actualizar los valores del elemento
            item.setName(name); // 00085720 Actualizar el nombre del elemento
            item.setDescription(description); // 00085720 Actualizar la descripcion del elemento
            item.setFacilitador(facilitador); // 00085720 Actualizar el facilitador del elemento
            dataTableView.refresh(); // 00085720 Refrescar la tabla para mostrar los cambios
            clearFields(); // 00085720 Limpiar los campos de texto
        } else { // 00085720 Si no
            showAlert(Alert.AlertType.INFORMATION, "Informacion", "Elemento no encontrado."); // 00085720 Mostrar alerta
        }
    }

    // 00085720 Metodo para eliminar un elemento
    @FXML
    private void delete() {
        String id = idField.getText(); // 00085720 Obtener el ID del campo de texto
        // 00085720 Validar que el campo de ID no este vacío
        if (id.isEmpty()) { // 00085720 Si id esta vacio
            showAlert(Alert.AlertType.ERROR, "Error", "ID es obligatorio."); // 00085720 Mostrar alerta
            return;
        }
        Item item = findItemById(id); // 00085720 Buscar el elemento por su ID
        if (item != null) { // 00085720 Item diferente a nulo
            itemList.remove(item); // 00085720 Eliminar el elemento de la lista
            clearFields(); // 00085720 Limpiar los campos de texto
        } else { // 00085720 Si no
            showAlert(Alert.AlertType.INFORMATION, "Informacion", "Elemento no encontrado."); // 00085720 Mostrar alerta
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
        facilitadorField.clear(); // 00085720 Limpia campos
    }

    // 00085720 Metodo para mostrar alertas
    private void showAlert(Alert.AlertType alertType, String title, String content) {
        Alert alert = new Alert(alertType, content, ButtonType.OK); // 00085720 Crea una alerta
        alert.setTitle(title); // 00085720 Titulo de la alerta
        alert.setHeaderText(null); // 00085720 No tiene encabezado
        alert.showAndWait(); // 00085720 Mostrar la alerta y esperar a que se cierre
    }

    // 00085720 Metodos para generar reportes
    @FXML
    private void onGenerarReporteAButtonClick(ActionEvent event) {
        // 00085720 Codigo para generar reporte A
        showAlert(Alert.AlertType.INFORMATION, "Reporte A", "Generar Reporte A");
    }

    @FXML
    private void onGenerarReporteBButtonClick(ActionEvent event) {
        // 00085720 Codigo para generar reporte B
        showAlert(Alert.AlertType.INFORMATION, "Reporte B", "Generar Reporte B");
    }

    @FXML
    private void onGenerarReporteCButtonClick(ActionEvent event) {
        // 00085720 Codigo para generar reporte C
        showAlert(Alert.AlertType.INFORMATION, "Reporte C", "Generar Reporte C");
    }

    @FXML
    private void onGenerarReporteDButtonClick(ActionEvent event) {
        // 00085720 Codigo para generar reporte D
        ReporteD reporteD = new ReporteD();
        reporteD.crearReporteD(); // Llamar al metodo para generar el reporte D
        showAlert(Alert.AlertType.INFORMATION, "Reporte D", "Reporte D generado correctamente.");
    }

    public void connectToDatabase() { // 00013423: Creando una funcion para establecer conexion con la base de datos
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver"); // 00013423: Cargando el controlador para la base de datos
            String url = "jdbc:sqlserver://localhost:1433;databaseName=PARCIALFINAL;encrypt=false"; // 00013423: Variable String la cual se inicializa con la URL de la conexion a la BD
            String user = "poo"; // 00013423: Usuario que se le pasara como parametro al metodo .getConnection(url,user,password)
            String password = "ParcialFinal"; // 00013423: Contraseña del usuario que se le pasara como parametro al metodo .getConnection(url,user,password)
            Connection conn = DriverManager.getConnection(url, user, password); // 00013423: Estableciendo conexion con la base de datos
            System.out.println("Se establecio la conexion correctamente"); // 00013423: Mensaje para saber si la conexion se establecio correctamente
        } catch (Exception e) { // 00013423: Control para el manejo de excepciones
            e.printStackTrace(); // 00013423: Imprime mensajes de errores estandar en caso de que haya habido algun error
        }
    }
}

