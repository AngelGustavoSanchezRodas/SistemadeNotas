package pro.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

/**
 * Clase utilitaria para manejar una sola instancia de EntityManagerFactory.
 * Garantiza que la aplicación no abra múltiples sesiones y maneja la conexión de manera centralizada.
 */
public class JPAUtil {

    private static final String PERSISTENCE_UNIT_NAME = "ProPro";
    private static EntityManagerFactory emf;

    // Inicializa el EntityManagerFactory solo una vez
    static {
        try {
            emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ExceptionInInitializerError(
                "❌ Error al inicializar la unidad de persistencia: " + e.getMessage()
            );
        }
    }

    /**
     * Devuelve el EntityManagerFactory (para casos avanzados).
     */
    public static EntityManagerFactory getEntityManagerFactory() {
        return emf;
    }

    /**
     * Devuelve un nuevo EntityManager (sesión) para uso de DAOs.
     */
    public static EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    /**
     * Cierra el EntityManagerFactory cuando la aplicación termina.
     */
    public static void cerrar() {
        if (emf != null && emf.isOpen()) {
            emf.close();
        }
    }
}
