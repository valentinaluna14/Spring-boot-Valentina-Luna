package com.utn.tareas.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PruebaController {

    @GetMapping("/")
    public String inicio() {
        return "Servidor funcionando correctamente en la raíz";
    }

    @GetMapping("/hola")
    public String hola() {
        return "¡Hola desde el servidor Spring Boot!";
    }
}
