package com.utn.ejer01.repositorios;


import com.utn.ejer01.entidades.Factura;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FacturaRepository extends JpaRepository<Factura, Long> {
}
