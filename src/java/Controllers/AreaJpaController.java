/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controllers;

import Controllers.exceptions.IllegalOrphanException;
import Controllers.exceptions.NonexistentEntityException;
import Controllers.exceptions.PreexistingEntityException;
import Logic.Area;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import Logic.Red;
import Logic.CaracterizarInstructor;
import java.util.ArrayList;
import java.util.List;
import Logic.Dotacion;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author isaac
 */
public class AreaJpaController implements Serializable {

    public AreaJpaController() {
        this.emf = Persistence.createEntityManagerFactory("DotasoftPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Area area) throws PreexistingEntityException, Exception {
        if (area.getCaracterizarInstructorList() == null) {
            area.setCaracterizarInstructorList(new ArrayList<CaracterizarInstructor>());
        }
        if (area.getDotacionList() == null) {
            area.setDotacionList(new ArrayList<Dotacion>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Red redIdred = area.getRedIdred();
            if (redIdred != null) {
                redIdred = em.getReference(redIdred.getClass(), redIdred.getIdred());
                area.setRedIdred(redIdred);
            }
            List<CaracterizarInstructor> attachedCaracterizarInstructorList = new ArrayList<CaracterizarInstructor>();
            for (CaracterizarInstructor caracterizarInstructorListCaracterizarInstructorToAttach : area.getCaracterizarInstructorList()) {
                caracterizarInstructorListCaracterizarInstructorToAttach = em.getReference(caracterizarInstructorListCaracterizarInstructorToAttach.getClass(), caracterizarInstructorListCaracterizarInstructorToAttach.getIdCaracterizarInstructor());
                attachedCaracterizarInstructorList.add(caracterizarInstructorListCaracterizarInstructorToAttach);
            }
            area.setCaracterizarInstructorList(attachedCaracterizarInstructorList);
            List<Dotacion> attachedDotacionList = new ArrayList<Dotacion>();
            for (Dotacion dotacionListDotacionToAttach : area.getDotacionList()) {
                dotacionListDotacionToAttach = em.getReference(dotacionListDotacionToAttach.getClass(), dotacionListDotacionToAttach.getIddotacion());
                attachedDotacionList.add(dotacionListDotacionToAttach);
            }
            area.setDotacionList(attachedDotacionList);
            em.persist(area);
            if (redIdred != null) {
                redIdred.getAreaList().add(area);
                redIdred = em.merge(redIdred);
            }
            for (CaracterizarInstructor caracterizarInstructorListCaracterizarInstructor : area.getCaracterizarInstructorList()) {
                Area oldAreaIdareaOfCaracterizarInstructorListCaracterizarInstructor = caracterizarInstructorListCaracterizarInstructor.getAreaIdarea();
                caracterizarInstructorListCaracterizarInstructor.setAreaIdarea(area);
                caracterizarInstructorListCaracterizarInstructor = em.merge(caracterizarInstructorListCaracterizarInstructor);
                if (oldAreaIdareaOfCaracterizarInstructorListCaracterizarInstructor != null) {
                    oldAreaIdareaOfCaracterizarInstructorListCaracterizarInstructor.getCaracterizarInstructorList().remove(caracterizarInstructorListCaracterizarInstructor);
                    oldAreaIdareaOfCaracterizarInstructorListCaracterizarInstructor = em.merge(oldAreaIdareaOfCaracterizarInstructorListCaracterizarInstructor);
                }
            }
            for (Dotacion dotacionListDotacion : area.getDotacionList()) {
                Area oldAreaIdareaOfDotacionListDotacion = dotacionListDotacion.getAreaIdarea();
                dotacionListDotacion.setAreaIdarea(area);
                dotacionListDotacion = em.merge(dotacionListDotacion);
                if (oldAreaIdareaOfDotacionListDotacion != null) {
                    oldAreaIdareaOfDotacionListDotacion.getDotacionList().remove(dotacionListDotacion);
                    oldAreaIdareaOfDotacionListDotacion = em.merge(oldAreaIdareaOfDotacionListDotacion);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findArea(area.getIdarea()) != null) {
                throw new PreexistingEntityException("Area " + area + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Area area) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Area persistentArea = em.find(Area.class, area.getIdarea());
            Red redIdredOld = persistentArea.getRedIdred();
            Red redIdredNew = area.getRedIdred();
            List<CaracterizarInstructor> caracterizarInstructorListOld = persistentArea.getCaracterizarInstructorList();
            List<CaracterizarInstructor> caracterizarInstructorListNew = area.getCaracterizarInstructorList();
            List<Dotacion> dotacionListOld = persistentArea.getDotacionList();
            List<Dotacion> dotacionListNew = area.getDotacionList();
            List<String> illegalOrphanMessages = null;
            for (CaracterizarInstructor caracterizarInstructorListOldCaracterizarInstructor : caracterizarInstructorListOld) {
                if (!caracterizarInstructorListNew.contains(caracterizarInstructorListOldCaracterizarInstructor)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain CaracterizarInstructor " + caracterizarInstructorListOldCaracterizarInstructor + " since its areaIdarea field is not nullable.");
                }
            }
            /*for (Dotacion dotacionListOldDotacion : dotacionListOld) {
                if (!dotacionListNew.contains(dotacionListOldDotacion)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Dotacion " + dotacionListOldDotacion + " since its areaIdarea field is not nullable.");
                }
            }*/
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (redIdredNew != null) {
                redIdredNew = em.getReference(redIdredNew.getClass(), redIdredNew.getIdred());
                area.setRedIdred(redIdredNew);
            }
            List<CaracterizarInstructor> attachedCaracterizarInstructorListNew = new ArrayList<CaracterizarInstructor>();
            /*for (CaracterizarInstructor caracterizarInstructorListNewCaracterizarInstructorToAttach : caracterizarInstructorListNew) {
                caracterizarInstructorListNewCaracterizarInstructorToAttach = em.getReference(caracterizarInstructorListNewCaracterizarInstructorToAttach.getClass(), caracterizarInstructorListNewCaracterizarInstructorToAttach.getIdCaracterizarInstructor());
                attachedCaracterizarInstructorListNew.add(caracterizarInstructorListNewCaracterizarInstructorToAttach);
            }*/
            caracterizarInstructorListNew = attachedCaracterizarInstructorListNew;
            area.setCaracterizarInstructorList(caracterizarInstructorListNew);
            List<Dotacion> attachedDotacionListNew = new ArrayList<Dotacion>();
            /*for (Dotacion dotacionListNewDotacionToAttach : dotacionListNew) {
                dotacionListNewDotacionToAttach = em.getReference(dotacionListNewDotacionToAttach.getClass(), dotacionListNewDotacionToAttach.getIddotacion());
                attachedDotacionListNew.add(dotacionListNewDotacionToAttach);
            }*/
            dotacionListNew = attachedDotacionListNew;
            area.setDotacionList(dotacionListNew);
            area = em.merge(area);
            if (redIdredOld != null && !redIdredOld.equals(redIdredNew)) {
                redIdredOld.getAreaList().remove(area);
                redIdredOld = em.merge(redIdredOld);
            }
            if (redIdredNew != null && !redIdredNew.equals(redIdredOld)) {
                redIdredNew.getAreaList().add(area);
                redIdredNew = em.merge(redIdredNew);
            }
            for (CaracterizarInstructor caracterizarInstructorListNewCaracterizarInstructor : caracterizarInstructorListNew) {
                if (!caracterizarInstructorListOld.contains(caracterizarInstructorListNewCaracterizarInstructor)) {
                    Area oldAreaIdareaOfCaracterizarInstructorListNewCaracterizarInstructor = caracterizarInstructorListNewCaracterizarInstructor.getAreaIdarea();
                    caracterizarInstructorListNewCaracterizarInstructor.setAreaIdarea(area);
                    caracterizarInstructorListNewCaracterizarInstructor = em.merge(caracterizarInstructorListNewCaracterizarInstructor);
                    if (oldAreaIdareaOfCaracterizarInstructorListNewCaracterizarInstructor != null && !oldAreaIdareaOfCaracterizarInstructorListNewCaracterizarInstructor.equals(area)) {
                        oldAreaIdareaOfCaracterizarInstructorListNewCaracterizarInstructor.getCaracterizarInstructorList().remove(caracterizarInstructorListNewCaracterizarInstructor);
                        oldAreaIdareaOfCaracterizarInstructorListNewCaracterizarInstructor = em.merge(oldAreaIdareaOfCaracterizarInstructorListNewCaracterizarInstructor);
                    }
                }
            }
            for (Dotacion dotacionListNewDotacion : dotacionListNew) {
                if (!dotacionListOld.contains(dotacionListNewDotacion)) {
                    Area oldAreaIdareaOfDotacionListNewDotacion = dotacionListNewDotacion.getAreaIdarea();
                    dotacionListNewDotacion.setAreaIdarea(area);
                    dotacionListNewDotacion = em.merge(dotacionListNewDotacion);
                    if (oldAreaIdareaOfDotacionListNewDotacion != null && !oldAreaIdareaOfDotacionListNewDotacion.equals(area)) {
                        oldAreaIdareaOfDotacionListNewDotacion.getDotacionList().remove(dotacionListNewDotacion);
                        oldAreaIdareaOfDotacionListNewDotacion = em.merge(oldAreaIdareaOfDotacionListNewDotacion);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = area.getIdarea();
                if (findArea(id) == null) {
                    throw new NonexistentEntityException("The area with id " + id + " no longer exists.");
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
            Area area;
            try {
                area = em.getReference(Area.class, id);
                area.getIdarea();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The area with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<CaracterizarInstructor> caracterizarInstructorListOrphanCheck = area.getCaracterizarInstructorList();
            for (CaracterizarInstructor caracterizarInstructorListOrphanCheckCaracterizarInstructor : caracterizarInstructorListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Area (" + area + ") cannot be destroyed since the CaracterizarInstructor " + caracterizarInstructorListOrphanCheckCaracterizarInstructor + " in its caracterizarInstructorList field has a non-nullable areaIdarea field.");
            }
            List<Dotacion> dotacionListOrphanCheck = area.getDotacionList();
            for (Dotacion dotacionListOrphanCheckDotacion : dotacionListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Area (" + area + ") cannot be destroyed since the Dotacion " + dotacionListOrphanCheckDotacion + " in its dotacionList field has a non-nullable areaIdarea field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Red redIdred = area.getRedIdred();
            if (redIdred != null) {
                redIdred.getAreaList().remove(area);
                redIdred = em.merge(redIdred);
            }
            em.remove(area);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Area> findAreaEntities() {
        return findAreaEntities(true, -1, -1);
    }

    public List<Area> findAreaEntities(int maxResults, int firstResult) {
        return findAreaEntities(false, maxResults, firstResult);
    }

    private List<Area> findAreaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Area.class));
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

    public Area findArea(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Area.class, id);
        } finally {
            em.close();
        }
    }

    public int getAreaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Area> rt = cq.from(Area.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
