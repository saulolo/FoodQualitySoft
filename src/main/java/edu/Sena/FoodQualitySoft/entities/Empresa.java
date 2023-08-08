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
@Table(name = "Empresas")
public class Empresa implements Serializable { //si agregamos la palabra abstract quiere decir que no podemos
    // instanciar objetos de la clase Empresa pero si de sus derivados, y sus clases derivadss deben de incluir
    // en su denominación la palabra extends y la clase heredada no es necesario que la serialicemos
    //Y para traer los atributos en la otra clase, de la clase heredada (Empresa) lo hago con los constructores


    private static final long serialVersionUID = 5022341805021141326L;


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "empresa_id")
    private Long empresaId;

    @Column(name = "nit", nullable = false, length = 30, unique = true)
    private String Nit;

    @Column(name = "nombre_empresa", nullable = false, length = 30)
    private String nombreEmpresa;

    @Column(name = "direccion", nullable = false, length = 50)
    private String direccion;

    @Column(name = "telefono", nullable = false, length = 10)
    private String telefono;

    @Column(name = "email", nullable = false, length = 50, unique = true)
    private String email;

    @Column(name = "categoria_alimentos", nullable = false, length = 30)
    private String categoriaAlimentos;

}
