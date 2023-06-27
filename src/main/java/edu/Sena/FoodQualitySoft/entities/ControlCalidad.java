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
@Table(name = "Control_Calidad")
public class ControlCalidad implements Serializable {

    private static final long serialVersionUID = 5022341805021141326L;


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "control_calidad_id")
    private Long controlCalidad;

    @Column(name = "fecha_control", nullable = false)
    private LocalDateTime fechaControl;

    @Column(name = "lote", nullable = false)
    private Integer lote;

    @Column(name="verificacion_lote_requerida")
    private Boolean verificacionLoteRequerida;

    @Column(name="verificacion_peso_requerida")
    private Boolean verificacionPesoRequerida;

    @Column(name="verificacion_fecha_ven_requerida")
    private Boolean verificacionFechaVenRequerida;

    @Column(name="verificacion_hermeticidad_requerida")
    private Boolean verificacionHermeticidadRequerida;

    @Column(name="verificacion_impresion_requerida")
    private Boolean verificacionImpresionRequerida;

    @Column(name="verificacion_lyd_requerida")
    private Boolean verificacionLyDRequerida;

    @Column(name="verificacion_producto_conforme_requerida")
    private Boolean verificacionProductoConformeRequerida;

    @Column(name = "observaciones", length = 600)
    private String observaciones;

    @OneToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "Empaque_id", nullable = false)
    private Empaque empaque;

    @OneToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "lider_produccion_calidad_id", nullable = false)
    private LiderProduccionCalidad lider_produccion_calidad_id;


}
