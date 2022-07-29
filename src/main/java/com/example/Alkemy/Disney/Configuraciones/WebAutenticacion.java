package com.example.Alkemy.Disney.Configuraciones;

import com.example.Alkemy.Disney.Servicios.UsuarioServicio;
import com.example.Alkemy.Disney.models.Usuario;
import com.example.Alkemy.Disney.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.GlobalAuthenticationConfigurerAdapter;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class WebAutenticacion extends GlobalAuthenticationConfigurerAdapter {

    @Autowired
    private UsuarioServicio usuarioServicio;

    @Override
    public void init(AuthenticationManagerBuilder auth) throws Exception {

        auth.userDetailsService(inputName-> {

            Usuario usuario = usuarioServicio.findByEmail(inputName);

            if (usuario != null) {

                if (usuario.getEmail().equals("admin@admin.com")) {

                    return new User(usuario.getEmail(), usuario.getContraseña(),

                            AuthorityUtils.createAuthorityList("ADMIN"));

                }else {

                    return new User(usuario.getEmail(), usuario.getContraseña(),

                            AuthorityUtils.createAuthorityList("CLIENT"));
                }

            } else {

                throw new UsernameNotFoundException("Unknown user: " + inputName);

            }

        });

    }



    @Bean
    public PasswordEncoder passwordEncoder() {

        return PasswordEncoderFactories.createDelegatingPasswordEncoder();

    }

}


