/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package marketplaceT.marketplaceTd.interfaceservice;

import java.util.List;
import marketplaceT.marketplaceTd.modelo.direccion;

/**
 *
 * @author PC
 */
public interface IdireccionService {
    public List<direccion> listar();
    public direccion listarId(int id);
    public void save(direccion p);
    public void delete(int id);
}
