/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package marketplaceT.marketplaceTd.interfaceservice;

import java.util.List;
import marketplaceT.marketplaceTd.modelo.categoriaproducto;

/**
 *
 * @author PC
 */
public interface IcategoriaproductoService {
    public List<categoriaproducto> listar();
    public categoriaproducto listarId(int id);
    public void save(categoriaproducto p);
    public void delete(int id);
}
