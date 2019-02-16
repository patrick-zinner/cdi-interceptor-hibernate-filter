package com.pz.task;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@ApplicationScoped
public class TaskDao {

    @PersistenceContext(unitName = "h2")
    private EntityManager em;

    public Task create(Task task) {
        em.persist(task);
        return task;
    }

    public Task findById(Long id) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Task> cq = cb.createQuery(Task.class);
        Root<Task> task = cq.from(Task.class);
        cq.where(cb.equal(task.get("id"), id));
        TypedQuery<Task> q = em.createQuery(cq);
        List<Task> resultList = q.getResultList();
        return resultList.isEmpty() ? null : resultList.get(0);
    }

    public List<Task> findAll() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Task> cq = cb.createQuery(Task.class);
        Root<Task> rootEntry = cq.from(Task.class);
        CriteriaQuery<Task> all = cq.select(rootEntry);
        TypedQuery<Task> allQuery = em.createQuery(all);
        return allQuery.getResultList();
    }

}
