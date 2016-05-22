package app.vrtic.Util;

import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;

import org.hibernate.cfg.Configuration;
import java.io.FileInputStream;
import java.util.Properties;

import javax.imageio.spi.ServiceRegistry;

import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import app.vrtic.View.login;
import app.vrtic.App;

public class HibernateUtil {
	final static Logger logger = Logger.getLogger(login.class);
	 public static final SessionFactory sessionFactory = buildSessionFactory();
	 //private static final SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
	
	    private static SessionFactory buildSessionFactory() {
	        try {
	        	java.util.Properties properties = new Properties();
				properties.load(new FileInputStream("db.properties"));
				// Create the SessionFactory from hibernate.cfg.xml
				return new Configuration().addProperties(properties).configure().buildSessionFactory();
	            // Create the SessionFactory from hibernate.cfg.xml
	     
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
