package mx.com.gm.web;

import java.util.*;
import java.util.stream.*;

import javax.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import mx.com.gm.domain.Persona;
import mx.com.gm.servicio.PersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model; //agrega informacion a compartir con la vista
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@Controller //controlador tipo mvc
@Slf4j //da acceso a log
public class ControladorInicio {

    @Autowired //similar a inject -- inyectamos persona dao
    private PersonaService personaService;

    @GetMapping("/")
    public String inicio(Model model, @AuthenticationPrincipal User user) { //autentication nos dice quien inicia sesion
//        Persona personas = (Persona) personaDao.findAll();
        Iterable<Persona> personas = personaService.listarPersonas();
        log.info("Ejecutando controlador Spring MVC");
        log.info("Usuario que inicio sesion: " + user);
        model.addAttribute("personas", personas);

        Double saldoTotal = 0D;
        for (Persona p : personas) {
            saldoTotal += p.getSaldo();
        }
        model.addAttribute("saldoTotal", saldoTotal);
        //model.addAttribute("totalClientes", personas.size());  //original del curso

        //chat
        List<Persona> personasList = StreamSupport.stream(personas.spliterator(), false).collect(Collectors.toList());
        model.addAttribute("totalClientes", personasList.size());

        return "index";//nombre de pagina web extension html se crea en templates
    }

    @GetMapping("/agregar")
    public String agregar(Persona persona) {
        return "modificar";
    }

    @PostMapping("/guardar")
    public String guardar(@Valid @ModelAttribute("persona") Persona persona, Errors errores) {
        if (errores.hasErrors()) {  //Esto es por si haz errores
            return "modificar";
        }
//    public String guardar(@Valid Persona persona){
        personaService.guardar(persona);
        return "redirect:/";
    }

    @GetMapping("/editar/{id_persona}")
    public String editar(Persona persona, Model model) { //no hace falta set ni inicializar; si existe el objeto lo inyecta
        persona = personaService.encontrarPersona(persona);
        model.addAttribute("persona", persona);
        return "modificar";
    }

    @GetMapping("/eliminar/{id_persona}")
    public String eliminar(Persona persona) {
        personaService.eliminar(persona);
        return "redirect:/";
    }
}
