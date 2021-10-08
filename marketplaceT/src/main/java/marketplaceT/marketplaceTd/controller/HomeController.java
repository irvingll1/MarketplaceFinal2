/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package marketplaceT.marketplaceTd.controller;

import java.security.Principal;
import java.util.List;
import marketplaceT.marketplaceTd.interfaceservice.IPersonaService;
import marketplaceT.marketplaceTd.modelo.persona;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 *
 * @author PC
 */
@Controller
public class HomeController {
    
//    @Autowired
//    private IPersonaService personaService; 
//    
//    @GetMapping({"/index","/home","/"})
//    public String index(Model model,Principal principal) {
//        
//         if(principal!=null){
//            List<persona> listaper= personaService.buscarnombre(Integer.parseInt(principal.getName()));   
//            System.out.println("devuelve"+listaper.toString());
//            model.addAttribute("objetopersona",listaper.get(0).getNombre());
//         }
//        return "home";
//    }
}
