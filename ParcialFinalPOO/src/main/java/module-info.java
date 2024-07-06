module org.example.parcialfinalpoo {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.parcialfinalpoo to javafx.fxml;
    exports org.example.parcialfinalpoo;
}