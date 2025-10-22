/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pro.dao;

import pro.entities.Estudiantes;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;
import java.util.List;
import pro.entities.Asignaturas;

/**
 *
 * @author USER
 */
public class EstudiantesDAO {
    
    private EntityManagerFactory emf;
    
    public EstudiantesDAO() {
        emf = Persistence.createEntityManagerFactory("ProPro");
    }
    
    public void crear(Estudiantes estudiante) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        
        try {
            tx.begin();
            em.persist(estudiante);
            tx.commit();
        } catch (Exception e) {
            if (tx.isActive()) {
                tx.rollback();
            }
            throw e;
        } finally {
            em.close();
        }
    }
    
    public Estudiantes buscarPorId(Integer id) {
        EntityManager em = emf.createEntityManager();
        
        try {
            return em.find(Estudiantes.class, id);
        } finally {
            em.close();
        }
    }
    
    public List<Estudiantes> listarTodos() {
        EntityManager em = emf.createEntityManager();
        
        try {
            TypedQuery<Estudiantes> query = em.createQuery("SELECT e FROM Estudiantes e", Estudiantes.class);
            return query.getResultList();
        } finally {
            em.close();
        }
    }
    
    public Estudiantes buscarPorCarnet(String carnet) {
        EntityManager em = emf.createEntityManager();
        
        try {
            TypedQuery<Estudiantes> query = em.createQuery(
                "SELECT e FROM Estudiantes e WHERE e.carnet = :carnet", Estudiantes.class);
            query.setParameter("carnet", carnet);
            List<Estudiantes> resultados = query.getResultList();
            return resultados.isEmpty() ? null : resultados.get(0);
        } finally {
            em.close();
        }
    }
    
    public List<Estudiantes> buscarPorCarrera(String carrera) {
        EntityManager em = emf.createEntityManager();
        
        try {
            TypedQuery<Estudiantes> query = em.createQuery(
                "SELECT e FROM Estudiantes e WHERE e.carrera = :carrera", Estudiantes.class);
            query.setParameter("carrera", carrera);
            return query.getResultList();
        } finally {
            em.close();
        }
    }
    
    public Estudiantes buscarPorUsuario(Integer idUsuario) {
        EntityManager em = emf.createEntityManager();
        
        try {
            TypedQuery<Estudiantes> query = em.createQuery(
                "SELECT e FROM Estudiantes e WHERE e.idUsuario.idUsuario = :idUsuario", Estudiantes.class);
            query.setParameter("idUsuario", idUsuario);
            List<Estudiantes> resultados = query.getResultList();
            return resultados.isEmpty() ? null : resultados.get(0);
        } finally {
            em.close();
        }
    }
    
    public void actualizar(Estudiantes estudiante) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        
        try {
            tx.begin();
            em.merge(estudiante);
            tx.commit();
        } catch (Exception e) {
            if (tx.isActive()) {
                tx.rollback();
            }
            throw e;
        } finally {
            em.close();
        }
    }
    
    public void eliminar(Integer id) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        
        try {
            tx.begin();
            Estudiantes estudiante = em.find(Estudiantes.class, id);
            if (estudiante != null) {
                em.remove(estudiante);
            }
            tx.commit();
        } catch (Exception e) {
            if (tx.isActive()) {
                tx.rollback();
            }
            throw e;
        } finally {
            em.close();
        }
    }
    
    public void cerrar() {
        if (emf != null && emf.isOpen()) {
            emf.close();
        }
    }
    
    public List<Asignaturas> obtenerCursosEstudiante(Integer idEstudiante) {
    EntityManager em = emf.createEntityManager();
    try {
        // Seleccionamos directamente las asignaturas relacionadas a las calificaciones del estudiante
        TypedQuery<Asignaturas> query = em.createQuery(
            "SELECT c.idAsignatura FROM Calificaciones c WHERE c.idEstudiante.idEstudiante = :idEstudiante",
            Asignaturas.class
        );
        query.setParameter("idEstudiante", idEstudiante);
        return query.getResultList();
    } finally {
        em.close();
    }
}


}

