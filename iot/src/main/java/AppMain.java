
import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import com.master.iot.entity.Componente;
import com.master.iot.entity.Placa;

public class AppMain {

	static Session sessionObj;
	static SessionFactory sessionFactoryObj;

	private static SessionFactory buildSessionFactory() {
		// Creating Configuration Instance & Passing Hibernate Configuration File
		Configuration configObj = new Configuration();
		File f = new File("hibernate.cfg.xml");
		System.out.println(f.getAbsolutePath() + " - " + f.exists());
		configObj.configure(f);
		configObj.addAnnotatedClass(Componente.class);
		configObj.addAnnotatedClass(Placa.class);
		configObj.addFile(new File("customer.hbm.xml"));

		// Since Hibernate Version 4.x, ServiceRegistry Is Being Used
		ServiceRegistry serviceRegistryObj = new StandardServiceRegistryBuilder().applySettings(configObj.getProperties()).build();

		// Creating Hibernate SessionFactory Instance
		sessionFactoryObj = configObj.buildSessionFactory(serviceRegistryObj);
		return sessionFactoryObj;
	}

	public static void main(String[] args) {
		System.out.println(".......Hibernate Maven Example.......\n");
		try {
			sessionObj = buildSessionFactory().openSession();
			sessionObj.beginTransaction();

			System.out.println("\n.......Records Saved Successfully To The Database.......\n");
			Map<String, Object> customer = new HashMap<>();
			customer.put("name", "comprador");
			customer.put("address", "casa");
			sessionObj.save("Customer",customer);
			// Committing The Transactions To The Database
			sessionObj.getTransaction().commit();
		} catch (Exception sqlException) {
			sqlException.printStackTrace();
			if (null != sessionObj.getTransaction()) {
				System.out.println("\n.......Transaction Is Being Rolled Back.......");
				sessionObj.getTransaction().rollback();
			}
		} finally {
			if (sessionObj != null) {
				sessionObj.close();
			}
		}
	}
}
