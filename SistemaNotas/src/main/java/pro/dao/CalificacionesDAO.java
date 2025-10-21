/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pro.dao;

import pro.entities.Calificaciones;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;
import java.util.List;

/**
 *
 * @author USER
 */
public class CalificacionesDAO {
    
    private EntityManagerFactory emf;
    
    public CalificacionesDAO() {
        emf = Persistence.createEntityManagerFactory("ProPro");
    }
    
    public void crear(Calificaciones calificacion) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        
        try {
            tx.begin();
            em.persist(calificacion);
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
    
    public Calificaciones buscarPorId(Integer id) {
        EntityManager em = emf.createEntityManager();
        
        try {
            return em.find(Calificaciones.class, id);
        } finally {
            em.close();
        }
    }
    
    public List<Calificaciones> listarTodos() {
        EntityManager em = emf.createEntityManager();
        
        try {
            TypedQuery<Calificaciones> query = em.createQuery("SELECT c FROM Calificaciones c", Calificaciones.class);
            return query.getResultList();
        } finally {
            em.close();
        }
    }
    
    public List<Calificaciones> buscarPorEstudiante(Integer idEstudiante) {
        EntityManager em = emf.createEntityManager();
        
        try {
            TypedQuery<Calificaciones> query = em.createQuery(
                "SELECT c FROM Calificaciones c WHERE c.idEstudiante.idEstudiante = :idEstudiante", Calificaciones.class);
            query.setParameter("idEstudiante", idEstudiante);
            return query.getResultList();
        } finally {
            em.close();
        }
    }
    
    public List<Calificaciones> buscarPorAsignatura(Integer idAsignatura) {
        EntityManager em = emf.createEntityManager();
        
        try {
            TypedQuery<Calificaciones> query = em.createQuery(
                "SELECT c FROM Calificaciones c WHERE c.idAsignatura.idAsignatura = :idAsignatura", Calificaciones.class);
            query.setParameter("idAsignatura", idAsignatura);
            return query.getResultList();
        } finally {
            em.close();
        }
    }
    
    public Calificaciones buscarPorEstudianteYAsignatura(Integer idEstudiante, Integer idAsignatura) {
        EntityManager em = emf.createEntityManager();
        
        try {
            TypedQuery<Calificaciones> query = em.createQuery(
                "SELECT c FROM Calificaciones c WHERE c.idEstudiante.idEstudiante = :idEstudiante " +
                "AND c.idAsignatura.idAsignatura = :idAsignatura", Calificaciones.class);
            query.setParameter("idEstudiante", idEstudiante);
            query.setParameter("idAsignatura", idAsignatura);
            List<Calificaciones> resultados = query.getResultList();
            return resultados.isEmpty() ? null : resultados.get(0);
        } finally {
            em.close();
        }
    }
    
    public void actualizar(Calificaciones calificacion) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        
        try {
            tx.begin();
            em.merge(calificacion);
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
            Calificaciones calificacion = em.find(Calificaciones.class, id);
            if (calificacion != null) {
                em.remove(calificacion);
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
}

