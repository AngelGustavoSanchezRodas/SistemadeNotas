/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pro.dao;

import pro.entities.Catedraticos;
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
public class CatedraticoDAO {
    
    private EntityManagerFactory emf;
    
    public CatedraticoDAO() {
        emf = Persistence.createEntityManagerFactory("ProPro");
    }
    
    public void crear(Catedraticos catedratico) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        
        try {
            tx.begin();
            em.persist(catedratico);
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
    
    public Catedraticos buscarPorId(Integer id) {
        EntityManager em = emf.createEntityManager();
        
        try {
            return em.find(Catedraticos.class, id);
        } finally {
            em.close();
        }
    }
    
    public List<Catedraticos> listarTodos() {
        EntityManager em = emf.createEntityManager();
        
        try {
            TypedQuery<Catedraticos> query = em.createQuery("SELECT c FROM Catedraticos c", Catedraticos.class);
            return query.getResultList();
        } finally {
            em.close();
        }
    }
    
    public List<Catedraticos> buscarPorEspecialidad(String especialidad) {
        EntityManager em = emf.createEntityManager();
        
        try {
            TypedQuery<Catedraticos> query = em.createQuery(
                "SELECT c FROM Catedraticos c WHERE c.especialidad = :especialidad", Catedraticos.class);
            query.setParameter("especialidad", especialidad);
            return query.getResultList();
        } finally {
            em.close();
        }
    }
    
    public Catedraticos buscarPorUsuario(Integer idUsuario) {
        EntityManager em = emf.createEntityManager();
        
        try {
            TypedQuery<Catedraticos> query = em.createQuery(
                "SELECT c FROM Catedraticos c WHERE c.idUsuario.idUsuario = :idUsuario", Catedraticos.class);
            query.setParameter("idUsuario", idUsuario);
            List<Catedraticos> resultados = query.getResultList();
            return resultados.isEmpty() ? null : resultados.get(0);
        } finally {
            em.close();
        }
    }
    
    public void actualizar(Catedraticos catedratico) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        
        try {
            tx.begin();
            em.merge(catedratico);
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
            Catedraticos catedratico = em.find(Catedraticos.class, id);
            if (catedratico != null) {
                em.remove(catedratico);
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
    
    public List<Asignaturas> cargarCursosCatedratico(Integer idCatedratico) {
    EntityManager em = emf.createEntityManager();
    try {
        TypedQuery<Asignaturas> query = em.createQuery(
            "SELECT a FROM Asignaturas a WHERE a.idCatedratico.idCatedratico = :idCatedratico",
            Asignaturas.class
        );
        query.setParameter("idCatedratico", idCatedratico);
        return query.getResultList();
    } finally {
        em.close();
    }
}

}
