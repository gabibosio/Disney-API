package com.example.Alkemy.Disney.controllers;

import com.example.Alkemy.Disney.Services.ClientService;
import com.example.Alkemy.Disney.dtos.ClientDTO;
import com.example.Alkemy.Disney.models.Client;
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

@RestController
public class ClientController {

    @Autowired
    private ClientService clientService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JavaMailSender javaMailSender;

    @GetMapping("/users")
    public List<ClientDTO> getUsers(){
        return clientService.getUsers();
    }

    @PostMapping("/auth/register")
    public ResponseEntity<Object> register(@RequestParam String name , @RequestParam String lastName,
                                            @RequestParam String email, @RequestParam String password) throws MessagingException, UnsupportedEncodingException {
        if(name.isEmpty() || lastName.isEmpty() || email.isEmpty() || password.isEmpty()){
            return new  ResponseEntity<>("Missing Data", HttpStatus.FORBIDDEN);
        }

        if(clientService.findByEmail(email) != null){
            return new ResponseEntity<>("email already in use",HttpStatus.FORBIDDEN);
        }

        Client client = new Client(name,lastName,email,passwordEncoder.encode(password));
        clientService.saveUser(client);
        sendVerificationEmail(client);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }


    private void sendVerificationEmail(Client newClient) throws MessagingException, UnsupportedEncodingException {
        String toAddress = newClient.getEmail();
        String fromAddress = "e.music.school.verified@gmail.com";
        String senderName = "Disney+";
        String subject = "Bienvenido a Disney+";
        String content = "<h2 style=\"color:black; font-family:Poppins, sans-serif; \">Hola [[name]]</h2>"
                + "<img src=\"https://i.blogs.es/51dbb0/catalogo-disney-plus/450_1000.jpg\" alt=\"Disney+\"/> <br>"
                + "<h4 style=\"color:black; font-family:Poppins, sans-serif; \"> Gracias por registrarte en Disney+ diviertete!</h4>"
                ;

        MimeMessage message = javaMailSender.createMimeMessage();

        MimeMessageHelper helper = new MimeMessageHelper(message);

        helper.setFrom(fromAddress, senderName);
        helper.setTo(toAddress);
        helper.setSubject(subject);

        content = content.replace("[[name]]",newClient.getName() + " " +  " " + newClient.getLastName());


        helper.setText(content,true);
        javaMailSender.send(message);
    }
}
