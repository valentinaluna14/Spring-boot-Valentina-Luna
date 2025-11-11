package com.utn.tareas;

import com.utn.tareas.model.Prioridad;
import com.utn.tareas.model.Tarea;
import com.utn.tareas.service.MensajeService;
import com.utn.tareas.service.TareaService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

/**
 * Clase principal de la aplicación Spring Boot.
 * SpringBootApplication: Anotación que combina:
 *   - @Configuration: Marca la clase como fuente de configuración
 *   - @EnableAutoConfiguration: Habilita la configuración automática de Spring Boot
 *   - @ComponentScan: Escanea el paquete actual y subpaquetes buscando componentes
 * CommandLineRunner: Interfaz que permite ejecutar código cuando la aplicación inicia.
 * El metodo run() se ejecuta después de que Spring inicialice todos los beans.
 * RequiredArgsConstructor: Lombok genera el constructor con los campos final,
 * implementando inyección de dependencias por constructor.
 */
@Slf4j
@SpringBootApplication
@RequiredArgsConstructor
public class TareasApplication implements CommandLineRunner {

    // Inyección de dependencias por constructor (final + @RequiredArgsConstructor)
    private final TareaService tareaService;
    private final MensajeService mensajeService;

    public static void main(String[] args) {
        SpringApplication.run(TareasApplication.class, args);
    }

    /**
     * Método que se ejecuta al iniciar la aplicación.
     * Implementa un flujo completo de operaciones con tareas.
     */
    @Override
    public void run(String... args) throws Exception {
        log.info("Iniciando flujo de la aplicación");

        // 1. Mostrar mensaje de bienvenida
        mensajeService.mostrarBienvenida();

        // 2. Mostrar la configuración actual
        tareaService.mostrarConfiguracion();

        // 3. Listar todas las tareas iniciales
        System.out.println("\n📋 TAREAS INICIALES:");
        System.out.println("═══════════════════════════════════════");
        List<Tarea> tareasIniciales = tareaService.listarTodas();
        tareasIniciales.forEach(t -> System.out.println("  • " + formatearTarea(t)));
        System.out.println("═══════════════════════════════════════");

        // 4. Agregar una nueva tarea
        System.out.println("\n➕ AGREGANDO NUEVA TAREA...");
        try {
            Tarea nuevaTarea = tareaService.agregarTarea(
                    "Documentar el código con Javadoc",
                    Prioridad.MEDIA
            );
            System.out.println("✅ Tarea agregada: " + formatearTarea(nuevaTarea));
        } catch (IllegalStateException e) {
            System.out.println("❌ Error: " + e.getMessage());
        }

        // 5. Listar tareas pendientes
        System.out.println("\n⏳ TAREAS PENDIENTES:");
        System.out.println("═══════════════════════════════════════");
        List<Tarea> pendientes = tareaService.listarPendientes();
        if (pendientes.isEmpty()) {
            System.out.println("  ¡No hay tareas pendientes!");
        } else {
            pendientes.forEach(t -> System.out.println("  • " + formatearTarea(t)));
        }
        System.out.println("═══════════════════════════════════════");

        // 6. Marcar una tarea como completada
        if (!pendientes.isEmpty()) {
            Tarea primeraPendiente = pendientes.getFirst();
            System.out.println("\n✔️  COMPLETANDO TAREA...");
            boolean completada = tareaService.marcarComoCompletada(primeraPendiente.getId());
            if (completada) {
                System.out.println("✅ Tarea completada: " + primeraPendiente.getDescripcion());
            } else {
                System.out.println("❌ No se pudo completar la tarea");
            }
        }

        // 7. Mostrar estadísticas (solo si está configurado)
        if (tareaService.debeMostrarEstadisticas()) {
            System.out.println(tareaService.obtenerEstadisticas());
        }

        // 8. Listar tareas completadas
        System.out.println("\n✅ TAREAS COMPLETADAS:");
        System.out.println("═══════════════════════════════════════");
        List<Tarea> completadas = tareaService.listarCompletadas();
        if (completadas.isEmpty()) {
            System.out.println("  ¡No hay tareas completadas aún!");
        } else {
            completadas.forEach(t -> System.out.println("  • " + formatearTarea(t)));
        }
        System.out.println("═══════════════════════════════════════");

        // 9. Mostrar mensaje de despedida
        mensajeService.mostrarDespedida();

        log.info("Flujo de la aplicación finalizado exitosamente");
    }

    /**
     * Formatea una tarea para mostrarla de manera legible.
     */
    private String formatearTarea(Tarea tarea) {
        String estado = tarea.isCompletada() ? "✅" : "⏳";
        String prioridad = switch (tarea.getPrioridad()) {
            case ALTA -> "🔴 ALTA";
            case MEDIA -> "🟡 MEDIA";
            case BAJA -> "🟢 BAJA";
        };
        return String.format("[ID: %d] %s %s - Prioridad: %s",
                tarea.getId(), estado, tarea.getDescripcion(), prioridad);
    }
}

