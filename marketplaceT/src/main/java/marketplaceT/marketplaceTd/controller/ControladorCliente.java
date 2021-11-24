/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package marketplaceT.marketplaceTd.controller;

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
import marketplaceT.marketplaceTd.modelo.rol;
import marketplaceT.marketplaceTd.modelo.tienda;
import marketplaceT.marketplaceTd.modelo.usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author PC
 */
@Controller
@RequestMapping
public class ControladorCliente {
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
    private tienda tiendacali;
    
    //REGISTRO DE CLIENTES      -------------------------------------------------------------------------
    @GetMapping("/registrocliente")
    public String crearcliente(Model model) {

        List<provincia> listarprovincia = provinciaService.listar();
        List<distrito> listardistrito = distritoService.listar();
//        usuario usuario = new usuario();
        //si es vendedor su tienda
        tienda tienda = new tienda();
        persona persona = new persona();
        
        
        model.addAttribute("titulo", "Formulario: Nuevo Cliente");
        model.addAttribute("provinciascrear", listarprovincia);
        model.addAttribute("distritoscrear", listardistrito);
        model.addAttribute("tienda", tienda);
        model.addAttribute("cliente", persona);

        return "frmRegistrocliente";
    }

    @GetMapping("/registrovendedor")
    public String crearvendedor(Model model) {
        List<provincia> listarprovincia = provinciaService.listar();
        List<distrito> listardistrito = distritoService.listar();

        usuario usuario = new usuario();
        tienda tienda = new tienda();
        persona persona = new persona();
        
        model.addAttribute("provinciascrear", listarprovincia);
        model.addAttribute("distritoscrear", listardistrito);

        model.addAttribute("titulo", "Formulario: Nuevo Cliente");
        model.addAttribute("tienda", tienda);
        model.addAttribute("cliente", persona);

        return "frmRegistrovendedor";
    }

    @PostMapping("/savepersonacliente")
    public String guardarcliente(@Valid @ModelAttribute persona persona, BindingResult result,
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
        persona.setTipopersona(tipopersonaService.listarId(3));
        //agrega persona a base de datos
        personaService.save(persona);
        System.out.println("Cliente guardado con exito!");
        attribute.addFlashAttribute("success", "Cliente guardado con exito,su usuario y contraseña es su DNI!");
        return "redirect:/home";
    }

    @PostMapping("/savepersonavendedor")
    public String guardarvemdedor(@Valid @ModelAttribute persona persona, BindingResult result,
            Model model, RedirectAttributes attribute) {

        if (result.hasErrors()) {
            model.addAttribute("titulo", "Formulario: Nuevo Cliente");
            model.addAttribute("cliente", persona);
            // model.addAttribute("ciudades", listCiudades);
            System.out.println("Existieron errores en el formulario");
            return "frmCrear";
        }

        //////////////////AGREGA USUARIO////////////////////////////////// 
        usuario usu = new usuario(usuarioService.listar().size() + 1, String.valueOf(persona.getDni()), String.valueOf(persona.getDni()), 1);

///////////////////////////AGREGA PERSONA/////////////////////////////////////////////
        persona.setUsuario(usu);

        usuarioService.save(usu);
        System.out.println(usuarioService.listarId(usuarioService.listar().size()).toString());
        //agrega rol
        persona.setRol(rolService.listarId(2));
        //agrega persona a base de datos
        System.out.println(persona.toString());
        //AGREGANDO TIENDA DE PERSONA
        persona.getTienda().setId(tiendaService.listar().size() + 1);
        persona.getTienda().setEstado(1);
        tienda tienda = new tienda(persona.getTienda().getId(), persona.getTienda().getNombre(),
                persona.getTienda().getTelefono(),
                persona.getTienda().getDescripcion(),
                persona.getTienda().getLatitud(),
                persona.getTienda().getLongitud(),
                persona.getTienda().getEstado(),
                persona.getTienda().getDireccion(),
                persona.getTienda().getDistrito());

        tiendaService.save(tienda);
        System.out.println(tienda.toString());
        //agrega tipopersona
        persona.setTipopersona(tipopersonaService.listarId(2));
        personaService.save(persona);
        System.out.println("Cliente guardado con exito!");
        attribute.addFlashAttribute("success", "Cliente guardado con exito,su usuario y contraseña es su DNI");
        return "redirect:/home";
    }
    //------------------------------------------------------------------------------------------------

}
