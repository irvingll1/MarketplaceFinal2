/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package marketplaceT.marketplaceTd.controller;


import marketplaceT.marketplaceTd.modelo.usuario;
import java.util.List;
import javax.validation.Valid;
import marketplaceT.marketplaceTd.interfaceservice.IPersonaService;
import marketplaceT.marketplaceTd.interfaceservice.IRolService;
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
@RequestMapping("/views/clientes")
public class UsuarioController {
//    @Autowired
//    private IPersonaService personaService;

    @Autowired
    private IUsuarioService usuarioService;
    
//    @Autowired
//    private IRolService rolService;
    @Secured("ROLE_USER")
    @GetMapping("/")
    public String listarClientes(Model model) {
            List<usuario> listadoClientes = usuarioService.listar();
            //List<rol> listadoRol=rolService.listar();

            model.addAttribute("titulo", "Lista de Usuarios");
            model.addAttribute("clientes", listadoClientes);
            //model.addAttribute("roles", listadoRol);

            return "/views/clientes/listar";
    }
    @Secured("ROLE_ADMIN")
    @GetMapping("/create")
    public String crear(Model model) {

            usuario cliente = new usuario();
            //List<usuario> listCiudades = usuarioService.listar();

            model.addAttribute("titulo", "Formulario: Nuevo Cliente");
            model.addAttribute("cliente", cliente);
            //model.addAttribute("ciudades", listCiudades);

            return "/views/clientes/frmCrear";
    }
    @Secured({"ROLE_ADMIN","ROLE_USER"})
    @PostMapping("/save")
    public String guardar(@Valid @ModelAttribute usuario cliente, BindingResult result,
                    Model model, RedirectAttributes attribute) {
            //List<usuario> listCiudades = usuarioService.listar();

            if (result.hasErrors()) {
                    model.addAttribute("titulo", "Formulario: Nuevo Cliente");
                    model.addAttribute("cliente", cliente);
                   // model.addAttribute("ciudades", listCiudades);
                    System.out.println("Existieron errores en el formulario");			
                    return "/views/clientes/frmCrear";
            }

            usuarioService.save(cliente);
            System.out.println("Cliente guardado con exito!");
            attribute.addFlashAttribute("success", "Cliente guardado con exito!");
            return "redirect:/views/clientes/";
    }
    @Secured({"ROLE_ADMIN","ROLE_USER"})
    @GetMapping("/edit/{id}")
    public String editar(@PathVariable("id") int idCliente, Model model, RedirectAttributes attribute) {

            usuario cliente = null;

            if (idCliente > 0) {
                    cliente = usuarioService.listarId(idCliente);

                    if (cliente == null) {
                            System.out.println("Error: El ID del cliente no existe!");
                            attribute.addFlashAttribute("error", "ATENCION: El ID del cliente no existe!");
                            return "redirect:/views/clientes/";
                    }
            }else {
                    System.out.println("Error: Error con el ID del Cliente");
                    attribute.addFlashAttribute("error", "ATENCION: Error con el ID del cliente");
                    return "redirect:/views/clientes/";
            }

           // List<usuario> listCiudades = usuarioService.listar();

            model.addAttribute("titulo", "Formulario: Editar Cliente");
            model.addAttribute("cliente", cliente);
          //  model.addAttribute("ciudades", listCiudades);

            return "/views/clientes/frmCrear";
    }
    @Secured("ROLE_ADMIN")
    @GetMapping("/delete/{id}")
    public String eliminar(@PathVariable("id") int idCliente, RedirectAttributes attribute) {

            usuario cliente = null;

            if (idCliente > 0) {
                    cliente = usuarioService.listarId(idCliente);

                    if (cliente == null) {
                            System.out.println("Error: El ID del cliente no existe!");
                            attribute.addFlashAttribute("error", "ATENCION: El ID del cliente no existe!");
                            return "redirect:/views/clientes/";
                    }
            }else {
                    System.out.println("Error: Error con el ID del Cliente");
                    attribute.addFlashAttribute("error", "ATENCION: Error con el ID del Cliente!");
                    return "redirect:/views/clientes/";
            }		

            usuarioService.delete(idCliente);
            System.out.println("Registro Eliminado con Exito!");
            attribute.addFlashAttribute("warning", "Registro Eliminado con Exito!");

            return "redirect:/views/clientes/";
    }
}
