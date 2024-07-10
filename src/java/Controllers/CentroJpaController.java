/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controllers;

import Controllers.exceptions.IllegalOrphanException;
import Controllers.exceptions.NonexistentEntityException;
import Controllers.exceptions.PreexistingEntityException;
import Logic.Centro;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import Logic.Regional;
import Logic.Coordinador;
import java.util.ArrayList;
import java.util.List;
import Logic.Instructor;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author isaac
 */
public class CentroJpaController implements Serializable {

    public CentroJpaController() {
        this.emf = Persistence.createEntityManagerFactory("DotasoftPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Centro centro) throws PreexistingEntityException, Exception {
        if (centro.getCoordinadorList() == null) {
            centro.setCoordinadorList(new ArrayList<Coordinador>());
        }
        if (centro.getInstructorList() == null) {
            centro.setInstructorList(new ArrayList<Instructor>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Regional regionalIdregional = centro.getRegionalIdregional();
            if (regionalIdregional != null) {
                regionalIdregional = em.getReference(regionalIdregional.getClass(), regionalIdregional.getIdregional());
                centro.setRegionalIdregional(regionalIdregional);
            }
            List<Coordinador> attachedCoordinadorList = new ArrayList<Coordinador>();
            for (Coordinador coordinadorListCoordinadorToAttach : centro.getCoordinadorList()) {
                coordinadorListCoordinadorToAttach = em.getReference(coordinadorListCoordinadorToAttach.getClass(), coordinadorListCoordinadorToAttach.getIdcoordinador());
                attachedCoordinadorList.add(coordinadorListCoordinadorToAttach);
            }
            centro.setCoordinadorList(attachedCoordinadorList);
            List<Instructor> attachedInstructorList = new ArrayList<Instructor>();
            for (Instructor instructorListInstructorToAttach : centro.getInstructorList()) {
                instructorListInstructorToAttach = em.getReference(instructorListInstructorToAttach.getClass(), instructorListInstructorToAttach.getIdinstructor());
                attachedInstructorList.add(instructorListInstructorToAttach);
            }
            centro.setInstructorList(attachedInstructorList);
            em.persist(centro);
            if (regionalIdregional != null) {
                regionalIdregional.getCentroList().add(centro);
                regionalIdregional = em.merge(regionalIdregional);
            }
            for (Coordinador coordinadorListCoordinador : centro.getCoordinadorList()) {
                Centro oldCentroIdcentroOfCoordinadorListCoordinador = coordinadorListCoordinador.getCentroIdcentro();
                coordinadorListCoordinador.setCentroIdcentro(centro);
                coordinadorListCoordinador = em.merge(coordinadorListCoordinador);
                if (oldCentroIdcentroOfCoordinadorListCoordinador != null) {
                    oldCentroIdcentroOfCoordinadorListCoordinador.getCoordinadorList().remove(coordinadorListCoordinador);
                    oldCentroIdcentroOfCoordinadorListCoordinador = em.merge(oldCentroIdcentroOfCoordinadorListCoordinador);
                }
            }
            for (Instructor instructorListInstructor : centro.getInstructorList()) {
                Centro oldCentroIdcentroOfInstructorListInstructor = instructorListInstructor.getCentroIdcentro();
                instructorListInstructor.setCentroIdcentro(centro);
                instructorListInstructor = em.merge(instructorListInstructor);
                if (oldCentroIdcentroOfInstructorListInstructor != null) {
                    oldCentroIdcentroOfInstructorListInstructor.getInstructorList().remove(instructorListInstructor);
                    oldCentroIdcentroOfInstructorListInstructor = em.merge(oldCentroIdcentroOfInstructorListInstructor);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findCentro(centro.getIdcentro()) != null) {
                throw new PreexistingEntityException("Centro " + centro + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Centro centro) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Centro persistentCentro = em.find(Centro.class, centro.getIdcentro());
            Regional regionalIdregionalOld = persistentCentro.getRegionalIdregional();
            Regional regionalIdregionalNew = centro.getRegionalIdregional();
            List<Coordinador> coordinadorListOld = persistentCentro.getCoordinadorList();
            List<Coordinador> coordinadorListNew = centro.getCoordinadorList();
            List<Instructor> instructorListOld = persistentCentro.getInstructorList();
            List<Instructor> instructorListNew = centro.getInstructorList();
            List<String> illegalOrphanMessages = null;
            for (Coordinador coordinadorListOldCoordinador : coordinadorListOld) {
                if (!coordinadorListNew.contains(coordinadorListOldCoordinador)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Coordinador " + coordinadorListOldCoordinador + " since its centroIdcentro field is not nullable.");
                }
            }
            for (Instructor instructorListOldInstructor : instructorListOld) {
                if (!instructorListNew.contains(instructorListOldInstructor)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Instructor " + instructorListOldInstructor + " since its centroIdcentro field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (regionalIdregionalNew != null) {
                regionalIdregionalNew = em.getReference(regionalIdregionalNew.getClass(), regionalIdregionalNew.getIdregional());
                centro.setRegionalIdregional(regionalIdregionalNew);
            }
            List<Coordinador> attachedCoordinadorListNew = new ArrayList<Coordinador>();
            /*for (Coordinador coordinadorListNewCoordinadorToAttach : coordinadorListNew) {
                coordinadorListNewCoordinadorToAttach = em.getReference(coordinadorListNewCoordinadorToAttach.getClass(), coordinadorListNewCoordinadorToAttach.getIdcoordinador());
                attachedCoordinadorListNew.add(coordinadorListNewCoordinadorToAttach);
            }*/
            coordinadorListNew = attachedCoordinadorListNew;
            centro.setCoordinadorList(coordinadorListNew);
            List<Instructor> attachedInstructorListNew = new ArrayList<Instructor>();
            /*for (Instructor instructorListNewInstructorToAttach : instructorListNew) {
                instructorListNewInstructorToAttach = em.getReference(instructorListNewInstructorToAttach.getClass(), instructorListNewInstructorToAttach.getIdinstructor());
                attachedInstructorListNew.add(instructorListNewInstructorToAttach);
            }*/
            instructorListNew = attachedInstructorListNew;
            centro.setInstructorList(instructorListNew);
            centro = em.merge(centro);
            if (regionalIdregionalOld != null && !regionalIdregionalOld.equals(regionalIdregionalNew)) {
                regionalIdregionalOld.getCentroList().remove(centro);
                regionalIdregionalOld = em.merge(regionalIdregionalOld);
            }
            if (regionalIdregionalNew != null && !regionalIdregionalNew.equals(regionalIdregionalOld)) {
                regionalIdregionalNew.getCentroList().add(centro);
                regionalIdregionalNew = em.merge(regionalIdregionalNew);
            }
            for (Coordinador coordinadorListNewCoordinador : coordinadorListNew) {
                if (!coordinadorListOld.contains(coordinadorListNewCoordinador)) {
                    Centro oldCentroIdcentroOfCoordinadorListNewCoordinador = coordinadorListNewCoordinador.getCentroIdcentro();
                    coordinadorListNewCoordinador.setCentroIdcentro(centro);
                    coordinadorListNewCoordinador = em.merge(coordinadorListNewCoordinador);
                    if (oldCentroIdcentroOfCoordinadorListNewCoordinador != null && !oldCentroIdcentroOfCoordinadorListNewCoordinador.equals(centro)) {
                        oldCentroIdcentroOfCoordinadorListNewCoordinador.getCoordinadorList().remove(coordinadorListNewCoordinador);
                        oldCentroIdcentroOfCoordinadorListNewCoordinador = em.merge(oldCentroIdcentroOfCoordinadorListNewCoordinador);
                    }
                }
            }
            for (Instructor instructorListNewInstructor : instructorListNew) {
                if (!instructorListOld.contains(instructorListNewInstructor)) {
                    Centro oldCentroIdcentroOfInstructorListNewInstructor = instructorListNewInstructor.getCentroIdcentro();
                    instructorListNewInstructor.setCentroIdcentro(centro);
                    instructorListNewInstructor = em.merge(instructorListNewInstructor);
                    if (oldCentroIdcentroOfInstructorListNewInstructor != null && !oldCentroIdcentroOfInstructorListNewInstructor.equals(centro)) {
                        oldCentroIdcentroOfInstructorListNewInstructor.getInstructorList().remove(instructorListNewInstructor);
                        oldCentroIdcentroOfInstructorListNewInstructor = em.merge(oldCentroIdcentroOfInstructorListNewInstructor);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = centro.getIdcentro();
                if (findCentro(id) == null) {
                    throw new NonexistentEntityException("The centro with id " + id + " no longer exists.");
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
            Centro centro;
            try {
                centro = em.getReference(Centro.class, id);
                centro.getIdcentro();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The centro with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Coordinador> coordinadorListOrphanCheck = centro.getCoordinadorList();
            for (Coordinador coordinadorListOrphanCheckCoordinador : coordinadorListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Centro (" + centro + ") cannot be destroyed since the Coordinador " + coordinadorListOrphanCheckCoordinador + " in its coordinadorList field has a non-nullable centroIdcentro field.");
            }
            List<Instructor> instructorListOrphanCheck = centro.getInstructorList();
            for (Instructor instructorListOrphanCheckInstructor : instructorListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Centro (" + centro + ") cannot be destroyed since the Instructor " + instructorListOrphanCheckInstructor + " in its instructorList field has a non-nullable centroIdcentro field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Regional regionalIdregional = centro.getRegionalIdregional();
            if (regionalIdregional != null) {
                regionalIdregional.getCentroList().remove(centro);
                regionalIdregional = em.merge(regionalIdregional);
            }
            em.remove(centro);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Centro> findCentroEntities() {
        return findCentroEntities(true, -1, -1);
    }

    public List<Centro> findCentroEntities(int maxResults, int firstResult) {
        return findCentroEntities(false, maxResults, firstResult);
    }

    private List<Centro> findCentroEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Centro.class));
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

    public Centro findCentro(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Centro.class, id);
        } finally {
            em.close();
        }
    }

    public int getCentroCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Centro> rt = cq.from(Centro.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
