/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package marketplaceT.marketplaceTd.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.validation.Valid;
import marketplaceT.marketplaceTd.correo.SendMailService;
import marketplaceT.marketplaceTd.interfaceservice.IPersonaService;
import marketplaceT.marketplaceTd.interfaceservice.IRolService;
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
import marketplaceT.marketplaceTd.modelo.atencionpedido;
import marketplaceT.marketplaceTd.modelo.calificacion;
import marketplaceT.marketplaceTd.modelo.detallepedido;
import marketplaceT.marketplaceTd.modelo.pedido;
import marketplaceT.marketplaceTd.modelo.persona;
import marketplaceT.marketplaceTd.modelo.producto;
import marketplaceT.marketplaceTd.modelo.tienda;
import marketplaceT.marketplaceTd.reporte.ReporteService;
import net.sf.jasperreports.engine.JRException;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author PC
 */
@Controller
@RequestMapping
public class ControladorVendedor {

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

    @Autowired
    private IpedidoService pedidoService;

    @Autowired
    private IdetallepedidoService detallepedidoService;

    @Autowired
    private IatencionpedidoService atencionpedidoService;

    @Autowired
    private IcalificacionService calificacionService;

    @Autowired
    private SendMailService sendmailService;

    private List<persona> listaper;
    private tienda tiendacali;
    private detallepedido detallepedidopublic;
    //reporte Jasper Report---------------
    @Autowired
    private ReporteService servicioReporte;
    //----------------------------------------

    private List<atencionpedido> listatecionp;
    
    
    
    
    //Tienda VEndedor
    @Secured({"ROLE_GUESS", "ROLE_ADMIN"})
    @GetMapping("/vendedor")
    public String adminVendedor(Model model, Principal priincipal) {
        if (priincipal != null) {
            listaper = personaService.buscarnombre(priincipal.getName());
            model.addAttribute("objetopersona", listaper.get(0).getNombre());
        }
        List<producto> listadoproducto = productoService.listar();
        List<producto> listadoproducto2 = new ArrayList();
        tienda tienda = listaper.get(0).getTienda();
        for (int i = 0; i < listadoproducto.size(); i++) {
            if (listadoproducto.get(i).getTienda().getNombre().equals(tienda.getNombre())) {
                listadoproducto2.add(listadoproducto.get(i));
            }
        }

        model.addAttribute("productos", listadoproducto2);

        return "frmVendedorProductos2";
    }

    //PRODUCTOS
    @Secured({"ROLE_GUESS", "ROLE_ADMIN"})
    @GetMapping("/createproducto")
    public String crearproducto(Model model, Principal principal) {

        producto producto = new producto();
        model.addAttribute("producto", producto);
        if (principal != null) {
            model.addAttribute("objetopersona", listaper.get(0).getNombre());
        }
        return "frmVendedorProductos";
    }

    @Secured({"ROLE_GUESS", "ROLE_ADMIN"})
    @GetMapping("/agregarp")
    public String crearAProducto(Model model, Principal principal) {
        producto producto = new producto();
        if (principal != null) {
            model.addAttribute("objetopersona", listaper.get(0).getNombre());
        }
        model.addAttribute("categoriaproductos", categoriaproductoService.listar());
        model.addAttribute("producto", producto);
        return "frmVendedorAproductos";
    }

    @Secured({"ROLE_USER", "ROLE_GUESS", "ROLE_ADMIN"})
    @PostMapping("/saveproductos")
    public String guardarProducto(@Valid @ModelAttribute producto producto, BindingResult result,
            Model model, @RequestParam("foto") MultipartFile imagen, RedirectAttributes attribute) {
        //List<usuario> listCiudades = usuarioService.listar();

        //Agregar tienda
        producto.setTienda(listaper.get(0).getTienda());
        if (!imagen.isEmpty()) {
            Path directorioImagenes = Paths.get("src//main//resources//static//images");
            String rutaAbsoluta = directorioImagenes.toFile().getAbsolutePath();
            try {
                byte[] bytesImg = imagen.getBytes();
                Path rutaCompleta = Paths.get(rutaAbsoluta + "//" + imagen.getOriginalFilename());
                Files.write(rutaCompleta, bytesImg);

                producto.setFoto(imagen.getOriginalFilename());

            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        System.out.println("Producto" + producto.toString());
        productoService.save(producto);

        System.out.println("Producto guardado con exito!");
        attribute.addFlashAttribute("success", "Producto guardado con exito!");
        return "redirect:/vendedor";
    }

    @Secured({"ROLE_GUESS", "ROLE_ADMIN"})
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

    @Secured({"ROLE_GUESS", "ROLE_ADMIN"})
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
        producto productoeliminar = productoService.listarId(idproducto);
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

        List<producto> listadoproducto = productoService.listar();
        List<producto> listadoproducto2 = new ArrayList();
        tienda tienda = listaper.get(0).getTienda();
        for (int i = 0; i < listadoproducto.size(); i++) {
            if (listadoproducto.get(i).getTienda().getNombre().equals(tienda.getNombre())) {
                listadoproducto2.add(listadoproducto.get(i));
            }
        }

        model.addAttribute("productos", listadoproducto2);

        if (principal != null) {
            model.addAttribute("objetopersona", listaper.get(0).getNombre());
        }
        return "frmVendedorProductos2";
    }

    @Secured({"ROLE_GUESS", "ROLE_ADMIN"})
    @GetMapping("/adminpedidos")
    public String PedidosVendedor(Model model, Principal principal) {

        List<pedido> listadopedido = pedidoService.listar();
        List<pedido> listadopedidos2 = new ArrayList();
//        
        tienda tienda = listaper.get(0).getTienda();
//        
        for (int i = 0; i < listadopedido.size(); i++) {
            if (listadopedido.get(i).getTienda().getNombre().equals(tienda.getNombre())) {
                listadopedidos2.add(listadopedido.get(i));
            }
        }
//        System.out.println("listaproductos"+listadoproducto2.get(1).getEstado());

        if (principal != null) {
            model.addAttribute("objetopersona", listaper.get(0).getNombre());
        }

        model.addAttribute("pedidos", listadopedidos2);

        return "frmVendedorPedidos2";
    }

    @GetMapping("/detapedido/{id}")
    public String detallepedido(@PathVariable("id") int idpedido, Model model, Principal principal, WebRequest request) {
        int id = 0;
        if (principal != null) {
            listaper = personaService.buscarnombre(principal.getName());
            model.addAttribute("objetopersona", listaper.get(0).getNombre());
        }

        detallepedidopublic = detallepedidoService.listarId(idpedido);

        List<detallepedido> listadetalles = new ArrayList<>();
        if (idpedido > 0) {

            for (int i = 0; i < detallepedidoService.listar().size(); i++) {
                id = detallepedidoService.listar().get(i).getPedido().getId();
                if (id == idpedido) {
                    listadetalles.add(detallepedidoService.listar().get(i));
                }
            }
        }
        model.addAttribute("idped", idpedido);
        model.addAttribute("detallepedido", listadetalles);

        return "frmVendedordetallePedidos";
    }

    @GetMapping("/adetapedidosubirc/{id}/{cantidad}")
    public String adetapedidosubirc(@PathVariable("id") int id,
            @PathVariable("cantidad") int cant, Model model, Principal principal) {

        if (principal != null) {
            model.addAttribute("objetopersona", listaper.get(0).getNombre());
        }
        detallepedido deta = detallepedidoService.listarId(id);
        deta.setCantidad(cant + 1);
        deta.setPrecio(deta.getPrecio() * 2);
        deta.setSubtotal(deta.getSubtotal() * 2);
        detallepedidoService.save(deta);
        return "redirect:/detapedido/" + detallepedidopublic.getId();
    }

    @GetMapping("/adetapedidobajarc/{id}/{cantidad}")
    public String adetapedidobajarc(@PathVariable("id") int id,
            @PathVariable("cantidad") int cant,
            Model model, Principal principal) {

        if (principal != null) {
            model.addAttribute("objetopersona", listaper.get(0).getNombre());
        }
        detallepedido deta = detallepedidoService.listarId(id);
        deta.setCantidad(cant - 1);
        deta.setPrecio(deta.getPrecio() / 2);
        deta.setSubtotal(deta.getSubtotal() / 2);
        System.out.println("iddetallepedido: " + id);
        System.out.println("cantidad: " + (cant - 1));
        detallepedidoService.save(deta);

        return "redirect:/detapedido/" + detallepedidopublic.getId();
    }

    @PostMapping("/correo")
    public String correo(@RequestParam("idped") int idpedido) throws IOException, FileNotFoundException, JRException {

        int id;
        String subject;
        String message;
        List<detallepedido> listadetalles = new ArrayList<>();
        if (idpedido > 0) {

            for (int i = 0; i < detallepedidoService.listar().size(); i++) {
                id = detallepedidoService.listar().get(i).getPedido().getId();
                if (id == idpedido) {
                    listadetalles.add(detallepedidoService.listar().get(i));
                }
            }
        }
        pedido pedido = pedidoService.listarId(idpedido);

        subject = "Detalle de pedido";
        message = "Productos del Pedido:\n";
        String cliente = "";
        for (int i = 0; i < listadetalles.size(); i++) {
            String estado = listadetalles.get(i).getProducto().getEstado().equals("1") ? "disponible" : "no disponible";
            cliente = listadetalles.get(i).getPersona().getEmail();
            message += "\nProducto " + (i + 1) + ": " + listadetalles.get(i).getProducto().getNombre() + " "
                    + "Cantidad: " + listadetalles.get(i).getCantidad() + " "
                    + "Precio: " + listadetalles.get(i).getProducto().getPrecio() + " "
                    + "Disponibilidad: " + estado;

        }
        message += "\nCantidad: " + pedido.getCantidad();
        message += "\nTotal Pedido: s./" + pedido.getTotal();

        servicioReporte.exportReport(listadetalles);

        sendmailService.sendMail("iakmarketplace@gmail.com", "irvingllerena@gmail.com",
                subject, message,new File("C:\\Users\\PC\\Desktop\\Report\\pedidos.pdf"));

        return "redirect:/vendedor";
    }

    @GetMapping("/adetapedido/{id}/{cantidad}")
    public String adetallepedido(@PathVariable("id") int idapedido,
            @PathVariable("cantidad") int cantidad,
            Model model,
            Principal principal) {

        if (principal != null) {
            listaper = personaService.buscarnombre(principal.getName());
            model.addAttribute("objetopersona", listaper.get(0).getNombre());
        }

        System.out.println("cantidad 2: " + cantidad);
        detallepedido objdetalle = detallepedidoService.listarId(idapedido);

        objdetalle.setEstado(1);
        detallepedidoService.save(objdetalle);
        return "redirect:/detapedido/" + objdetalle.getPedido().getId();
    }

    @GetMapping("/adeletedpedido/{id}")
    public String adeletedpedido(@PathVariable("id") int idapedido, Model model, Principal principal) {

        if (principal != null) {
            listaper = personaService.buscarnombre(principal.getName());
            model.addAttribute("objetopersona", listaper.get(0).getNombre());
        }
        detallepedido objdetalle = detallepedidoService.listarId(idapedido);

        objdetalle.setEstado(0);
        detallepedidoService.save(objdetalle);
        return "redirect:/detapedido/" + objdetalle.getPedido().getId();
    }

    @GetMapping("/aprobarpedido/{id}")
    public String aprobarpedido(@PathVariable("id") int idpedido, Model model, Principal principal) {

        if (principal != null) {
            model.addAttribute("objetopersona", listaper.get(0).getNombre());
        }
        pedido objedido = pedidoService.listarId(idpedido);

        objedido.setEstado(1);
        pedidoService.save(objedido);
        //agregar Atencion de pedido
        atencionpedido atencionpedido = new atencionpedido();
        atencionpedido.setPersona(objedido.getPersona());
        atencionpedido.setOrden(1);
        atencionpedido.setPedido(objedido);
        atencionpedido.setFechapedido(objedido.getFechapedido());
        atencionpedidoService.save(atencionpedido);

        return "redirect:/adminpedidos";
    }

    @GetMapping("/desaprobarpedido/{id}")
    public String desaprobarpedido(@PathVariable("id") int idpedido, Model model, Principal principal) {

        if (principal != null) {
            model.addAttribute("objetopersona", listaper.get(0).getNombre());
        }
        pedido objedido = pedidoService.listarId(idpedido);

        objedido.setEstado(0);
        pedidoService.save(objedido);

        return "redirect:/adminpedidos";
    }

    @GetMapping("/actualizafecha/{fecha}")
    public String actualizafecha(@PathVariable("fecha") String fecha, Model model, Principal principal) {

        System.out.println("Fecha:" + fecha);

        return "redirect:/adminpedidos";
    }

    @Secured({"ROLE_GUESS", "ROLE_ADMIN"})
    @GetMapping("/adminatencion")
    public String AtencionPedidosVendedor(Model model, Principal principal) {

        List<pedido> listadopedido = pedidoService.listar();
        List<atencionpedido> listadoatencionpedido = atencionpedidoService.listar();
        List<atencionpedido> listadoatencionpedido2 = new ArrayList();
        atencionpedido aten = new atencionpedido();
//        

//        for (int i = 0; i < listadoatencionpedido.size(); i++) {
//            if(listadoatencionpedido.get(i).getPersona().equals(listaper.get(0))){
//                listadoatencionpedido2.add(listadoatencionpedido.get(i));
//            }
//        }
        System.out.println("per" + listaper.get(0).toString());
        System.out.println("tamaño" + listadopedido.size());
        for (int i = 0; i < listadoatencionpedido.size(); i++) {
            if (listadopedido.get(i).getTienda().getNombre().equals(listaper.get(0).getTienda().getNombre())) {

                if (listadoatencionpedido.get(i).getEstado() == 0) {
                    if (listadoatencionpedido.get(i).getId() == listadopedido.get(i).getId()) {
                        listadoatencionpedido2.add(listadoatencionpedido.get(i));
                    }
                }
            }
        }

        if (principal != null) {
            model.addAttribute("objetopersona", listaper.get(0).getNombre());
        }

        //paginacion atencion pedidos
        listatecionp=listadoatencionpedido2;
        model.addAttribute("atencionpedidos", listadoatencionpedido2);
        model.addAttribute("atencionpedidom", aten);
        return "frmVendedorAtencionP";
    }

    @GetMapping("/atencionpeds/{id}")
    public String atencionpeds(@PathVariable("id") int id, Model model, Principal principal) {

        atencionpedido objate = atencionpedidoService.listarId(id);
        objate.setOrden(objate.getOrden() + 1);

        atencionpedidoService.save(objate);

        return "redirect:/adminatencion";

    }

    @GetMapping("/atencionpedb/{id}")
    public String atencionpedb(@PathVariable("id") int id, Model model, Principal principal) {

        atencionpedido objate = atencionpedidoService.listarId(id);
        objate.setOrden(objate.getOrden() - 1);
        atencionpedidoService.save(objate);

        return "redirect:/adminatencion";

    }
    @GetMapping("/correorepartidor")
    public String correorepartidor(Model model, Principal principal) throws FileNotFoundException, JRException {
 
        int id;
        String subject;
        String message;
        subject = "Atencion de Pedidos";
        message = "Lista de Pedidos:\n";
        String repartidor = "";
        for (int i = 0; i < listatecionp.size(); i++) {
            message += "\nPedido " + (i + 1) + ": " + listatecionp.get(i).getFechapedido() + " "
                    + "Direccion: " + listatecionp.get(i).getPedido().getPersona().getDireccion() + " "
                    + "Orden: " + listatecionp.get(i).getOrden() + " "
                    + "Monto: " + listatecionp.get(i).getPedido().getTotal();

        }
        
        servicioReporte.exportReportAtencionPedido(listatecionp);

        sendmailService.sendMail("iakmarketplace@gmail.com", "irvingllerena@gmail.com", 
                subject, message,new File("C:\\Users\\PC\\Desktop\\Report\\atencionpedidos.pdf"));
        for (int i = 0; i < listatecionp.size(); i++) {
            atencionpedido at=listatecionp.get(i);
            at.setEstado(1);
            atencionpedidoService.save(at);
        }
        
        return "redirect:/adminatencion";

    }

    @GetMapping("/reportes")
    public String reportes(Model model, Principal principal) {
        if (principal != null) {
            model.addAttribute("objetopersona", listaper.get(0).getNombre());
        };

        return "frmVendedorReportes";
    }
}
