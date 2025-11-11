package com.utn.tareas.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

/**
 * Implementación de MensajeService para el entorno de DESARROLLO.
 * Profile("dev"): Esta anotación hace que este bean SOLO se cree cuando
 * el profile activo sea "dev". Es una técnica profesional para adaptar
 * el comportamiento de la aplicación según el entorno.
 * En desarrollo queremos mensajes detallados, amigables y con emojis
 * para hacer la experiencia más agradable durante el desarrollo.
 */
@Slf4j
@Service
@Profile("dev")
public class MensajeDevService implements MensajeService {

    @Override
    public void mostrarBienvenida() {
        String mensaje = """
                
                ╔═══════════════════════════════════════════════════════════╗
                ║                                                           ║
                ║   🚀 ¡BIENVENIDO AL SISTEMA DE GESTIÓN DE TAREAS! 🚀    ║
                ║                                                           ║
                ║   Modo: DESARROLLO (DEV)                                 ║
                ║   Version: 1.0.0-SNAPSHOT                                ║
                ║                                                           ║
                ║   👨‍💻 Estás en modo desarrollo                            ║
                ║   📝 Logs detallados activados                            ║
                ║   🔧 DevTools habilitado para hot reload                 ║
                ║                                                           ║
                ╚═══════════════════════════════════════════════════════════╝
                """;

        System.out.println(mensaje);
        log.info("Aplicación iniciada en modo DESARROLLO");
    }

    @Override
    public void mostrarDespedida() {
        String mensaje = """
                
                ╔═══════════════════════════════════════════════════════════╗
                ║                                                           ║
                ║   👋 ¡GRACIAS POR USAR EL SISTEMA!                       ║
                ║                                                           ║
                ║   💡 Recordatorio: Estás en modo DESARROLLO              ║
                ║   🔄 Los cambios en el código se recargarán automáti    ║
                ║      camente con DevTools                                ║
                ║                                                           ║
                ║   ¡Feliz coding! 😊                                      ║
                ║                                                           ║
                ╚═══════════════════════════════════════════════════════════╝
                """;

        System.out.println(mensaje);
        log.info("Aplicación finalizada - Modo DESARROLLO");
    }
}
