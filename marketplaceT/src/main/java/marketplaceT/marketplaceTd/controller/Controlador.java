/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package marketplaceT.marketplaceTd.controller;

import java.security.Principal;
import java.util.ArrayList;
import marketplaceT.marketplaceTd.modelo.usuario;
import java.util.List;
import javax.validation.Valid;
import marketplaceT.marketplaceTd.interfaceservice.IPersonaService;
import marketplaceT.marketplaceTd.interfaceservice.IRolService;
import marketplaceT.marketplaceTd.interfaceservice.IUsuarioService;
import marketplaceT.marketplaceTd.interfaceservice.IcategoriaproductoService;
import marketplaceT.marketplaceTd.interfaceservice.IdistritoService;
import marketplaceT.marketplaceTd.interfaceservice.IproductoService;
import marketplaceT.marketplaceTd.interfaceservice.IprovinciaService;
import marketplaceT.marketplaceTd.interfaceservice.ItiendaService;
import marketplaceT.marketplaceTd.interfaceservice.ItipopagoService;
import marketplaceT.marketplaceTd.modelo.categoriaproducto;
import marketplaceT.marketplaceTd.modelo.distrito;
import marketplaceT.marketplaceTd.modelo.persona;
import marketplaceT.marketplaceTd.modelo.producto;
import marketplaceT.marketplaceTd.modelo.provincia;
import marketplaceT.marketplaceTd.modelo.rol;
import marketplaceT.marketplaceTd.modelo.tienda;
import marketplaceT.marketplaceTd.modelo.tipopago;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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

    private int idCliente;
    private List<persona> listaper;

    @GetMapping({"/index", "/home", "/"})
    public String index(Model model, Principal principal) {

        if (principal != null) {
            listaper = personaService.buscarnombre(Integer.parseInt(principal.getName()));
            System.out.println("devuelve" + listaper.toString());
            model.addAttribute("objetopersona", listaper.get(0).getNombre());
        }
        return "home";
    }

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

    //TIENDA Cliente!!!!!!!!!!
    @GetMapping("/tienda")
    public String crearTienda(Model model,Principal principal) {
        List<provincia> listadoprovincia = provinciaService.listar();
        List<distrito> listadodistrito = distritoService.listar();
        List<tienda> listadotienda = tiendaService.listar();
        List<producto> listadoproducto = productoService.listar();
        List<producto> listadoproducto2= new ArrayList();
        
        
        for (int i = 0; i < listadoproducto.size(); i++) {
            if(!listadoproducto.get(i).getEstado().equals("0")){
                listadoproducto2.add(listadoproducto.get(i));
            }
        }
        if (principal != null) {
            model.addAttribute("objetopersona", listaper.get(0).getNombre());
        }
        model.addAttribute("provincias", listadoprovincia);
        model.addAttribute("distritos", listadodistrito);
        model.addAttribute("tiendas", listadotienda);
        model.addAttribute("productos", listadoproducto2);
        return "frmtienda";
    }

    @GetMapping("/carrito")
    public String crearcarrito(Model model,Principal principal) {

        List<tipopago> listapago = tipopagoService.listar();
        if (principal != null) {
            model.addAttribute("objetopersona", listaper.get(0).getNombre());
        }
        model.addAttribute("tipopagos", listapago);
        return "frmcarrito";
    }

    @PostMapping("/comprar")
    public String comprartienda() {

        return "redirect:/tienda";
    }

    //Tienda VEndedor
    @Secured({"ROLE_GUESS", "ROLE_ADMIN"})
    @GetMapping("/vendedor")
    public String adminVendedor(Model model, Principal priincipal) {

        if (priincipal != null) {
            model.addAttribute("objetopersona", listaper.get(0).getNombre());
        }
        return findPaginated(1, priincipal, model);
    }

    
    //PRODUCTOS
    @Secured({"ROLE_GUESS", "ROLE_ADMIN"})
    @GetMapping("/createproducto")
    public String crearproducto(Model model,Principal principal) {

        producto producto = new producto();
        model.addAttribute("producto", producto);
        if (principal != null) {
            model.addAttribute("objetopersona", listaper.get(0).getNombre());
        }
        return "frmVendedorProductos";
    }

    @GetMapping("/page/{pageNo}")
    public String findPaginated(@PathVariable(value = "pageNo") int pageNo,
            Principal principal, Model model) {
        //NOMBREUSUARIO
        List<persona> lista = personaService.buscarnombre(Integer.parseInt(principal.getName()));
        
        //PAGINACION
        int pageSize = 5;
        Page<producto> page = productoService.findPaginated(pageNo, pageSize);

        List<producto> listadoproducto = page.getContent();
//        List<producto> listadoproducto2= new ArrayList();
//        
//        
//        for (int i = 0; i < listadoproducto.size(); i++) {
//            if(!listadoproducto.get(i).getEstado().equals("0")){
//                listadoproducto2.add(listadoproducto.get(i));
//            }
//        }
//        System.out.println("listaproductos"+listadoproducto2.get(1).getEstado());
        
        if (principal != null) {
            model.addAttribute("objetopersona", listaper.get(0).getNombre());
        }
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());
        model.addAttribute("productos", listadoproducto);

        return "frmVendedorProductos";
    }
    
    @GetMapping("/agregarp")
    public String crearProducto(Model model,Principal principal){
        producto producto = new producto();
        if (principal != null) {
            model.addAttribute("objetopersona", listaper.get(0).getNombre());
        }
        model.addAttribute("categoriaproductos", categoriaproductoService.listar());
        model.addAttribute("producto", producto);
        return "frmVendedorAproductos";
    }
    @PostMapping("/saveproductos")
    public String guardarProducto(@Valid @ModelAttribute producto producto, BindingResult result,
            Model model, RedirectAttributes attribute) {
        //List<usuario> listCiudades = usuarioService.listar();
        
        //Agregar tienda
        producto.setTienda(listaper.get(0).getTienda());   
        
        System.out.println("Producto"+producto.toString());
        productoService.save(producto);

        System.out.println("Producto guardado con exito!");
        attribute.addFlashAttribute("success", "Producto guardado con exito!");
        return "redirect:/vendedor";
    }
    @GetMapping("/editproducto/{id}")
    public String editarproducto(@PathVariable("id") int idProducto, Model model, RedirectAttributes attribute) {

        producto producto = null;

        if (idProducto > 0) {
            producto = productoService.listarId(idProducto);

            if (producto == null) {
                System.out.println("Error: El ID del cliente no existe!");
                attribute.addFlashAttribute("error", "ATENCION: El ID del producto no existe!");
                return "redirect:/vendedor";
            }
        } else {
            System.out.println("Error: Error con el ID del Cliente");
            attribute.addFlashAttribute("error", "ATENCION: Error con el ID del producto");
            return "redirect:/vendedor";
        }

        // List<usuario> listCiudades = usuarioService.listar();
        model.addAttribute("titulo", "Formulario: Editar Cliente");
        model.addAttribute("producto", producto);
        model.addAttribute("categoriaproductos", categoriaproductoService.listar());

        return "frmVendedorAproductos";
    }
    @GetMapping("/deleteproducto/{id}")
    public String eliminarproducto(@PathVariable("id") int idproducto, RedirectAttributes attribute) {

        producto producto = null;

        if (idproducto > 0) {
            producto = productoService.listarId(idproducto);

            if (producto == null) {
                System.out.println("Error: El ID del cliente no existe!");
                attribute.addFlashAttribute("error", "ATENCION: El ID del producto no existe!");
                return "redirect:/vendedor";
            }
        } else {
            System.out.println("Error: Error con el ID del Cliente");
            attribute.addFlashAttribute("error", "ATENCION: Error con el ID del producto!");
            return "redirect:/vendedor";
        }
        producto productoeliminar=productoService.listarId(idproducto);
        productoeliminar.setEstado("0");
        
        productoService.save(productoeliminar);
        
        System.out.println("Registro Eliminado con Exito!");
        attribute.addFlashAttribute("warning", "Registro Eliminado con Exito!");

        return "redirect:/vendedor";
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    

    @Secured({"ROLE_GUESS", "ROLE_ADMIN"})
    @GetMapping("/adminproductos")
    public String ProductosVendedor(Model model,
            Principal principal) {

//        List<producto> listadoproducto = productoService.listar();
//        model.addAttribute("productos", listadoproducto);
        if (principal != null) {
            model.addAttribute("objetopersona", listaper.get(0).getNombre());
        }
        return findPaginated(1, principal, model);
    }

    @Secured({"ROLE_GUESS", "ROLE_ADMIN"})
    @GetMapping("/adminpedidos")
    public String PedidosVendedor(Model model,Principal principal) {
        if (principal != null) {
            model.addAttribute("objetopersona", listaper.get(0).getNombre());
        }
        return "frmVendedorPedidos";
    }

    @Secured({"ROLE_GUESS", "ROLE_ADMIN"})
    @GetMapping("/adminatencion")
    public String AtencionPedidosVendedor(Model model,Principal principal) {
        if (principal != null) {
            model.addAttribute("objetopersona", listaper.get(0).getNombre());
        };
        return "frmVendedorAtencionP";
    }

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
        rol ro = new rol();
        ro.setNombre("ROLE_USER");
        ro.setIdusuario(usu.getId());
        System.out.println(ro.toString());
        rolService.save(ro);
        //agrega persona a base de datos
        personaService.save(persona);
        System.out.println("Cliente guardado con exito!");
        attribute.addFlashAttribute("success", "Cliente guardado con exito!");
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
        rol ro = new rol();
        ro.setNombre("ROLE_GUESS");
        ro.setIdusuario(usu.getId());
        System.out.println(ro.toString());
        rolService.save(ro);
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
        personaService.save(persona);
        System.out.println("Cliente guardado con exito!");
        attribute.addFlashAttribute("success", "Cliente guardado con exito!");
        return "redirect:/home";
    }

    //------------------------------------------------------------------------------------------------
}
