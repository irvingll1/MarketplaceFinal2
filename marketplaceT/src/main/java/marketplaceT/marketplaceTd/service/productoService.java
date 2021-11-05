/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package marketplaceT.marketplaceTd.service;

import java.util.HashMap;
import java.util.List;
import marketplaceT.marketplaceTd.interfaces.Iproducto;
import marketplaceT.marketplaceTd.interfaceservice.IproductoService;
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

    @Override
    public Page<producto> findPaginated(int pagno, int pagesize) {
        Pageable pageable = PageRequest.of(pagno-1, pagesize);
        
        return data.findAll(pageable);
    }

}
