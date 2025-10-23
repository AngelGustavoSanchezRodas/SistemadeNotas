/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pro.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import java.util.List;
import pro.entities.Asignaturas;

/**
 *
 * @author USER
 */
public class AsignaturasDAO {

    // 🔹 Ya no se crea un EntityManagerFactory en cada DAO.
    // Todos los métodos usan la instancia única de JPAUtil.

    public void crear(Asignaturas asignatura) {
        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();
            em.persist(asignatura);
            tx.commit();
        } catch (Exception e) {
            if (tx.isActive()) tx.rollback();
            System.err.println("❌ Error al crear la asignatura: " + e.getMessage());
            throw e;
        } finally {
            em.close();
        }
    }

    public Asignaturas buscarPorId(Integer id) {
        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
        try {
            return em.find(Asignaturas.class, id);
        } finally {
            em.close();
        }
    }

    public List<Asignaturas> listarTodos() {
        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
        try {
            TypedQuery<Asignaturas> query = em.createQuery("SELECT a FROM Asignaturas a", Asignaturas.class);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    public List<Asignaturas> buscarPorCodigo(String codigo) {
        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
        try {
            TypedQuery<Asignaturas> query = em.createQuery(
                "SELECT a FROM Asignaturas a WHERE a.codigo = :codigo", Asignaturas.class
            );
            query.setParameter("codigo", codigo);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    public List<Asignaturas> buscarPorCatedratico(int idCatedratico) {
        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
        try {
            TypedQuery<Asignaturas> query = em.createQuery(
                "SELECT a FROM Asignaturas a WHERE a.idCatedratico.idCatedratico = :idCatedratico",
                Asignaturas.class
            );
            query.setParameter("idCatedratico", idCatedratico);
            return query.getResultList();
        } catch (Exception e) {
            System.err.println(" Error al buscar asignaturas por catedrático: " + e.getMessage());
            throw e;
        } finally {
            em.close();
        }
    }

    public void actualizar(Asignaturas asignatura) {
        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();
            em.merge(asignatura);
            tx.commit();
        } catch (Exception e) {
            if (tx.isActive()) tx.rollback();
            System.err.println("❌ Error al actualizar la asignatura: " + e.getMessage());
            throw e;
        } finally {
            em.close();
        }
    }

    public void eliminar(Integer id) {
        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();
            Asignaturas asignatura = em.find(Asignaturas.class, id);
            if (asignatura != null) {
                em.remove(asignatura);
            }
            tx.commit();
        } catch (Exception e) {
            if (tx.isActive()) tx.rollback();
            System.err.println(" Error al eliminar la asignatura: " + e.getMessage());
            throw e;
        } finally {
            em.close();
        }
    }

    // 🔹 Este método solo llama al cierre global de JPAUtil
    public void cerrar() {
        JPAUtil.cerrar();
    }
}
