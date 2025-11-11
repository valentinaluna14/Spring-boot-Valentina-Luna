package com.utn.tareas.service;

import com.utn.tareas.model.Prioridad;
import com.utn.tareas.model.Tarea;
import com.utn.tareas.repository.TareaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Service: Estereotipo de Spring que marca esta clase como componente de lógica de negocio.
 * Contiene las reglas de negocio y coordina operaciones entre diferentes capas.
 * RequiredArgsConstructor: Lombok genera constructor con todos los campos "final".
 * Esto implementa inyección de dependencias por constructor (MEJOR PRÁCTICA).
 * Slf4j: Genera automáticamente un logger para esta clase.
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class TareaService {

    // Inyección por constructor (inmutable y testeable)
    private final TareaRepository tareaRepository;

    // @Value inyecta valores desde application.properties
    @Value("${app.nombre}")
    private String nombreApp;

    @Value("${app.max-tareas}")
    private int maxTareas;

    @Value("${app.mostrar-estadisticas}")
    private boolean mostrarEstadisticas;

    /**
     * Agrega una nueva tarea al sistema.
     * Valida que no se supere el límite máximo de tareas.
     */
    public Tarea agregarTarea(String descripcion, Prioridad prioridad) {
        log.info("Intentando agregar nueva tarea: {} con prioridad {}", descripcion, prioridad);

        // Validación de límite de tareas
        if (tareaRepository.contar() >= maxTareas) {
            String mensaje = String.format(
                    "No se puede agregar la tarea. Se alcanzó el límite máximo de %d tareas", maxTareas);
            log.error(mensaje);
            throw new IllegalStateException(mensaje);
        }

        Tarea nuevaTarea = new Tarea();
        nuevaTarea.setDescripcion(descripcion);
        nuevaTarea.setPrioridad(prioridad);
        nuevaTarea.setCompletada(false);

        Tarea tareaGuardada = tareaRepository.guardar(nuevaTarea);
        log.info("Tarea agregada exitosamente con ID: {}", tareaGuardada.getId());
        return tareaGuardada;
    }

    /**
     * Obtiene todas las tareas del sistema.
     */
    public List<Tarea> listarTodas() {
        log.debug("Listando todas las tareas");
        return tareaRepository.obtenerTodas();
    }

    /**
     * Filtra y retorna solo las tareas pendientes (no completadas).
     */
    public List<Tarea> listarPendientes() {
        log.debug("Listando tareas pendientes");
        return tareaRepository.obtenerTodas().stream()
                .filter(t -> !t.isCompletada())
                .collect(Collectors.toList());
    }

    /**
     * Filtra y retorna solo las tareas completadas.
     */
    public List<Tarea> listarCompletadas() {
        log.debug("Listando tareas completadas");
        return tareaRepository.obtenerTodas().stream()
                .filter(Tarea::isCompletada)
                .collect(Collectors.toList());
    }

    /**
     * Marca una tarea como completada.
     * @param id El ID de la tarea a completar
     * @return true si se completó exitosamente, false si no se encontró
     */
    public boolean marcarComoCompletada(Long id) {
        log.info("Marcando tarea {} como completada", id);
        return tareaRepository.buscarPorId(id)
                .map(tarea -> {
                    tarea.setCompletada(true);
                    log.info("Tarea {} completada exitosamente", id);
                    return true;
                })
                .orElseGet(() -> {
                    log.warn("No se encontró tarea con ID {} para completar", id);
                    return false;
                });
    }

    /**
     * Genera estadísticas sobre las tareas del sistema.
     * @return String formateado con las estadísticas
     */
    public String obtenerEstadisticas() {
        long total = tareaRepository.contar();
        long completadas = listarCompletadas().size();
        long pendientes = listarPendientes().size();

        String estadisticas = String.format("""
                
                ═══════════════════════════════════════
                📊 ESTADÍSTICAS DE TAREAS
                ═══════════════════════════════════════
                Total de tareas:      %d
                ✅ Completadas:       %d
                ⏳ Pendientes:        %d
                ═══════════════════════════════════════
                """, total, completadas, pendientes);

        log.debug("Estadísticas generadas: Total={}, Completadas={}, Pendientes={}",
                total, completadas, pendientes);

        return estadisticas;
    }

    /**
     * Muestra la configuración actual de la aplicación.
     * Útil para debugging y verificar qué profile está activo.
     */
    public void mostrarConfiguracion() {
        String config = String.format("""
                
                ⚙️  CONFIGURACIÓN DE LA APLICACIÓN
                ═══════════════════════════════════════
                Nombre:                    %s
                Máximo de tareas:          %d
                Mostrar estadísticas:      %s
                ═══════════════════════════════════════
                """, nombreApp, maxTareas, mostrarEstadisticas ? "Sí" : "No");

        System.out.println(config);
        log.info("Configuración mostrada");
    }

    /**
     * Retorna si se deben mostrar estadísticas según la configuración.
     */
    public boolean debeMostrarEstadisticas() {
        return mostrarEstadisticas;
    }
}
