/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package marketplaceT.marketplaceTd.interfaceservice;

import java.util.List;
import marketplaceT.marketplaceTd.modelo.distrito;

/**
 *
 * @author PC
 */
public interface IdistritoService {
    public List<distrito> listar();
    public distrito listarId(int id);
    public void save(distrito p);
    public void delete(int id);
}
