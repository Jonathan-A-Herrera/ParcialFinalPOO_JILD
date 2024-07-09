package org.example.parcialfinalpoo;

// 00085720 Clase que representa un elemento con ID, nombre y descripcion
public class Item {
    private String id; // 00085720 ID del elemento
    private String name; // 00085720 Nombre del elemento
    private String description; // 00085720 Descripcion del elemento

    // 00085720 Constructor que inicializa los atributos del elemento
    public Item(String id, String name, String description) {
        this.id = id; // 00085720 Asigna el ID
        this.name = name; // 00085720 Asigna el nombre
        this.description = description; // 00085720 Asigna la descripcion
    }

    // 00085720 Metodo para obtener el ID del elemento
    public String getId() {
        return id;
    }

    // 00085720 Metodo para establecer el ID del elemento
    public void setId(String id) {
        this.id = id;
    }

    // 00085720 Metodo para obtener el nombre del elemento
    public String getName() {
        return name;
    }

    // 00085720 Metodo para establecer el nombre del elemento
    public void setName(String name) {
        this.name = name;
    }

    // 00085720 Metodo para obtener la descripción del elemento
    public String getDescription() {
        return description;
    }

    // 00085720 Metodo para establecer la descripción del elemento
    public void setDescription(String description) {
        this.description = description;
    }
}