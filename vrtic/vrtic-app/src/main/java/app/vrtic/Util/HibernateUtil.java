package app.vrtic.Util;

import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;

import org.hibernate.cfg.Configuration;

import app.vrtic.View.login;

public class HibernateUtil {
	final static Logger logger = Logger.getLogger(login.class);
	  private static final SessionFactory sessionFactory = buildSessionFactory();

	    private static SessionFactory buildSessionFactory() {
	        try {
	            // Create the SessionFactory from hibernate.cfg.xml
	            return new Configuration().configure().buildSessionFactory();
	        }
	        catch (Exception ex) {
	            // Make sure you log the exception, as it might be swallowed
	            logger.info(ex);
	            throw new ExceptionInInitializerError(ex);
	        }
	    }

	    public static SessionFactory getSessionFactory() {
	        return sessionFactory;
	    }
}
