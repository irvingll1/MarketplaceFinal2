/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package marketplaceT.marketplaceTd.controller;

import java.security.Principal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author PC
 */
@Controller
public class LoginController {
    
    @GetMapping("/login")
    public String login(@RequestParam(value="error",required = false) String error,
            @RequestParam(value="logout",required = false) String logout,
            Model model,Principal principal, RedirectAttributes atribute){
        
        if(error!=null){
            model.addAttribute("error","ERROR DE ACCESO: Usuario y/o constraseña son incorrectos");
        }
        if(principal!=null){
            atribute.addFlashAttribute("warning","ATENCION: usted ya inicio sesion");
            return "redirect:/index";
        }
        if(logout!=null){
            model.addAttribute("success","Atencion: Ha finalizado sesion con exito");
        }
        
        return "login";
    }
}
