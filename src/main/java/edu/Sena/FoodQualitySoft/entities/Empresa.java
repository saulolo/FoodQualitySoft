package edu.Sena.FoodQualitySoft.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.antlr.v4.runtime.misc.NotNull;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Empresas")
public class Empresa implements Serializable {

    private static final long serialVersionUID = 5022341805021141326L;


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "empresa_id")
    private Long empresaId;

    @Column(name = "nit", nullable = false, length = 30)
    private String Nit;

    @Column(name = "nombre_empresa", nullable = false, length = 30)
    private String nombreEmpresa;

    @Column(name = "direccion", nullable = false, length = 50)
    private String direccion;

    @Column(name = "telefono", nullable = false, length = 10)
    private String telefono;

    @Column(name = "email", nullable = false, length = 50)
    private String email;

    @Column(name = "categoria_alimentos", nullable = false, length = 30)
    private String categoriaAlimentos;

}
