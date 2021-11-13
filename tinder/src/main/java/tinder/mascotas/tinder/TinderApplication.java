package tinder.mascotas.tinder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import tinder.mascotas.tinder.servicios.UsuarioServicio;

@SpringBootApplication
public class TinderApplication extends WebSecurityConfigurerAdapter{
    
    @Autowired
    private UsuarioServicio usuarioServicio;
    
    public static void main(String[] args) {
        SpringApplication.run(TinderApplication.class, args);
    }
    
    
//    //ESTE METODO LE DICE A SPRING SECURITY CUAL ES EL SERVICIO QUE VAMOS A UTILIZAR PARA AUTENTIFICAR AL USUARIO
//    @Autowired
//    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception{
//        auth.userDetailsService(usuarioServicio).passwordEncoder(new BCryptPasswordEncoder());  //seteo de un encriptador de contraseñas
//    }
}
