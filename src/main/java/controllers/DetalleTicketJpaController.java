/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import controllers.exceptions.NonexistentEntityException;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import models.DetalleTicket;
import models.Producto;
import models.Ticket;

/**
 *
 * @author aiman
 */
public class DetalleTicketJpaController implements Serializable {

    public DetalleTicketJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    // creo un constructor para poder tener una instancia de cada controller 
    // y asi hacer uso de los metodos de cada uno
    public DetalleTicketJpaController(){
        emf = Persistence.createEntityManagerFactory("dawFoodAimanXML");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(DetalleTicket detalleTicket) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Producto idProducto = detalleTicket.getIdProducto();
            if (idProducto != null) {
                idProducto = em.getReference(idProducto.getClass(), idProducto.getIdProducto());
                detalleTicket.setIdProducto(idProducto);
            }
            Ticket idTicket = detalleTicket.getIdTicket();
            if (idTicket != null) {
                idTicket = em.getReference(idTicket.getClass(), idTicket.getIdTicket());
                detalleTicket.setIdTicket(idTicket);
            }
            em.persist(detalleTicket);
            if (idProducto != null) {
                idProducto.getDetalleTicketCollection().add(detalleTicket);
                idProducto = em.merge(idProducto);
            }
            if (idTicket != null) {
                idTicket.getDetalleTicketCollection().add(detalleTicket);
                idTicket = em.merge(idTicket);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(DetalleTicket detalleTicket) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            DetalleTicket persistentDetalleTicket = em.find(DetalleTicket.class, detalleTicket.getCantidadProducto());
            Producto idProductoOld = persistentDetalleTicket.getIdProducto();
            Producto idProductoNew = detalleTicket.getIdProducto();
            Ticket idTicketOld = persistentDetalleTicket.getIdTicket();
            Ticket idTicketNew = detalleTicket.getIdTicket();
            if (idProductoNew != null) {
                idProductoNew = em.getReference(idProductoNew.getClass(), idProductoNew.getIdProducto());
                detalleTicket.setIdProducto(idProductoNew);
            }
            if (idTicketNew != null) {
                idTicketNew = em.getReference(idTicketNew.getClass(), idTicketNew.getIdTicket());
                detalleTicket.setIdTicket(idTicketNew);
            }
            detalleTicket = em.merge(detalleTicket);
            if (idProductoOld != null && !idProductoOld.equals(idProductoNew)) {
                idProductoOld.getDetalleTicketCollection().remove(detalleTicket);
                idProductoOld = em.merge(idProductoOld);
            }
            if (idProductoNew != null && !idProductoNew.equals(idProductoOld)) {
                idProductoNew.getDetalleTicketCollection().add(detalleTicket);
                idProductoNew = em.merge(idProductoNew);
            }
            if (idTicketOld != null && !idTicketOld.equals(idTicketNew)) {
                idTicketOld.getDetalleTicketCollection().remove(detalleTicket);
                idTicketOld = em.merge(idTicketOld);
            }
            if (idTicketNew != null && !idTicketNew.equals(idTicketOld)) {
                idTicketNew.getDetalleTicketCollection().add(detalleTicket);
                idTicketNew = em.merge(idTicketNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = detalleTicket.getCantidadProducto();
                if (findDetalleTicket(id) == null) {
                    throw new NonexistentEntityException("The detalleTicket with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            DetalleTicket detalleTicket;
            try {
                detalleTicket = em.getReference(DetalleTicket.class, id);
                detalleTicket.getCantidadProducto();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The detalleTicket with id " + id + " no longer exists.", enfe);
            }
            Producto idProducto = detalleTicket.getIdProducto();
            if (idProducto != null) {
                idProducto.getDetalleTicketCollection().remove(detalleTicket);
                idProducto = em.merge(idProducto);
            }
            Ticket idTicket = detalleTicket.getIdTicket();
            if (idTicket != null) {
                idTicket.getDetalleTicketCollection().remove(detalleTicket);
                idTicket = em.merge(idTicket);
            }
            em.remove(detalleTicket);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<DetalleTicket> findDetalleTicketEntities() {
        return findDetalleTicketEntities(true, -1, -1);
    }

    public List<DetalleTicket> findDetalleTicketEntities(int maxResults, int firstResult) {
        return findDetalleTicketEntities(false, maxResults, firstResult);
    }

    private List<DetalleTicket> findDetalleTicketEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(DetalleTicket.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public DetalleTicket findDetalleTicket(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(DetalleTicket.class, id);
        } finally {
            em.close();
        }
    }

    public int getDetalleTicketCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<DetalleTicket> rt = cq.from(DetalleTicket.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
