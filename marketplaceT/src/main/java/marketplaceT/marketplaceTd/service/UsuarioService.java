/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package marketplaceT.marketplaceTd.service;


import marketplaceT.marketplaceTd.modelo.usuario;
import java.util.List;

import marketplaceT.marketplaceTd.interfaces.IUsuario;
import marketplaceT.marketplaceTd.interfaceservice.IUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author PC
 */
@Service
public class UsuarioService implements IUsuarioService{

    @Autowired
    private IUsuario data;

    @Override
    public List<usuario> listar() {
        return (List<usuario>)data.findAll();
    }

    @Override
    public usuario listarId(int id) {
        return data.findById(id).orElse(null);
    }

    @Override
    public void save(usuario p) {
        data.save(p);

    }

    @Override
    public void delete(int id) {
        data.deleteById(id);
    }
    
            

    
}
