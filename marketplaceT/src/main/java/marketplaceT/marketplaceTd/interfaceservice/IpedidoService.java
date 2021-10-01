/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package marketplaceT.marketplaceTd.interfaceservice;

import java.util.List;
import marketplaceT.marketplaceTd.modelo.pedido;

/**
 *
 * @author PC
 */
public interface IpedidoService {
    public List<pedido> listar();
    public pedido listarId(int id);
    public void save(pedido p);
    public void delete(int id);
}
