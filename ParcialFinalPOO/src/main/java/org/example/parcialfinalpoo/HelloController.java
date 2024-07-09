package org.example.parcialfinalpoo;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.fxml.Initializable;
import javafx.scene.control.cell.PropertyValueFactory;

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
    private TextField facilitadorField; // 00085720 Campo de texto para el facilitador
    @FXML
    private Label facilitadorTextField; // 00085720 Campo de texto para el estado del reporte
    @FXML
    private ComboBox<String> cbFacilitador; // 00085720 ComboBox para el facilitador

    private ReporteD reporteD; // 00085720 Variable para el objeto ReporteD
    @FXML
    private TextField facilitadorFieldD; // Campo de texto para el facilitador de tarjeta
    @FXML
    private TableView<Cliente> reporteTableViewD; // TableView para mostrar los resultados del Reporte D
    @FXML
    private TableColumn<Cliente, Integer> idColumnD; // Columna para el ID del cliente
    @FXML
    private TableColumn<Cliente, String> nameColumnD; // Columna para el nombre del cliente
    @FXML
    private TableColumn<Cliente, Integer> comprasColumnD; // Columna para la cantidad de compras
    @FXML
    private TableColumn<Cliente, Double> totalGastadoColumnD; // Columna para el total gastado

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

    // 00085720 Lista observable para almacenar los elementos
    private final ObservableList<Item> itemList = FXCollections.observableArrayList();

    ObservableList<String> facilitadores = FXCollections.observableArrayList( //00085720 Lista de facilitadores
            "Visa", "MasterCard", "American Express"
    );

    @FXML
    public void poblarCombo() {
        cbFacilitador.setItems(facilitadores); // 00085720 se asignan los facilitadores al ComboBox
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        connectToDatabase();
        // Configurar las columnas de la tabla
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
        facilitadorColumn.setCellValueFactory(new PropertyValueFactory<>("facilitador"));
        dataTableView.setItems(itemList);

        // Configurar las columnas de la tabla de reporte D
        idColumnD.setCellValueFactory(new PropertyValueFactory<>("ID_Cliente"));
        nameColumnD.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        comprasColumnD.setCellValueFactory(new PropertyValueFactory<>("cantidadCompras"));
        totalGastadoColumnD.setCellValueFactory(new PropertyValueFactory<>("totalGastado"));
    }

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
            facilitadorField.setText(item.getFacilitador()); // 00085720 Asigna el facilitador del elemento al campo de texto
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
        if (id.isEmpty()) { // 00085720 Validar que el campo de ID no este vacio
            showAlert(Alert.AlertType.ERROR, "Error", "ID es obligatorio."); // 00085720 Mostrar alerta
            return;
        }

        // 00085720 Buscar el elemento por su ID y eliminarlo de la lista
        Item item = findItemById(id); // 00085720 Busca el elemento por su ID
        if (item != null) { // 00085720 Si item diferente a nulo
            itemList.remove(item); // 00085720 Eliminar el elemento de la lista
            clearFields(); // 00085720 Limpiar los campos de texto
        } else { // 00085720 Si no
            showAlert(Alert.AlertType.INFORMATION, "Informacion", "Elemento no encontrado."); // 00085720 Mostrar alerta
        }
    }

    // 00085720 Metodo para encontrar un elemento por su ID
    private Item findItemById(String id) {
        for (Item item : itemList) { // 00085720 Itera sobre los elementos de la lista
            if (item.getId().equals(id)) { // 00085720 Si el ID del elemento coincide con el ID buscado
                return item; // 00085720 Retorna el elemento
            }
        }
        return null; // 00085720 Si no encuentra el elemento retorna null
    }

    // 00085720 Metodo para mostrar alertas
    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType); // 00085720 Crear una nueva alerta
        alert.setTitle(title); // 00085720 Establecer el título de la alerta
        alert.setHeaderText(null); // 00085720 Establecer el encabezado de la alerta en nulo
        alert.setContentText(message); // 00085720 Establecer el mensaje de la alerta
        alert.showAndWait(); // 00085720 Mostrar la alerta y esperar
    }

    // 00085720 Metodo para limpiar los campos de texto
    private void clearFields() {
        idField.clear(); // 00085720 Limpiar el campo de texto del ID
        nameField.clear(); // 00085720 Limpiar el campo de texto del nombre
        descriptionField.clear(); // 00085720 Limpiar el campo de texto de la descripcion
        facilitadorField.clear(); // 00085720 Limpiar el campo de texto del facilitador
    }

    //00085720 metodo para guardar el csv
    @FXML
    private void guardarCSV() {
        try (FileWriter writer = new FileWriter("report.csv")) { // 00085720 Crea un nuevo FileWriter para el archivo "report.csv"
            writer.write("ID,Name,Description,Facilitador\n"); // 00085720 Escribe los encabezados en el archivo CSV
            for (Item item : itemList) { // 00085720 Itera sobre los elementos de la lista
                writer.write(item.getId() + "," + item.getName() + "," + item.getDescription() + "," + item.getFacilitador() + "\n"); // 00085720 Escribe los datos del elemento en el archivo CSV
            }
            showAlert(Alert.AlertType.INFORMATION, "Informacion", "Datos guardados en report.csv"); // 00085720 Muestra alerta
        } catch (IOException e) { // 00085720 Captura cualquier excepción de E/S
            showAlert(Alert.AlertType.ERROR, "Error", "No se pudo guardar el archivo."); // 00085720 Muestra alerta
        }
    }

    private void connectToDatabase() {
        String url = "jdbc:mysql://localhost:3306/tienda";
        String user = "root";
        String password = "password";

        try {
            Connection connection = DriverManager.getConnection(url, user, password);
            String query = "SELECT * FROM items";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                String id = resultSet.getString("id");
                String name = resultSet.getString("name");
                String description = resultSet.getString("description");
                String facilitador = resultSet.getString("facilitador");

                Item item = new Item(id, name, description, facilitador);
                itemList.add(item);
            }

            resultSet.close();
            statement.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void onGenerarReporteDButtonClick(ActionEvent event) {
        String facilitador = facilitadorFieldD.getText();

        if (facilitador.isEmpty()) {
            showAlert(Alert.AlertType.WARNING, "Fallo", "El facilitador de tarjeta es obligatorio");
        } else {
            ReporteD reporteD = new ReporteD(facilitador);
            ObservableList<Cliente> datos = reporteD.getClientes();
            reporteTableViewD.setItems(datos);

            File file = new File(System.getProperty("user.dir") + "/src/main/java/Reportes/", "ReporteD - " + java.time.LocalDateTime.now().toString().replace(":", "-") + ".txt");
            try {
                FileWriter writer = new FileWriter(file);
                for (Cliente cliente : datos) {
                    String info = "ID: " + cliente.getID_Cliente()
                            + "    Nombre: " + cliente.getNombre()
                            + "    Cantidad de Compras: " + cliente.getCantidadCompras()
                            + "    Total Gastado: " + cliente.getTotalGastado() + "\n";
                    writer.write(info);
                }
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}