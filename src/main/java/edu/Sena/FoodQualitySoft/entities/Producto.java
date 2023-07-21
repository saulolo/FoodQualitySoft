package edu.Sena.FoodQualitySoft.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Productos")
public class Producto implements Serializable {

    private static final long serialVersionUID = 5022341805021141326L;


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "producto_id")
    private Long productoId;

    @Column(name = "referencia", nullable = false)
    private Integer referencia;

    @Column(name = "nombre_producto", nullable = false, length = 30)
    private String nombreProducto;

    @Column(name = "gramaje", nullable = false)
    private Double gramaje;

    @Column(name = "descripcion", nullable = false, length = 600)
    private String descripcion;

    /*fetch = FetchType.LAZY ==> Si trabajamos con esta propiedad, no nos ocupa tanto espacio en memoria,
    pero me trae un error de serialización, el cual para ignorarlo debo de poner la siguiente anotación:
    @JsonProperty(access = Access.WRITE_ONLY)*/
    @ManyToOne(fetch = FetchType.EAGER)//No se pone nullable = false,porque lo asume por defecto
    @JoinColumn(name = "empresa_id")
    private Empresa empresa;


}
