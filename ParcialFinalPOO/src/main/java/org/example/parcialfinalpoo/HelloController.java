
package org.example.parcialfinalpoo;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class HelloController {

    @FXML
    private TextField idClienteA;
    @FXML
    private TextField fechaInicioA;
    @FXML
    private TextField fechaFinA;
    @FXML
    private TextArea reporteTextAreaA;

    @FXML
    private TextField idClienteB;
    @FXML
    private TextField mesB;
    @FXML
    private TextField añoB;
    @FXML
    private TextArea reporteTextAreaB;

    @FXML
    private TextField idClienteC;
    @FXML
    private TextArea reporteTextAreaC;

    @FXML
    private TextField facilitadorTextField;
    @FXML
    private TextArea reporteTextAreaD;

    @FXML
    protected void onGenerarReporteAButtonClick() {
        String idCliente = idClienteA.getText();
        String fechaInicio = fechaInicioA.getText();
        String fechaFin = fechaFinA.getText();

        // Logica para generar el reporte A
        String reporteA = generarReporteA(idCliente, fechaInicio, fechaFin);

        // Mostrar el reporte en el TextArea
        reporteTextAreaA.setText(reporteA);
    }

    @FXML
    protected void onGenerarReporteBButtonClick() {
        String idCliente = idClienteB.getText();
        String mes = mesB.getText();
        String año = añoB.getText();

        // Logica para generar el reporte B
        String reporteB = generarReporteB(idCliente, mes, año);

        // Mostrar el reporte en el TextArea
        reporteTextAreaB.setText(reporteB);
    }

    @FXML
    protected void onGenerarReporteCButtonClick() {
        String idCliente = idClienteC.getText();

        // Logica para generar el reporte C
        String reporteC = generarReporteC(idCliente);

        // Mostrar el reporte en el TextArea
        reporteTextAreaC.setText(reporteC);
    }

    @FXML
    protected void onGenerarReporteDButtonClick() {
        String facilitador = facilitadorTextField.getText();

        // Logica para generar el reporte D
        String reporteD = generarReporteD(facilitador);

        // Mostrar el reporte en el TextArea
        reporteTextAreaD.setText(reporteD);
    }

    private String generarReporteA(String idCliente, String fechaInicio, String fechaFin) {
        // Aquiva la logica para generar el reporte A
        // Retornar un string con el contenido del reporte
        return "Reporte A generado para cliente " + idCliente + " desde " + fechaInicio + " hasta " + fechaFin;
    }

    private String generarReporteB(String idCliente, String mes, String año) {
        // Aqui va la logica para generar el reporte B
        // Retornar un string con el contenido del reporte
        return "Reporte B generado para cliente " + idCliente + " en el mes " + mes + " del año " + año;
    }

    private String generarReporteC(String idCliente) {
        // Aqui va la logica para generar el reporte C
        // Retornar un string con el contenido del reporte
        return "Reporte C generado para cliente " + idCliente;
    }

    private String generarReporteD(String facilitador) {
        // Aqui va la logica para generar el reporte D
        // Retornar un string con el contenido del reporte
        return "Reporte D generado para compras con facilitador " + facilitador;
    }
}
