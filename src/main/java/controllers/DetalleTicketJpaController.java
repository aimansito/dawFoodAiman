/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import controllers.exceptions.NonexistentEntityException;
import controllers.exceptions.PreexistingEntityException;
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
import models.DetalleTicketPK;
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
    private EntityManagerFactory emf = null;

    // creo un constructor para poder tener una instancia de cada controller 
    // y asi hacer uso de los metodos de cada uno
    public DetalleTicketJpaController() {
        emf = Persistence.createEntityManagerFactory("dawFoodAimanXML");
    }

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(DetalleTicket detalleTicket) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();

            // Obtener referencia al Producto asociado al DetalleTicket
            Producto producto = em.getReference(Producto.class, detalleTicket.getProducto().getIdProducto());
            detalleTicket.setIdProducto(producto);

            // Obtener referencia al Ticket asociado al DetalleTicket
            Ticket ticket = em.getReference(Ticket.class, detalleTicket.getTicket().getIdTicket());
            detalleTicket.setTicket(ticket);

            em.persist(detalleTicket);

            // Actualizar la colección de DetalleTicket en Producto y Ticket
            producto.getDetalleTicketCollection().add(detalleTicket);
            ticket.getDetalleTicketCollection().add(detalleTicket);

            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findDetalleTicket(detalleTicket.getDetalleTicketPK()) != null) {
                throw new PreexistingEntityException("DetalleTicket " + detalleTicket + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(DetalleTicket detalleTicket) throws NonexistentEntityException, Exception {
        detalleTicket.getDetalleTicketPK().setIdTicket(detalleTicket.getTicket().getIdTicket());
        detalleTicket.getDetalleTicketPK().setIdProducto(detalleTicket.getProducto().getIdProducto());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            DetalleTicket persistentDetalleTicket = em.find(DetalleTicket.class, detalleTicket.getDetalleTicketPK());
            Producto productoOld = persistentDetalleTicket.getProducto();
            Producto productoNew = detalleTicket.getProducto();
            Ticket ticketOld = persistentDetalleTicket.getTicket();
            Ticket ticketNew = detalleTicket.getTicket();
            if (productoNew != null) {
                productoNew = em.getReference(productoNew.getClass(), productoNew.getIdProducto());
                detalleTicket.setProducto(productoNew);
            }
            if (ticketNew != null) {
                ticketNew = em.getReference(ticketNew.getClass(), ticketNew.getIdTicket());
                detalleTicket.setTicket(ticketNew);
            }
            detalleTicket = em.merge(detalleTicket);
            if (productoOld != null && !productoOld.equals(productoNew)) {
                productoOld.getDetalleTicketCollection().remove(detalleTicket);
                productoOld = em.merge(productoOld);
            }
            if (productoNew != null && !productoNew.equals(productoOld)) {
                productoNew.getDetalleTicketCollection().add(detalleTicket);
                productoNew = em.merge(productoNew);
            }
            if (ticketOld != null && !ticketOld.equals(ticketNew)) {
                ticketOld.getDetalleTicketCollection().remove(detalleTicket);
                ticketOld = em.merge(ticketOld);
            }
            if (ticketNew != null && !ticketNew.equals(ticketOld)) {
                ticketNew.getDetalleTicketCollection().add(detalleTicket);
                ticketNew = em.merge(ticketNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                DetalleTicketPK id = detalleTicket.getDetalleTicketPK();
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

    public void destroy(DetalleTicketPK id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            DetalleTicket detalleTicket;
            try {
                detalleTicket = em.getReference(DetalleTicket.class, id);
                detalleTicket.getDetalleTicketPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The detalleTicket with id " + id + " no longer exists.", enfe);
            }
            Producto producto = detalleTicket.getProducto();
            if (producto != null) {
                producto.getDetalleTicketCollection().remove(detalleTicket);
                producto = em.merge(producto);
            }
            Ticket ticket = detalleTicket.getTicket();
            if (ticket != null) {
                ticket.getDetalleTicketCollection().remove(detalleTicket);
                ticket = em.merge(ticket);
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

    public DetalleTicket findDetalleTicket(DetalleTicketPK id) {
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
