/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package marketplaceT.marketplaceTd.interfaces;
import marketplaceT.marketplaceTd.modelo.tienda;
import org.springframework.data.repository.CrudRepository;
/**
 *
 * @author PC
 */
public interface Itienda extends CrudRepository<tienda, Integer>{
    
}
