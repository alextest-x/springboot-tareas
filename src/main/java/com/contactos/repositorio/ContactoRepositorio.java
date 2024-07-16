package com.contactos.repositorio;

import com.contactos.modelo.Contacto;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ContactoRepositorio extends JpaRepository<Contacto, Integer> {

}
