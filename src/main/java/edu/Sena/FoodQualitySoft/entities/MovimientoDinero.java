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
@Table(name = "Movimientos")
public class MovimientoDinero implements Serializable {


    private static final long serialVersionUID = 5022341805021141326L;


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "movimiento_id")
    private Long movimientoId;

    @Column(name = "monto", nullable = false)
    private Long monto;

    @Column(name = "concepto", nullable = false, length = 600)
    private String concepto;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "vendedor_id")
    private Vendedor vendedor;

}
