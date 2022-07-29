package com.example.Alkemy.Disney.controllers;

import com.example.Alkemy.Disney.Servicios.UsuarioServicio;
import com.example.Alkemy.Disney.dtos.UsuarioDTO;
import com.example.Alkemy.Disney.models.Usuario;
import com.example.Alkemy.Disney.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class UsuarioController {

    @Autowired
    private UsuarioServicio usuarioServicio;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JavaMailSender javaMailSender;

    @GetMapping("/usuarios")
    public List<UsuarioDTO> usuarios(){
        return usuarioServicio.getusuarios();
    }

    @PostMapping("/auth/register")
    public ResponseEntity<Object> registro(@RequestParam String nombre , @RequestParam String apellido,
                                            @RequestParam String email, @RequestParam String contraseña) throws MessagingException, UnsupportedEncodingException {
        if(nombre.isEmpty() || apellido.isEmpty() || email.isEmpty() || contraseña.isEmpty()){
            return new  ResponseEntity<>("los campos no pueden estar vacios", HttpStatus.FORBIDDEN);
        }

        if(usuarioServicio.findByEmail(email) != null){
            return new ResponseEntity<>("email en uso",HttpStatus.FORBIDDEN);
        }

        Usuario usuario = new Usuario(nombre,apellido,email,passwordEncoder.encode(contraseña));
        usuarioServicio.guardarUsuario(usuario);
        sendVerificationEmail(usuario);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }


    private void sendVerificationEmail(Usuario newClient) throws MessagingException, UnsupportedEncodingException {
        String toAddress = newClient.getEmail();
        String fromAddress = "e.music.school.verified@gmail.com";
        String senderName = "Disney+";
        String subject = "Bienvenido a Disney+";
        String content = "<h2 style=\"color:black; font-family:Poppins, sans-serif; \">Hola [[name]]</h2>"
                + "<img src=\"https://i.imgur.com/I3EMJb4.png\" alt=\"logEMusicSchool\"/> <br>"
                + "<h4 style=\"color:black; font-family:Poppins, sans-serif; \"> Gracias por registrarte en Disney+ diviertete!</h4>"
                ;

        MimeMessage message = javaMailSender.createMimeMessage();

        MimeMessageHelper helper = new MimeMessageHelper(message);

        helper.setFrom(fromAddress, senderName);
        helper.setTo(toAddress);
        helper.setSubject(subject);

        content = content.replace("[[name]]",newClient.getNombre() + " " +  " " + newClient.getApellido());



        helper.setText(content,true);
        javaMailSender.send(message);
    }
}
