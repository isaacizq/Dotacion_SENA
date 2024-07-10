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
public class CoordinadorJpaController implements Serializable {

    public CoordinadorJpaController() {
        this.emf = Persistence.createEntityManagerFactory("DotasoftPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Coordinador coordinador) throws PreexistingEntityException, Exception {
        if (coordinador.getInstructorList() == null) {
            coordinador.setInstructorList(new ArrayList<Instructor>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Centro centroIdcentro = coordinador.getCentroIdcentro();
            if (centroIdcentro != null) {
                centroIdcentro = em.getReference(centroIdcentro.getClass(), centroIdcentro.getIdcentro());
                coordinador.setCentroIdcentro(centroIdcentro);
            }
            List<Instructor> attachedInstructorList = new ArrayList<Instructor>();
            for (Instructor instructorListInstructorToAttach : coordinador.getInstructorList()) {
                instructorListInstructorToAttach = em.getReference(instructorListInstructorToAttach.getClass(), instructorListInstructorToAttach.getIdinstructor());
                attachedInstructorList.add(instructorListInstructorToAttach);
            }
            coordinador.setInstructorList(attachedInstructorList);
            em.persist(coordinador);
            if (centroIdcentro != null) {
                centroIdcentro.getCoordinadorList().add(coordinador);
                centroIdcentro = em.merge(centroIdcentro);
            }
            for (Instructor instructorListInstructor : coordinador.getInstructorList()) {
                Coordinador oldCoordinadorIdcoordinadorOfInstructorListInstructor = instructorListInstructor.getCoordinadorIdcoordinador();
                instructorListInstructor.setCoordinadorIdcoordinador(coordinador);
                instructorListInstructor = em.merge(instructorListInstructor);
                if (oldCoordinadorIdcoordinadorOfInstructorListInstructor != null) {
                    oldCoordinadorIdcoordinadorOfInstructorListInstructor.getInstructorList().remove(instructorListInstructor);
                    oldCoordinadorIdcoordinadorOfInstructorListInstructor = em.merge(oldCoordinadorIdcoordinadorOfInstructorListInstructor);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findCoordinador(coordinador.getIdcoordinador()) != null) {
                throw new PreexistingEntityException("Coordinador " + coordinador + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Coordinador coordinador) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Coordinador persistentCoordinador = em.find(Coordinador.class, coordinador.getIdcoordinador());
            Centro centroIdcentroOld = persistentCoordinador.getCentroIdcentro();
            Centro centroIdcentroNew = coordinador.getCentroIdcentro();
            List<Instructor> instructorListOld = persistentCoordinador.getInstructorList();
            List<Instructor> instructorListNew = coordinador.getInstructorList();
            List<String> illegalOrphanMessages = null;
            /* for (Instructor instructorListOldInstructor : instructorListOld) {
                if (!instructorListNew.contains(instructorListOldInstructor)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Instructor " + instructorListOldInstructor + " since its coordinadorIdcoordinador field is not nullable.");
                }
            }*/
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (centroIdcentroNew != null) {
                centroIdcentroNew = em.getReference(centroIdcentroNew.getClass(), centroIdcentroNew.getIdcentro());
                coordinador.setCentroIdcentro(centroIdcentroNew);
            }
            List<Instructor> attachedInstructorListNew = new ArrayList<Instructor>();
            /*for (Instructor instructorListNewInstructorToAttach : instructorListNew) {
                instructorListNewInstructorToAttach = em.getReference(instructorListNewInstructorToAttach.getClass(), instructorListNewInstructorToAttach.getIdinstructor());
                attachedInstructorListNew.add(instructorListNewInstructorToAttach);
            }*/
            instructorListNew = attachedInstructorListNew;
            coordinador.setInstructorList(instructorListNew);
            coordinador = em.merge(coordinador);
            if (centroIdcentroOld != null && !centroIdcentroOld.equals(centroIdcentroNew)) {
                centroIdcentroOld.getCoordinadorList().remove(coordinador);
                centroIdcentroOld = em.merge(centroIdcentroOld);
            }
            if (centroIdcentroNew != null && !centroIdcentroNew.equals(centroIdcentroOld)) {
                centroIdcentroNew.getCoordinadorList().add(coordinador);
                centroIdcentroNew = em.merge(centroIdcentroNew);
            }
            for (Instructor instructorListNewInstructor : instructorListNew) {
                if (!instructorListOld.contains(instructorListNewInstructor)) {
                    Coordinador oldCoordinadorIdcoordinadorOfInstructorListNewInstructor = instructorListNewInstructor.getCoordinadorIdcoordinador();
                    instructorListNewInstructor.setCoordinadorIdcoordinador(coordinador);
                    instructorListNewInstructor = em.merge(instructorListNewInstructor);
                    if (oldCoordinadorIdcoordinadorOfInstructorListNewInstructor != null && !oldCoordinadorIdcoordinadorOfInstructorListNewInstructor.equals(coordinador)) {
                        oldCoordinadorIdcoordinadorOfInstructorListNewInstructor.getInstructorList().remove(instructorListNewInstructor);
                        oldCoordinadorIdcoordinadorOfInstructorListNewInstructor = em.merge(oldCoordinadorIdcoordinadorOfInstructorListNewInstructor);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = coordinador.getIdcoordinador();
                if (findCoordinador(id) == null) {
                    throw new NonexistentEntityException("The coordinador with id " + id + " no longer exists.");
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
            Coordinador coordinador;
            try {
                coordinador = em.getReference(Coordinador.class, id);
                coordinador.getIdcoordinador();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The coordinador with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Instructor> instructorListOrphanCheck = coordinador.getInstructorList();
            for (Instructor instructorListOrphanCheckInstructor : instructorListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Coordinador (" + coordinador + ") cannot be destroyed since the Instructor " + instructorListOrphanCheckInstructor + " in its instructorList field has a non-nullable coordinadorIdcoordinador field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Centro centroIdcentro = coordinador.getCentroIdcentro();
            if (centroIdcentro != null) {
                centroIdcentro.getCoordinadorList().remove(coordinador);
                centroIdcentro = em.merge(centroIdcentro);
            }
            em.remove(coordinador);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Coordinador> findCoordinadorEntities() {
        return findCoordinadorEntities(true, -1, -1);
    }

    public List<Coordinador> findCoordinadorEntities(int maxResults, int firstResult) {
        return findCoordinadorEntities(false, maxResults, firstResult);
    }

    private List<Coordinador> findCoordinadorEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Coordinador.class));
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

    public Coordinador findCoordinador(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Coordinador.class, id);
        } finally {
            em.close();
        }
    }

    public int getCoordinadorCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Coordinador> rt = cq.from(Coordinador.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
