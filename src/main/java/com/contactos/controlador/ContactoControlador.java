package com.contactos.controlador;

import com.contactos.modelo.Contacto;
import com.contactos.servicio.IContactoServicio;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ContactoControlador {


    private static final Logger logger =
            LoggerFactory.getLogger(ContactoControlador.class);


    @Autowired
    IContactoServicio contactoServicio;


    @GetMapping("/")
    public String iniciar(ModelMap modelo){

        List<Contacto> contactos = contactoServicio.listarContactos();
        contactos.forEach((contacto) -> logger.info(contacto.toString()));

        //pasando a la vista se usa el put porque utiliza el hashtable
        modelo.put("contactos", contactos);
        return "index";  //index.html
    }

    @GetMapping("/agregar")
    public String mostrarAgregar(){
        return "agregar";
    }


    //contactoForma = nombre del objeto que rcibimos del formulario
    //Contacto contacto = es el objeto de tipo contacto
    @PostMapping("/agregar")
    public String agregar(@ModelAttribute("contactoForma") Contacto contacto){
        logger.info("Contacto a agregar: " +  contacto);
        contactoServicio.guardarContacto(contacto);
        return "redirect:/";
        //redigimos al controlador el path "/" iniciar
        //se manda a llamar el metodo de inicio se vuelve a recargar el lisatdo de contactos
        // y recarga de nuevos los objetos con la nueva inforamacio en BD

        //return "/index";  no recarga los objetos de que ya se agregaron a la base de datos


    }


    @GetMapping("/editar/{id}")
    public String mostarEditar(@PathVariable(value= "id") int idContacto, ModelMap modelo){
        Contacto contacto = contactoServicio.buscarContactoPorId(idContacto);
        logger.info("contacto a editar (mostar):  " + contacto);
        modelo.put("contacto", contacto);
        return "editar";
    }


    @PostMapping("/editar")
    public String editar(@ModelAttribute("contacto") Contacto contacto){
        logger.info("contacto a guardar (editar): " + contacto);
        contactoServicio.guardarContacto(contacto);

        //el redirect es ara recargar de nuevo la lista nueva actualizacion
        //redirigimos al controlador al path "/" de inicio
        return "redirect:/";
    }


    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable(value= "id") int idContacto){

        Contacto contacto = new Contacto();
        contacto.setIdContacto(idContacto);
        logger.info("contacto eliminado (eliminar por id)  : " + idContacto);
        contactoServicio.eliminarContacto(contacto);

        return "redirect:/";  //redirigimos al controlador al path "/" de inicio

    }

}
