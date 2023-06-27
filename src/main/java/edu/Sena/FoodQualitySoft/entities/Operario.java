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
@Table(name = "Operarios")
public class Operario implements Serializable {

    private static final long serialVersionUID = 5022341805021141326L;


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "operario_id")
    private Long operarioId;

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

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "produccion_id", nullable = false)
    private Produccion produccion;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "Empaque_id", nullable = false)
    private Empaque empaque;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "inventario_id", nullable = false)
    private Inventario inventario;


    //NORMALIZAR ESTA TABLA Y LA DE LIDERES POR UNA QUE DIGA EMPLEADOS

}
