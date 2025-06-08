package org.example.presentacion;

import org.example.dao.EstudianteDAO;
import org.example.dominio.Estudiante;

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
                salir = ejecutarOpciones(consola, estudianteDao);
            }
            catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
            System.out.println();
        }
    }

    private static void mostrarMenu(){
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

    private static boolean ejecutarOpciones(Scanner consola, EstudianteDAO estudianteDAO){
        var opcion = Integer.parseInt(consola.nextLine());
        var salir = false;
        switch (opcion){
            case 1 -> {
                System.out.println("Listado de estudiantes:");
                var estudiantes = estudianteDAO.listarEstudiantes();
                estudiantes.forEach(System.out::println);
            }
            case 2 -> {
                System.out.println("Ingrese el ID del estudiante a buscar:");
                var idEstudiante = Integer.parseInt(consola.nextLine());
                var estudiante = new Estudiante(idEstudiante);
                var encontrado = estudianteDAO.findEstudianteById(estudiante);
                if (encontrado) {
                    System.out.println("Estudiante encontrado: " + estudiante);
                } else {
                    System.out.println("Estudiante no encontrado.");
                }
            }
            case 3 -> {
                System.out.println("Ingrese los datos del nuevo estudiante:");
                System.out.print("Nombre: ");
                var nombre = consola.nextLine();
                System.out.print("Apellido: ");
                var apellido = consola.nextLine();
                System.out.print("Correo: ");
                var correo = consola.nextLine();
                System.out.print("Teléfono: ");
                var telefono = consola.nextLine();

                var nuevoEstudiante = new Estudiante(nombre, apellido, correo, telefono);
                var agregado = estudianteDAO.agregarEstudiante(nuevoEstudiante);
                if (agregado) {
                    System.out.println("Estudiante agregado exitosamente: " + nuevoEstudiante);
                } else {
                    System.out.println("Error al agregar estudiante.");
                }
            }
            case 4 -> {
                System.out.println("Ingrese el ID del estudiante a actualizar:");
                var idEstudiante = Integer.parseInt(consola.nextLine());
                System.out.print("Nuevo nombre: ");
                var nombre = consola.nextLine();
                System.out.print("Nuevo apellido: ");
                var apellido = consola.nextLine();
                System.out.print("Nuevo correo: ");
                var correo = consola.nextLine();
                System.out.print("Nuevo teléfono: ");
                var telefono = consola.nextLine();

                var estudianteActualizar = new Estudiante(idEstudiante, nombre, apellido, correo, telefono);
                var actualizado = estudianteDAO.actualizarEstudiante(estudianteActualizar);
                if (actualizado) {
                    System.out.println("Estudiante actualizado exitosamente: " + estudianteActualizar);
                } else {
                    System.out.println("Error al actualizar estudiante.");
                }
            }
            case 5 -> {
                System.out.println("Ingrese el ID del estudiante a eliminar:");
                var idEstudiante = Integer.parseInt(consola.nextLine());
                var estudianteEliminar = new Estudiante(idEstudiante);
                var eliminado = estudianteDAO.eliminarEstudiante(estudianteEliminar);
                if (eliminado) {
                    System.out.println("Estudiante eliminado exitosamente: " + estudianteEliminar);
                } else {
                    System.out.println("Error al eliminar estudiante.");
                }
            }
            case 6 -> {
                System.out.println("Saliendo del sistema...");
                salir = true;
            }
            default -> System.out.println("Opción no válida. Por favor, intente de nuevo.");
        }
        return salir;
    }

}