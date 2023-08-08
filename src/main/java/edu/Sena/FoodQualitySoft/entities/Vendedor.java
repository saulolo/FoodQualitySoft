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
@Table(name = "Vendedores")
public class Vendedor implements Serializable {

    private static final long serialVersionUID = 5022341805021141326L;


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "vendedor_id")
    private Long vendedorId;

    @Column(name = "nombre", nullable = false, length = 30)
    private String nombre;

    @Column(name = "apellido", nullable = false, length = 30)
    private String apellido;

    @Column(name = "documento", nullable = false, length = 20, unique = true)
    private String documento;

    @Column(name = "edad", nullable = false)
    private Integer edad;

    @Column(name = "telefono", nullable = false, length = 10)
    private String telefono;

    @Column(name = "fecha_nacimiento", nullable = false)
    private LocalDateTime fechaNacimiento;

    @Column(name = "cargo", nullable = false, length = 30)
    private String cargo;

    @Column(name = "fecha_ingreso", nullable = false)
    private LocalDateTime fechaIngreso;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "empresa_id", foreignKey = @ForeignKey(name = "FK_ID_EMPRESA"))
    private Empresa empresa;

    @OneToOne(mappedBy = "vendedor", cascade = CascadeType.ALL) //mappedBy = "vendedor" quiere decir que la otra entidad (carnet) es la dueña de la relación
    private Carnet carnet; //Si le ponemos el parametro CascadeType.ALL esto nos va a permitir hacer el save completo de los objetos
    //que si existe uno en un lado lo va a eliminar y lo va a persistir en caso de que no exista.*/



}
