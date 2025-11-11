package com.utn.tareas.service;

/**
 * Interfaz que define el contrato para servicios de mensajes.
 * Usar interfaces permite:
 * - Desacoplar la implementación de la definición
 * - Tener múltiples implementaciones (dev, prod) intercambiables
 * - Facilitar testing con mocks
 * - Aplicar el principio de inversión de dependencias (SOLID)
 */
public interface MensajeService {

    /**
     * Muestra un mensaje de bienvenida al iniciar la aplicación.
     */
    void mostrarBienvenida();

    /**
     * Muestra un mensaje de despedida al finalizar la aplicación.
     */
    void mostrarDespedida();
}
