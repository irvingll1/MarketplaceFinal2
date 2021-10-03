/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package marketplaceT.marketplaceTd.service;

import java.util.List;
import marketplaceT.marketplaceTd.interfaces.IDistrito;
import marketplaceT.marketplaceTd.interfaceservice.IdistritoService;
import marketplaceT.marketplaceTd.modelo.distrito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author PC
 */
@Service
public class distritoService implements IdistritoService{

    @Autowired
    private IDistrito data;
    
    @Override
    public List<distrito> listar() {
        return (List<distrito>)data.findAll();
    }

    @Override
    public distrito listarId(int id) {
        return data.findById(id).orElse(null);
    }

    @Override
    public void save(distrito p) {
        data.save(p);
    }

    @Override
    public void delete(int id) {
        data.deleteById(id);
    }
    
}
