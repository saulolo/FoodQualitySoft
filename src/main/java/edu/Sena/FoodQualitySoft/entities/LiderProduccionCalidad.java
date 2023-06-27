package edu.Sena.FoodQualitySoft.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Lideres_Produccion_calidad")
public class LiderProduccionCalidad implements Serializable {

    private static final long serialVersionUID = 5022341805021141326L;


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "lider_produccion_calidad_id")
    private Long liderProduccionCalidadId;

    @Column(name = "nombre", nullable = false, length = 30)
    private String nombre;

    @Column(name = "apellido", nullable = false, length = 30)
    private String apellido;

    @Column(name = "DNI", nullable = false, length = 20)
    private String DNI;

    @Column(name = "edad", nullable = false, length = 100)
    private Integer edad;

    @Column(name = "telefono", nullable = false, length = 10)
    private String telefono;

    @Column(name = "fecha_nacimiento", nullable = false)
    private LocalDate fechaNacimiento;

    @Column(name = "rol", nullable = false, length = 10)
    private String rol;

    @Column(name = "fecha_ingreso", nullable = false)
    private LocalDate fechaIngreso;

}
