/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package marketplaceT.marketplaceTd.service;

import java.util.List;
import marketplaceT.marketplaceTd.interfaces.Iproducto;
import marketplaceT.marketplaceTd.interfaceservice.IproductoService;
import marketplaceT.marketplaceTd.modelo.producto;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author PC
 */
public class productoService implements IproductoService{

    @Autowired
    private Iproducto data;
    @Override
    public List<producto> listar() {
        return (List<producto>)data.findAll();
    }

    @Override
    public producto listarId(int id) {
        return data.findById(id).orElse(null);
    }

    @Override
    public void save(producto p) {
        data.save(p);
    }

    @Override
    public void delete(int id) {
        data.deleteById(id);
    }
    
}
