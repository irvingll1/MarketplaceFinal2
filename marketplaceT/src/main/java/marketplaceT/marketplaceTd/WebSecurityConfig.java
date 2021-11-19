/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package marketplaceT.marketplaceTd;

import javax.sql.DataSource;
import marketplaceT.marketplaceTd.util.LoginSuccessMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;

/**
 *
 * @author PC
 */
@EnableGlobalMethodSecurity(securedEnabled = true)
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private DataSource dataSource;

    @Autowired
    private LoginSuccessMessage successMesage;
    
    
    private int idCliente;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers("/index", "/registrocliente/**",
                "/registrovendedor/**","/registro/**", "/savepersonacliente/**","/savepersonavendedor/**",
                "/carrito/**","/static/**", "/tienda/**", "/home", "/**", "/css/**", 
                "/js/**","/comprando/**", "/images/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .successHandler(successMesage)
                .loginPage("/login")
                .permitAll()
                .and()
                .logout().permitAll();
    }
    
    @Override
    public void configure(WebSecurity web) throws Exception {
        web
                .ignoring()
                .antMatchers("/resources/static/**","/resources/static/js/**","/agregacarrito/**");
    }

    @Autowired
    public void configurerSecurityGlobal(AuthenticationManagerBuilder builder) throws Exception {
        builder.jdbcAuthentication()
                .dataSource(dataSource)
                .usersByUsernameQuery("Select usuario,contrasena,estado from usuario where usuario=?")
                .authoritiesByUsernameQuery("SELECT u.usuario, r.nombre FROM usuario u inner JOIN rol r on u.id=r.idusuario WHERE u.usuario=?")
                .passwordEncoder(passwordEncoder());
    }

    @Bean
    public static NoOpPasswordEncoder passwordEncoder() {
        return (NoOpPasswordEncoder) NoOpPasswordEncoder.getInstance();
    }
}
