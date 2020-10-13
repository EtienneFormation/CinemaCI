package dal;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public abstract class SessionSingleton {
	private static Session session;
	
	public static Session getInstance() {
		if (session == null) {
			 StandardServiceRegistry ssr = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build(); 
		     Metadata meta = new MetadataSources(ssr).getMetadataBuilder().build(); 
		     SessionFactory factory = meta.getSessionFactoryBuilder().build();
		     session = factory.openSession();
		}
		return session;
	}
}
