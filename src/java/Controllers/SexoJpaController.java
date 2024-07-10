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
import java.util.ArrayList;
import java.util.List;
import Logic.Dotacion;
import Logic.Sexo;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author isaac
 */
public class SexoJpaController implements Serializable {

    public SexoJpaController() {
        this.emf = Persistence.createEntityManagerFactory("DotasoftPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Sexo sexo) throws PreexistingEntityException, Exception {
        if (sexo.getCaracterizarInstructorList() == null) {
            sexo.setCaracterizarInstructorList(new ArrayList<CaracterizarInstructor>());
        }
        if (sexo.getDotacionList() == null) {
            sexo.setDotacionList(new ArrayList<Dotacion>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<CaracterizarInstructor> attachedCaracterizarInstructorList = new ArrayList<CaracterizarInstructor>();
            for (CaracterizarInstructor caracterizarInstructorListCaracterizarInstructorToAttach : sexo.getCaracterizarInstructorList()) {
                caracterizarInstructorListCaracterizarInstructorToAttach = em.getReference(caracterizarInstructorListCaracterizarInstructorToAttach.getClass(), caracterizarInstructorListCaracterizarInstructorToAttach.getIdCaracterizarInstructor());
                attachedCaracterizarInstructorList.add(caracterizarInstructorListCaracterizarInstructorToAttach);
            }
            sexo.setCaracterizarInstructorList(attachedCaracterizarInstructorList);
            List<Dotacion> attachedDotacionList = new ArrayList<Dotacion>();
            for (Dotacion dotacionListDotacionToAttach : sexo.getDotacionList()) {
                dotacionListDotacionToAttach = em.getReference(dotacionListDotacionToAttach.getClass(), dotacionListDotacionToAttach.getIddotacion());
                attachedDotacionList.add(dotacionListDotacionToAttach);
            }
            sexo.setDotacionList(attachedDotacionList);
            em.persist(sexo);
            for (CaracterizarInstructor caracterizarInstructorListCaracterizarInstructor : sexo.getCaracterizarInstructorList()) {
                Sexo oldSexoIdsexoOfCaracterizarInstructorListCaracterizarInstructor = caracterizarInstructorListCaracterizarInstructor.getSexoIdsexo();
                caracterizarInstructorListCaracterizarInstructor.setSexoIdsexo(sexo);
                caracterizarInstructorListCaracterizarInstructor = em.merge(caracterizarInstructorListCaracterizarInstructor);
                if (oldSexoIdsexoOfCaracterizarInstructorListCaracterizarInstructor != null) {
                    oldSexoIdsexoOfCaracterizarInstructorListCaracterizarInstructor.getCaracterizarInstructorList().remove(caracterizarInstructorListCaracterizarInstructor);
                    oldSexoIdsexoOfCaracterizarInstructorListCaracterizarInstructor = em.merge(oldSexoIdsexoOfCaracterizarInstructorListCaracterizarInstructor);
                }
            }
            for (Dotacion dotacionListDotacion : sexo.getDotacionList()) {
                Sexo oldSexoIdsexoOfDotacionListDotacion = dotacionListDotacion.getSexoIdsexo();
                dotacionListDotacion.setSexoIdsexo(sexo);
                dotacionListDotacion = em.merge(dotacionListDotacion);
                if (oldSexoIdsexoOfDotacionListDotacion != null) {
                    oldSexoIdsexoOfDotacionListDotacion.getDotacionList().remove(dotacionListDotacion);
                    oldSexoIdsexoOfDotacionListDotacion = em.merge(oldSexoIdsexoOfDotacionListDotacion);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findSexo(sexo.getIdsexo()) != null) {
                throw new PreexistingEntityException("Sexo " + sexo + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Sexo sexo) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Sexo persistentSexo = em.find(Sexo.class, sexo.getIdsexo());
            List<CaracterizarInstructor> caracterizarInstructorListOld = persistentSexo.getCaracterizarInstructorList();
            List<CaracterizarInstructor> caracterizarInstructorListNew = sexo.getCaracterizarInstructorList();
            List<Dotacion> dotacionListOld = persistentSexo.getDotacionList();
            List<Dotacion> dotacionListNew = sexo.getDotacionList();
            List<String> illegalOrphanMessages = null;
            for (CaracterizarInstructor caracterizarInstructorListOldCaracterizarInstructor : caracterizarInstructorListOld) {
                /*if (!caracterizarInstructorListNew.contains(caracterizarInstructorListOldCaracterizarInstructor)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain CaracterizarInstructor " + caracterizarInstructorListOldCaracterizarInstructor + " since its sexoIdsexo field is not nullable.");
                }*/
            }
            for (Dotacion dotacionListOldDotacion : dotacionListOld) {
                /*if (!dotacionListNew.contains(dotacionListOldDotacion)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Dotacion " + dotacionListOldDotacion + " since its sexoIdsexo field is not nullable.");
                }*/
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<CaracterizarInstructor> attachedCaracterizarInstructorListNew = new ArrayList<CaracterizarInstructor>();
            /*for (CaracterizarInstructor caracterizarInstructorListNewCaracterizarInstructorToAttach : caracterizarInstructorListNew) {
                caracterizarInstructorListNewCaracterizarInstructorToAttach = em.getReference(caracterizarInstructorListNewCaracterizarInstructorToAttach.getClass(), caracterizarInstructorListNewCaracterizarInstructorToAttach.getIdCaracterizarInstructor());
                attachedCaracterizarInstructorListNew.add(caracterizarInstructorListNewCaracterizarInstructorToAttach);
            }*/
            caracterizarInstructorListNew = attachedCaracterizarInstructorListNew;
            sexo.setCaracterizarInstructorList(caracterizarInstructorListNew);
            List<Dotacion> attachedDotacionListNew = new ArrayList<Dotacion>();
            /*for (Dotacion dotacionListNewDotacionToAttach : dotacionListNew) {
                dotacionListNewDotacionToAttach = em.getReference(dotacionListNewDotacionToAttach.getClass(), dotacionListNewDotacionToAttach.getIddotacion());
                attachedDotacionListNew.add(dotacionListNewDotacionToAttach);
            }*/
            dotacionListNew = attachedDotacionListNew;
            sexo.setDotacionList(dotacionListNew);
            sexo = em.merge(sexo);
            for (CaracterizarInstructor caracterizarInstructorListNewCaracterizarInstructor : caracterizarInstructorListNew) {
                if (!caracterizarInstructorListOld.contains(caracterizarInstructorListNewCaracterizarInstructor)) {
                    Sexo oldSexoIdsexoOfCaracterizarInstructorListNewCaracterizarInstructor = caracterizarInstructorListNewCaracterizarInstructor.getSexoIdsexo();
                    caracterizarInstructorListNewCaracterizarInstructor.setSexoIdsexo(sexo);
                    caracterizarInstructorListNewCaracterizarInstructor = em.merge(caracterizarInstructorListNewCaracterizarInstructor);
                    if (oldSexoIdsexoOfCaracterizarInstructorListNewCaracterizarInstructor != null && !oldSexoIdsexoOfCaracterizarInstructorListNewCaracterizarInstructor.equals(sexo)) {
                        oldSexoIdsexoOfCaracterizarInstructorListNewCaracterizarInstructor.getCaracterizarInstructorList().remove(caracterizarInstructorListNewCaracterizarInstructor);
                        oldSexoIdsexoOfCaracterizarInstructorListNewCaracterizarInstructor = em.merge(oldSexoIdsexoOfCaracterizarInstructorListNewCaracterizarInstructor);
                    }
                }
            }
            for (Dotacion dotacionListNewDotacion : dotacionListNew) {
                if (!dotacionListOld.contains(dotacionListNewDotacion)) {
                    Sexo oldSexoIdsexoOfDotacionListNewDotacion = dotacionListNewDotacion.getSexoIdsexo();
                    dotacionListNewDotacion.setSexoIdsexo(sexo);
                    dotacionListNewDotacion = em.merge(dotacionListNewDotacion);
                    if (oldSexoIdsexoOfDotacionListNewDotacion != null && !oldSexoIdsexoOfDotacionListNewDotacion.equals(sexo)) {
                        oldSexoIdsexoOfDotacionListNewDotacion.getDotacionList().remove(dotacionListNewDotacion);
                        oldSexoIdsexoOfDotacionListNewDotacion = em.merge(oldSexoIdsexoOfDotacionListNewDotacion);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = sexo.getIdsexo();
                if (findSexo(id) == null) {
                    throw new NonexistentEntityException("The sexo with id " + id + " no longer exists.");
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
            Sexo sexo;
            try {
                sexo = em.getReference(Sexo.class, id);
                sexo.getIdsexo();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The sexo with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<CaracterizarInstructor> caracterizarInstructorListOrphanCheck = sexo.getCaracterizarInstructorList();
            for (CaracterizarInstructor caracterizarInstructorListOrphanCheckCaracterizarInstructor : caracterizarInstructorListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Sexo (" + sexo + ") cannot be destroyed since the CaracterizarInstructor " + caracterizarInstructorListOrphanCheckCaracterizarInstructor + " in its caracterizarInstructorList field has a non-nullable sexoIdsexo field.");
            }
            List<Dotacion> dotacionListOrphanCheck = sexo.getDotacionList();
            for (Dotacion dotacionListOrphanCheckDotacion : dotacionListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Sexo (" + sexo + ") cannot be destroyed since the Dotacion " + dotacionListOrphanCheckDotacion + " in its dotacionList field has a non-nullable sexoIdsexo field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(sexo);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Sexo> findSexoEntities() {
        return findSexoEntities(true, -1, -1);
    }

    public List<Sexo> findSexoEntities(int maxResults, int firstResult) {
        return findSexoEntities(false, maxResults, firstResult);
    }

    private List<Sexo> findSexoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Sexo.class));
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

    public Sexo findSexo(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Sexo.class, id);
        } finally {
            em.close();
        }
    }

    public int getSexoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Sexo> rt = cq.from(Sexo.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
