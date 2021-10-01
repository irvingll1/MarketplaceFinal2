/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package marketplaceT.marketplaceTd.service;

import java.util.List;
import marketplaceT.marketplaceTd.interfaces.Icalificacion;
import marketplaceT.marketplaceTd.interfaceservice.IcalificacionService;
import marketplaceT.marketplaceTd.modelo.calificacion;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author PC
 */
public class calificacionService implements IcalificacionService{

    @Autowired
    private Icalificacion data;
    @Override
    public List<calificacion> listar() {
        return (List<calificacion>)data.findAll();
    }

    @Override
    public calificacion listarId(int id) {
        return data.findById(id).orElse(null);
    }

    @Override
    public void save(calificacion p) {
        data.save(p);
    }

    @Override
    public void delete(int id) {
        data.deleteById(id);
    }
    
}
