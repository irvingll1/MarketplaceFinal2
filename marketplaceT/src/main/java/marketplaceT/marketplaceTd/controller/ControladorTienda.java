/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package marketplaceT.marketplaceTd.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import java.security.Principal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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
import marketplaceT.marketplaceTd.modelo.calificacion;
import marketplaceT.marketplaceTd.modelo.carrito;
import marketplaceT.marketplaceTd.modelo.categoriaproducto;
import marketplaceT.marketplaceTd.modelo.detallepedido;
import marketplaceT.marketplaceTd.modelo.distrito;
import marketplaceT.marketplaceTd.modelo.pedido;
import marketplaceT.marketplaceTd.modelo.persona;
import marketplaceT.marketplaceTd.modelo.producto;
import marketplaceT.marketplaceTd.modelo.provincia;
import marketplaceT.marketplaceTd.modelo.tienda;
import marketplaceT.marketplaceTd.modelo.tipopago;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author PC
 */
@Controller
@RequestMapping
public class ControladorTienda {
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
    
    private List<persona> listaper;
    private tienda tiendacali;

    //TIENDA Cliente!!!!!!!!!!
    @GetMapping("/tienda")
    public String crearTienda(Model model,Principal principal) {
        tienda objtienda = new tienda();
        calificacion objcalif = new calificacion();
        List<provincia> listadoprovincia = provinciaService.listar();
        List<distrito> listadodistrito = distritoService.listar();
        List<tienda> listadotienda = tiendaService.listar();
//        List<producto> listadoproducto = productoService.listar();
        List<producto> listadoproducto2= new ArrayList();

        if (principal != null) {
            listaper = personaService.buscarnombre(principal.getName());
            model.addAttribute("objetopersona", listaper.get(0).getNombre());
        }
        model.addAttribute("provincias", listadoprovincia);
        model.addAttribute("distritos", listadodistrito);
        model.addAttribute("jtiendas", listadotienda);
        model.addAttribute("tienda", objtienda);
        model.addAttribute("productos", listadoproducto2);
        model.addAttribute("calificacion", objcalif);

        return "frmtienda";
    }
//  
    @GetMapping("/carrito")
    public String crearcarrito(Model model,Principal principal) {
//        producto productos = new producto();
        model.addAttribute("carritos", new carrito());
        List<tipopago> listapago = tipopagoService.listar();
        if (principal != null) {
            model.addAttribute("objetopersona", listaper.get(0).getNombre());
        }


        model.addAttribute("tipopagos", listapago);
        return "frmcarrito";
    }
    @GetMapping("/filtrotienda")
    public String tiendaVendedor(@ModelAttribute tienda tienda,Principal principal,RedirectAttributes attribute,Model model){
        if (principal != null) {
            model.addAttribute("objetopersona", listaper.get(0).getNombre());
        }
//        String ocultar ="hidden";
        List<provincia> listadoprovincia = provinciaService.listar();
        List<distrito> listadodistrito = distritoService.listar();
        List<tienda> listadotienda = tiendaService.listar();
        List<calificacion> listadocalifica = calificacionService.listar();
        List<calificacion> listadocalifica2= new ArrayList();
        System.out.println("calificaion:"+listadocalifica.toString());
        
        List<categoriaproducto> listadocategorias = categoriaproductoService.listar();
     
        calificacion objcalif = new calificacion();
        tienda objtienda = new tienda();
        System.out.println("ID TIENDA:"+tienda.toString());
        tiendacali=tienda;      
        List<producto> listadoproducto = productoService.listar();
        List<producto> listadoproducto2= new ArrayList();
        
        for (int i = 0; i < listadoproducto.size(); i++) {
            if(!listadoproducto.get(i).getEstado().equals("0")){
                if(listadoproducto.get(i).getTienda().equals(tienda)){
                    listadoproducto2.add(listadoproducto.get(i));
                    
                }      
            }  
        }
        for (int i = 0; i < listadocalifica.size(); i++) {
            if(listadocalifica.get(i).getTienda().getNombre().equals(tienda.getNombre())){
                listadocalifica2.add(listadocalifica.get(i));

            }      
 
        }
        model.addAttribute("nomtienda", tienda.getNombre());
        model.addAttribute("tiendaid", tienda.getId());
//        model.addAttribute("oculta", ocultar);
        model.addAttribute("provincias", listadoprovincia);
        model.addAttribute("calitienda", listadocalifica2);
        model.addAttribute("distritos", listadodistrito);
        model.addAttribute("tiendas", listadotienda);
        model.addAttribute("calificacion", objcalif);
        model.addAttribute("tienda", objtienda);
        model.addAttribute("productos", listadoproducto2);
         model.addAttribute("categoriap", listadocategorias);
        
        return "frmtienda2";
    }
    
    @PostMapping("/busquedacategoria")
    public String busquedacategoria(@RequestParam("catego") int cate,
            Principal principal,RedirectAttributes attribute,Model model){
        System.out.println("categoria: "+cate);
        System.out.println("Tienda: "+tiendacali.getId());
        
        
        
        
        return "redirect:filtrotienda";
    }
    
    @GetMapping("/cali")
    public String calificatienda(@ModelAttribute calificacion calificacion,Principal principal,RedirectAttributes attribute,Model model){
        if (principal != null) {
            model.addAttribute("objetopersona", listaper.get(0).getNombre());
        }
        
        Date todaysDate = Date.from(Instant.now());
        
        calificacion.setFecha(todaysDate);
        calificacion.setPersona(listaper.get(0));
        calificacion.setTienda(tiendacali);
        
        
        System.out.println("calificacion: "+calificacion.toString());
        calificacionService.save(calificacion);
        return "tienda";
    }
    
    
    @PostMapping("/agregacarrito")
    public String extraerproductos(@ModelAttribute carrito carrito, RedirectAttributes attribute) throws JSONException, JsonProcessingException, ParseException {
        
        pedido pedido = new pedido();
        tipopago tipopago = new tipopago();
        persona perso = personaService.listarId(listaper.get(0).getId());
        JSONArray jsonArray = new JSONArray(carrito.getNombre());
        System.out.println("Tamaño: "+jsonArray.length());
        
        
        producto pro =new producto();
        double total=0.0;
        //insercion de pedido
        pedido.setPersona(perso);
        JSONObject objeto = jsonArray.getJSONObject(0);
        pro= productoService.listarId(Integer.parseInt(objeto.getString("id")));
        pedido.setTienda(pro.getTienda());
        pedido.setEstado(0);
        pedido.setCantidad(jsonArray.length()); 
        for (int i = 0; i < jsonArray.length(); i++) {
            objeto = jsonArray.getJSONObject(i);
            pro= productoService.listarId(Integer.parseInt(objeto.getString("id")));
            total+=pro.getPrecio();
        } 
        pedido.setTotal(total);
        
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
        pedido.setFechapedido(formato.parse(carrito.getFecha()));
        pedido.setFechaenvio(formato.parse(carrito.getFecha()));
        tipopago=tipopagoService.listarId(Integer.parseInt(carrito.getTipopago())); 
        pedido.setTipopago(tipopago);    
        pedidoService.save(pedido);
        
        //insercion del detalle de pedido
        
        for (int i = 0; i < jsonArray.length(); i++) {
            detallepedido detallepedidos = new detallepedido();
            objeto = jsonArray.getJSONObject(i);
            pro= productoService.listarId(Integer.parseInt(objeto.getString("id")));     
            detallepedidos.setProducto(pro);
            detallepedidos.setEstado(0);
            detallepedidos.setPrecio(pro.getPrecio());
            detallepedidos.setSubtotal(pro.getPrecio());
            detallepedidos.setCantidad(1);
            detallepedidos.setPersona(perso);
            total+=pro.getPrecio();
            detallepedidos.setPedido(pedidoService.listarId(pedidoService.listar().size()));
            System.out.println("Detalle pedido "+i+":"+detallepedidos.toString());
            detallepedidoService.save(detallepedidos);   
        }         

        System.out.println("fecha: "+carrito.getFecha());     
        System.out.println("pedido:"+pedido.toString());
   
        return "redirect:/tienda";
    }
    
    @GetMapping("/detalleproducto/{id}")
    public String detalleproducto(@PathVariable("id") int idproducto,Principal principal,
            Model model, RedirectAttributes attribute) {
        if (principal != null) {
            model.addAttribute("objetopersona", listaper.get(0).getNombre());
        }
        producto producto = productoService.listarId(idproducto);
        
        model.addAttribute("titulo","Detalle del producto  " +producto.getNombre());
        model.addAttribute("producto",producto);
        
        return "frmtiendadetalleproducto";
    }
    
    
    
}
