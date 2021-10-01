/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package marketplaceT.marketplaceTd.interfaceservice;

import java.util.List;
import marketplaceT.marketplaceTd.modelo.provincia;

/**
 *
 * @author PC
 */
public interface IprovinciaService {
    public List<provincia> listar();
    public provincia listarId(int id);
    public void save(provincia p);
    public void delete(int id);
}
