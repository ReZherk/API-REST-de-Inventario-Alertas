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