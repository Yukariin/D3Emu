package com.d3emu.util;

import com.d3emu.db.entities.*;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public final class HibernateUtil {

    private static SessionFactory sessionFactory;

    private static SessionFactory buildSessionFactory() {
        Configuration c = new Configuration();

        c.addAnnotatedClass(Account.class);
        c.addAnnotatedClass(GameAccount.class);
        c.addAnnotatedClass(Toon.class);

        c.setProperty("hibernate.connection.driver_class", "org.postgresql.Driver");
        c.setProperty("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");

        c.setProperty("hibernate.connection.url", "jdbc:postgresql://localhost:5432/d3emu");
        c.setProperty("hibernate.connection.username", "postgres");
        c.setProperty("hibernate.connection.password", "dghqmjyo");

        c.setProperty("hibernate.c3p0.min_size", "5");
        c.setProperty("hibernate.c3p0.max_size", "10");
        c.setProperty("hibernate.c3p0.max_statements", "50");
        c.setProperty("hibernate.c3p0.idle_test_period", "3000");

        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder();
        builder.applySettings(c.getProperties());
        return c.buildSessionFactory(builder.build());
    }

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) sessionFactory = buildSessionFactory();
        return sessionFactory;
    }

    public static void closeSessionFactory() {
        if (sessionFactory != null) sessionFactory.close();
    }
}
