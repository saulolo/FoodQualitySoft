package edu.Sena.FoodQualitySoft.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Produccion")
public class Produccion implements Serializable {

    private static final long serialVersionUID = 5022341805021141326L;


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "produccion_id")
    private Long produccionId;

    @Column(name = "fecha_produccion", nullable = false)
    private LocalDateTime fechaProduccion;

    @Column(name = "fecha_vencimiento", nullable = false)
    private LocalDate fechaVencimiento;

    @Column(name = "unidades_producidas", nullable = false)
    private Integer unidadesProducidas;

    @Column(name = "descripcion_olor", nullable = false, length = 600)
    private String descripcionOlor;

    @Column(name = "descripcion_color", nullable = false, length = 600)
    private String descripcionColor;

    @Column(name = "descripcion_sabor", nullable = false, length = 600)
    private String descripcionSabor;

    @Column(name = "descripcion_textura", nullable = false, length = 600)
    private String descripcionTextura;

    @Column(name = "temperatura_horno", nullable = false)
    private Double temperaturaHorno;

    @Column(name = "peso_producto", nullable = false)
    private Double pesoProducto;

    @Column(name = "observaciones", length = 600)
    private String observaciones;

    @OneToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "orden_produccion_id", nullable = false)
    private OrdenProduccion ordenProduccion;


}
