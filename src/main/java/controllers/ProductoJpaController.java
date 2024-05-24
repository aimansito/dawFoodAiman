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
import models.TipoProducto;
import models.DetalleTicket;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import models.Producto;

/**
 *
 * @author aiman
 */
public class ProductoJpaController implements Serializable {

    public ProductoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    // creo un constructor para poder tener una instancia de cada controller 
    // y asi hacer uso de los metodos de cada uno
    public ProductoJpaController() {
        emf = Persistence.createEntityManagerFactory("dawFoodAimanXML");
    }

    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Producto producto) {
        if (producto.getDetalleTicketCollection() == null) {
            producto.setDetalleTicketCollection(new ArrayList<DetalleTicket>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            TipoProducto codTipoProducto = producto.getCodTipoProducto();
            if (codTipoProducto != null) {
                codTipoProducto = em.getReference(codTipoProducto.getClass(), codTipoProducto.getCodTipoProducto());
                producto.setCodTipoProducto(codTipoProducto);
            }
            Collection<DetalleTicket> attachedDetalleTicketCollection = new ArrayList<DetalleTicket>();
            for (DetalleTicket detalleTicketCollectionDetalleTicketToAttach : producto.getDetalleTicketCollection()) {
                detalleTicketCollectionDetalleTicketToAttach = em.getReference(detalleTicketCollectionDetalleTicketToAttach.getClass(), detalleTicketCollectionDetalleTicketToAttach.getCantidadProducto());
                attachedDetalleTicketCollection.add(detalleTicketCollectionDetalleTicketToAttach);
            }
            producto.setDetalleTicketCollection(attachedDetalleTicketCollection);
            em.persist(producto);
            if (codTipoProducto != null) {
                codTipoProducto.getProductoCollection().add(producto);
                codTipoProducto = em.merge(codTipoProducto);
            }
            for (DetalleTicket detalleTicketCollectionDetalleTicket : producto.getDetalleTicketCollection()) {
                Producto oldIdProductoOfDetalleTicketCollectionDetalleTicket = detalleTicketCollectionDetalleTicket.getIdProducto();
                detalleTicketCollectionDetalleTicket.setIdProducto(producto);
                detalleTicketCollectionDetalleTicket = em.merge(detalleTicketCollectionDetalleTicket);
                if (oldIdProductoOfDetalleTicketCollectionDetalleTicket != null) {
                    oldIdProductoOfDetalleTicketCollectionDetalleTicket.getDetalleTicketCollection().remove(detalleTicketCollectionDetalleTicket);
                    oldIdProductoOfDetalleTicketCollectionDetalleTicket = em.merge(oldIdProductoOfDetalleTicketCollectionDetalleTicket);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Producto producto) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Producto persistentProducto = em.find(Producto.class, producto.getIdProducto());
            TipoProducto codTipoProductoOld = persistentProducto.getCodTipoProducto();
            TipoProducto codTipoProductoNew = producto.getCodTipoProducto();
            Collection<DetalleTicket> detalleTicketCollectionOld = persistentProducto.getDetalleTicketCollection();
            Collection<DetalleTicket> detalleTicketCollectionNew = producto.getDetalleTicketCollection();
            List<String> illegalOrphanMessages = null;
            for (DetalleTicket detalleTicketCollectionOldDetalleTicket : detalleTicketCollectionOld) {
                if (!detalleTicketCollectionNew.contains(detalleTicketCollectionOldDetalleTicket)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain DetalleTicket " + detalleTicketCollectionOldDetalleTicket + " since its idProducto field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (codTipoProductoNew != null) {
                codTipoProductoNew = em.getReference(codTipoProductoNew.getClass(), codTipoProductoNew.getCodTipoProducto());
                producto.setCodTipoProducto(codTipoProductoNew);
            }
            Collection<DetalleTicket> attachedDetalleTicketCollectionNew = new ArrayList<DetalleTicket>();
            for (DetalleTicket detalleTicketCollectionNewDetalleTicketToAttach : detalleTicketCollectionNew) {
                detalleTicketCollectionNewDetalleTicketToAttach = em.getReference(detalleTicketCollectionNewDetalleTicketToAttach.getClass(), detalleTicketCollectionNewDetalleTicketToAttach.getCantidadProducto());
                attachedDetalleTicketCollectionNew.add(detalleTicketCollectionNewDetalleTicketToAttach);
            }
            detalleTicketCollectionNew = attachedDetalleTicketCollectionNew;
            producto.setDetalleTicketCollection(detalleTicketCollectionNew);
            producto = em.merge(producto);
            if (codTipoProductoOld != null && !codTipoProductoOld.equals(codTipoProductoNew)) {
                codTipoProductoOld.getProductoCollection().remove(producto);
                codTipoProductoOld = em.merge(codTipoProductoOld);
            }
            if (codTipoProductoNew != null && !codTipoProductoNew.equals(codTipoProductoOld)) {
                codTipoProductoNew.getProductoCollection().add(producto);
                codTipoProductoNew = em.merge(codTipoProductoNew);
            }
            for (DetalleTicket detalleTicketCollectionNewDetalleTicket : detalleTicketCollectionNew) {
                if (!detalleTicketCollectionOld.contains(detalleTicketCollectionNewDetalleTicket)) {
                    Producto oldIdProductoOfDetalleTicketCollectionNewDetalleTicket = detalleTicketCollectionNewDetalleTicket.getIdProducto();
                    detalleTicketCollectionNewDetalleTicket.setIdProducto(producto);
                    detalleTicketCollectionNewDetalleTicket = em.merge(detalleTicketCollectionNewDetalleTicket);
                    if (oldIdProductoOfDetalleTicketCollectionNewDetalleTicket != null && !oldIdProductoOfDetalleTicketCollectionNewDetalleTicket.equals(producto)) {
                        oldIdProductoOfDetalleTicketCollectionNewDetalleTicket.getDetalleTicketCollection().remove(detalleTicketCollectionNewDetalleTicket);
                        oldIdProductoOfDetalleTicketCollectionNewDetalleTicket = em.merge(oldIdProductoOfDetalleTicketCollectionNewDetalleTicket);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = producto.getIdProducto();
                if (findProducto(id) == null) {
                    throw new NonexistentEntityException("The producto with id " + id + " no longer exists.");
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
            Producto producto;
            try {
                producto = em.getReference(Producto.class, id);
                producto.getIdProducto();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The producto with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<DetalleTicket> detalleTicketCollectionOrphanCheck = producto.getDetalleTicketCollection();
            for (DetalleTicket detalleTicketCollectionOrphanCheckDetalleTicket : detalleTicketCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Producto (" + producto + ") cannot be destroyed since the DetalleTicket " + detalleTicketCollectionOrphanCheckDetalleTicket + " in its detalleTicketCollection field has a non-nullable idProducto field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            TipoProducto codTipoProducto = producto.getCodTipoProducto();
            if (codTipoProducto != null) {
                codTipoProducto.getProductoCollection().remove(producto);
                codTipoProducto = em.merge(codTipoProducto);
            }
            em.remove(producto);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Producto> findProductoEntities() {
        return findProductoEntities(true, -1, -1);
    }

    public List<Producto> findProductoEntities(int maxResults, int firstResult) {
        return findProductoEntities(false, maxResults, firstResult);
    }

    private List<Producto> findProductoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Producto.class));
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

    public Producto findProducto(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Producto.class, id);
        } finally {
            em.close();
        }
    }

    public int getProductoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Producto> rt = cq.from(Producto.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
