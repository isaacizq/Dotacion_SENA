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
import Logic.CaracterizarInstructor;
import Logic.Clima;
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
public class ClimaJpaController implements Serializable {

    public ClimaJpaController() {
        this.emf = Persistence.createEntityManagerFactory("DotasoftPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Clima clima) throws PreexistingEntityException, Exception {
        if (clima.getCaracterizarInstructorList() == null) {
            clima.setCaracterizarInstructorList(new ArrayList<CaracterizarInstructor>());
        }
        if (clima.getDotacionList() == null) {
            clima.setDotacionList(new ArrayList<Dotacion>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<CaracterizarInstructor> attachedCaracterizarInstructorList = new ArrayList<CaracterizarInstructor>();
            for (CaracterizarInstructor caracterizarInstructorListCaracterizarInstructorToAttach : clima.getCaracterizarInstructorList()) {
                caracterizarInstructorListCaracterizarInstructorToAttach = em.getReference(caracterizarInstructorListCaracterizarInstructorToAttach.getClass(), caracterizarInstructorListCaracterizarInstructorToAttach.getIdCaracterizarInstructor());
                attachedCaracterizarInstructorList.add(caracterizarInstructorListCaracterizarInstructorToAttach);
            }
            clima.setCaracterizarInstructorList(attachedCaracterizarInstructorList);
            List<Dotacion> attachedDotacionList = new ArrayList<Dotacion>();
            for (Dotacion dotacionListDotacionToAttach : clima.getDotacionList()) {
                dotacionListDotacionToAttach = em.getReference(dotacionListDotacionToAttach.getClass(), dotacionListDotacionToAttach.getIddotacion());
                attachedDotacionList.add(dotacionListDotacionToAttach);
            }
            clima.setDotacionList(attachedDotacionList);
            em.persist(clima);
            for (CaracterizarInstructor caracterizarInstructorListCaracterizarInstructor : clima.getCaracterizarInstructorList()) {
                Clima oldClimaIdclimaOfCaracterizarInstructorListCaracterizarInstructor = caracterizarInstructorListCaracterizarInstructor.getClimaIdclima();
                caracterizarInstructorListCaracterizarInstructor.setClimaIdclima(clima);
                caracterizarInstructorListCaracterizarInstructor = em.merge(caracterizarInstructorListCaracterizarInstructor);
                if (oldClimaIdclimaOfCaracterizarInstructorListCaracterizarInstructor != null) {
                    oldClimaIdclimaOfCaracterizarInstructorListCaracterizarInstructor.getCaracterizarInstructorList().remove(caracterizarInstructorListCaracterizarInstructor);
                    oldClimaIdclimaOfCaracterizarInstructorListCaracterizarInstructor = em.merge(oldClimaIdclimaOfCaracterizarInstructorListCaracterizarInstructor);
                }
            }
            for (Dotacion dotacionListDotacion : clima.getDotacionList()) {
                Clima oldClimaIdclimaOfDotacionListDotacion = dotacionListDotacion.getClimaIdclima();
                dotacionListDotacion.setClimaIdclima(clima);
                dotacionListDotacion = em.merge(dotacionListDotacion);
                if (oldClimaIdclimaOfDotacionListDotacion != null) {
                    oldClimaIdclimaOfDotacionListDotacion.getDotacionList().remove(dotacionListDotacion);
                    oldClimaIdclimaOfDotacionListDotacion = em.merge(oldClimaIdclimaOfDotacionListDotacion);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findClima(clima.getIdclima()) != null) {
                throw new PreexistingEntityException("Clima " + clima + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Clima clima) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Clima persistentClima = em.find(Clima.class, clima.getIdclima());
            List<CaracterizarInstructor> caracterizarInstructorListOld = persistentClima.getCaracterizarInstructorList();
            List<CaracterizarInstructor> caracterizarInstructorListNew = clima.getCaracterizarInstructorList();
            List<Dotacion> dotacionListOld = persistentClima.getDotacionList();
            List<Dotacion> dotacionListNew = clima.getDotacionList();
            List<String> illegalOrphanMessages = null;
            /*for (CaracterizarInstructor caracterizarInstructorListOldCaracterizarInstructor : caracterizarInstructorListOld) {
                if (!caracterizarInstructorListNew.contains(caracterizarInstructorListOldCaracterizarInstructor)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain CaracterizarInstructor " + caracterizarInstructorListOldCaracterizarInstructor + " since its climaIdclima field is not nullable.");
                }
            }*/
            /*for (Dotacion dotacionListOldDotacion : dotacionListOld) {
                if (!dotacionListNew.contains(dotacionListOldDotacion)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Dotacion " + dotacionListOldDotacion + " since its climaIdclima field is not nullable.");
                }
            }*/
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<CaracterizarInstructor> attachedCaracterizarInstructorListNew = new ArrayList<CaracterizarInstructor>();
            /*for (CaracterizarInstructor caracterizarInstructorListNewCaracterizarInstructorToAttach : caracterizarInstructorListNew) {
                caracterizarInstructorListNewCaracterizarInstructorToAttach = em.getReference(caracterizarInstructorListNewCaracterizarInstructorToAttach.getClass(), caracterizarInstructorListNewCaracterizarInstructorToAttach.getIdCaracterizarInstructor());
                attachedCaracterizarInstructorListNew.add(caracterizarInstructorListNewCaracterizarInstructorToAttach);
            }*/
            caracterizarInstructorListNew = attachedCaracterizarInstructorListNew;
            clima.setCaracterizarInstructorList(caracterizarInstructorListNew);
            List<Dotacion> attachedDotacionListNew = new ArrayList<Dotacion>();
            /*for (Dotacion dotacionListNewDotacionToAttach : dotacionListNew) {
                dotacionListNewDotacionToAttach = em.getReference(dotacionListNewDotacionToAttach.getClass(), dotacionListNewDotacionToAttach.getIddotacion());
                attachedDotacionListNew.add(dotacionListNewDotacionToAttach);
            }*/
            dotacionListNew = attachedDotacionListNew;
            clima.setDotacionList(dotacionListNew);
            clima = em.merge(clima);
            for (CaracterizarInstructor caracterizarInstructorListNewCaracterizarInstructor : caracterizarInstructorListNew) {
                if (!caracterizarInstructorListOld.contains(caracterizarInstructorListNewCaracterizarInstructor)) {
                    Clima oldClimaIdclimaOfCaracterizarInstructorListNewCaracterizarInstructor = caracterizarInstructorListNewCaracterizarInstructor.getClimaIdclima();
                    caracterizarInstructorListNewCaracterizarInstructor.setClimaIdclima(clima);
                    caracterizarInstructorListNewCaracterizarInstructor = em.merge(caracterizarInstructorListNewCaracterizarInstructor);
                    if (oldClimaIdclimaOfCaracterizarInstructorListNewCaracterizarInstructor != null && !oldClimaIdclimaOfCaracterizarInstructorListNewCaracterizarInstructor.equals(clima)) {
                        oldClimaIdclimaOfCaracterizarInstructorListNewCaracterizarInstructor.getCaracterizarInstructorList().remove(caracterizarInstructorListNewCaracterizarInstructor);
                        oldClimaIdclimaOfCaracterizarInstructorListNewCaracterizarInstructor = em.merge(oldClimaIdclimaOfCaracterizarInstructorListNewCaracterizarInstructor);
                    }
                }
            }
            for (Dotacion dotacionListNewDotacion : dotacionListNew) {
                if (!dotacionListOld.contains(dotacionListNewDotacion)) {
                    Clima oldClimaIdclimaOfDotacionListNewDotacion = dotacionListNewDotacion.getClimaIdclima();
                    dotacionListNewDotacion.setClimaIdclima(clima);
                    dotacionListNewDotacion = em.merge(dotacionListNewDotacion);
                    if (oldClimaIdclimaOfDotacionListNewDotacion != null && !oldClimaIdclimaOfDotacionListNewDotacion.equals(clima)) {
                        oldClimaIdclimaOfDotacionListNewDotacion.getDotacionList().remove(dotacionListNewDotacion);
                        oldClimaIdclimaOfDotacionListNewDotacion = em.merge(oldClimaIdclimaOfDotacionListNewDotacion);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = clima.getIdclima();
                if (findClima(id) == null) {
                    throw new NonexistentEntityException("The clima with id " + id + " no longer exists.");
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
            Clima clima;
            try {
                clima = em.getReference(Clima.class, id);
                clima.getIdclima();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The clima with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<CaracterizarInstructor> caracterizarInstructorListOrphanCheck = clima.getCaracterizarInstructorList();
            for (CaracterizarInstructor caracterizarInstructorListOrphanCheckCaracterizarInstructor : caracterizarInstructorListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Clima (" + clima + ") cannot be destroyed since the CaracterizarInstructor " + caracterizarInstructorListOrphanCheckCaracterizarInstructor + " in its caracterizarInstructorList field has a non-nullable climaIdclima field.");
            }
            List<Dotacion> dotacionListOrphanCheck = clima.getDotacionList();
            for (Dotacion dotacionListOrphanCheckDotacion : dotacionListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Clima (" + clima + ") cannot be destroyed since the Dotacion " + dotacionListOrphanCheckDotacion + " in its dotacionList field has a non-nullable climaIdclima field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(clima);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Clima> findClimaEntities() {
        return findClimaEntities(true, -1, -1);
    }

    public List<Clima> findClimaEntities(int maxResults, int firstResult) {
        return findClimaEntities(false, maxResults, firstResult);
    }

    private List<Clima> findClimaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Clima.class));
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

    public Clima findClima(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Clima.class, id);
        } finally {
            em.close();
        }
    }

    public int getClimaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Clima> rt = cq.from(Clima.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
