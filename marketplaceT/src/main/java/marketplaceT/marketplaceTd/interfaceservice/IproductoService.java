/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package marketplaceT.marketplaceTd.interfaceservice;

import java.util.List;
import marketplaceT.marketplaceTd.modelo.producto;

/**
 *
 * @author PC
 */
public interface IproductoService {
    public List<producto> listar();
    public producto listarId(int id);
    public void save(producto p);
    public void delete(int id);
}
