/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package marketplaceT.marketplaceTd.controller;


import marketplaceT.marketplaceTd.modelo.usuario;
import java.util.List;
import javax.validation.Valid;

import marketplaceT.marketplaceTd.interfaceservice.IUsuarioService;
import marketplaceT.marketplaceTd.modelo.rol;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author PC
 */
@Controller
@RequestMapping
public class UsuarioController {
//    @Autowired
//    private IPersonaService personaService;

    @Autowired
    private IUsuarioService usuarioService;
    
//    @Autowired
//    private IRolService rolService;
    @Secured("ROLE_USER")
    @GetMapping("/listar")
    public String listarClientes(Model model) {
            List<usuario> listadoClientes = usuarioService.listar();
            //List<rol> listadoRol=rolService.listar();

            model.addAttribute("titulo", "Lista de Usuarios");
            model.addAttribute("usuarios", listadoClientes);

            return "/listar";
    }
    @Secured("ROLE_ADMIN")
    @GetMapping("/create")
    public String crear(Model model) {

            usuario usuario = new usuario();
            model.addAttribute("titulo", "Formulario: Nuevo Usuario");
            model.addAttribute("usuario", usuario);

            return "frmCrear";
    }
    
    @Secured({"ROLE_ADMIN","ROLE_USER"})
    @PostMapping("/save")
    public String guardar(@Valid @ModelAttribute usuario usuario, BindingResult result,
                    Model model, RedirectAttributes attribute) {
            //List<usuario> listCiudades = usuarioService.listar();

            if (result.hasErrors()) {
                    model.addAttribute("titulo", "Formulario: Nuevo usuario");
                    model.addAttribute("usuario", usuario);
                    System.out.println("Existieron errores en el formulario");			
                    return "frmCrear";
            }

            usuarioService.save(usuario);
            System.out.println("Cliente guardado con exito!");
            attribute.addFlashAttribute("success", "Cliente guardado con exito!");
            return "redirect:/listar";
    }
    @Secured({"ROLE_ADMIN","ROLE_USER"})
    @GetMapping("/edit/{id}")
    public String editar(@PathVariable("id") int idUsuario, Model model, RedirectAttributes attribute) {

            usuario usuario = null;

            if (idUsuario > 0) {
                    usuario = usuarioService.listarId(idUsuario);

                    if (usuario == null) {
                            System.out.println("Error: El ID del cliente no existe!");
                            attribute.addFlashAttribute("error", "ATENCION: El ID del cliente no existe!");
                            return "redirect:/listar";
                    }
            }else {
                    System.out.println("Error: Error con el ID del Cliente");
                    attribute.addFlashAttribute("error", "ATENCION: Error con el ID del cliente");
                    return "redirect:/listar";
            }

           // List<usuario> listCiudades = usuarioService.listar();

            model.addAttribute("titulo", "Formulario: Editar Cliente");
            model.addAttribute("usuario", usuario);
          //  model.addAttribute("ciudades", listCiudades);

            return "frmCrear";
    }
    
    @Secured("ROLE_ADMIN")
    @GetMapping("/delete/{id}")
    public String eliminar(@PathVariable("id") int idUsuario, RedirectAttributes attribute) {

            usuario usuario = null;

            if (idUsuario > 0) {
                    usuario = usuarioService.listarId(idUsuario);

                    if (usuario == null) {
                            System.out.println("Error: El ID del cliente no existe!");
                            attribute.addFlashAttribute("error", "ATENCION: El ID del usuario no existe!");
                            return "redirect:/listar";
                    }
            }else {
                    System.out.println("Error: Error con el ID del Cliente");
                    attribute.addFlashAttribute("error", "ATENCION: Error con el ID del Cliente!");
                    return "redirect:/listar";
            }		

            usuarioService.delete(idUsuario);
            System.out.println("Registro Eliminado con Exito!");
            attribute.addFlashAttribute("warning", "Registro Eliminado con Exito!");

            return "redirect:/listar";
    }
}
