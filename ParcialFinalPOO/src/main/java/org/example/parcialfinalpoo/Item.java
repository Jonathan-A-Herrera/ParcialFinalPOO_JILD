package org.example.parcialfinalpoo;

// Clase que representa un elemento con ID, nombre y descripcion
public class Item {
    private String id; // ID del elemento
    private String name; // Nombre del elemento
    private String description; // Descripcion del elemento

    // Constructor que inicializa los atributos del elemento
    public Item(String id, String name, String description) {
        this.id = id; // Asigna el ID
        this.name = name; // Asigna el nombre
        this.description = description; // Asigna la descripcion
    }

    // Metodo para obtener el ID del elemento
    public String getId() {
        return id;
    }

    // Metodo para establecer el ID del elemento
    public void setId(String id) {
        this.id = id;
    }

    // Metodo para obtener el nombre del elemento
    public String getName() {
        return name;
    }

    // Metodo para establecer el nombre del elemento
    public void setName(String name) {
        this.name = name;
    }

    // Metodo para obtener la descripción del elemento
    public String getDescription() {
        return description;
    }

    // Metodo para establecer la descripción del elemento
    public void setDescription(String description) {
        this.description = description;
    }
}
