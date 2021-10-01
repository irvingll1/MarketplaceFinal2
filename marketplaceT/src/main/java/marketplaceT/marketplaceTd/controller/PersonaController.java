package marketplaceT.marketplaceTd.controller;

import javax.validation.Valid;
import marketplaceT.marketplaceTd.interfaceservice.IPersonaService;
import marketplaceT.marketplaceTd.modelo.direccion;
import marketplaceT.marketplaceTd.modelo.persona;
import marketplaceT.marketplaceTd.modelo.tienda;
import marketplaceT.marketplaceTd.modelo.tipopersona;
import marketplaceT.marketplaceTd.modelo.usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author PC
 */
@Controller
@RequestMapping
public class PersonaController {
    @Autowired
    private IPersonaService personaService;
    
    
    @GetMapping("/registrop")
    public String crear(Model model) {

            direccion direccion = new direccion();
            usuario usuario = new usuario();
            //si es vendedor su tienda
            tienda tienda = new tienda();
            tipopersona tipopersona = new tipopersona();
            persona persona = new persona();
            //List<usuario> listCiudades = usuarioService.listar();

            model.addAttribute("titulo", "Formulario: Nuevo Cliente");
            model.addAttribute("direccion", direccion);
            model.addAttribute("tienda", tienda);
            model.addAttribute("tipopersona", tipopersona);
            model.addAttribute("cliente", persona);

            return "frmRegistrop";
    }
    @PostMapping("/savepersona")
    public String guardar(@Valid @ModelAttribute persona cliente, BindingResult result,
                    Model model, RedirectAttributes attribute) {
            //List<usuario> listCiudades = usuarioService.listar();

            if (result.hasErrors()) {
                    model.addAttribute("titulo", "Formulario: Nuevo Cliente");
                    model.addAttribute("cliente", cliente);
                   // model.addAttribute("ciudades", listCiudades);
                    System.out.println("Existieron errores en el formulario");			
                    return "frmCrear";
            }

            personaService.save(cliente);
            System.out.println("Cliente guardado con exito!");
            attribute.addFlashAttribute("success", "Cliente guardado con exito!");
            return "redirect:/listar";
    }
}
