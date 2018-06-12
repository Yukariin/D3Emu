package com.d3emu.utils;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public final class HibernateUtils {

    private static SessionFactory sessionFactory;

    private static SessionFactory buildSessionFactory() {
        Configuration c = new Configuration();
        c.configure();

        return c.buildSessionFactory();
    }

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) sessionFactory = buildSessionFactory();
        return sessionFactory;
    }

    public static void closeSessionFactory() {
        if (sessionFactory != null) sessionFactory.close();
    }
}
