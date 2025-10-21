/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pro.dao;

import pro.entities.Asignaturas;
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
public class AsignaturasDAO {
    
    private EntityManagerFactory emf;
    
    public AsignaturasDAO() {
        emf = Persistence.createEntityManagerFactory("ProPro");
    }
    
    public void crear(Asignaturas asignatura) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        
        try {
            tx.begin();
            em.persist(asignatura);
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
    
    public Asignaturas buscarPorId(Integer id) {
        EntityManager em = emf.createEntityManager();
        
        try {
            return em.find(Asignaturas.class, id);
        } finally {
            em.close();
        }
    }
    
    public List<Asignaturas> listarTodos() {
        EntityManager em = emf.createEntityManager();
        
        try {
            TypedQuery<Asignaturas> query = em.createQuery("SELECT a FROM Asignaturas a", Asignaturas.class);
            return query.getResultList();
        } finally {
            em.close();
        }
    }
    
    public List<Asignaturas> buscarPorCodigo(String codigo) {
        EntityManager em = emf.createEntityManager();
        
        try {
            TypedQuery<Asignaturas> query = em.createQuery(
                "SELECT a FROM Asignaturas a WHERE a.codigo = :codigo", Asignaturas.class);
            query.setParameter("codigo", codigo);
            return query.getResultList();
        } finally {
            em.close();
        }
    }
    
    public List<Asignaturas> buscarPorCatedratico(Integer idCatedratico) {
        EntityManager em = emf.createEntityManager();
        
        try {
            TypedQuery<Asignaturas> query = em.createQuery(
                "SELECT a FROM Asignaturas a WHERE a.idCatedratico.idCatedratico = :idCatedratico", Asignaturas.class);
            query.setParameter("idCatedratico", idCatedratico);
            return query.getResultList();
        } finally {
            em.close();
        }
    }
    
    public void actualizar(Asignaturas asignatura) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        
        try {
            tx.begin();
            em.merge(asignatura);
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
            Asignaturas asignatura = em.find(Asignaturas.class, id);
            if (asignatura != null) {
                em.remove(asignatura);
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

