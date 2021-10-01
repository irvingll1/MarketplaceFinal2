/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package marketplaceT.marketplaceTd.interfaceservice;

import java.util.List;
import marketplaceT.marketplaceTd.modelo.detallepedido;

/**
 *
 * @author PC
 */
public interface IdetallepedidoService {
    public List<detallepedido> listar();
    public detallepedido listarId(int id);
    public void save(detallepedido p);
    public void delete(int id);
}
