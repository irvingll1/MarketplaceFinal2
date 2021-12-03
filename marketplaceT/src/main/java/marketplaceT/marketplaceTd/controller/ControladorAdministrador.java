/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package marketplaceT.marketplaceTd.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import javax.validation.Valid;
import marketplaceT.marketplaceTd.interfaceservice.IPersonaService;
import marketplaceT.marketplaceTd.interfaceservice.IRolService;
import marketplaceT.marketplaceTd.interfaceservice.ITipoPersonaService;
import marketplaceT.marketplaceTd.interfaceservice.IUsuarioService;
import marketplaceT.marketplaceTd.interfaceservice.IatencionpedidoService;
import marketplaceT.marketplaceTd.interfaceservice.IcalificacionService;
import marketplaceT.marketplaceTd.interfaceservice.IcategoriaproductoService;
import marketplaceT.marketplaceTd.interfaceservice.IdetallepedidoService;
import marketplaceT.marketplaceTd.interfaceservice.IdistritoService;
import marketplaceT.marketplaceTd.interfaceservice.IpedidoService;
import marketplaceT.marketplaceTd.interfaceservice.IproductoService;
import marketplaceT.marketplaceTd.interfaceservice.IprovinciaService;
import marketplaceT.marketplaceTd.interfaceservice.ItiendaService;
import marketplaceT.marketplaceTd.interfaceservice.ItipopagoService;
import marketplaceT.marketplaceTd.modelo.distrito;
import marketplaceT.marketplaceTd.modelo.persona;
import marketplaceT.marketplaceTd.modelo.provincia;
import marketplaceT.marketplaceTd.modelo.tienda;
import marketplaceT.marketplaceTd.modelo.usuario;
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
public class ControladorAdministrador {
    @Autowired
    private IPersonaService personaService;
    
    @Autowired
    private ITipoPersonaService tipopersonaService;
    
    @Autowired
    private IUsuarioService usuarioService;

    @Autowired
    private IRolService rolService;

    @Autowired
    private IprovinciaService provinciaService;

    @Autowired
    private IdistritoService distritoService;

    @Autowired
    private ItiendaService tiendaService;

    @Autowired
    private ItipopagoService tipopagoService;

    @Autowired
    private IproductoService productoService;

    @Autowired
    private IcategoriaproductoService categoriaproductoService;
    
    @Autowired
    private IpedidoService pedidoService;
    
    @Autowired
    private IdetallepedidoService detallepedidoService;
    
    @Autowired
    private IatencionpedidoService atencionpedidoService;
    
    @Autowired
    private IcalificacionService calificacionService;
     
    private List<persona> listaper;
    
    @Secured("ROLE_ADMIN")
    @GetMapping("/listarpersonas")
    public String listarPersonas(Model model,Principal principal) {
        List<persona> listadoPersonas = personaService.listar();
        listaper = personaService.buscarnombre(principal.getName());
        model.addAttribute("objetopersona", listaper.get(0).getNombre());
        model.addAttribute("personas", listadoPersonas);
        return "listarp";
    }
    @Secured("ROLE_ADMIN")
    @GetMapping("/listarusuarios")
    public String listarUsuarios(Model model,Principal principal) {
        List<usuario> listadoUsuario = usuarioService.listar();
        listaper = personaService.buscarnombre(principal.getName());
        model.addAttribute("objetopersona", listaper.get(0).getNombre());
        model.addAttribute("usuarios", listadoUsuario);
        return "listaru";
    }
    @Secured("ROLE_ADMIN")
    @GetMapping("/listartiendas")
    public String listarTiendas(Model model,Principal principal) {
        List<tienda> listadoTiendas = tiendaService.listar();
        listaper = personaService.buscarnombre(principal.getName());
        model.addAttribute("objetopersona", listaper.get(0).getNombre());
        model.addAttribute("tiendas", listadoTiendas);
        return "listart";
    }
    @Secured("ROLE_ADMIN")
    @GetMapping("/listarRepartidores")
    public String listarRepartidores(Model model,Principal principal) {
        List<persona> listadoPersonas = personaService.listar();
        List<persona> Repartidores= new ArrayList();
        listaper = personaService.buscarnombre(principal.getName());
        model.addAttribute("objetopersona", listaper.get(0).getNombre());
        for (int i = 0; i < listadoPersonas.size(); i++) {
            if (listadoPersonas.get(i).getTipopersona().getNombre().equals("Repartidor")) {
                Repartidores.add(listadoPersonas.get(i));
            }
        }
        
        
        model.addAttribute("personas", Repartidores);
        return "listarr";
    }

    @Secured("ROLE_ADMIN")
    @GetMapping("/create")
    public String crear(Model model) {

        usuario usuario = new usuario();

        model.addAttribute("titulo", "Formulario: Nuevo Usuario");
        model.addAttribute("usuario", usuario);

        return "frmCrear";
    }
    @Secured("ROLE_ADMIN")
    @GetMapping("/createRepartidor")
    public String crearRepartidor(Model model) {

        List<provincia> listarprovincia = provinciaService.listar();
        List<distrito> listardistrito = distritoService.listar();
        persona persona = new persona();    

        model.addAttribute("provinciascrear", listarprovincia);
        model.addAttribute("distritoscrear", listardistrito);
        model.addAttribute("cliente", persona);

        return "frmRegistroRepartidor";
    }
    @PostMapping("/savepersonarepartidor")
    public String guardarrepartidor(@Valid @ModelAttribute persona persona, BindingResult result,
            Model model, RedirectAttributes attribute) {

        if (result.hasErrors()) {
            model.addAttribute("titulo", "Formulario: Nuevo Cliente");
            model.addAttribute("cliente", persona);
            System.out.println(persona.toString());
            System.out.println("Existieron errores en el formulario");
            return "index";
        }
//.................................
        //////////////////AGREGA USUARIO////////////////////////////////// 

        usuario usu = new usuario(usuarioService.listar().size() + 1, String.valueOf(persona.getDni()), String.valueOf(persona.getDni()), 1);

///////////////////////////AGREGA PERSONA/////////////////////////////////////////////
        persona.setUsuario(usu);
        System.out.println(persona.toString());
        usuarioService.save(usu);
        System.out.println(usuarioService.listarId(usuarioService.listar().size()).toString());
        //agrega rol
        persona.setRol(rolService.listarId(3));
        //agrega tipopersona
        persona.setTipopersona(tipopersonaService.listarId(4));
        //agrega persona a base de datos
        personaService.save(persona);
        System.out.println("Repartidor guardado con exito!");
        
        return "redirect:/listarRepartidores";
    }

    @Secured("ROLE_ADMIN")
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

    @Secured({"ROLE_GUESS", "ROLE_ADMIN"})
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
        } else {
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
        } else {
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
