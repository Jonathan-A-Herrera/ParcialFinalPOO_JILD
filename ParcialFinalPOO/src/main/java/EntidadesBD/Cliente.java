
package EntidadesBD;

public class Cliente {
    private int ID_Cliente; //00013423: Variable que se usara como PK de Cliente
    private String nombre; //00013423: Variable nombre que se relaciona al campo Nombre de la BD
    private String direccion; //00013423: Variable direccion que se relaciona al campo Direccion de la BD
    private String telefono; //00013423: Variable telefono que se relaciona al campo Telefono de la BD
    private double monto; //00013423: variables que ayudaran a hacer los joins entre consultas para darle valores a la columna monto
    private String fechaCompra; //00013423: variable que ayudara a hacer los join entre consulta para darle valores a la columna fecha
    private int cantidadCompras; //00085720 : variable que ayudara a hacer los join entre consulta para darle valores a la columna cantidad
    private String facilitador;

    public Cliente() {} //00013423: Constructor vacio para la instanciacion de objetos sin inicializar sus variables

    // 00085720 Constructor de la clase Cliente
    public Cliente(int id, String nombre, int cantidadCompras,String facilitador) {
        this.ID_Cliente = id;
        this.nombre = nombre;
        this.cantidadCompras = cantidadCompras;
        this.facilitador = facilitador;
    }
    public Cliente(int ID_Cliente, String nombre, String direccion, String telefono,double monto, String fechaCompra) { ///00013423: Constructor para crear una instancia con sus variables inicializadas
        this.ID_Cliente = ID_Cliente;
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefono = telefono;
    }

    public Cliente(int ID_Cliente, String nombre, double monto, String fechaCompra){ //00013423: Builder para hacer la consulta del reporte A
        this.ID_Cliente = ID_Cliente;
        this.nombre = nombre;
        this.monto = monto;
        this.fechaCompra = fechaCompra;
    }

    public int getID_Cliente() {
        return ID_Cliente; //00013423: Metodo getter para acceder al campo id
    }

    public void setID_Cliente(int ID_Cliente) {
        this.ID_Cliente = ID_Cliente; //00013423: Metodo setter para darle un valor al campo id
    }

    public String getNombre() {
        return nombre; //00013423: Metodo getter para acceder al campo nombre
    }

    public void setNombre(String nombre) {
        this.nombre = nombre; //00013423: Metodo setter para darle un valor al campo nombre
    }

    public String getDireccion() {
        return direccion; //00013423: Metodo getter para acceder al campo direccion
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion; //00013423: Metodo setter para darle un valor al campo direccion
    }

    public String getTelefono() {
        return telefono; //00013423: Metodo getter para acceder al campo telefono
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono; //00013423: Metodo setter para darle un valor al campo telefono
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public String getFechaCompra() {
        return fechaCompra;
    }

    public void setFechaCompra(String fechaCompra) {
        this.fechaCompra = fechaCompra;
    }
    public int getCantidadCompras() {
        return cantidadCompras;
    }

    public void setCantidadCompras(int cantidadCompras) {
        this.cantidadCompras = cantidadCompras;
    }

    public String getFacilitador() {
        return facilitador;
    }

    public void setFacilitador(String facilitador) {
        this.facilitador = facilitador;
    }
}