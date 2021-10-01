/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package marketplaceT.marketplaceTd.interfaceservice;

import java.util.List;
import marketplaceT.marketplaceTd.modelo.tipopersona;

/**
 *
 * @author PC
 */
public interface ItipopersonaService {
    public List<tipopersona> listar();
    public tipopersona listarId(int id);
    public void save(tipopersona p);
    public void delete(int id);
}
