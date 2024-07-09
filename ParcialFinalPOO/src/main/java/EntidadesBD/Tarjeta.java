package EntidadesBD;

public class Tarjeta {
    private String numeroTarjeta; //00013423: Variable numeroTarjeta que se relaciona al campo PK_NumeroTarjeta de la BD
    private String fechaExpiracion; //00013423: Variable fechaExpiracion que se relaciona al campo FechaExpiracion de la BD
    private String tipo; //00013423: Variable tipo que se relaciona al campo Tipo de la BD
    private String facilitador; //00013423: Variable facilitador que se relaciona al campo Facilitador de la BD
    private Cliente FK_idCliente; //00013423: Variable FK_idCliente que se relaciona a la clave foránea del Cliente en la BD

    public Tarjeta() {} //00013423: Constructor vacío para crear una instancia de Tarjeta sin inicializar variables

    public Tarjeta(String numeroTarjeta, String fechaExpiracion, String tipo, String facilitador, Cliente FK_idCliente) {
        this.numeroTarjeta = numeroTarjeta;
        this.fechaExpiracion = fechaExpiracion;
        this.tipo = tipo;
        this.facilitador = facilitador;
        this.FK_idCliente = FK_idCliente;
    } //00013423: Constructor para crear una instancia de Tarjeta con todas las variables inicializadas

    public Tarjeta(String tarjetaCensurada, String crédito) { //comenta aca
    }

    public String getNumeroTarjeta() {
        return numeroTarjeta; //00013423: Metodo getter para acceder al campo numeroTarjeta
    }

    public void setNumeroTarjeta(String numeroTarjeta) {
        this.numeroTarjeta = numeroTarjeta; //00013423: Metodo setter para darle un valor al campo numeroTarjeta
    }

    public String getFechaExpiracion() {
        return fechaExpiracion; //00013423: Metodo getter para acceder al campo fechaExpiracion
    }

    public void setFechaExpiracion(String fechaExpiracion) {
        this.fechaExpiracion = fechaExpiracion; //00013423: Metodo setter para darle un valor al campo fechaExpiracion
    }

    public String getTipo() {
        return tipo; //00013423: Metodo getter para acceder al campo tipo
    }

    public void setTipo(String tipo) {
        this.tipo = tipo; //00013423: Metodo setter para darle un valor al campo tipo
    }

    public String getFacilitador() {
        return facilitador; //00013423: Metodo getter para acceder al campo facilitador
    }

    public void setFacilitador(String facilitador) {
        this.facilitador = facilitador; //00013423: Metodo setter para darle un valor al campo facilitador
    }

    public Cliente getFK_idCliente() {
        return FK_idCliente; //00013423: Metodo getter para acceder al campo FK_idCliente
    }

    public void setFK_idCliente(Cliente FK_idCliente) {
        this.FK_idCliente = FK_idCliente; //00013423: Metodo setter para darle un valor al campo FK_idCliente
    }

    public String getNumeroTarjetaCensurado() {
        if (numeroTarjeta.length() > 4) {
            return "**** **** **** " + numeroTarjeta.substring(numeroTarjeta.length() - 4);
        } else {
            return numeroTarjeta; // En caso de que el número de tarjeta tenga menos de 4 dígitos
        }
    }
}
