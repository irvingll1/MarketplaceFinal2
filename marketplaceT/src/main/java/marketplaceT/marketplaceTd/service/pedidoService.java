/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package marketplaceT.marketplaceTd.service;

import java.sql.Date;
import java.util.List;
import marketplaceT.marketplaceTd.interfaces.Ipedido;
import marketplaceT.marketplaceTd.interfaceservice.IpedidoService;
import marketplaceT.marketplaceTd.modelo.pedido;
import marketplaceT.marketplaceTd.modelo.producto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 *
 * @author PC
 */
@Service
public class pedidoService implements IpedidoService{

    @Autowired
    private Ipedido data;
    
    @Override
    public List<pedido> listar() {
        return (List<pedido>)data.findAll();
    }

    @Override
    public pedido listarId(int id) {
        return data.findById(id).orElse(null);
    }

    @Override
    public void save(pedido p) {
        data.save(p);
    }

    @Override
    public void delete(int id) {
        data.deleteById(id);
    }

    @Override
    public List<pedido> buscarentredos(Date des, Date has) {
        return data.findByAllData(des, has);
    }
    
    @Override
    public List<pedido> pedidosEntregados(){
        return data.pedidosEntregados();
    }
    
    @Override
    public List<pedido> pedidosNoEntregados(){
        return data.pedidosNoEntregados();
    }
  
    @Override
    public List<pedido> pedidosMontoMayor(double total){
        return data.pedidosMontoMayor(total);
    }
    
    @Override
    public List<pedido> pedidosMontoMenor(double total){
        return data.pedidosMontoMenor(total);
    }
    
    
}
