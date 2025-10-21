/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package servicios;

/**
 *
 * @author belma
 */
public class ServicioNotas {;

// Esta clase contiene los métodos internos para trabajar con calificaciones.
// Son los métodos que se usarán en tus ventanas para procesar los datos.

    // MÉTODO INTERNO 1: Calcula el promedio de dos exámenes
    public double calcularPromedio(double examen1, double examen2) {
        return (examen1 + examen2) / 2.0;
    }

    // MÉTODO INTERNO 2: Evalúa si el estudiante aprueba o reprueba
    public String evaluarEstado(double promedio, double notaMinimaAprobatoria) {
        if (promedio >= notaMinimaAprobatoria) {
            return "Aprobado";
        } else {
            return "Reprobado";
        }
    }
    
    // MÉTODO INTERNO 3: Formatea el promedio (para que se vea con 2 decimales)
    public String formatearNota(double nota) {
        return String.format("%.2f", nota);
    }
}
    

