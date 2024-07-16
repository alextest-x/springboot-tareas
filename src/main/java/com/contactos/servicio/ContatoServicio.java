package com.contactos.servicio;

import com.contactos.modelo.Contacto;
import com.contactos.repositorio.ContactoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ContatoServicio implements IContactoServicio{


    @Autowired
    private ContactoRepositorio contactoRepositorio;


    @Override
    public List<Contacto> listarContactos() {
        return contactoRepositorio.findAll();
    }

    //Es un optional si lo encuetra trae un objeto de tipo contacto  el registro sino trae un valor nulo
    @Override
    public Contacto buscarContactoPorId(Integer idContacto) {
      Contacto contacto = contactoRepositorio.findById(idContacto).orElse(null);
      return contacto;
    }



    // guardamos el objeto de tipo contacto
    //id = null inserta registro
    //id = not null actualiza registro
    @Override
    public void guardarContacto(Contacto contacto) {
         contactoRepositorio.save(contacto);
    }


    @Override
    public void eliminarContacto(Contacto contacto) {
         contactoRepositorio.delete(contacto);
    }

}
