/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package marketplaceT.marketplaceTd.service;

import java.util.List;
import marketplaceT.marketplaceTd.interfaces.Itipopago;
import marketplaceT.marketplaceTd.interfaceservice.ItipopagoService;
import marketplaceT.marketplaceTd.modelo.tipopago;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author PC
 */
@Service
public class tipopagoService implements ItipopagoService{

    @Autowired
    private Itipopago data;
    @Override
    public List<tipopago> listar() {
        return (List<tipopago>)data.findAll();
    }

    @Override
    public tipopago listarId(int id) {
        return data.findById(id).orElse(null);
    }

    @Override
    public void save(tipopago p) {
        data.save(p);
    }

    @Override
    public void delete(int id) {
        data.deleteById(id);
    }
    
}
