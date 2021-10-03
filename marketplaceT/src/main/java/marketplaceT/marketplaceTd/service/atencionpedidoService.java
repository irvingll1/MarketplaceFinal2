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
import org.springframework.beans.factory.annotation.Autowired;
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
    
}
