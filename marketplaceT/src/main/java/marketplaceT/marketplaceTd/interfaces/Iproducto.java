/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package marketplaceT.marketplaceTd.interfaces;
import marketplaceT.marketplaceTd.modelo.producto;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
/**
 *
 * @author PC
 */
@Repository
public interface Iproducto extends CrudRepository<producto, Integer>{
    
}
