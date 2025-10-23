package pro.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import java.util.List;
import pro.entities.Asignaturas;
import pro.entities.Estudiantes;

/**
 * DAO para Estudiantes y consultas relacionadas.
 */
public class EstudiantesDAO {

    public void crear(Estudiantes estudiante) {
        EntityManager em = JPAUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();
            em.persist(estudiante);
            tx.commit();
        } catch (Exception e) {
            if (tx.isActive()) tx.rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    public Estudiantes buscarPorId(Integer id) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            return em.find(Estudiantes.class, id);
        } finally {
            em.close();
        }
    }

    public List<Estudiantes> listarTodos() {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            TypedQuery<Estudiantes> query = em.createQuery("SELECT e FROM Estudiantes e", Estudiantes.class);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    public Estudiantes buscarPorCarnet(String carnet) {
        EntityManager em = JPAUtil.getEntityManager();
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
        EntityManager em = JPAUtil.getEntityManager();
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
        EntityManager em = JPAUtil.getEntityManager();
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
        EntityManager em = JPAUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();
            em.merge(estudiante);
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
            Estudiantes estudiante = em.find(Estudiantes.class, id);
            if (estudiante != null) {
                em.remove(estudiante);
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
     * Retorna las asignaturas (entidades Asignaturas) en las que está inscrito un estudiante.
     * Devuelve una lista de Asignaturas (puede contener duplicados si hay entradas múltiples,
     * pero por diseño idealmente cada (estudiante,asignatura) es único en calificaciones).
     */
    public List<Asignaturas> obtenerCursosEstudiante(Integer idEstudiante) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
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

    /**
     * Retorna las notas (examen1, examen2, examenFinal) para un estudiante en una asignatura.
     * Cada Object[] corresponde a {examen1, examen2, examenFinal} (Integer o null).
     */
    public List<Object[]> obtenerNotasPorCurso(Integer idEstudiante, Integer idAsignatura) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            TypedQuery<Object[]> query = em.createQuery(
                "SELECT c.examen1, c.examen2, c.examenFinal " +
                "FROM Calificaciones c " +
                "WHERE c.idEstudiante.idEstudiante = :idEstudiante " +
                "AND c.idAsignatura.idAsignatura = :idAsignatura",
                Object[].class
            );
            query.setParameter("idEstudiante", idEstudiante);
            query.setParameter("idAsignatura", idAsignatura);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    /**
     * Retorna la lista de estudiantes inscritos en una asignatura junto con sus notas.
     * Cada Object[] devuelto contiene: { idEstudiante (Integer), nombreUsuario (String), examen1 (Integer|null), examen2 (Integer|null), examenFinal (Integer|null) }
     *
     * IMPORTANTE: en la entidad Usuarios el campo se llama `nombreUsuario` (no `nombre`), por eso se usa e.idUsuario.nombreUsuario.
     */
    public List<Object[]> obtenerEstudiantesPorCurso(Integer idAsignatura) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            TypedQuery<Object[]> query = em.createQuery(
                "SELECT e.idEstudiante, e.idUsuario.nombreUsuario, c.examen1, c.examen2, c.examenFinal " +
                "FROM Calificaciones c " +
                "JOIN c.idEstudiante e " +
                "WHERE c.idAsignatura.idAsignatura = :idAsignatura",
                Object[].class
            );
            query.setParameter("idAsignatura", idAsignatura);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

}
