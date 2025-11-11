package com.utn.tareas.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Clase que representa una Tarea en el sistema.
 * Data: genera getters, setters, toString(), equals() y hashCode()
 * NoArgsConstructor: genera constructor sin parámetros
 * AllArgsConstructor: genera constructor con todos los parámetros
 * Lombok elimina el código boilerplate y hace el código más limpio
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Tarea {
    private Long id;
    private String descripcion;
    private boolean completada;
    private Prioridad prioridad;
}
