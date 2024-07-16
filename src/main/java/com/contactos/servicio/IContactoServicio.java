package com.contactos.servicio;

import com.contactos.modelo.Contacto;

import java.util.List;

public interface IContactoServicio {

    public List<Contacto> listarContactos();

    public Contacto buscarContactoPorId(Integer idContacto);

    //actualiza o  regsitrar un registro
    public void guardarContacto(Contacto contacto);

    public void eliminarContacto(Contacto contacto);



}
