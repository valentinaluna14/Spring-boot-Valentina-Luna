package com.utn.tareas.repository;

import com.utn.tareas.model.Prioridad;
import com.utn.tareas.model.Tarea;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Repository: Estereotipo de Spring que marca esta clase como componente de acceso a datos.
 * Spring la registra como bean y la inyecta automáticamente donde se necesite.
 * Slf4j: Anotación de Lombok que genera automáticamente un logger llamado "log"
 * Esta clase simula una base de datos usando una lista en memoria.
 */
@Slf4j
@Repository
public class TareaRepository {

    private final List<Tarea> tareas = new ArrayList<>();
    private final AtomicLong contador = new AtomicLong(1);

    /**
     * Constructor que inicializa el repositorio con tareas de ejemplo.
     * Se ejecuta cuando Spring crea el bean.
     */
    public TareaRepository() {
        log.debug("Inicializando TareaRepository con datos de ejemplo");

        tareas.add(new Tarea(contador.getAndIncrement(),
                "Estudiar Spring Boot", false, Prioridad.ALTA));
        tareas.add(new Tarea(contador.getAndIncrement(),
                "Completar el TP de Programación III", false, Prioridad.ALTA));
        tareas.add(new Tarea(contador.getAndIncrement(),
                "Revisar conceptos de inyección de dependencias", false, Prioridad.MEDIA));
        tareas.add(new Tarea(contador.getAndIncrement(),
                "Practicar Gradle y configuración de proyectos", true, Prioridad.MEDIA));
        tareas.add(new Tarea(contador.getAndIncrement(),
                "Leer documentación de Lombok", true, Prioridad.BAJA));

        log.info("Repositorio inicializado con {} tareas", tareas.size());
    }

    /**
     * Guarda una nueva tarea generando automáticamente su ID.
     * @param tarea La tarea a guardar
     * @return La tarea guardada con su ID asignado
     */
    public Tarea guardar(Tarea tarea) {
        tarea.setId(contador.getAndIncrement());
        tareas.add(tarea);
        log.debug("Tarea guardada: {}", tarea);
        return tarea;
    }

    /**
     * Obtiene todas las tareas almacenadas.
     * @return Lista de todas las tareas
     */
    public List<Tarea> obtenerTodas() {
        log.debug("Obteniendo todas las tareas. Total: {}", tareas.size());
        return new ArrayList<>(tareas); // Retorna una copia para evitar modificaciones externas
    }

    /**
     * Busca una tarea por su ID.
     * @param id El ID de la tarea a buscar
     * @return Optional con la tarea si existe, Optional.empty() si no existe
     */
    public Optional<Tarea> buscarPorId(Long id) {
        log.debug("Buscando tarea con ID: {}", id);
        return tareas.stream()
                .filter(t -> t.getId().equals(id))
                .findFirst();
    }

    /**
     * Elimina una tarea por su ID.
     * @param id El ID de la tarea a eliminar
     * @return true si se eliminó, false si no existía
     */
    public boolean eliminarPorId(Long id) {
        log.debug("Intentando eliminar tarea con ID: {}", id);
        boolean eliminada = tareas.removeIf(t -> t.getId().equals(id));
        if (eliminada) {
            log.info("Tarea con ID {} eliminada exitosamente", id);
        } else {
            log.warn("No se encontró tarea con ID {} para eliminar", id);
        }
        return eliminada;
    }

    /**
     * Cuenta el total de tareas almacenadas.
     * @return Número total de tareas
     */
    public long contar() {
        return tareas.size();
    }
}
