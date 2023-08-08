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
@Table(name = "Carnets")
public class Carnet implements Serializable {

    private static final long serialVersionUID = 5022341805021141326L;


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "carnetId")
    private Long carnetId;

    @Column(name = "codigo", nullable = false, unique = true)
    private Integer codigo;

    @Column(name = "documento", nullable = false, length = 20, unique = true)
    private String documento;

    @OneToOne
    @JoinColumn(name = "vendedor_id", foreignKey = @ForeignKey(name = "FK_ID_VENDEDOR"))
    private Vendedor vendedor;




}
