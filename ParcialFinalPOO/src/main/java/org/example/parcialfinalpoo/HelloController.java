package org.example.parcialfinalpoo;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class HelloController implements Initializable {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) { //00013423: Ejecuta lo que haya dentro del metodo al correr la aplicacion
       connectToDatabase(); //00013423: Llamado al metodo para establecer conexion con la BD
    }

    public void connectToDatabase(){ //00013423: Creando una funcion para establecer conexion con la base de datos
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver"); //00013423: Cargando el controlador para la base de datos
            String url = "jdbc:sqlserver://localhost:1433;databaseName=PARCIALFINAL;encrypt=false"; //00013423: Variable String la cual se inicializa con la URL de la conexion a la BD
            String user = "poo"; //00013423: Usuario que se le pasara como parametro al metodo .getConnection(url,user,password)
            String password = "ParcialFinal"; //00013423: Contraseña del usuario que se le pasara como parametro al metodo .getConnection(url,user,password)
            Connection conn = DriverManager.getConnection(url,user,password); //00013423: Estableciendo conexion con la base de datos
            System.out.println("Se establecio la conexion correctamente"); //00013423: Mensaje para saber si la conexion se establecio correctamente
        } catch (Exception e){ //00013423: Control para el manejo de excepciones
            e.printStackTrace(); //00013423: Imprime mensajes de errores estandar en caso de que haya habido algun error
        }
    }
}