/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import controllers.exceptions.IllegalOrphanException;
import controllers.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import models.Tpv;
import models.DetalleTicket;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import models.Ticket;

/**
 *
 * @author aiman
 */
public class TicketJpaController implements Serializable {

    public TicketJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }

    // creo un constructor para poder tener una instancia de cada controller 
    // y asi hacer uso de los metodos de cada uno
    public TicketJpaController() {
        emf = Persistence.createEntityManagerFactory("dawFoodAimanXML");
    }
    
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Ticket ticket) {
        if (ticket.getDetalleTicketCollection() == null) {
            ticket.setDetalleTicketCollection(new ArrayList<DetalleTicket>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Tpv idTPV = ticket.getIdTPV();
            if (idTPV != null) {
                idTPV = em.getReference(idTPV.getClass(), idTPV.getIdTPV());
                ticket.setIdTPV(idTPV);
            }
            Collection<DetalleTicket> attachedDetalleTicketCollection = new ArrayList<DetalleTicket>();
            for (DetalleTicket detalleTicketCollectionDetalleTicketToAttach : ticket.getDetalleTicketCollection()) {
                detalleTicketCollectionDetalleTicketToAttach = em.getReference(detalleTicketCollectionDetalleTicketToAttach.getClass(), detalleTicketCollectionDetalleTicketToAttach.getCantidadProducto());
                attachedDetalleTicketCollection.add(detalleTicketCollectionDetalleTicketToAttach);
            }
            ticket.setDetalleTicketCollection(attachedDetalleTicketCollection);
            em.persist(ticket);
            if (idTPV != null) {
                idTPV.getTicketCollection().add(ticket);
                idTPV = em.merge(idTPV);
            }
            for (DetalleTicket detalleTicketCollectionDetalleTicket : ticket.getDetalleTicketCollection()) {
                Ticket oldIdTicketOfDetalleTicketCollectionDetalleTicket = detalleTicketCollectionDetalleTicket.getIdTicket();
                detalleTicketCollectionDetalleTicket.setIdTicket(ticket);
                detalleTicketCollectionDetalleTicket = em.merge(detalleTicketCollectionDetalleTicket);
                if (oldIdTicketOfDetalleTicketCollectionDetalleTicket != null) {
                    oldIdTicketOfDetalleTicketCollectionDetalleTicket.getDetalleTicketCollection().remove(detalleTicketCollectionDetalleTicket);
                    oldIdTicketOfDetalleTicketCollectionDetalleTicket = em.merge(oldIdTicketOfDetalleTicketCollectionDetalleTicket);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Ticket ticket) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Ticket persistentTicket = em.find(Ticket.class, ticket.getIdTicket());
            Tpv idTPVOld = persistentTicket.getIdTPV();
            Tpv idTPVNew = ticket.getIdTPV();
            Collection<DetalleTicket> detalleTicketCollectionOld = persistentTicket.getDetalleTicketCollection();
            Collection<DetalleTicket> detalleTicketCollectionNew = ticket.getDetalleTicketCollection();
            List<String> illegalOrphanMessages = null;
            for (DetalleTicket detalleTicketCollectionOldDetalleTicket : detalleTicketCollectionOld) {
                if (!detalleTicketCollectionNew.contains(detalleTicketCollectionOldDetalleTicket)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain DetalleTicket " + detalleTicketCollectionOldDetalleTicket + " since its idTicket field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (idTPVNew != null) {
                idTPVNew = em.getReference(idTPVNew.getClass(), idTPVNew.getIdTPV());
                ticket.setIdTPV(idTPVNew);
            }
            Collection<DetalleTicket> attachedDetalleTicketCollectionNew = new ArrayList<DetalleTicket>();
            for (DetalleTicket detalleTicketCollectionNewDetalleTicketToAttach : detalleTicketCollectionNew) {
                detalleTicketCollectionNewDetalleTicketToAttach = em.getReference(detalleTicketCollectionNewDetalleTicketToAttach.getClass(), detalleTicketCollectionNewDetalleTicketToAttach.getCantidadProducto());
                attachedDetalleTicketCollectionNew.add(detalleTicketCollectionNewDetalleTicketToAttach);
            }
            detalleTicketCollectionNew = attachedDetalleTicketCollectionNew;
            ticket.setDetalleTicketCollection(detalleTicketCollectionNew);
            ticket = em.merge(ticket);
            if (idTPVOld != null && !idTPVOld.equals(idTPVNew)) {
                idTPVOld.getTicketCollection().remove(ticket);
                idTPVOld = em.merge(idTPVOld);
            }
            if (idTPVNew != null && !idTPVNew.equals(idTPVOld)) {
                idTPVNew.getTicketCollection().add(ticket);
                idTPVNew = em.merge(idTPVNew);
            }
            for (DetalleTicket detalleTicketCollectionNewDetalleTicket : detalleTicketCollectionNew) {
                if (!detalleTicketCollectionOld.contains(detalleTicketCollectionNewDetalleTicket)) {
                    Ticket oldIdTicketOfDetalleTicketCollectionNewDetalleTicket = detalleTicketCollectionNewDetalleTicket.getIdTicket();
                    detalleTicketCollectionNewDetalleTicket.setIdTicket(ticket);
                    detalleTicketCollectionNewDetalleTicket = em.merge(detalleTicketCollectionNewDetalleTicket);
                    if (oldIdTicketOfDetalleTicketCollectionNewDetalleTicket != null && !oldIdTicketOfDetalleTicketCollectionNewDetalleTicket.equals(ticket)) {
                        oldIdTicketOfDetalleTicketCollectionNewDetalleTicket.getDetalleTicketCollection().remove(detalleTicketCollectionNewDetalleTicket);
                        oldIdTicketOfDetalleTicketCollectionNewDetalleTicket = em.merge(oldIdTicketOfDetalleTicketCollectionNewDetalleTicket);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = ticket.getIdTicket();
                if (findTicket(id) == null) {
                    throw new NonexistentEntityException("The ticket with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Ticket ticket;
            try {
                ticket = em.getReference(Ticket.class, id);
                ticket.getIdTicket();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The ticket with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<DetalleTicket> detalleTicketCollectionOrphanCheck = ticket.getDetalleTicketCollection();
            for (DetalleTicket detalleTicketCollectionOrphanCheckDetalleTicket : detalleTicketCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Ticket (" + ticket + ") cannot be destroyed since the DetalleTicket " + detalleTicketCollectionOrphanCheckDetalleTicket + " in its detalleTicketCollection field has a non-nullable idTicket field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Tpv idTPV = ticket.getIdTPV();
            if (idTPV != null) {
                idTPV.getTicketCollection().remove(ticket);
                idTPV = em.merge(idTPV);
            }
            em.remove(ticket);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Ticket> findTicketEntities() {
        return findTicketEntities(true, -1, -1);
    }

    public List<Ticket> findTicketEntities(int maxResults, int firstResult) {
        return findTicketEntities(false, maxResults, firstResult);
    }

    private List<Ticket> findTicketEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Ticket.class));
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

    public Ticket findTicket(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Ticket.class, id);
        } finally {
            em.close();
        }
    }

    public int getTicketCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Ticket> rt = cq.from(Ticket.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
