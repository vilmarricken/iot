import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import com.master.iot.entity.Componente;
import com.master.iot.entity.Historico;
import com.master.iot.entity.Monitor;
import com.master.iot.entity.Placa;
import com.master.iot.entity.Temporizador;

public class AppMain {

	static SessionFactory sessionFactoryObj;
	static Session sessionObj;

	private static SessionFactory buildSessionFactory() {
		// Creating Configuration Instance & Passing Hibernate Configuration
		// File
		final Configuration configObj = new Configuration();
		final File f = new File("hibernate.cfg.xml");
		System.out.println(f.getAbsolutePath() + " - " + f.exists());
		configObj.configure(f);
		configObj.addAnnotatedClass(Componente.class);
		configObj.addAnnotatedClass(Placa.class);
		configObj.addAnnotatedClass(Historico.class);
		configObj.addAnnotatedClass(Monitor.class);
		configObj.addAnnotatedClass(Temporizador.class);

		// Since Hibernate Version 4.x, ServiceRegistry Is Being Used
		final ServiceRegistry serviceRegistryObj = new StandardServiceRegistryBuilder().applySettings(configObj.getProperties()).build();

		// Creating Hibernate SessionFactory Instance
		AppMain.sessionFactoryObj = configObj.buildSessionFactory(serviceRegistryObj);
		return AppMain.sessionFactoryObj;
	}

	public static void main(final String[] args) {
		System.out.println(".......Hibernate Maven Example.......\n");
		try {
			AppMain.sessionObj = AppMain.buildSessionFactory().openSession();
			AppMain.sessionObj.beginTransaction();

			System.out.println("\n.......Records Saved Successfully To The Database.......\n");
			final Map<String, Object> customer = new HashMap<>();
			customer.put("name", "comprador");
			customer.put("address", "casa");
			AppMain.sessionObj.save("Customer", customer);
			// Committing The Transactions To The Database
			AppMain.sessionObj.getTransaction().commit();
		} catch (final Exception sqlException) {
			sqlException.printStackTrace();
			if (null != AppMain.sessionObj.getTransaction()) {
				System.out.println("\n.......Transaction Is Being Rolled Back.......");
				AppMain.sessionObj.getTransaction().rollback();
			}
		} finally {
			if (AppMain.sessionObj != null) {
				AppMain.sessionObj.close();
			}
		}
	}
}
