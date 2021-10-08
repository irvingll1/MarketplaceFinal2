/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package marketplaceT.marketplaceTd.service;

import java.util.List;
import java.util.Optional;
import marketplaceT.marketplaceTd.interfaces.IPersona;
import marketplaceT.marketplaceTd.interfaces.IRol;
import marketplaceT.marketplaceTd.interfaceservice.IPersonaService;
import marketplaceT.marketplaceTd.modelo.persona;
import marketplaceT.marketplaceTd.modelo.rol;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author PC
 */
@Service
public class PersonaService implements IPersonaService{

    @Autowired
    private IPersona data;

    
    @Override
    public List<persona> listar() {
        return (List<persona>)data.findAll();
    }

    @Override
    public persona listarId(int id) {
        return data.findById(id).orElse(null);
    }

    @Override
    public void save(persona p) {
        persona perso = data.save(p);
    }
    
    @Override
    public void delete(int id) {
        data.deleteById(id);
    }

    @Override
    public List<persona> buscarnombre(int dni){
        return data.buscarnombre(dni);
    }
    
}
