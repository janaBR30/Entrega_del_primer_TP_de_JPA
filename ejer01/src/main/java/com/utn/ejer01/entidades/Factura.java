package com.utn.ejer01.entidades;

import com.utn.ejer01.enums.FormaDePago;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Factura implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer numero;
    @Temporal(TemporalType.DATE)
    private Date fecha;
    private Double descuento;
    private FormaDePago formaDePago;
    private Double total;
}
