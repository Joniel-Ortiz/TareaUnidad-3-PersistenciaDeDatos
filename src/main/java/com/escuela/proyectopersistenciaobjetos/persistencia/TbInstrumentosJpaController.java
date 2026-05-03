/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.escuela.proyectopersistenciaobjetos.persistencia;

import com.escuela.proyectopersistenciaobjetos.entites.TbInstrumentos;
import com.escuela.proyectopersistenciaobjetos.persistencia.exceptions.NonexistentEntityException;
import com.escuela.proyectopersistenciaobjetos.persistencia.exceptions.PreexistingEntityException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import java.io.Serializable;
import jakarta.persistence.Query;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import java.util.List;

/**
 *
 * @author ortiz
 */
public class TbInstrumentosJpaController implements Serializable {

    public TbInstrumentosJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(TbInstrumentos tbInstrumentos) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(tbInstrumentos);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findTbInstrumentos(tbInstrumentos.getIdPr()) != null) {
                throw new PreexistingEntityException("TbInstrumentos " + tbInstrumentos + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(TbInstrumentos tbInstrumentos) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            tbInstrumentos = em.merge(tbInstrumentos);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = tbInstrumentos.getIdPr();
                if (findTbInstrumentos(id) == null) {
                    throw new NonexistentEntityException("The tbInstrumentos with id " + id + " no longer exists.");
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
            TbInstrumentos tbInstrumentos;
            try {
                tbInstrumentos = em.getReference(TbInstrumentos.class, id);
                tbInstrumentos.getIdPr();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The tbInstrumentos with id " + id + " no longer exists.", enfe);
            }
            em.remove(tbInstrumentos);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<TbInstrumentos> findTbInstrumentosEntities() {
        return findTbInstrumentosEntities(true, -1, -1);
    }

    public List<TbInstrumentos> findTbInstrumentosEntities(int maxResults, int firstResult) {
        return findTbInstrumentosEntities(false, maxResults, firstResult);
    }

    private List<TbInstrumentos> findTbInstrumentosEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(TbInstrumentos.class));
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

    public TbInstrumentos findTbInstrumentos(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(TbInstrumentos.class, id);
        } finally {
            em.close();
        }
    }

    public int getTbInstrumentosCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<TbInstrumentos> rt = cq.from(TbInstrumentos.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
