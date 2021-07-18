package com.mokhs.springplayground.springvalidation.controller;

import com.mokhs.springplayground.springvalidation.dto.User;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/v1")
public class Api {

    @PostMapping("user")
    public ResponseEntity user(@RequestBody @Valid User user, BindingResult bindingResult) {
        System.out.println(user);

        if (bindingResult.hasErrors()) {
            StringBuilder sb = new StringBuilder();

            bindingResult.getAllErrors().forEach(objectError -> {
                FieldError fieldError = (FieldError) objectError;
                String message = objectError.getDefaultMessage();
                System.out.println(message);

                sb.append("field : " + fieldError.getField() + "\n");
                sb.append("message : " + message + "\n");

                sb.append("field : " + ((FieldError) objectError).getField()+ "\n");
                sb.append("message : " + objectError.getDefaultMessage() + "\n");
            });

            return ResponseEntity.badRequest().body(sb.toString());
        }
        return ResponseEntity.ok(user);
    }
}
