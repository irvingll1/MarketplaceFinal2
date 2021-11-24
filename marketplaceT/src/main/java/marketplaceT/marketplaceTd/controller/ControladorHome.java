/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package marketplaceT.marketplaceTd.controller;

import java.security.Principal;
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
import marketplaceT.marketplaceTd.modelo.persona;
import marketplaceT.marketplaceTd.modelo.tienda;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author PC
 */
@Controller
@RequestMapping
public class ControladorHome {
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
    
    //    private int idCliente;
    private List<persona> listaper;
    private tienda tiendacali;
    
    
    @GetMapping({"/index", "/home", "/"})
    public String index(Model model, Principal principal) {

        if (principal != null) {
            listaper = personaService.buscarnombre(principal.getName());
            System.out.println("devuelve" + listaper.toString());
            if (listaper.get(0).getTipopersona().getNombre().equals("Administrador")) {
                model.addAttribute("objetopersona", listaper.get(0).getNombre());
                return "home";
            }else if (listaper.get(0).getTipopersona().getNombre().equals("Vendedor")) {
                model.addAttribute("objetopersona", listaper.get(0).getNombre());
                return "homevendedor";
            }
            else if (listaper.get(0).getTipopersona().getNombre().equals("Cliente")) {
                model.addAttribute("objetopersona", listaper.get(0).getNombre());
                return "homecliente";
            }
            model.addAttribute("objetopersona", listaper.get(0).getNombre());
            
        }
        return "home";
    }
}
