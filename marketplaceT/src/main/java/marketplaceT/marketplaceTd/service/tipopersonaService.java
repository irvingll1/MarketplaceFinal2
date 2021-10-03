/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package marketplaceT.marketplaceTd.service;

import java.util.List;
import marketplaceT.marketplaceTd.interfaces.Itipopersona;
import marketplaceT.marketplaceTd.interfaceservice.ItipopersonaService;
import marketplaceT.marketplaceTd.modelo.tipopersona;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author PC
 */
@Service
public class tipopersonaService implements ItipopersonaService{

    @Autowired
    private Itipopersona data;
    @Override
    public List<tipopersona> listar() {
        return (List<tipopersona>)data.findAll();
    }

    @Override
    public tipopersona listarId(int id) {
        return data.findById(id).orElse(null);
    }

    @Override
    public void save(tipopersona p) {
        data.save(p);
    }

    @Override
    public void delete(int id) {
        data.deleteById(id);
    }
    
}
