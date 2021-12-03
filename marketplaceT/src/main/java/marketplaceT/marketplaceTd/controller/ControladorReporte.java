/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package marketplaceT.marketplaceTd.controller;

import java.util.List;
import marketplaceT.marketplaceTd.interfaceservice.IpedidoService;
import marketplaceT.marketplaceTd.modelo.pedido;
import marketplaceT.marketplaceTd.reporte.ReporteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ControladorReporte {
    @Autowired
    private IpedidoService pedidoService;
    @Autowired
    private ReporteService servicioReporte;
    
    
    @GetMapping("/pedidos")
    public List<pedido> getpedido(){
        return pedidoService.listar();
    }
    
}
