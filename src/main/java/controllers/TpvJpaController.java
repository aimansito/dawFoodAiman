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
import models.Ticket;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.JOptionPane;
import models.Tpv;

/**
 *
 * @author aiman
 */
public class TpvJpaController implements Serializable {

    public TpvJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    
     // creo un constructor para poder tener una instancia de cada controller 
    // y asi hacer uso de los metodos de cada uno

    public TpvJpaController() {
        emf = Persistence.createEntityManagerFactory("dawFoodAimanXML");
    }

    public void create(Tpv tpv) {
        if (tpv.getTicketCollection() == null) {
            tpv.setTicketCollection(new ArrayList<Ticket>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();

            // Verificar si ya existe un TPV con la misma ubicación
            Query query = em.createQuery("SELECT t FROM Tpv t WHERE t.ubicacion = :ubicacion");
            query.setParameter("ubicacion", tpv.getUbicacion());
            List<Tpv> existingTPVs = query.getResultList();

            if (!existingTPVs.isEmpty()) {
                // Si ya existe un TPV con la misma ubicación, no se insertan los datos
                JOptionPane.showMessageDialog(null, "Ya existe un TPV en esta ubicación");
            } else {
                // Continuar con la inserción del nuevo TPV
                Collection<Ticket> attachedTicketCollection = new ArrayList<Ticket>();
                for (Ticket ticketCollectionTicketToAttach : tpv.getTicketCollection()) {
                    ticketCollectionTicketToAttach = em.getReference(ticketCollectionTicketToAttach.getClass(), ticketCollectionTicketToAttach.getIdTicket());
                    attachedTicketCollection.add(ticketCollectionTicketToAttach);
                }
                tpv.setTicketCollection(attachedTicketCollection);
                em.persist(tpv);
                for (Ticket ticketCollectionTicket : tpv.getTicketCollection()) {
                    Tpv oldIdTPVOfTicketCollectionTicket = ticketCollectionTicket.getIdTPV();
                    ticketCollectionTicket.setIdTPV(tpv);
                    ticketCollectionTicket = em.merge(ticketCollectionTicket);
                    if (oldIdTPVOfTicketCollectionTicket != null) {
                        oldIdTPVOfTicketCollectionTicket.getTicketCollection().remove(ticketCollectionTicket);
                        oldIdTPVOfTicketCollectionTicket = em.merge(oldIdTPVOfTicketCollectionTicket);
                    }
                }
                em.getTransaction().commit();
            }
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Tpv tpv) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Tpv persistentTpv = em.find(Tpv.class, tpv.getIdTPV());
            Collection<Ticket> ticketCollectionOld = persistentTpv.getTicketCollection();
            Collection<Ticket> ticketCollectionNew = tpv.getTicketCollection();
            List<String> illegalOrphanMessages = null;
            for (Ticket ticketCollectionOldTicket : ticketCollectionOld) {
                if (!ticketCollectionNew.contains(ticketCollectionOldTicket)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Ticket " + ticketCollectionOldTicket + " since its idTPV field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Collection<Ticket> attachedTicketCollectionNew = new ArrayList<Ticket>();
            for (Ticket ticketCollectionNewTicketToAttach : ticketCollectionNew) {
                ticketCollectionNewTicketToAttach = em.getReference(ticketCollectionNewTicketToAttach.getClass(), ticketCollectionNewTicketToAttach.getIdTicket());
                attachedTicketCollectionNew.add(ticketCollectionNewTicketToAttach);
            }
            ticketCollectionNew = attachedTicketCollectionNew;
            tpv.setTicketCollection(ticketCollectionNew);
            tpv = em.merge(tpv);
            for (Ticket ticketCollectionNewTicket : ticketCollectionNew) {
                if (!ticketCollectionOld.contains(ticketCollectionNewTicket)) {
                    Tpv oldIdTPVOfTicketCollectionNewTicket = ticketCollectionNewTicket.getIdTPV();
                    ticketCollectionNewTicket.setIdTPV(tpv);
                    ticketCollectionNewTicket = em.merge(ticketCollectionNewTicket);
                    if (oldIdTPVOfTicketCollectionNewTicket != null && !oldIdTPVOfTicketCollectionNewTicket.equals(tpv)) {
                        oldIdTPVOfTicketCollectionNewTicket.getTicketCollection().remove(ticketCollectionNewTicket);
                        oldIdTPVOfTicketCollectionNewTicket = em.merge(oldIdTPVOfTicketCollectionNewTicket);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = tpv.getIdTPV();
                if (findTpv(id) == null) {
                    throw new NonexistentEntityException("The tpv with id " + id + " no longer exists.");
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
            Tpv tpv;
            try {
                tpv = em.getReference(Tpv.class, id);
                tpv.getIdTPV();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The tpv with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<Ticket> ticketCollectionOrphanCheck = tpv.getTicketCollection();
            for (Ticket ticketCollectionOrphanCheckTicket : ticketCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Tpv (" + tpv + ") cannot be destroyed since the Ticket " + ticketCollectionOrphanCheckTicket + " in its ticketCollection field has a non-nullable idTPV field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(tpv);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Tpv> findTpvEntities() {
        return findTpvEntities(true, -1, -1);
    }

    public List<Tpv> findTpvEntities(int maxResults, int firstResult) {
        return findTpvEntities(false, maxResults, firstResult);
    }

    private List<Tpv> findTpvEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Tpv.class));
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

    public Tpv findTpv(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Tpv.class, id);
        } finally {
            em.close();
        }
    }

    public int getTpvCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Tpv> rt = cq.from(Tpv.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
