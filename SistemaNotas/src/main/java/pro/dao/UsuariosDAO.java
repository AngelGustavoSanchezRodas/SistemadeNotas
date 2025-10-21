/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pro.dao;

import pro.entities.Usuarios;
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
public class UsuariosDAO {
    
    private EntityManagerFactory emf;
    
    public UsuariosDAO() {
        emf = Persistence.createEntityManagerFactory("ProPro");
    }
    
    public void crear(Usuarios usuario) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        
        try {
            tx.begin();
            em.persist(usuario);
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
    
    public Usuarios buscarPorId(Integer id) {
        EntityManager em = emf.createEntityManager();
        
        try {
            return em.find(Usuarios.class, id);
        } finally {
            em.close();
        }
    }
    
    public List<Usuarios> listarTodos() {
        EntityManager em = emf.createEntityManager();
        
        try {
            TypedQuery<Usuarios> query = em.createQuery("SELECT u FROM Usuarios u", Usuarios.class);
            return query.getResultList();
        } finally {
            em.close();
        }
    }
    
    public Usuarios buscarPorCorreo(String correo) {
        EntityManager em = emf.createEntityManager();
        
        try {
            TypedQuery<Usuarios> query = em.createQuery(
                "SELECT u FROM Usuarios u WHERE u.correo = :correo", Usuarios.class);
            query.setParameter("correo", correo);
            List<Usuarios> resultados = query.getResultList();
            return resultados.isEmpty() ? null : resultados.get(0);
        } finally {
            em.close();
        }
    }
    
    public Usuarios buscarPorNombreUsuario(String nombreUsuario) {
        EntityManager em = emf.createEntityManager();
        
        try {
            TypedQuery<Usuarios> query = em.createQuery(
                "SELECT u FROM Usuarios u WHERE u.nombreUsuario = :nombreUsuario", Usuarios.class);
            query.setParameter("nombreUsuario", nombreUsuario);
            List<Usuarios> resultados = query.getResultList();
            return resultados.isEmpty() ? null : resultados.get(0);
        } finally {
            em.close();
        }
    }
    
    public List<Usuarios> buscarPorRol(String rol) {
        EntityManager em = emf.createEntityManager();
        
        try {
            TypedQuery<Usuarios> query = em.createQuery(
                "SELECT u FROM Usuarios u WHERE u.rol = :rol", Usuarios.class);
            query.setParameter("rol", rol);
            return query.getResultList();
        } finally {
            em.close();
        }
    }
    
    public Usuarios autenticar(String correo, String contrasena) {
        EntityManager em = emf.createEntityManager();
        
        try {
            TypedQuery<Usuarios> query = em.createQuery(
                "SELECT u FROM Usuarios u WHERE u.correo = :correo AND u.contrasena = :contrasena", Usuarios.class);
            query.setParameter("correo", correo);
            query.setParameter("contrasena", contrasena);
            List<Usuarios> resultados = query.getResultList();
            return resultados.isEmpty() ? null : resultados.get(0);
        } finally {
            em.close();
        }
    }
    
    public void actualizar(Usuarios usuario) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        
        try {
            tx.begin();
            em.merge(usuario);
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
            Usuarios usuario = em.find(Usuarios.class, id);
            if (usuario != null) {
                em.remove(usuario);
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

