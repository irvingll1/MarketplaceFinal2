/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package marketplaceT.marketplaceTd.interfaceservice;

import java.util.List;
import marketplaceT.marketplaceTd.modelo.rol;

/**
 *
 * @author PC
 */
public interface IRolService {
    public List<rol> listar();
    public rol listarId(int id);
    public void save(rol p);
    public void delete(int id);
}
