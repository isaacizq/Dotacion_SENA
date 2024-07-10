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
import Logic.Area;
import Logic.Red;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author isaac
 */
public class RedJpaController implements Serializable {

    public RedJpaController() {
        this.emf = Persistence.createEntityManagerFactory("DotasoftPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Red red) throws PreexistingEntityException, Exception {
        if (red.getAreaList() == null) {
            red.setAreaList(new ArrayList<Area>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Area> attachedAreaList = new ArrayList<Area>();
            for (Area areaListAreaToAttach : red.getAreaList()) {
                areaListAreaToAttach = em.getReference(areaListAreaToAttach.getClass(), areaListAreaToAttach.getIdarea());
                attachedAreaList.add(areaListAreaToAttach);
            }
            red.setAreaList(attachedAreaList);
            em.persist(red);
            for (Area areaListArea : red.getAreaList()) {
                Red oldRedIdredOfAreaListArea = areaListArea.getRedIdred();
                areaListArea.setRedIdred(red);
                areaListArea = em.merge(areaListArea);
                if (oldRedIdredOfAreaListArea != null) {
                    oldRedIdredOfAreaListArea.getAreaList().remove(areaListArea);
                    oldRedIdredOfAreaListArea = em.merge(oldRedIdredOfAreaListArea);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findRed(red.getIdred()) != null) {
                throw new PreexistingEntityException("Red " + red + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Red red) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Red persistentRed = em.find(Red.class, red.getIdred());
            List<Area> areaListOld = persistentRed.getAreaList();
            List<Area> areaListNew = red.getAreaList();
            List<String> illegalOrphanMessages = null;
            /*for (Area areaListOldArea : areaListOld) {
                if (!areaListNew.contains(areaListOldArea)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Area " + areaListOldArea + " since its redIdred field is not nullable.");
                }
            }*/
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<Area> attachedAreaListNew = new ArrayList<Area>();
            /*for (Area areaListNewAreaToAttach : areaListNew) {
                areaListNewAreaToAttach = em.getReference(areaListNewAreaToAttach.getClass(), areaListNewAreaToAttach.getIdarea());
                attachedAreaListNew.add(areaListNewAreaToAttach);
            }*/
            areaListNew = attachedAreaListNew;
            red.setAreaList(areaListNew);
            red = em.merge(red);
            for (Area areaListNewArea : areaListNew) {
                if (!areaListOld.contains(areaListNewArea)) {
                    Red oldRedIdredOfAreaListNewArea = areaListNewArea.getRedIdred();
                    areaListNewArea.setRedIdred(red);
                    areaListNewArea = em.merge(areaListNewArea);
                    if (oldRedIdredOfAreaListNewArea != null && !oldRedIdredOfAreaListNewArea.equals(red)) {
                        oldRedIdredOfAreaListNewArea.getAreaList().remove(areaListNewArea);
                        oldRedIdredOfAreaListNewArea = em.merge(oldRedIdredOfAreaListNewArea);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = red.getIdred();
                if (findRed(id) == null) {
                    throw new NonexistentEntityException("The red with id " + id + " no longer exists.");
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
            Red red;
            try {
                red = em.getReference(Red.class, id);
                red.getIdred();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The red with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Area> areaListOrphanCheck = red.getAreaList();
            for (Area areaListOrphanCheckArea : areaListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Red (" + red + ") cannot be destroyed since the Area " + areaListOrphanCheckArea + " in its areaList field has a non-nullable redIdred field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(red);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Red> findRedEntities() {
        return findRedEntities(true, -1, -1);
    }

    public List<Red> findRedEntities(int maxResults, int firstResult) {
        return findRedEntities(false, maxResults, firstResult);
    }

    private List<Red> findRedEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Red.class));
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

    public Red findRed(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Red.class, id);
        } finally {
            em.close();
        }
    }

    public int getRedCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Red> rt = cq.from(Red.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
