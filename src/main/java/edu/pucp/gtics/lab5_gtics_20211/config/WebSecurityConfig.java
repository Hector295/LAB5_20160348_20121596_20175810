package edu.pucp.gtics.lab5_gtics_20211.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {


    @Override
    protected void configure(HttpSecurity http) throws Exception {

        //2. Para configurar la ruta del loginForm personalizado
        http.formLogin().loginPage("/loginForm").loginProcessingUrl("/processLogin")
                .usernameParameter("correo").
                //A. Para que redireccione por ROL al entrar -> Login Controller
                        defaultSuccessUrl("/signInRedirect",true);

        //1. Protegiendo las rutas con ROLES
        http.authorizeRequests()
                .antMatchers("/employee","/employee/**").hasAnyAuthority("admin","logistica") //hasAnyAuthority: DOS a + roles
                .antMatchers("/shipper","/shipper/**").hasAuthority("admin") //hasAuthority: UN rol
                .anyRequest().permitAll(); //Las demás rutas están permitidas

        //Para cerrar sesión.
        http.logout();
               
    }

    @Autowired
    DataSource datasource;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        auth.jdbcAuthentication()
                .dataSource(datasource)
                .passwordEncoder(new BCryptPasswordEncoder())
                .usersByUsernameQuery("select u.correo, u.password, u.enabled from usuarios u WHERE u.correo = ?") //email, pwd, activo
                //Para obtener la credencial y el rol (no pwd)
                .authoritiesByUsernameQuery("select u.correo, u.autorizacion from usuarios u where u.correo = ? and u.enabled = 1");
    }

}