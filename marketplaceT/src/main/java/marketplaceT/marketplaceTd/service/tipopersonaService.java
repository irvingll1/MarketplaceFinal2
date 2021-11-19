package marketplaceT.marketplaceTd.service;

import java.util.List;
import marketplaceT.marketplaceTd.interfaces.ItipoPersona;
import marketplaceT.marketplaceTd.interfaceservice.ITipoPersonaService;
import marketplaceT.marketplaceTd.modelo.tipopersona;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author PC
 */
@Service
public class tipopersonaService implements ITipoPersonaService{
    @Autowired
    private ItipoPersona data;

    @Override
    public List<tipopersona> listar() {
        return (List<tipopersona>)data.findAll();
    }

    @Override
    public tipopersona listarId(int id) {
        return data.findById(id).orElse(null);
    }

    @Override
    public void save(tipopersona p) {
         data.save(p);
    }

    @Override
    public void delete(int id) {
        data.deleteById(id);
    }
    
    
    
}
