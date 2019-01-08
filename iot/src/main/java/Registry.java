import java.io.File;
import java.io.Serializable;

import org.apache.log4j.xml.DOMConfigurator;

import com.master.core.exception.MasterException;
import com.master.core.persistence.PersistenceManager;
import com.master.core.persistence.filter.FilterCompare;
import com.master.core.persistence.filter.FilterOperation;
import com.master.core.resource.MasterContext;
import com.master.core.resource.MasterContextTransaction;
import com.master.core.resource.MasterRunnable;
import com.master.core.resource.MasterThread;
import com.master.iot.entity.Placa;

public class Registry implements MasterRunnable {

	public static void main(final String[] args) throws Exception {
		final File f = new File("log4j.xml");
		System.out.println(f.getAbsolutePath() + " - " + f.exists());
		DOMConfigurator.configure(f.toURI().toURL());
		final MasterContext context = new MasterContextTransaction();
		new MasterThread(new Registry(), context).run();
	}

	@Override
	public void run() throws MasterException {
		final Placa placaRegrado = this.newPlacaRegrador();
		final Placa p = this.mergePlaca(placaRegrado);
		System.out.println(placaRegrado);
		System.out.println(p);
	}

	private Placa mergePlaca(Placa placa) throws MasterException {
		final Placa placaSaved = PersistenceManager.getPersistence().get(Placa.class, new FilterCompare("nome", placa.getNome(), FilterOperation.EQ));
		if (placaSaved == null) {
			final Serializable p = PersistenceManager.getPersistence().save(placa);
			System.out.println(p);
			return placa;
		} else {
			final String ipSaved = placaSaved.getIp();
			if (ipSaved == null || !ipSaved.equals(placa.getIp())) {
				placaSaved.setIp(placa.getIp());
				final Serializable p = PersistenceManager.getPersistence().save(placaSaved);
				System.out.println(p);
				return placaSaved;
			}
		}
		return placaSaved;
	}

	private Placa newPlacaRegrador() throws MasterException {
		final Placa placa = new Placa();
		placa.setDescricao("Regrador");
		placa.setIp("1.1.1.4");
		placa.setNome("Regrador");
		placa.setVersao(2);
		return placa;
	}

	private Placa newPlacaPiscina() throws MasterException {
		final Placa placa = new Placa();
		placa.setDescricao("Piscina");
		placa.setIp("1.1.1.3");
		placa.setNome("Piscina");
		placa.setVersao(1);
		return placa;
	}

}
