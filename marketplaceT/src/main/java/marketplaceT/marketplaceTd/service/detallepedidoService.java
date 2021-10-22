/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package marketplaceT.marketplaceTd.service;

import java.util.List;
import marketplaceT.marketplaceTd.interfaces.Idetallepedido;
import marketplaceT.marketplaceTd.interfaceservice.IdetallepedidoService;
import marketplaceT.marketplaceTd.modelo.detallepedido;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author PC
 */
@Service
public class detallepedidoService implements IdetallepedidoService{

    @Autowired
    private Idetallepedido data;
    
    @Override
    public List<detallepedido> listar() {
        return (List<detallepedido>)data.findAll();
    }

    @Override
    public detallepedido listarId(int id) {
        return data.findById(id).orElse(null);
    }

    @Override
    public void save(detallepedido p) {
        data.save(p);
    }

    @Override
    public void delete(int id) {
        data.deleteById(id);
    }
    
}
