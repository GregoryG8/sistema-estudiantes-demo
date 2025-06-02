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

    public static void main(String[] args) {
        var estudiantes = new EstudianteDAO();
        System.out.println("Listando estudiantes...");
        List<Estudiante> listaEstudiantes = estudiantes.listarEstudiantes();
        listaEstudiantes.forEach(System.out::println);
    }
}
