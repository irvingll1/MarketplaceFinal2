/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package marketplaceT.marketplaceTd.interfaceservice;

import java.util.List;
import marketplaceT.marketplaceTd.modelo.calificacion;

/**
 *
 * @author PC
 */
public interface IcalificacionService {
    public List<calificacion> listar();
    public calificacion listarId(int id);
    public void save(calificacion p);
    public void delete(int id);
}
