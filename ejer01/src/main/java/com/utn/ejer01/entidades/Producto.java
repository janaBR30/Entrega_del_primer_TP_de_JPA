package com.utn.ejer01.entidades;

import com.utn.ejer01.enums.TipoProducto;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Producto implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private TipoProducto TP;
    private Integer tiempoEstimadoCocina;
    private String denominacion;
    private Double precioVenta;

    private Double precioCompra;

    private Integer stockActual;
    private Integer stockMin;

    private String unidadVendida;

    private String receta;

}
