/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package marketplaceT.marketplaceTd.service;

import java.util.List;
import marketplaceT.marketplaceTd.interfaces.Itienda;
import marketplaceT.marketplaceTd.interfaceservice.ItiendaService;
import marketplaceT.marketplaceTd.modelo.tienda;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author PC
 */
@Service
public class tiendaService implements ItiendaService{

    @Autowired
    private Itienda data;
    @Override
    public List<tienda> listar() {
        return (List<tienda>)data.findAll();
    }

    @Override
    public tienda listarId(int id) {
        return data.findById(id).orElse(null);
    }

    @Override
    public void save(tienda p) {
        data.save(p);
    }

    @Override
    public void delete(int id) {
        data.deleteById(id);
    }
}
