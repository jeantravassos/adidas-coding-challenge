package com.jeantravassos.emailservice.web.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

import static org.apache.commons.lang3.StringUtils.isBlank;

@RestController
@RequestMapping("/api/emails")
@Slf4j
public class EmailController {

    @GetMapping("/send/{email}")
    public ResponseEntity<String> sendEmail(@PathVariable String email) {
        log.info("email-service - EmailController - sendEmail()");

        if (isBlank(email)) {
            log.error("Mandatory email is missing");
            return ResponseEntity.unprocessableEntity().body("Email is missing");
        }
        try {
            InternetAddress internetAddress = new InternetAddress(email);
            internetAddress.validate();
        } catch (AddressException e) {
            return ResponseEntity.unprocessableEntity().body("Email format is incorrect");
        }

        String str = "E-mail sent to:" + email;

        log.info("{}", str);
        return ResponseEntity.ok(str);
    }

}
