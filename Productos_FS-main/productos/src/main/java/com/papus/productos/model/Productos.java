package com.papus.productos.model;

import jakarta.persistence.*; //Para conectar con MySQL
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity // Marca esta clase como una entidad de base de datos
@Table(name = "Productos") // El nombre de la tabla será "Productos"
@Getter @Setter // Genera automáticamente getters y setters (Lombok)
@NoArgsConstructor // Genera un constructor vacío

public class Productos {

    @Id // Marca este campo como la clave primaria
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false) 
    private String nombre;

    @Column(nullable = false, unique = false) 
    private Integer precio;

    @Column(nullable = false) 
    private Integer cantidad;

    @Column(nullable = false) 
    private String descripcion;
}