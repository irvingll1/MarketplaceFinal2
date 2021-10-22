/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package marketplaceT.marketplaceTd.service;

import java.util.List;
import marketplaceT.marketplaceTd.interfaces.Iatencionpedido;
import marketplaceT.marketplaceTd.interfaceservice.IatencionpedidoService;
import marketplaceT.marketplaceTd.modelo.atencionpedido;
import marketplaceT.marketplaceTd.modelo.pedido;
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
public class atencionpedidoService implements IatencionpedidoService{

    @Autowired
    private Iatencionpedido data;
    
    @Override
    public List<atencionpedido> listar() {
        return (List<atencionpedido>)data.findAll();
    }

    @Override
    public atencionpedido listarId(int id) {
        return data.findById(id).orElse(null);
    }

    @Override
    public void save(atencionpedido p) {
        data.save(p);
    }

    @Override
    public void delete(int id) {
        data.deleteById(id);
    }
    @Override
    public Page<atencionpedido> findPaginated(int pagno, int pagesize) {
        Pageable pageable = PageRequest.of(pagno-1, pagesize);
        return data.findAll(pageable);
    }
}
