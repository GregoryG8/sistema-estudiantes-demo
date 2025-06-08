package org.example.dao;

import org.example.conexion.Conexion;
import org.example.dominio.Estudiante;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class EstudianteDAO {
    public List<Estudiante> listarEstudiantes() {
        List<Estudiante> estudiantes = new ArrayList<>();
        PreparedStatement preparedStatement;
        ResultSet resultSet;
        Connection conexion = Conexion.getConexion();
        String sql = "SELECT * FROM estudiante ORDER BY id_estudiante";
        try {
            preparedStatement = conexion.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                var estudiante = new Estudiante();
                estudiante.setIdEstudiante(resultSet.getInt("id_estudiante"));
                estudiante.setNombre(resultSet.getString("nombre"));
                estudiante.setApellido(resultSet.getString("apellido"));
                estudiante.setCorreo(resultSet.getString("email"));
                estudiante.setTelefono(resultSet.getString("telefono"));
                estudiantes.add(estudiante);
            }
        } catch (Exception e) {
            System.out.println("Error al listar estudiantes: " + e.getMessage());
        }
        finally {
            try {
                conexion.close();
            } catch (Exception e) {
                System.out.println("Error al cerrar recursos: " + e.getMessage());
            }
        }
        return estudiantes;
    }

    public boolean findEstudianteById(Estudiante estudiante) {
        PreparedStatement preparedStatement;
        ResultSet resultSet;
        Connection conexion = Conexion.getConexion();
        String sql = "SELECT * FROM estudiante WHERE id_estudiante = ?";
        try {
            preparedStatement = conexion.prepareStatement(sql);
            preparedStatement.setInt(1, estudiante.getIdEstudiante());
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                estudiante.setNombre(resultSet.getString("nombre"));
                estudiante.setApellido(resultSet.getString("apellido"));
                estudiante.setCorreo(resultSet.getString("email"));
                estudiante.setTelefono(resultSet.getString("telefono"));
                return true;
            }
        } catch (Exception e) {
            System.out.println("Error al buscar estudiante por ID: " + e.getMessage());
        } finally {
            try {
                conexion.close();
            } catch (Exception e) {
                System.out.println("Error al cerrar recursos: " + e.getMessage());
            }
        }
        return false;
    }

    public boolean agregarEstudiante(Estudiante estudiante) {
        PreparedStatement preparedStatement;
        Connection conexion = Conexion.getConexion();
        String sql = "INSERT INTO estudiante (nombre, apellido, email, telefono) VALUES (?, ?, ?, ?)";
        try{
            preparedStatement = conexion.prepareStatement(sql);
            preparedStatement.setString(1, estudiante.getNombre());
            preparedStatement.setString(2, estudiante.getApellido());
            preparedStatement.setString(3, estudiante.getCorreo());
            preparedStatement.setString(4, estudiante.getTelefono());
            preparedStatement.execute();
            return true;
        } catch (Exception e) {
            System.out.println("Error al agregar estudiante: " + e.getMessage());
        } finally {
            try {
                conexion.close();
            } catch (Exception e) {
                System.out.println("Error al cerrar recursos: " + e.getMessage());
            }
        }
        return false;
    }

    public boolean actualizarEstudiante(Estudiante estudiante) {
        PreparedStatement preparedStatement;
        Connection conexion = Conexion.getConexion();
        String sql = "UPDATE estudiante SET nombre = ?, apellido = ?, email = ?, telefono = ? WHERE id_estudiante = ?";
        try {
            preparedStatement = conexion.prepareStatement(sql);
            preparedStatement.setString(1, estudiante.getNombre());
            preparedStatement.setString(2, estudiante.getApellido());
            preparedStatement.setString(3, estudiante.getCorreo());
            preparedStatement.setString(4, estudiante.getTelefono());
            preparedStatement.setInt(5, estudiante.getIdEstudiante());
            preparedStatement.executeUpdate();
            return true;
        } catch (Exception e) {
            System.out.println("Error al actualizar estudiante: " + e.getMessage());
        } finally {
            try {
                conexion.close();
            } catch (Exception e) {
                System.out.println("Error al cerrar recursos: " + e.getMessage());
            }
        }
        return false;
    }

    public static void main(String[] args) {
        var estudiantes = new EstudianteDAO();

        // Actualizar estudiante
        var actualizarEstudiante = new Estudiante(4, "Gustambo", "Kunst", "", "");
        var actualizado = estudiantes.actualizarEstudiante(actualizarEstudiante);
        if (actualizado) {
            System.out.println("Estudiante actualizado exitosamente: " + actualizarEstudiante);
        } else {
            System.out.println("Error al actualizar estudiante.");
        }

        //agregar estudiante
//        var nuevoEstudiante = new Estudiante("Carlos", "Alberto", "aasas@hjsh.com", "123123");
//        var agregado = estudiantes.agregarEstudiante(nuevoEstudiante);
//        if (agregado) {
//            System.out.println("Estudiante agregado exitosamente: " + nuevoEstudiante);
//        } else {
//            System.out.println("Error al agregar estudiante.");
//        }

        //listar estudiantes
        System.out.println("Listando estudiantes...");
        List<Estudiante> listaEstudiantes = estudiantes.listarEstudiantes();
        listaEstudiantes.forEach(System.out::println);

        //byId
//        var estudiante1 = new Estudiante(2);
//        System.out.println("Buscando estudiante por ID...");
//        var encontrado = estudiantes.findEstudianteById(estudiante1);
//        if (encontrado) {
//            System.out.println("Estudiante encontrado: " + estudiante1);
//        } else {
//            System.out.println("Estudiante no encontrado.");
//        }
    }
}
