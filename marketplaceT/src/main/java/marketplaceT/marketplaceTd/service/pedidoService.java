/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package marketplaceT.marketplaceTd.service;

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
    public Page<pedido> findPaginated(int pagno, int pagesize) {
        Pageable pageable = PageRequest.of(pagno-1, pagesize);
        return data.findAll(pageable);
    }
}
