package pro.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import java.util.List;
import pro.entities.Asignaturas;
import pro.entities.Catedraticos;

/**
 * DAO optimizado para manejar catedráticos sin abrir múltiples sesiones.
 * Utiliza una sola EntityManagerFactory (JPAUtil).
 */
public class CatedraticoDAO {

    public void crear(Catedraticos catedratico) {
        EntityManager em = JPAUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();
            em.persist(catedratico);
            tx.commit();
        } catch (Exception e) {
            if (tx.isActive()) tx.rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    public Catedraticos buscarPorId(Integer id) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            return em.find(Catedraticos.class, id);
        } finally {
            em.close();
        }
    }

    public List<Catedraticos> listarTodos() {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            TypedQuery<Catedraticos> query = em.createQuery("SELECT c FROM Catedraticos c", Catedraticos.class);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    public List<Catedraticos> buscarPorEspecialidad(String especialidad) {
        EntityManager em = JPAUtil.getEntityManager();
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
        EntityManager em = JPAUtil.getEntityManager();
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
        EntityManager em = JPAUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();
            em.merge(catedratico);
            tx.commit();
        } catch (Exception e) {
            if (tx.isActive()) tx.rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    public void eliminar(Integer id) {
        EntityManager em = JPAUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();
            Catedraticos catedratico = em.find(Catedraticos.class, id);
            if (catedratico != null) {
                em.remove(catedratico);
            }
            tx.commit();
        } catch (Exception e) {
            if (tx.isActive()) tx.rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    /**
     * 🔹 Devuelve los cursos que imparte un catedrático.
     */
    public List<Asignaturas> cargarCursosCatedratico(Integer idCatedratico) {
        EntityManager em = JPAUtil.getEntityManager();
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
