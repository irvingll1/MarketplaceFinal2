/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package marketplaceT.marketplaceTd.interfaceservice;

import java.util.List;
import marketplaceT.marketplaceTd.modelo.tipopago;

/**
 *
 * @author PC
 */
public interface ItipopagoService {
    public List<tipopago> listar();
    public tipopago listarId(int id);
    public void save(tipopago p);
    public void delete(int id);
}
