/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package marketplaceT.marketplaceTd.service;

import java.util.List;
import marketplaceT.marketplaceTd.interfaces.IProvincia;
import marketplaceT.marketplaceTd.interfaceservice.IprovinciaService;
import marketplaceT.marketplaceTd.modelo.provincia;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author PC
 */
@Service
public class provinciaService implements IprovinciaService{

    @Autowired
    private IProvincia data;
    
    
    @Override
    public List<provincia> listar() {
        return (List<provincia>)data.findAll();
    }

    @Override
    public provincia listarId(int id) {
        return data.findById(id).orElse(null);
    }

    @Override
    public void save(provincia p) {
        data.save(p);
    }

    @Override
    public void delete(int id) {
        data.deleteById(id);
    }
    
}
