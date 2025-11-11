# 📝 Sistema de Gestión de Tareas - Spring Boot

## 📄 Descripción del Proyecto

Este proyecto es una aplicación de gestión de tareas (To-Do List) desarrollada con **Spring Boot**, que implementa las mejores prácticas de desarrollo profesional. El sistema permite crear, listar, completar y gestionar tareas con diferentes niveles de prioridad, utilizando arquitectura por capas y principios SOLID.

El proyecto fue desarrollado como Trabajo Práctico para la asignatura **Programación III** de la **Tecnicatura Universitaria en Programación** de la UTN.

---

## ⚙️ Tecnologías Utilizadas

- **Java 21** (JDK 21)
- **Spring Boot 3.3.5**
- **Gradle 9** (Gestión de dependencias y build)
- **Lombok** (Reducción de código boilerplate)
- **Spring Boot DevTools** (Hot reload en desarrollo)
- **SLF4J + Logback** (Logging)

---

## 🏗️ Arquitectura del Proyecto

El proyecto sigue una arquitectura en capas profesional:

```
com.utn.tareas/
├── model/              # Entidades del dominio
│   ├── Tarea.java
│   └── Prioridad.java
├── repository/         # Capa de acceso a datos
│   └── TareaRepository.java
├── service/            # Lógica de negocio
│   ├── TareaService.java
│   ├── MensajeService.java (Interface)
│   ├── MensajeDevService.java
│   └── MensajeProdService.java
└── TareasApplication.java  # Clase principal
```

### 📦 Componentes Principales

#### 1. **Modelo (model)**
- **Tarea**: Entidad que representa una tarea con ID, descripción, estado y prioridad
- **Prioridad**: Enum con valores ALTA, MEDIA, BAJA

#### 2. **Repositorio (repository)**
- **TareaRepository**: Componente marcado con `@Repository` que simula una base de datos en memoria usando una lista. Gestiona el almacenamiento y recuperación de tareas.

#### 3. **Servicio (service)**
- **TareaService**: Componente `@Service` con toda la lógica de negocio. Coordina operaciones, valida límites de tareas y genera estadísticas.
- **MensajeService**: Interfaz que define el contrato para mostrar mensajes
- **MensajeDevService**: Implementación para desarrollo con mensajes detallados
- **MensajeProdService**: Implementación para producción con mensajes concisos

---

## 🔑 Conceptos Clave de Spring Boot Aplicados

### 1. **Inyección de Dependencias**
Se utiliza **inyección por constructor** (mejor práctica) con `@RequiredArgsConstructor` de Lombok:

```java
@Service
@RequiredArgsConstructor
public class TareaService {
    private final TareaRepository tareaRepository; // Inyectado automáticamente
}
```

**¿Por qué?**
- Hace las dependencias explícitas e inmutables
- Facilita el testing (puedes mockear las dependencias)
- Evita NullPointerException

### 2. **Estereotipos de Spring**
Cada clase está marcada con su rol específico:
- `@Repository`: Clases de acceso a datos
- `@Service`: Clases con lógica de negocio
- `@Component`: Componentes genéricos (base de todos los estereotipos)

**¿Por qué?** Spring escanea estas anotaciones y automáticamente crea y gestiona los beans.

### 3. **Application Context**
El contenedor IoC de Spring gestiona el ciclo de vida de todos los beans. Cuando la aplicación inicia:
1. Escanea las clases con estereotipos
2. Crea instancias (beans)
3. Inyecta las dependencias automáticamente
4. Los gestiona durante toda la ejecución

### 4. **Configuración con Properties**
Se utilizan archivos `.properties` para externalizar la configuración:

```properties
app.nombre=Sistema de Gestión de Tareas UTN
app.max-tareas=10
app.mostrar-estadisticas=true
```

Y se inyectan con `@Value`:

```java
@Value("${app.max-tareas}")
private int maxTareas;
```

**¿Por qué?** Permite cambiar configuraciones sin recompilar el código.

### 5. **Profiles**
Se definen dos perfiles de configuración:

- **dev** (desarrollo): Límite bajo de tareas, logs detallados, estadísticas activadas
- **prod** (producción): Límite alto, logs mínimos, sin estadísticas

```java
@Service
@Profile("dev")
public class MensajeDevService implements MensajeService { ... }
```

**¿Por qué?** Una misma aplicación se comporta diferente según el entorno (desarrollo, testing, producción).

### 6. **CommandLineRunner**
Permite ejecutar código al iniciar la aplicación:

```java
@SpringBootApplication
public class TareasApplication implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {
        // Código que se ejecuta al iniciar
    }
}
```

---

## 🚀 Instrucciones para Clonar y Ejecutar

### Prerrequisitos
- JDK 21 instalado
- Gradle 9 (o usar el wrapper incluido)
- IDE compatible (IntelliJ IDEA, Eclipse, VS Code)

### 1. Clonar el repositorio

```bash
git clone https://github.com/TU_USUARIO/tareas-spring-boot.git
cd tareas-spring-boot
```

### 2. Compilar el proyecto

```bash
# En Windows
gradlew.bat build

# En Linux/Mac
./gradlew build
```

### 3. Ejecutar la aplicación

```bash
# En Windows
gradlew.bat bootRun

# En Linux/Mac
./gradlew bootRun
```

---

## 🔧 Cómo Cambiar entre Profiles (dev/prod)

### Método 1: Modificar `application.properties`

Edita el archivo `src/main/resources/application.properties`:

```properties
# Para desarrollo
spring.profiles.active=dev

# Para producción
spring.profiles.active=prod
```

### Método 2: Parámetro en la línea de comandos

```bash
# Ejecutar en modo desarrollo
./gradlew bootRun --args='--spring.profiles.active=dev'

# Ejecutar en modo producción
./gradlew bootRun --args='--spring.profiles.active=prod'
```

### Método 3: Variable de entorno

```bash
# En Windows
set SPRING_PROFILES_ACTIVE=prod
gradlew.bat bootRun

# En Linux/Mac
export SPRING_PROFILES_ACTIVE=prod
./gradlew bootRun
```

---

## 📸 Capturas de Pantalla

### Ejecución en Modo DESARROLLO (dev)

![Modo Dev](screenshots/modo-dev.png)

**Características visibles:**
- ✅ Mensaje de bienvenida detallado con emojis
- ✅ Límite de 10 tareas
- ✅ Estadísticas mostradas
- ✅ Logs en nivel DEBUG (muy detallados)
- ✅ Mensajes amigables y coloridos

### Ejecución en Modo PRODUCCIÓN (prod)

![Modo Prod](screenshots/modo-prod.png)

**Características visibles:**
- ✅ Mensaje de bienvenida conciso y profesional
- ✅ Límite de 1000 tareas
- ✅ Sin estadísticas
- ✅ Logs en nivel ERROR (solo errores críticos)
- ✅ Mensajes simples y formales

---

## 📚 Conceptos Aprendidos

### 1. **Inversión de Control (IoC)**
Spring gestiona el ciclo de vida de los objetos. No creamos instancias con `new`, Spring las crea e inyecta automáticamente.

### 2. **Inyección de Dependencias**
Las clases declaran qué necesitan (en el constructor) y Spring las proporciona automáticamente. Esto desacopla el código y facilita el testing.

### 3. **Separación de Responsabilidades**
Cada capa tiene su función:
- **Repository**: Acceso a datos
- **Service**: Lógica de negocio
- **Controller** (futuro): Exposición de APIs

### 4. **Configuración Externa**
Los valores configurables están fuera del código, permitiendo cambios sin recompilar.

### 5. **Adaptación por Entorno**
Con profiles, una misma aplicación se comporta diferente en desarrollo, testing y producción.

### 6. **Lombok para Código Limpio**
Lombok elimina código repetitivo:
- `@Data`: Genera getters, setters, toString, equals, hashCode
- `@RequiredArgsConstructor`: Genera constructor con campos final
- `@Slf4j`: Genera un logger automáticamente

---

## 💭 Conclusiones Personales

Este proyecto me permitió comprender los **fundamentos de Spring Boot** y cómo este framework facilita el desarrollo de aplicaciones profesionales:

1. **Inyección de Dependencias**: Entendí cómo Spring gestiona automáticamente las dependencias, lo que hace el código más modular y testeable. La inyección por constructor es más segura que por campo.

2. **Estereotipos y Arquitectura en Capas**: Aprendí a organizar el código en capas con responsabilidades claras. Usar `@Service`, `@Repository` no es solo una convención, sino que comunica el propósito de cada clase.

3. **Configuración con Properties**: Separar la configuración del código es fundamental. Permite desplegar la misma aplicación en diferentes entornos solo cambiando archivos de properties.

4. **Profiles**: Esta característica es extremadamente útil para el desarrollo profesional. Permite tener comportamientos diferentes según el entorno sin duplicar código.

5. **Lombok**: Reduce significativamente el código boilerplate. Las anotaciones como `@Data` y `@RequiredArgsConstructor` hacen el código más limpio y mantenible.

6. **Buenas Prácticas**: Implementé principios SOLID (especialmente el de inversión de dependencias con interfaces) y patrones de diseño que facilitan el mantenimiento y escalabilidad.

**Aprendizaje más valioso**: Spring Boot no es "magia", es un framework bien diseñado que automatiza tareas repetitivas y promueve buenas prácticas. Entender cómo funciona por dentro (Application Context, IoC Container, Component Scanning) es clave para usarlo efectivamente.

---

## 👤 Información del Estudiante

- **Nombre**: Santiago Herrerias
- **Legajo**: 50953
- **Materia**: Desarrollo de Software
- **Carrera**: Ingeniería en Sistemas de Información
- **Universidad**: UTN FRM (Universidad Tecnológica Nacional Facultad Regional Mendoza)

---

## 📞 Contacto

- **GitHub**: Santiagoherrerias02
- **Email**: santiago.herrerias.utn@gmail.com

---

## 📄 Licencia

Este proyecto fue desarrollado con fines educativos para la UTN FRM.

---

**⭐ Si este proyecto te resultó útil, no olvides darle una estrella en GitHub!**