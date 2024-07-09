module org.example.parcialfinalpoo {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires com.microsoft.sqlserver.jdbc;

    opens org.example.parcialfinalpoo to javafx.fxml;
    opens EntidadesBD to javafx.base;
    exports org.example.parcialfinalpoo;
}