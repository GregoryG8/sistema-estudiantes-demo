package org.example.presentacion;

import org.example.dao.EstudianteDAO;

import java.util.Scanner;

public class SistemaEstudiantesApp {
    public static void main(String[] args) {

        var salir = false;
        var consola = new Scanner(System.in);

        //Crear instancia clase servicio
        var estudianteDao = new EstudianteDAO();
        while (!salir){
            try {
                mostrarMenu();
                //salir = ejecutarOpciones(consola, estudianteDao);
            }
            catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
            System.out.println();
        }
    }

    public static void mostrarMenu(){
        System.out.println("""
                **** Sistema de Estudiantes ****
                1. Listar estudiantes
                2. Buscar estudiante
                3. Agregar estudiante
                4. Actualizar estudiante
                5. Eliminar estudiante
                6. Salir
                Seleccione una opción:
                """);
    }

}