package com.ReZherk.alertas_inventario.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

@Configuration
@EnableScheduling
public class SchedulingConfig {
}

/*
 * Configuración global para habilitar tareas programadas en el proyecto.
 * Esta clase activa el soporte de @Scheduled, permitiendo ejecutar métodos automáticamente
 * en intervalos definidos.
 */

//@EnableScheduling
// Habilita el soporte para tareas programadas en Spring mediante anotaciones como @Scheduled.
// Esta anotación debe colocarse en una clase de configuración (por ejemplo, la clase principal con @SpringBootApplication).
// Proviene del paquete org.springframework.scheduling.annotation y activa el procesamiento interno de Spring para ejecutar métodos programados.
