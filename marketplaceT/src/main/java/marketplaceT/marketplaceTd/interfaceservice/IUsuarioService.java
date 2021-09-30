/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package marketplaceT.marketplaceTd.interfaceservice;


import marketplaceT.marketplaceTd.modelo.usuario;
import java.util.List;

public interface IUsuarioService {
    public List<usuario> listar();
    public usuario listarId(int id);
    public void save(usuario p);
    public void delete(int id);
}
