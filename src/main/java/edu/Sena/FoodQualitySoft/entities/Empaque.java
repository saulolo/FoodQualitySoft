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
@Table(name = "Empaques")
public class Empaque implements Serializable {

    private static final long serialVersionUID = 5022341805021141326L;


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "Empaque_id")
    private Long EmpaqueId;

    @Column(name = "fecha_empaque", nullable = false)
    private LocalDateTime fechaEmpaque;

    @Column(name = "fecha_vencimiento", nullable = false)
    private LocalDate fechaVencimiento;

    @Column(name = "unidades_empacadas", nullable = false)
    private Integer unidadesEmpacadas;

    @Column(name = "unidades_averias", nullable = false)
    private Integer unidadesAverias;

    @Column(name = "temperatura_sellado", nullable = false)
    private Double temperaturaSellado;

    @Column(name = "peso_neto", nullable = false)
    private Double pesoNeto;

    @Column(name = "observaciones", length = 600)
    private String observaciones;

    @OneToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "produccion_id", nullable = false)
    private Produccion produccion;


}
