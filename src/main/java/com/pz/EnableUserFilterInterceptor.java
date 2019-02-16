package com.pz;

import org.hibernate.Session;

import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Interceptor
@EnableUserFilter
public class EnableUserFilterInterceptor {

    @PersistenceContext
    private EntityManager em;

    @AroundInvoke
    public Object enableFilters(InvocationContext ctx) throws Exception {

        Session session = em.unwrap(Session.class);
        if (session != null) {
            session.enableFilter("taskByUserFilter").setParameter("userId", 1);
            session.enableFilter("subtaskByUserFilter").setParameter("userId", 1);
        }
        return ctx.proceed();
    }
}
