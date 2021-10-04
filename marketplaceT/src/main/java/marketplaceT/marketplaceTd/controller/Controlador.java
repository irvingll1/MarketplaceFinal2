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
import marketplaceT.marketplaceTd.interfaceservice.IcategoriaproductoService;
import marketplaceT.marketplaceTd.interfaceservice.IdireccionService;
import marketplaceT.marketplaceTd.interfaceservice.IdistritoService;
import marketplaceT.marketplaceTd.interfaceservice.IproductoService;
import marketplaceT.marketplaceTd.interfaceservice.IprovinciaService;
import marketplaceT.marketplaceTd.interfaceservice.ItiendaService;
import marketplaceT.marketplaceTd.interfaceservice.ItipopersonaService;
import marketplaceT.marketplaceTd.modelo.categoriaproducto;
import marketplaceT.marketplaceTd.modelo.direccion;
import marketplaceT.marketplaceTd.modelo.distrito;
import marketplaceT.marketplaceTd.modelo.persona;
import marketplaceT.marketplaceTd.modelo.producto;
import marketplaceT.marketplaceTd.modelo.provincia;
import marketplaceT.marketplaceTd.modelo.tienda;
import marketplaceT.marketplaceTd.modelo.tipopersona;
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
public class Controlador {
    @Autowired
    private IPersonaService personaService; 

    @Autowired
    private ItipopersonaService tipopersonaService;

    @Autowired
    private IUsuarioService usuarioService;
    
    @Autowired
    private IRolService rolService;

    @Autowired
    private IprovinciaService provinciaService;
    
    @Autowired
    private IdistritoService distritoService;
    
    @Autowired
    private IdireccionService direccionService;
    
    @Autowired
    private ItiendaService tiendaService;
    
    @Autowired
    private IproductoService productoService;
    
    @Autowired
    private IcategoriaproductoService categoriaproductoService;

    @Secured("ROLE_ADMIN")
    @GetMapping("/listar")
    public String listarClientes(Model model) {
            List<usuario> listadoClientes = usuarioService.listar();        
            model.addAttribute("titulo", "Lista de usuarios");
            model.addAttribute("usuarios", listadoClientes);
            return "listar";
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
    
    @Secured("ROLE_GUESS")
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
    
    //TIENDA Cliente!!!!!!!!!!
    @GetMapping("/tienda")
    public String crearTienda(Model model) {
        List<provincia>  listadoprovincia =provinciaService.listar();
        List<distrito>  listadodistrito =distritoService.listar();
        List<tienda>  listadotienda =tiendaService.listar();
        List<producto> listadoproducto = productoService.listar();
        model.addAttribute("provincias", listadoprovincia);
        model.addAttribute("distritos", listadodistrito);
        model.addAttribute("tiendas", listadotienda);
        model.addAttribute("productos", listadoproducto);
        return "frmtienda";
    }
    
    @GetMapping("/carrito")
    public String crearcarrito() {

        return "frmcarrito";
    }

    
    //Tienda VEndedor
    @Secured({"ROLE_GUESS","ROLE_ADMIN"})
    @GetMapping("/vendedor")
    public String adminVendedor(Model model) {
        List<producto> listadoproducto = productoService.listar();
        List<categoriaproducto> categoriaproducto = categoriaproductoService.listar();
        model.addAttribute("productos", listadoproducto);
        model.addAttribute("catproductos", categoriaproducto);
        return "frmVendedorProductos";
    }
    @Secured({"ROLE_GUESS","ROLE_ADMIN"})
    @GetMapping("/createproducto")
    public String crearproducto(Model model) {

        producto producto = new producto();
        model.addAttribute("producto", producto);

        return "frmVendedorProductos";
    }
    
    @Secured({"ROLE_GUESS","ROLE_ADMIN"})
    @GetMapping("/adminproductos")
    public String ProductosVendedor(Model model) {

        List<producto> listadoproducto = productoService.listar();
        model.addAttribute("productos", listadoproducto);
        return "frmVendedorProductos";
    }
    @Secured({"ROLE_GUESS","ROLE_ADMIN"})
    @GetMapping("/adminpedidos")
    public String PedidosVendedor() {

        return "frmVendedorPedidos";
    }
    @Secured({"ROLE_GUESS","ROLE_ADMIN"})
    @GetMapping("/adminatencion")
    public String AtencionPedidosVendedor() {

        return "frmVendedorAtencionP";
    }
    
    //REGISTRO DE CLIENTES      -------------------------------------------------------------------------
    
    @GetMapping("/registrocliente")
    public String crearcliente(Model model) {

        List<provincia> listarprovincia = provinciaService.listar();
        List<distrito> listardistrito = distritoService.listar();
        direccion direccion = new direccion();
        usuario usuario = new usuario();
        //si es vendedor su tienda
        tienda tienda = new tienda();
        tipopersona tipopersona = new tipopersona();
        persona persona = new persona();
       

        model.addAttribute("titulo", "Formulario: Nuevo Cliente");
        model.addAttribute("provinciascrear", listarprovincia);
        model.addAttribute("distritoscrear", listardistrito);
        model.addAttribute("direccion", direccion);
        model.addAttribute("tienda", tienda);
        model.addAttribute("tipopersona", tipopersona);
        model.addAttribute("cliente", persona);

        return "frmRegistrocliente";
    }
    
    @GetMapping("/registrovendedor")
    public String crearvendedor(Model model) {
        List<provincia> listarprovincia = provinciaService.listar();
        List<distrito> listardistrito = distritoService.listar();

        direccion direccion = new direccion();
        usuario usuario = new usuario();
        tienda tienda = new tienda();
        tipopersona tipopersona = new tipopersona();
        persona persona = new persona();

        model.addAttribute("provincias", listarprovincia);
        model.addAttribute("distritos", listardistrito);

        model.addAttribute("titulo", "Formulario: Nuevo Cliente");
        model.addAttribute("direccion", direccion);
        model.addAttribute("tienda", tienda);
        model.addAttribute("tipopersona", tipopersona);
        model.addAttribute("cliente", persona);

        return "frmRegistrovendedor";
    }

    @PostMapping("/savepersonacliente")
    public String guardarcliente(@Valid @ModelAttribute persona persona, BindingResult result,
                    Model model, RedirectAttributes attribute) {
//            List<tipopersona> tipopersona = tipopersonaService.listar();
//            List<usuario> tipousuario = usuarioService.listar();
//            model.addAttribute("direccions",distrito);
//            model.addAttribute("direccion.descripcion",direccion);
            
            if (result.hasErrors()) {
                    model.addAttribute("titulo", "Formulario: Nuevo Cliente");
                    model.addAttribute("cliente", persona);
                    
                   // model.addAttribute("ciudades", listCiudades);
                    System.out.println(persona.toString());
                    System.out.println("Existieron errores en el formulario");			
                    return "index";
            }
            
//            usuario usu = new usuario();
//            usu.setUsuario(String.valueOf(persona.getDni()));
//            usu.setContrasena(String.valueOf(persona.getDni()));
//            usuarioService.save(usu);
//            int a=usuarioService.listar().size();
//            int idw=0;
//            persona.setUsuario(tipousuario.get(a));
//            persona.setTipopersona(tipopersona.get(3));
//.................................
            usuario usu= new usuario();
            usu.setUsuario(String.valueOf(persona.getDni()));
            usu.setContrasena(String.valueOf(persona.getDni()));
            usu.setEstado(1);
 //////////////////AGREGA USUARIO//////////////////////////////////           
            usuarioService.save(usu);
            int a=usuarioService.listar().size();
            persona.setUsuario(usuarioService.listarId(a));
//            persona.setUsuario(usu);
//////////////////AGREGA ROL///////////////////////////////////
//            rol rrol = new rol();
//            
//            rrol.setNombre("ROLE_USER");
//            rrol.setUsuario(usuarioService.listarId(a));
//            rolService.save(rrol);
//.....................AGRGEGA TIPO PERSONA....................
            persona.setTipopersona(tipopersonaService.listarId(3));
//,,,,,,,,,,,,,,,,,,,,,,,,,AGREGA DIRECCION,,,,,,,,,,,,,,,,,,,,,,,,,,,,   
            direccion dire = new direccion();
            dire.setId(direccionService.listar().size()+1);
            dire.setDescripcion("Avenida 12");
            dire.setDistrito(distritoService.listarId(1));
            direccionService.save(dire);    
            persona.setDireccion(dire);
///////////////////////////AGREGA PERSONA/////////////////////////////////////////////
            System.out.println(persona.toString());
            personaService.save(persona);
            System.out.println("Cliente guardado con exito!");
            attribute.addFlashAttribute("success", "Cliente guardado con exito!");
            return "redirect:/home";
    }
    
    
    
    
    @PostMapping("/savepersonavendedor")
    public String guardarvemdedor(@Valid @ModelAttribute persona persona, BindingResult result,
                    Model model, RedirectAttributes attribute) {
            //List<usuario> listCiudades = usuarioService.listar();

            if (result.hasErrors()) {
                    model.addAttribute("titulo", "Formulario: Nuevo Cliente");
                    model.addAttribute("cliente", persona);
                   // model.addAttribute("ciudades", listCiudades);
                    System.out.println("Existieron errores en el formulario");			
                    return "frmCrear";
            }

            personaService.save(persona);
            System.out.println("Cliente guardado con exito!");
            attribute.addFlashAttribute("success", "Cliente guardado con exito!");
            return "redirect:/listar";
    }
    
    //------------------------------------------------------------------------------------------------
}
