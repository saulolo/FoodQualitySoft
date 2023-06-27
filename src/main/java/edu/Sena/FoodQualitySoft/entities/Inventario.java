package edu.Sena.FoodQualitySoft.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Inventario")
public class Inventario implements Serializable {

    private static final long serialVersionUID = 5022341805021141326L;


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "inventario_id")
    private Long inventarioId;

    @Column(name = "fecha_ingreso", nullable = false)
    private LocalDateTime fechaIngreso;

    @Column(name = "lote", nullable = false)
    private Integer lote;

    @Column(name = "cantidad", nullable = false)
    private Integer cantidad;

    @Column(name = "observaciones", length = 600)
    private String observaciones;

    @OneToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "control_calidad_id", nullable = false)
    private ControlCalidad controlCalidad ;

}
