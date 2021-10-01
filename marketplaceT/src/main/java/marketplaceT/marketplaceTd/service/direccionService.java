/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package marketplaceT.marketplaceTd.service;

import java.util.List;
import marketplaceT.marketplaceTd.interfaces.Iatencionpedido;
import marketplaceT.marketplaceTd.interfaces.Idireccion;
import marketplaceT.marketplaceTd.interfaceservice.IdireccionService;
import marketplaceT.marketplaceTd.modelo.direccion;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author PC
 */
public class direccionService implements IdireccionService {

    @Autowired
    private Idireccion data;

    @Override
    public List<direccion> listar() {
        return (List<direccion>) data.findAll();
    }

    @Override
    public direccion listarId(int id) {

        return data.findById(id).orElse(null);
    }

    @Override
    public void save(direccion p) {
        data.save(p);
    }

    @Override
    public void delete(int id) {
        data.deleteById(id);
    }

}
