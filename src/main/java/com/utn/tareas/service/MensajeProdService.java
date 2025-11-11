package com.utn.tareas.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

/**
 * Implementación de MensajeService para el entorno de PRODUCCIÓN.
 * Profile("prod"): Este bean SOLO se crea cuando el profile activo es "prod".
 * En producción queremos mensajes concisos, profesionales y sin emojis.
 * Los mensajes son más serios y formales, apropiados para un entorno real.
 */
@Slf4j
@Service
@Profile("prod")
public class MensajeProdService implements MensajeService {

    @Override
    public void mostrarBienvenida() {
        String mensaje = """
                
                ========================================
                SISTEMA DE GESTIÓN DE TAREAS
                ========================================
                Entorno: PRODUCCIÓN
                Aplicación iniciada correctamente
                ========================================
                """;

        System.out.println(mensaje);
        log.info("Sistema iniciado en entorno de producción");
    }

    @Override
    public void mostrarDespedida() {
        String mensaje = """
                
                ========================================
                Sistema finalizado correctamente
                ========================================
                """;

        System.out.println(mensaje);
        log.info("Sistema finalizado - Entorno de producción");
    }
}
