/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package marketplaceT.marketplaceTd.interfaceservice;

import java.util.List;
import marketplaceT.marketplaceTd.modelo.tienda;

/**
 *
 * @author PC
 */
public interface ItiendaService {
    public List<tienda> listar();
    public tienda listarId(int id);
    public void save(tienda p);
    public void delete(int id);
}
