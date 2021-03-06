/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package marketplaceT.marketplaceTd.service;

import java.util.List;
import marketplaceT.marketplaceTd.interfaces.IRol;
import marketplaceT.marketplaceTd.interfaceservice.IRolService;
import marketplaceT.marketplaceTd.modelo.rol;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author PC
 */
@Service
public class RolService implements IRolService {
    @Autowired
    private IRol data;

    @Override
    public List<rol> listar() {
        return (List<rol>)data.findAll();
    }

    @Override
    public rol listarId(int id) {
        return data.findById(id).orElse(null);
    }

    @Override
    public void save(rol p) {
        data.save(p);

    }

    @Override
    public void delete(int id) {
        data.deleteById(id);
    }
}
