/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package marketplaceT.marketplaceTd.interfaceservice;

import java.util.List;
import marketplaceT.marketplaceTd.modelo.persona;

/**
 *
 * @author PC
 */
public interface IPersonaService {
    public List<persona> listar();
    public persona listarId(int id);
    public void save(persona p);
    public void delete(int id);
}
