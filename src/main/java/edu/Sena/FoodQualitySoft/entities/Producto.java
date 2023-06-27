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
    @GeneratedValue(strategy = GenerationType.AUTO)
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

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "empresa_id", nullable = false)
    private Empresa empresa;


}
