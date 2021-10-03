/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package marketplaceT.marketplaceTd.service;

import java.util.List;
import marketplaceT.marketplaceTd.interfaces.Icategoriaproducto;
import marketplaceT.marketplaceTd.interfaceservice.IcategoriaproductoService;
import marketplaceT.marketplaceTd.modelo.categoriaproducto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author PC
 */
@Service
public class categoriaproductosService implements IcategoriaproductoService{

    @Autowired
    private Icategoriaproducto data;
    
    @Override
    public List<categoriaproducto> listar() {
        return (List<categoriaproducto>)data.findAll();

    }

    @Override
    public categoriaproducto listarId(int id) {
        return data.findById(id).orElse(null);
    }

    @Override
    public void save(categoriaproducto p) {
        data.save(p);
    }

    @Override
    public void delete(int id) {
        data.deleteById(id);
    }
    
}
