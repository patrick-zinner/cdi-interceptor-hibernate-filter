package com.pz.task.sub;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@ApplicationScoped
public class SubtaskDao {

    @PersistenceContext(unitName = "h2")
    private EntityManager em;

    public Subtask create(Subtask task) {
        em.persist(task);
        return task;
    }

    public Subtask findById(Long id) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Subtask> cq = cb.createQuery(Subtask.class);
        Root<Subtask> task = cq.from(Subtask.class);
        cq.where(cb.equal(task.get("id"), id));
        TypedQuery<Subtask> q = em.createQuery(cq);
        List<Subtask> resultList = q.getResultList();
        return resultList.isEmpty() ? null : resultList.get(0);
    }


}
