
### 🧱 Arquitectura del proyecto

El proyecto sigue una **arquitectura en capas**, organizada por paquetes con responsabilidades claras:

- `application`: lógica de negocio y servicios principales.
- `domain`: entidades, modelos y validaciones.
- `web`: controladores REST que exponen la API.
- `config`: configuración global (Swagger, seguridad, etc.).
- `scheduler`: tareas programadas para alertas automáticas.
- `AlertasInventarioApplication`: clase principal con `@SpringBootApplication`.

Esta estructura modular facilita la escalabilidad, el mantenimiento y la presentación profesional del backend. Está diseñada para reflejar buenas prácticas en proyectos Java con Spring Boot.
