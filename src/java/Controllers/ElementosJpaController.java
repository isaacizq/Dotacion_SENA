/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controllers;

import Controllers.exceptions.IllegalOrphanException;
import Controllers.exceptions.NonexistentEntityException;
import Controllers.exceptions.PreexistingEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import Logic.Dotacion;
import Logic.Elementos;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author isaac
 */
public class ElementosJpaController implements Serializable {

    public ElementosJpaController() {
        this.emf = Persistence.createEntityManagerFactory("DotasoftPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Elementos elementos) throws PreexistingEntityException, Exception {
        if (elementos.getDotacionList() == null) {
            elementos.setDotacionList(new ArrayList<Dotacion>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Dotacion> attachedDotacionList = new ArrayList<Dotacion>();
            for (Dotacion dotacionListDotacionToAttach : elementos.getDotacionList()) {
                dotacionListDotacionToAttach = em.getReference(dotacionListDotacionToAttach.getClass(), dotacionListDotacionToAttach.getIddotacion());
                attachedDotacionList.add(dotacionListDotacionToAttach);
            }
            elementos.setDotacionList(attachedDotacionList);
            em.persist(elementos);
            for (Dotacion dotacionListDotacion : elementos.getDotacionList()) {
                Elementos oldElementosIdelementoOfDotacionListDotacion = dotacionListDotacion.getElementosIdelemento();
                dotacionListDotacion.setElementosIdelemento(elementos);
                dotacionListDotacion = em.merge(dotacionListDotacion);
                if (oldElementosIdelementoOfDotacionListDotacion != null) {
                    oldElementosIdelementoOfDotacionListDotacion.getDotacionList().remove(dotacionListDotacion);
                    oldElementosIdelementoOfDotacionListDotacion = em.merge(oldElementosIdelementoOfDotacionListDotacion);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findElementos(elementos.getIdelemento()) != null) {
                throw new PreexistingEntityException("Elementos " + elementos + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Elementos elementos) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Elementos persistentElementos = em.find(Elementos.class, elementos.getIdelemento());
            List<Dotacion> dotacionListOld = persistentElementos.getDotacionList();
            List<Dotacion> dotacionListNew = elementos.getDotacionList();
            List<String> illegalOrphanMessages = null;
            /*for (Dotacion dotacionListOldDotacion : dotacionListOld) {
                if (!dotacionListNew.contains(dotacionListOldDotacion)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Dotacion " + dotacionListOldDotacion + " since its elementosIdelemento field is not nullable.");
                }
            }*/
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<Dotacion> attachedDotacionListNew = new ArrayList<Dotacion>();
            /*for (Dotacion dotacionListNewDotacionToAttach : dotacionListNew) {
                dotacionListNewDotacionToAttach = em.getReference(dotacionListNewDotacionToAttach.getClass(), dotacionListNewDotacionToAttach.getIddotacion());
                attachedDotacionListNew.add(dotacionListNewDotacionToAttach);
            }*/
            dotacionListNew = attachedDotacionListNew;
            elementos.setDotacionList(dotacionListNew);
            elementos = em.merge(elementos);
            for (Dotacion dotacionListNewDotacion : dotacionListNew) {
                if (!dotacionListOld.contains(dotacionListNewDotacion)) {
                    Elementos oldElementosIdelementoOfDotacionListNewDotacion = dotacionListNewDotacion.getElementosIdelemento();
                    dotacionListNewDotacion.setElementosIdelemento(elementos);
                    dotacionListNewDotacion = em.merge(dotacionListNewDotacion);
                    if (oldElementosIdelementoOfDotacionListNewDotacion != null && !oldElementosIdelementoOfDotacionListNewDotacion.equals(elementos)) {
                        oldElementosIdelementoOfDotacionListNewDotacion.getDotacionList().remove(dotacionListNewDotacion);
                        oldElementosIdelementoOfDotacionListNewDotacion = em.merge(oldElementosIdelementoOfDotacionListNewDotacion);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = elementos.getIdelemento();
                if (findElementos(id) == null) {
                    throw new NonexistentEntityException("The elementos with id " + id + " no longer exists.");
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
            Elementos elementos;
            try {
                elementos = em.getReference(Elementos.class, id);
                elementos.getIdelemento();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The elementos with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Dotacion> dotacionListOrphanCheck = elementos.getDotacionList();
            for (Dotacion dotacionListOrphanCheckDotacion : dotacionListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Elementos (" + elementos + ") cannot be destroyed since the Dotacion " + dotacionListOrphanCheckDotacion + " in its dotacionList field has a non-nullable elementosIdelemento field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(elementos);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Elementos> findElementosEntities() {
        return findElementosEntities(true, -1, -1);
    }

    public List<Elementos> findElementosEntities(int maxResults, int firstResult) {
        return findElementosEntities(false, maxResults, firstResult);
    }

    private List<Elementos> findElementosEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Elementos.class));
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

    public Elementos findElementos(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Elementos.class, id);
        } finally {
            em.close();
        }
    }

    public int getElementosCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Elementos> rt = cq.from(Elementos.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
