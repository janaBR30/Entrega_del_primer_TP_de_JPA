package com.utn.ejer01.entidades;

import com.utn.ejer01.enums.Estado;
import com.utn.ejer01.enums.TipoEnvio;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Pedido implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private TipoEnvio TIPODEENVIO;
    private Estado ESTADO;
    @Temporal(TemporalType.DATE)
    private Date fecha;

    private Double total;
    @OneToOne(cascade = CascadeType.ALL)
    private Factura factura;


    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @JoinColumn(name = "pedido_id")
    @Builder.Default
    private List<DetallePedido> detallePedidos = new ArrayList<>();
}
