/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package marketplaceT.marketplaceTd.interfaces;

import java.util.List;
import marketplaceT.marketplaceTd.modelo.detallepedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author PC
 */
@Repository
public interface Idetallepedido extends JpaRepository<detallepedido, Integer>{
    
//    @Query("select p from detallepedido p where p.nombre like CONCAT('%',:nombre,'%')")
//    List<detallepedido> buscarproducto(String nombre);
}
