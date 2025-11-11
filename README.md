
# ✅ Sistema de Gestión de Tareas – Spring Boot

## 📘 Descripción General

Este proyecto implementa una aplicación de gestión de tareas (*To-Do List*) desarrollada con **Spring Boot**, siguiendo buenas prácticas de arquitectura y diseño orientadas al desarrollo profesional.
Permite crear, listar, completar y administrar tareas con diferentes prioridades, aplicando principios SOLID, inyección de dependencias y configuración por perfiles.

El trabajo fue realizado como **Trabajo Práctico** para la materia **Desarrollo de Software** de la **Ingeniería en Sistemas de Información (UTN)**.

---

## ⚙️ Tecnologías

* **Java 21**
* **Spring Boot 3.3.5**
* **Gradle 9**
* **Lombok**
* **Spring DevTools**
* **SLF4J + Logback**

---

## 🏗️ Arquitectura

El proyecto está organizado bajo una **arquitectura en capas**, garantizando separación de responsabilidades y mantenibilidad.

```
com.utn.tareas/
├── model/                    # Entidades del dominio
│   ├── Tarea.java
│   └── Prioridad.java
├── repository/               # Acceso a datos
│   └── TareaRepository.java
├── service/                  # Lógica de negocio
│   ├── TareaService.java
│   ├── MensajeService.java
│   ├── MensajeDevService.java
│   └── MensajeProdService.java
└── TareasApplication.java    # Entry point
```

### 📌 Componentes principales

#### ✅ **Modelo**

* **Tarea**: entidad que representa una tarea con ID, descripción, estado y prioridad.
* **Prioridad**: enum con valores **ALTA**, **MEDIA**, **BAJA**.

#### ✅ **Repositorio**

* `TareaRepository`: simula una base de datos en memoria utilizando una lista.

#### ✅ **Servicios**

* `TareaService`: lógica de negocio, control de límites, estadísticas.
* `MensajeService`: interfaz para mostrar mensajes.
* `MensajeDevService` y `MensajeProdService`: implementaciones según el perfil activo.

---

## 🧩 Conceptos de Spring Aplicados

### 🔹 Inyección de Dependencias

Se utiliza **inyección por constructor**, recomendada como mejor práctica:

```java
@Service
@RequiredArgsConstructor
public class TareaService {
    private final TareaRepository tareaRepository;
}
```

Ventajas:

* Dependencias explícitas
* Facilita el testing
* Evita errores de inicialización

---

### 🔹 Estereotipos de Spring

* `@Repository` → acceso a datos
* `@Service` → lógica de negocio
* `@Component` → componentes genéricos

Spring detecta estas clases mediante *component scanning* y las gestiona como *beans*.

---

### 🔹 Application Context (IoC Container)

Spring:

1. Escanea clases con estereotipos
2. Crea los beans
3. Resuelve e inyecta dependencias
4. Administra su ciclo de vida

---

### 🔹 Configuración mediante Properties

```properties
app.nombre=Sistema de Gestión de Tareas UTN
app.max-tareas=10
app.mostrar-estadisticas=true
```

Uso en código:

```java
@Value("${app.max-tareas}")
private int maxTareas;
```

Permite modificar configuraciones sin recompilar.

---

### 🔹 Profiles (dev / prod)

Ejemplo:

```java
@Service
@Profile("dev")
public class MensajeDevService implements MensajeService { ... }
```

✔ Permite comportamientos distintos según el entorno
✔ Facilita debugging y testing
✔ Evita duplicación de código

---

### 🔹 CommandLineRunner

Ejecución de código al iniciar la aplicación:

```java
@SpringBootApplication
public class TareasApplication implements CommandLineRunner {
    @Override
    public void run(String... args) { ... }
}
```

---

## 🚀 Ejecución del Proyecto

### 1️⃣ Clonar

```bash
git clone https://github.com/TU_USUARIO/tareas-spring-boot.git
cd tareas-spring-boot
```

### 2️⃣ Compilar

```bash
# Windows
gradlew.bat build

# Linux/Mac
./gradlew build
```

### 3️⃣ Ejecutar

```bash
# Windows
gradlew.bat bootRun

# Linux/Mac
./gradlew bootRun
```

---

## 🔧 Selección de Profiles

### ✅ Desde `application.properties`

```properties
spring.profiles.active=dev
# spring.profiles.active=prod
```

### ✅ Desde consola

```bash
./gradlew bootRun --args='--spring.profiles.active=dev'
```

### ✅ Con variable de entorno

```bash
export SPRING_PROFILES_ACTIVE=prod
```

---

## 🖼️ Capturas

### 🟢 Modo DEV

* Mensajes detallados
* Estadísticas activas
* Límite bajo
* Logs en DEBUG

### 🔵 Modo PROD

* Mensajes concisos
* Límite alto
* Sin estadísticas
* Logs mínimos

---

## 📚 Aprendizajes Principales

* Comprensión del **IoC Container** y su importancia.
* Uso correcto de **inyección de dependencias**.
* Aplicación de **arquitectura en capas**.
* Manejo de **profiles** para diferentes entornos.
* Configuración externa mediante **properties**.
* Uso de **Lombok** para reducir boilerplate.
* Aplicación de principios **SOLID**.

El aprendizaje más importante fue entender que Spring Boot no es “magia”: es un framework que implementa conceptos sólidos de ingeniería de software para simplificar tareas repetitivas.

---

## 👤 Información del Estudiante

* **Nombre:** Valentina Luna
* **Legajo:** 50988
* **Materia:** Desarrollo de Software
* **Carrera:** Ingeniería en Sistemas de Información
* **Universidad:** UTN FRM (Facultad Regional Mendoza)

---

## 📬 Contacto

* **Email:** [mariav.luna.utn@gmail.com](mailto:mariav.luna.utn@gmail.com)

---

## 📄 Licencia

Proyecto desarrollado con fines educativos para la UTN FRM.


---

## 📄 Licencia

Este proyecto fue desarrollado con fines educativos para la UTN FRM.
