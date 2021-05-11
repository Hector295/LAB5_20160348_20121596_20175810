package edu.pucp.gtics.lab5_gtics_20211.controller;

import edu.pucp.gtics.lab5_gtics_20211.entity.Juegos;
import edu.pucp.gtics.lab5_gtics_20211.entity.Plataformas;
import edu.pucp.gtics.lab5_gtics_20211.entity.User;
import edu.pucp.gtics.lab5_gtics_20211.repository.JuegosRepository;
import edu.pucp.gtics.lab5_gtics_20211.repository.PlataformasRepository;
import edu.pucp.gtics.lab5_gtics_20211.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/juegos")
public class JuegosController {

    @Autowired
    PlataformasRepository plataformasRepository;

    @Autowired
    JuegosRepository juegosRepository;

    @GetMapping( ... )
    public String listaJuegos ( ... ){
               /** Completar */
    }

    @GetMapping(value = {"", "/", "/vista"})
    public String vistaJuegos ( Model model ){
               /** Completar */
               return "juegos/vista";
    }

    @GetMapping("/nuevo")
    public String nuevoJuegos(Model model, @ModelAttribute("juego") Juegos juego){
        model.addAttribute("plataformasList",plataformasRepository.findAll());
        return "juegos/editarFrm";
    }

    @GetMapping("/editar" )
    public String editarJuegos(@RequestParam("id") int id, Model model,@ModelAttribute("juego") Juegos juegos){
        Optional<Juegos> juegosOptional= juegosRepository.findById(id);
        if (juegosOptional.isPresent()) {
            juegos=juegosOptional.get();
            model.addAttribute("juego", juegos);
            model.addAttribute("plataformasList",plataformasRepository.findAll());
            return "juegos/editarFrm";
        } else {
            return "redirect:/juegos";
        }

    }

    @PostMapping("/guardar")
    public String guardarJuegos(Model model, RedirectAttributes attr, @ModelAttribute("juego") @Valid Juegos juego, BindingResult bindingResult ){
        if(bindingResult.hasErrors()){
            model.addAttribute("plataformasList",plataformasRepository.findAll());
            return "juegos/editarFrm";
        }else {
            if (juego.getIdjuego() == 0) {
                attr.addFlashAttribute("msg", "Juego creado exitosamente");
            } else {
                attr.addFlashAttribute("msg", "Juego actualizado exitosamente");
            }
            juegosRepository.save(juego);
            return "redirect:/product";
        }

    }

    @GetMapping("/juegos/borrar")
    public String borrarDistribuidora(@RequestParam("id") int id){
        Optional<Juegos> opt = juegosRepository.findById(id);
        if (opt.isPresent()) {
            juegosRepository.deleteById(id);
        }
        return "redirect:/juegos/lista";
    }

}
