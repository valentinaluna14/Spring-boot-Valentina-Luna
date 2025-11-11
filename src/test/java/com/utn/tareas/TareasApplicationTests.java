package com.utn.tareas;

import com.utn.tareas.model.Prioridad;
import com.utn.tareas.model.Tarea;
import com.utn.tareas.service.MensajeService;
import com.utn.tareas.service.TareaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@SpringBootTest
class TareasApplicationTests {
        @MockBean
        private TareaService tareaService;

        @MockBean
        private MensajeService mensajeService;

        private TareasApplication tareasApplication;

        @BeforeEach
        void setUp() {
            // Creamos una instancia de la aplicación inyectando los mocks
            tareasApplication = new TareasApplication(tareaService, mensajeService);

            // Configuramos los mocks con comportamientos simulados
            Tarea tarea1 = new Tarea(1L, "Probar funcionalidad", false, Prioridad.ALTA);
            Tarea tarea2 = new Tarea(2L, "Escribir documentación", false, Prioridad.MEDIA);
            List<Tarea> listaInicial = List.of(tarea1, tarea2);

            when(tareaService.listarTodas()).thenReturn(listaInicial);
            when(tareaService.agregarTarea(anyString(), any())).thenReturn(new Tarea(3L, "Documentar el código con Javadoc", false, Prioridad.MEDIA));
            when(tareaService.listarPendientes()).thenReturn(listaInicial);
            when(tareaService.marcarComoCompletada(anyLong())).thenReturn(true);
            when(tareaService.listarCompletadas()).thenReturn(List.of());
            when(tareaService.debeMostrarEstadisticas()).thenReturn(false);
        }

        @Test
        void testFlujoPrincipalEjecutaSinErrores() {
            // Verificamos que el metodo run() no lanza ninguna excepción
            assertDoesNotThrow(() -> tareasApplication.run());

            // Además, verificamos que los métodos esperados fueron invocados
            verify(mensajeService).mostrarBienvenida();
            verify(mensajeService).mostrarDespedida();
            verify(tareaService).listarTodas();
            verify(tareaService).agregarTarea(anyString(), any(Prioridad.class));
            verify(tareaService, atLeastOnce()).listarPendientes();
            verify(tareaService, atLeastOnce()).marcarComoCompletada(anyLong());
        }
    }
