/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package marketplaceT.marketplaceTd.interfaces;
import java.sql.Date;
import java.util.List;
import marketplaceT.marketplaceTd.modelo.pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
/**
 *
 * @author PC
 */
@Repository
public interface Ipedido extends JpaRepository<pedido, Integer>{
     
    @Query("SELECT Distinct  p FROM pedido p WHERE p.fechapedido BETWEEN ?1 AND ?2 ")
    public List<pedido> findByAllData(Date des,Date has);
    
    @Query("Select p from pedido p where p.estado=1")
    public List<pedido> pedidosEntregados();
    
    @Query("Select p from pedido p where p.estado=0")
    public List<pedido> pedidosNoEntregados();
    
    @Query("Select p from pedido p where p.total>=:total")
    public List<pedido> pedidosMontoMayor(double total);
    
    @Query("Select p from pedido p where p.total<=:total")
    public List<pedido> pedidosMontoMenor(double total);
    
}
