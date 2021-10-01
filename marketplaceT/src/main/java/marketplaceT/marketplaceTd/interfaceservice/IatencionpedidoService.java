/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package marketplaceT.marketplaceTd.interfaceservice;

import java.util.List;
import marketplaceT.marketplaceTd.modelo.atencionpedido;

/**
 *
 * @author PC
 */
public interface IatencionpedidoService {
    public List<atencionpedido> listar();
    public atencionpedido listarId(int id);
    public void save(atencionpedido p);
    public void delete(int id);
}
