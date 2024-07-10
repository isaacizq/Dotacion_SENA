/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controllers;

import Controllers.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import Logic.Area;
import Logic.CaracterizarInstructor;
import Logic.Clima;
import Logic.Instructor;
import Logic.Sexo;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author isaac
 */
public class CaracterizarInstructorJpaController implements Serializable {

    public CaracterizarInstructorJpaController() {
        this.emf = Persistence.createEntityManagerFactory("DotasoftPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(CaracterizarInstructor caracterizarInstructor) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Area areaIdarea = caracterizarInstructor.getAreaIdarea();
            if (areaIdarea != null) {
                areaIdarea = em.getReference(areaIdarea.getClass(), areaIdarea.getIdarea());
                caracterizarInstructor.setAreaIdarea(areaIdarea);
            }
            Clima climaIdclima = caracterizarInstructor.getClimaIdclima();
            if (climaIdclima != null) {
                climaIdclima = em.getReference(climaIdclima.getClass(), climaIdclima.getIdclima());
                caracterizarInstructor.setClimaIdclima(climaIdclima);
            }
            Instructor instructorIdinstructor = caracterizarInstructor.getInstructorIdinstructor();
            if (instructorIdinstructor != null) {
                instructorIdinstructor = em.getReference(instructorIdinstructor.getClass(), instructorIdinstructor.getIdinstructor());
                caracterizarInstructor.setInstructorIdinstructor(instructorIdinstructor);
            }
            Sexo sexoIdsexo = caracterizarInstructor.getSexoIdsexo();
            if (sexoIdsexo != null) {
                sexoIdsexo = em.getReference(sexoIdsexo.getClass(), sexoIdsexo.getIdsexo());
                caracterizarInstructor.setSexoIdsexo(sexoIdsexo);
            }
            em.persist(caracterizarInstructor);
            if (areaIdarea != null) {
                areaIdarea.getCaracterizarInstructorList().add(caracterizarInstructor);
                areaIdarea = em.merge(areaIdarea);
            }
            if (climaIdclima != null) {
                climaIdclima.getCaracterizarInstructorList().add(caracterizarInstructor);
                climaIdclima = em.merge(climaIdclima);
            }
            if (instructorIdinstructor != null) {
                instructorIdinstructor.getCaracterizarInstructorList().add(caracterizarInstructor);
                instructorIdinstructor = em.merge(instructorIdinstructor);
            }
            if (sexoIdsexo != null) {
                sexoIdsexo.getCaracterizarInstructorList().add(caracterizarInstructor);
                sexoIdsexo = em.merge(sexoIdsexo);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(CaracterizarInstructor caracterizarInstructor) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            CaracterizarInstructor persistentCaracterizarInstructor = em.find(CaracterizarInstructor.class, caracterizarInstructor.getIdCaracterizarInstructor());
            Area areaIdareaOld = persistentCaracterizarInstructor.getAreaIdarea();
            Area areaIdareaNew = caracterizarInstructor.getAreaIdarea();
            Clima climaIdclimaOld = persistentCaracterizarInstructor.getClimaIdclima();
            Clima climaIdclimaNew = caracterizarInstructor.getClimaIdclima();
            Instructor instructorIdinstructorOld = persistentCaracterizarInstructor.getInstructorIdinstructor();
            Instructor instructorIdinstructorNew = caracterizarInstructor.getInstructorIdinstructor();
            Sexo sexoIdsexoOld = persistentCaracterizarInstructor.getSexoIdsexo();
            Sexo sexoIdsexoNew = caracterizarInstructor.getSexoIdsexo();
            if (areaIdareaNew != null) {
                areaIdareaNew = em.getReference(areaIdareaNew.getClass(), areaIdareaNew.getIdarea());
                caracterizarInstructor.setAreaIdarea(areaIdareaNew);
            }
            if (climaIdclimaNew != null) {
                climaIdclimaNew = em.getReference(climaIdclimaNew.getClass(), climaIdclimaNew.getIdclima());
                caracterizarInstructor.setClimaIdclima(climaIdclimaNew);
            }
            if (instructorIdinstructorNew != null) {
                instructorIdinstructorNew = em.getReference(instructorIdinstructorNew.getClass(), instructorIdinstructorNew.getIdinstructor());
                caracterizarInstructor.setInstructorIdinstructor(instructorIdinstructorNew);
            }
            if (sexoIdsexoNew != null) {
                sexoIdsexoNew = em.getReference(sexoIdsexoNew.getClass(), sexoIdsexoNew.getIdsexo());
                caracterizarInstructor.setSexoIdsexo(sexoIdsexoNew);
            }
            caracterizarInstructor = em.merge(caracterizarInstructor);
            if (areaIdareaOld != null && !areaIdareaOld.equals(areaIdareaNew)) {
                areaIdareaOld.getCaracterizarInstructorList().remove(caracterizarInstructor);
                areaIdareaOld = em.merge(areaIdareaOld);
            }
            if (areaIdareaNew != null && !areaIdareaNew.equals(areaIdareaOld)) {
                areaIdareaNew.getCaracterizarInstructorList().add(caracterizarInstructor);
                areaIdareaNew = em.merge(areaIdareaNew);
            }
            if (climaIdclimaOld != null && !climaIdclimaOld.equals(climaIdclimaNew)) {
                climaIdclimaOld.getCaracterizarInstructorList().remove(caracterizarInstructor);
                climaIdclimaOld = em.merge(climaIdclimaOld);
            }
            if (climaIdclimaNew != null && !climaIdclimaNew.equals(climaIdclimaOld)) {
                climaIdclimaNew.getCaracterizarInstructorList().add(caracterizarInstructor);
                climaIdclimaNew = em.merge(climaIdclimaNew);
            }
            if (instructorIdinstructorOld != null && !instructorIdinstructorOld.equals(instructorIdinstructorNew)) {
                instructorIdinstructorOld.getCaracterizarInstructorList().remove(caracterizarInstructor);
                instructorIdinstructorOld = em.merge(instructorIdinstructorOld);
            }
            if (instructorIdinstructorNew != null && !instructorIdinstructorNew.equals(instructorIdinstructorOld)) {
                instructorIdinstructorNew.getCaracterizarInstructorList().add(caracterizarInstructor);
                instructorIdinstructorNew = em.merge(instructorIdinstructorNew);
            }
            if (sexoIdsexoOld != null && !sexoIdsexoOld.equals(sexoIdsexoNew)) {
                sexoIdsexoOld.getCaracterizarInstructorList().remove(caracterizarInstructor);
                sexoIdsexoOld = em.merge(sexoIdsexoOld);
            }
            if (sexoIdsexoNew != null && !sexoIdsexoNew.equals(sexoIdsexoOld)) {
                sexoIdsexoNew.getCaracterizarInstructorList().add(caracterizarInstructor);
                sexoIdsexoNew = em.merge(sexoIdsexoNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = caracterizarInstructor.getIdCaracterizarInstructor();
                if (findCaracterizarInstructor(id) == null) {
                    throw new NonexistentEntityException("The caracterizarInstructor with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            CaracterizarInstructor caracterizarInstructor;
            try {
                caracterizarInstructor = em.getReference(CaracterizarInstructor.class, id);
                caracterizarInstructor.getIdCaracterizarInstructor();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The caracterizarInstructor with id " + id + " no longer exists.", enfe);
            }
            Area areaIdarea = caracterizarInstructor.getAreaIdarea();
            if (areaIdarea != null) {
                areaIdarea.getCaracterizarInstructorList().remove(caracterizarInstructor);
                areaIdarea = em.merge(areaIdarea);
            }
            Clima climaIdclima = caracterizarInstructor.getClimaIdclima();
            if (climaIdclima != null) {
                climaIdclima.getCaracterizarInstructorList().remove(caracterizarInstructor);
                climaIdclima = em.merge(climaIdclima);
            }
            Instructor instructorIdinstructor = caracterizarInstructor.getInstructorIdinstructor();
            if (instructorIdinstructor != null) {
                instructorIdinstructor.getCaracterizarInstructorList().remove(caracterizarInstructor);
                instructorIdinstructor = em.merge(instructorIdinstructor);
            }
            Sexo sexoIdsexo = caracterizarInstructor.getSexoIdsexo();
            if (sexoIdsexo != null) {
                sexoIdsexo.getCaracterizarInstructorList().remove(caracterizarInstructor);
                sexoIdsexo = em.merge(sexoIdsexo);
            }
            em.remove(caracterizarInstructor);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<CaracterizarInstructor> findCaracterizarInstructorEntities() {
        return findCaracterizarInstructorEntities(true, -1, -1);
    }

    public List<CaracterizarInstructor> findCaracterizarInstructorEntities(int maxResults, int firstResult) {
        return findCaracterizarInstructorEntities(false, maxResults, firstResult);
    }

    private List<CaracterizarInstructor> findCaracterizarInstructorEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(CaracterizarInstructor.class));
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

    public CaracterizarInstructor findCaracterizarInstructor(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(CaracterizarInstructor.class, id);
        } finally {
            em.close();
        }
    }

    public int getCaracterizarInstructorCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<CaracterizarInstructor> rt = cq.from(CaracterizarInstructor.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
