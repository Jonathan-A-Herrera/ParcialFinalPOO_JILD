package org.example.parcialfinalpoo;

public class Item {

    private final String id;
    private String name;
    private String description;
    private String facilitador;

    // 00085720 Constructor con parametros para inicializar las variables del item
    public Item(String id, String name, String description, String facilitador) {
        this.id = id; // 00085720: Asignar el ID del ítem
        this.name = name; // 00085720: Asignar el nombre del ítem
        this.description = description; // 00085720: Asignar la descripción del ítem
        this.facilitador = facilitador; // 00085720: Asignar el facilitador del ítem
    }

    // 00085720 Obtener el ID del item
    public String getId() {
        return id;
    }

    // 00085720 Obtener el nombre del item
    public String getName() {
        return name;
    }

    // 00085720 Asignar el nombre del item
    public void setName(String name) {
        this.name = name;
    }

    // 00085720 Obtener la descripción del item
    public String getDescription() {
        return description;
    }

    // 00085720 Asignar la descripción del item
    public void setDescription(String description) {
        this.description = description;
    }

    // 00085720 Obtener el facilitador del item
    public String getFacilitador() {
        return facilitador;
    }

    // 00085720 Asignar el facilitador del item
    public void setFacilitador(String facilitador) {
        this.facilitador = facilitador;
    }

    // 00085720: Representacion en forma de cadena del ítem
    @Override
    public String toString() {
        return "Item{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", facilitador='" + facilitador + '\'' +
                '}';
    }
}
