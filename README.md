### 🛍️ Descripción del Proyecto

Este proyecto consiste en una **API REST** desarrollada con **Spring Boot**, diseñada para la **gestión de productos en un sistema de e-commerce**.
La aplicación permite realizar operaciones **CRUD (Crear, Leer, Actualizar y Eliminar)** sobre los productos, implementando una **arquitectura por capas**, validaciones, manejo centralizado de errores, persistencia mediante **Spring Data JPA** y documentación automática con **Swagger/OpenAPI**.

---

### ⚙️ Tecnologías Utilizadas

* **Java 17**
* **Spring Boot 3.5.7**
* **Spring Data JPA**
* **H2 Database** (base de datos en memoria)
* **Lombok** (para reducir código boilerplate)
* **SpringDoc OpenAPI** (documentación con Swagger)
* **Maven** (gestión de dependencias)

---

### 🚀 Instrucciones para Clonar y Ejecutar

#### 🧩 Requisitos Previos

* Tener instalado **Java 17**.
* Tener instalado **Maven**.
* Contar con un **IDE** (IntelliJ IDEA, Eclipse, VS Code, etc.).

#### ▶️ Pasos para la Ejecución

1. **Clonar el repositorio:**

   ```bash
   git clone https://github.com/joasfunes/tp_api_rest
   ```
2. **Acceder al directorio del proyecto:**

   ```bash
   cd tu-repositorio
   ```
3. **Compilar y ejecutar el proyecto:**

   ```bash
   mvn clean install
   mvn spring-boot:run
   ```
4. La aplicación estará disponible en:
   👉 [http://localhost:8080](http://localhost:8080)

---

### 🔗 Tabla de Endpoints

*(Aquí se puede incluir una tabla o imagen con los endpoints principales de la API)*

---

### 📘 Acceso a Swagger UI y Consola H2

#### 📄 Swagger UI

Documentación interactiva de la API disponible en:
👉 [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)

#### 🗃️ Consola H2

Accede a la base de datos en memoria desde:
👉 [http://localhost:8080/h2-console](http://localhost:8080/h2-console)

**Datos de conexión:**

* **JDBC URL:** `jdbc:h2:mem:productosdb`
* **Usuario:** `sa`
* **Contraseña:** *(dejar en blanco)*

---

### 🧠 Conclusiones Personales

Durante el desarrollo de este proyecto aprendí a:

* Diseñar y construir una **API REST completa** desde cero.
* Utilizar correctamente los **métodos HTTP** según su propósito.
* Implementar una **arquitectura en capas profesional**.
* Emplear **DTOs** para desacoplar las capas de presentación y dominio.
* Aplicar **validaciones con Bean Validation**.
* Gestionar errores de forma **centralizada y consistente**.
* Documentar APIs con **Swagger/OpenAPI**.
* Probar y explorar endpoints de manera **interactiva** con Swagger UI.

---

### ✍️ Autor

**Nombre:** Valentina Luna
**Legajo:** 50988
