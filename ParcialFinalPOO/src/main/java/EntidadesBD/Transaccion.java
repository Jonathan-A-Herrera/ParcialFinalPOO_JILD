
package EntidadesBD;

public class Transaccion {
    private int idTransaccion; //00013423: Variable idTransaccion que se usara como PK de Transaccion
    private String fechaCompra; //00013423: Variable fechaCompra que se relaciona al campo FechaCompra de la BD
    private float montoTotal; //00013423: Variable montoTotal que se relaciona al campo MontoTotal de la BD
    private String descripcion; //00013423: Variable descripcion que se relaciona al campo Descripcion de la BD
    private String numeroTarjeta; //00013423: Variable numeroTarjeta que se relaciona al campo NumeroTarjeta de la BD
    private Tarjeta FK_numeroDeTarjeta; //00013423: Variable FK_numeroDeTarjeta que se relaciona a la clave foránea de Tarjeta en la BD

    public Transaccion(int idTransaccion, String fechaCompra, float montoTotal, String descripcion, String numeroTarjeta, Tarjeta FK_numeroDeTarjeta) {
        this.idTransaccion = idTransaccion;
        this.fechaCompra = fechaCompra;
        this.montoTotal = montoTotal;
        this.descripcion = descripcion;
        this.numeroTarjeta = numeroTarjeta;
        this.FK_numeroDeTarjeta = FK_numeroDeTarjeta;
    } //00013423: Constructor para crear una instancia de Transaccion con todas las variables inicializadas

    public Transaccion() {} //00013423: Constructor vacío para crear una instancia de Transaccion sin inicializar variables

    public int getIdTransaccion() {
        return idTransaccion; //00013423: Metodo getter para acceder al campo idTransaccion
    }

    public void setIdTransaccion(int idTransaccion) {
        this.idTransaccion = idTransaccion; //00013423: Metodo setter para darle un valor al campo idTransaccion
    }

    public String getFechaCompra() {
        return fechaCompra; //00013423: Metodo getter para acceder al campo fechaCompra
    }

    public void setFechaCompra(String fechaCompra) {
        this.fechaCompra = fechaCompra; //00013423: Metodo setter para darle un valor al campo fechaCompra
    }

    public float getMontoTotal() {
        return montoTotal; //00013423: Metodo getter para acceder al campo montoTotal
    }

    public void setMontoTotal(float montoTotal) {
        this.montoTotal = montoTotal; //00013423: Metodo setter para darle un valor al campo montoTotal
    }

    public String getDescripcion() {
        return descripcion; //00013423: Metodo getter para acceder al campo descripcion
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion; //00013423: Metodo setter para darle un valor al campo descripcion
    }

    public String getNumeroTarjeta() {
        return numeroTarjeta; //00013423: Metodo getter para acceder al campo numeroTarjeta
    }

    public void setNumeroTarjeta(String numeroTarjeta) {
        this.numeroTarjeta = numeroTarjeta; //00013423: Metodo setter para darle un valor al campo numeroTarjeta
    }

    public Tarjeta getFK_numeroDeTarjeta() {
        return FK_numeroDeTarjeta; //00013423: Metodo getter para acceder al campo FK_numeroDeTarjeta
    }

    public void setFK_numeroDeTarjeta(Tarjeta FK_numeroDeTarjeta) {
        this.FK_numeroDeTarjeta = FK_numeroDeTarjeta; //00013423: Metodo setter para darle un valor al campo FK_numeroDeTarjeta
    }
}