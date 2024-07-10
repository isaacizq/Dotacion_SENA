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
import Logic.Centro;
import Logic.Coordinador;
import Logic.CaracterizarInstructor;
import Logic.Instructor;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author isaac
 */
public class InstructorJpaController implements Serializable {

    public InstructorJpaController() {
        this.emf = Persistence.createEntityManagerFactory("DotasoftPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Instructor instructor) throws PreexistingEntityException, Exception {
        if (instructor.getCaracterizarInstructorList() == null) {
            instructor.setCaracterizarInstructorList(new ArrayList<CaracterizarInstructor>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Centro centroIdcentro = instructor.getCentroIdcentro();
            if (centroIdcentro != null) {
                centroIdcentro = em.getReference(centroIdcentro.getClass(), centroIdcentro.getIdcentro());
                instructor.setCentroIdcentro(centroIdcentro);
            }
            Coordinador coordinadorIdcoordinador = instructor.getCoordinadorIdcoordinador();
            if (coordinadorIdcoordinador != null) {
                coordinadorIdcoordinador = em.getReference(coordinadorIdcoordinador.getClass(), coordinadorIdcoordinador.getIdcoordinador());
                instructor.setCoordinadorIdcoordinador(coordinadorIdcoordinador);
            }
            List<CaracterizarInstructor> attachedCaracterizarInstructorList = new ArrayList<CaracterizarInstructor>();
            for (CaracterizarInstructor caracterizarInstructorListCaracterizarInstructorToAttach : instructor.getCaracterizarInstructorList()) {
                caracterizarInstructorListCaracterizarInstructorToAttach = em.getReference(caracterizarInstructorListCaracterizarInstructorToAttach.getClass(), caracterizarInstructorListCaracterizarInstructorToAttach.getIdCaracterizarInstructor());
                attachedCaracterizarInstructorList.add(caracterizarInstructorListCaracterizarInstructorToAttach);
            }
            instructor.setCaracterizarInstructorList(attachedCaracterizarInstructorList);
            em.persist(instructor);
            if (centroIdcentro != null) {
                centroIdcentro.getInstructorList().add(instructor);
                centroIdcentro = em.merge(centroIdcentro);
            }
            if (coordinadorIdcoordinador != null) {
                coordinadorIdcoordinador.getInstructorList().add(instructor);
                coordinadorIdcoordinador = em.merge(coordinadorIdcoordinador);
            }
            for (CaracterizarInstructor caracterizarInstructorListCaracterizarInstructor : instructor.getCaracterizarInstructorList()) {
                Instructor oldInstructorIdinstructorOfCaracterizarInstructorListCaracterizarInstructor = caracterizarInstructorListCaracterizarInstructor.getInstructorIdinstructor();
                caracterizarInstructorListCaracterizarInstructor.setInstructorIdinstructor(instructor);
                caracterizarInstructorListCaracterizarInstructor = em.merge(caracterizarInstructorListCaracterizarInstructor);
                if (oldInstructorIdinstructorOfCaracterizarInstructorListCaracterizarInstructor != null) {
                    oldInstructorIdinstructorOfCaracterizarInstructorListCaracterizarInstructor.getCaracterizarInstructorList().remove(caracterizarInstructorListCaracterizarInstructor);
                    oldInstructorIdinstructorOfCaracterizarInstructorListCaracterizarInstructor = em.merge(oldInstructorIdinstructorOfCaracterizarInstructorListCaracterizarInstructor);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findInstructor(instructor.getIdinstructor()) != null) {
                throw new PreexistingEntityException("Instructor " + instructor + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Instructor instructor) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Instructor persistentInstructor = em.find(Instructor.class, instructor.getIdinstructor());
            Centro centroIdcentroOld = persistentInstructor.getCentroIdcentro();
            Centro centroIdcentroNew = instructor.getCentroIdcentro();
            Coordinador coordinadorIdcoordinadorOld = persistentInstructor.getCoordinadorIdcoordinador();
            Coordinador coordinadorIdcoordinadorNew = instructor.getCoordinadorIdcoordinador();
            List<CaracterizarInstructor> caracterizarInstructorListOld = persistentInstructor.getCaracterizarInstructorList();
            List<CaracterizarInstructor> caracterizarInstructorListNew = instructor.getCaracterizarInstructorList();
            List<String> illegalOrphanMessages = null;
            if (caracterizarInstructorListOld != null) {
                if (caracterizarInstructorListNew != null && caracterizarInstructorListOld != null) {
                    for (CaracterizarInstructor caracterizarInstructorListOldCaracterizarInstructor : caracterizarInstructorListOld) {
                        if (!caracterizarInstructorListNew.contains(caracterizarInstructorListOldCaracterizarInstructor)) {
                            if (illegalOrphanMessages == null) {
                                illegalOrphanMessages = new ArrayList<String>();
                            }
                            illegalOrphanMessages.add("You must retain CaracterizarInstructor " + caracterizarInstructorListOldCaracterizarInstructor + " since its instructorIdinstructor field is not nullable.");
                        }
                    }
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (centroIdcentroNew != null) {
                centroIdcentroNew = em.getReference(centroIdcentroNew.getClass(), centroIdcentroNew.getIdcentro());
                instructor.setCentroIdcentro(centroIdcentroNew);
            }
            if (coordinadorIdcoordinadorNew != null) {
                coordinadorIdcoordinadorNew = em.getReference(coordinadorIdcoordinadorNew.getClass(), coordinadorIdcoordinadorNew.getIdcoordinador());
                instructor.setCoordinadorIdcoordinador(coordinadorIdcoordinadorNew);
            }
            List<CaracterizarInstructor> attachedCaracterizarInstructorListNew = new ArrayList<CaracterizarInstructor>();
           /* for (CaracterizarInstructor caracterizarInstructorListNewCaracterizarInstructorToAttach : caracterizarInstructorListNew) {
                caracterizarInstructorListNewCaracterizarInstructorToAttach = em.getReference(caracterizarInstructorListNewCaracterizarInstructorToAttach.getClass(), caracterizarInstructorListNewCaracterizarInstructorToAttach.getIdCaracterizarInstructor());
                attachedCaracterizarInstructorListNew.add(caracterizarInstructorListNewCaracterizarInstructorToAttach);
            }*/
            caracterizarInstructorListNew = attachedCaracterizarInstructorListNew;
            instructor.setCaracterizarInstructorList(caracterizarInstructorListNew);
            instructor = em.merge(instructor);
            if (centroIdcentroOld != null && !centroIdcentroOld.equals(centroIdcentroNew)) {
                centroIdcentroOld.getInstructorList().remove(instructor);
                centroIdcentroOld = em.merge(centroIdcentroOld);
            }
            if (centroIdcentroNew != null && !centroIdcentroNew.equals(centroIdcentroOld)) {
                centroIdcentroNew.getInstructorList().add(instructor);
                centroIdcentroNew = em.merge(centroIdcentroNew);
            }
            if (coordinadorIdcoordinadorOld != null && !coordinadorIdcoordinadorOld.equals(coordinadorIdcoordinadorNew)) {
                coordinadorIdcoordinadorOld.getInstructorList().remove(instructor);
                coordinadorIdcoordinadorOld = em.merge(coordinadorIdcoordinadorOld);
            }
            if (coordinadorIdcoordinadorNew != null && !coordinadorIdcoordinadorNew.equals(coordinadorIdcoordinadorOld)) {
                coordinadorIdcoordinadorNew.getInstructorList().add(instructor);
                coordinadorIdcoordinadorNew = em.merge(coordinadorIdcoordinadorNew);
            }
            for (CaracterizarInstructor caracterizarInstructorListNewCaracterizarInstructor : caracterizarInstructorListNew) {
                if (!caracterizarInstructorListOld.contains(caracterizarInstructorListNewCaracterizarInstructor)) {
                    Instructor oldInstructorIdinstructorOfCaracterizarInstructorListNewCaracterizarInstructor = caracterizarInstructorListNewCaracterizarInstructor.getInstructorIdinstructor();
                    caracterizarInstructorListNewCaracterizarInstructor.setInstructorIdinstructor(instructor);
                    caracterizarInstructorListNewCaracterizarInstructor = em.merge(caracterizarInstructorListNewCaracterizarInstructor);
                    if (oldInstructorIdinstructorOfCaracterizarInstructorListNewCaracterizarInstructor != null && !oldInstructorIdinstructorOfCaracterizarInstructorListNewCaracterizarInstructor.equals(instructor)) {
                        oldInstructorIdinstructorOfCaracterizarInstructorListNewCaracterizarInstructor.getCaracterizarInstructorList().remove(caracterizarInstructorListNewCaracterizarInstructor);
                        oldInstructorIdinstructorOfCaracterizarInstructorListNewCaracterizarInstructor = em.merge(oldInstructorIdinstructorOfCaracterizarInstructorListNewCaracterizarInstructor);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = instructor.getIdinstructor();
                if (findInstructor(id) == null) {
                    throw new NonexistentEntityException("The instructor with id " + id + " no longer exists.");
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
            Instructor instructor;
            try {
                instructor = em.getReference(Instructor.class, id);
                instructor.getIdinstructor();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The instructor with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<CaracterizarInstructor> caracterizarInstructorListOrphanCheck = instructor.getCaracterizarInstructorList();
            for (CaracterizarInstructor caracterizarInstructorListOrphanCheckCaracterizarInstructor : caracterizarInstructorListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Instructor (" + instructor + ") cannot be destroyed since the CaracterizarInstructor " + caracterizarInstructorListOrphanCheckCaracterizarInstructor + " in its caracterizarInstructorList field has a non-nullable instructorIdinstructor field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Centro centroIdcentro = instructor.getCentroIdcentro();
            if (centroIdcentro != null) {
                centroIdcentro.getInstructorList().remove(instructor);
                centroIdcentro = em.merge(centroIdcentro);
            }
            Coordinador coordinadorIdcoordinador = instructor.getCoordinadorIdcoordinador();
            if (coordinadorIdcoordinador != null) {
                coordinadorIdcoordinador.getInstructorList().remove(instructor);
                coordinadorIdcoordinador = em.merge(coordinadorIdcoordinador);
            }
            em.remove(instructor);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Instructor> findInstructorEntities() {
        return findInstructorEntities(true, -1, -1);
    }

    public List<Instructor> findInstructorEntities(int maxResults, int firstResult) {
        return findInstructorEntities(false, maxResults, firstResult);
    }

    private List<Instructor> findInstructorEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Instructor.class));
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

    public Instructor findInstructor(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Instructor.class, id);
        } finally {
            em.close();
        }
    }

    public int getInstructorCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Instructor> rt = cq.from(Instructor.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
