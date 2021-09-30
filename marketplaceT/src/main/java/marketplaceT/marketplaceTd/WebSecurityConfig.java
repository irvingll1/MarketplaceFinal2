/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package marketplaceT.marketplaceTd;

import javax.sql.DataSource;
import marketplaceT.marketplaceTd.util.LoginSuccessMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

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
    private BCryptPasswordEncoder passEncoder;
    
    @Autowired
    private LoginSuccessMessage successMesage;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers("/index","/home","/","/css/**","/js/**","/images/**").permitAll()
//        .antMatchers("/views/clientes/").hasAnyRole("USER")
//        .antMatchers("/views/clientes/create").hasAnyRole("ADMIN")
//        .antMatchers("/views/clientes/save").hasAnyRole("ADMIN")
//        .antMatchers("/views/clientes/edit/**").hasAnyRole("ADMIN")
//        .antMatchers("/views/clientes/delete/**").hasAnyRole("ADMIN")
        .anyRequest().authenticated()
        .and()
        .formLogin()
                .successHandler(successMesage)
                .loginPage("/login")
        .permitAll()
        .and()
        .logout().permitAll();
    }
    
    
    
    @Autowired
    public void configurerSecurityGlobal(AuthenticationManagerBuilder builder) throws Exception{
        builder.jdbcAuthentication()
        .dataSource(dataSource)
        .passwordEncoder(passEncoder)
        .usersByUsernameQuery("Select usuario,contrasena,estado from usuario where usuario=?")
        .authoritiesByUsernameQuery("SELECT u.usuario, r.nombre FROM usuario u inner JOIN rol r on u.id=r.idusuario WHERE u.usuario=?");
    }
    
}
