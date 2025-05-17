package com.proyectogrado.app.Configuracion;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.proyectogrado.app.Entidades.Administrador;
import com.proyectogrado.app.Repositorios.AdministradorRepositorio;

@Configuration
public class AdminSetup {

    @Bean
    CommandLineRunner setupAdministrador(AdministradorRepositorio repositorio) {
        return args -> {
            // Verifica si ya existe el administrador
            Administrador existingAdmin = repositorio.findByUsuario("admin");
            if (existingAdmin == null) {
                Administrador admin = new Administrador();
                admin.setUsuario("admin");
                admin.setPassword("admin123"); // Usando la contraseña en texto plano
                repositorio.save(admin); // Guardamos el administrador en la base de datos
                System.out.println("Administrador creado con éxito.");
            } else {
                System.out.println("El administrador ya existe.");
            }
        };
    }
}
