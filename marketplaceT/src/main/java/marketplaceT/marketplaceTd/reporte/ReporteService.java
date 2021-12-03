/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package marketplaceT.marketplaceTd.reporte;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import marketplaceT.marketplaceTd.interfaceservice.IdetallepedidoService;
import marketplaceT.marketplaceTd.interfaceservice.IpedidoService;
import marketplaceT.marketplaceTd.modelo.atencionpedido;
import marketplaceT.marketplaceTd.modelo.detallepedido;
import marketplaceT.marketplaceTd.modelo.pedido;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

/**
 *
 * @author PC
 */
@Service
public class ReporteService {
    
    @Autowired
    private IpedidoService pedidoService;
    
    @Autowired
    private IdetallepedidoService detallepedidoService;
    
    
    public void exportReport(List<detallepedido> lista) throws FileNotFoundException, JRException{
        
        String path="C:\\Users\\PC\\Desktop\\Report";
        
        //cargar y compilar       
        File file= ResourceUtils.getFile("classpath:pedido.jrxml");
        JasperReport JasperReport=JasperCompileManager.compileReport(file.getAbsolutePath());
        JRBeanCollectionDataSource dataSource= new JRBeanCollectionDataSource(lista);
        Map<String,Object> parametros = new HashMap<>();
        parametros.put("creado por", "MarketplaceUTP");
        JasperPrint jasperPrint = JasperFillManager.fillReport(JasperReport,parametros,dataSource);

        JasperExportManager.exportReportToPdfFile(jasperPrint,path+"\\pedidos.pdf");
        JasperExportManager.exportReportToHtmlFile(jasperPrint, path+"\\pagina.html");
    }
    public void exportReportAtencionPedido(List<atencionpedido> lista) throws FileNotFoundException, JRException{
        
        String path="C:\\Users\\PC\\Desktop\\Report";
        
        //cargar y compilar       
        File file= ResourceUtils.getFile("classpath:atencionpedidor.jrxml");
        JasperReport JasperReport=JasperCompileManager.compileReport(file.getAbsolutePath());
        JRBeanCollectionDataSource dataSource= new JRBeanCollectionDataSource(lista);
        Map<String,Object> parametros = new HashMap<>();
        parametros.put("creado por", "MarketplaceUTP");
        JasperPrint jasperPrint = JasperFillManager.fillReport(JasperReport,parametros,dataSource);

        JasperExportManager.exportReportToPdfFile(jasperPrint,path+"\\atencionpedidos.pdf");
        JasperExportManager.exportReportToHtmlFile(jasperPrint, path+"\\atencionppagina.html");
    }
}
