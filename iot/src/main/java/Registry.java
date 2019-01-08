import java.io.File;
import java.util.List;

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
		Placa placa = this.find("Regrador");
		if (placa == null) {
			placa = new Placa();
			placa.setDescricao("Regrador");
			placa.setIp("1.1.1.1");
			placa.setNome("Regrador");
			placa.setVersao(2);
			System.out.println(PersistenceManager.getPersistence().save(placa));
		}
		System.out.println(placa.getId());
	}

	private Placa find(String nome) {
		final List<Placa> list = PersistenceManager.getPersistence().list(Placa.class, new FilterCompare("nome", nome, FilterOperation.EQ));
		if (list.size() > 1) {
			throw new RuntimeException("Quantidade de registros, " + list.size() + ", não esperado para a placa: " + nome);
		}
		if (list.size() == 0) {
			return null;
		}
		return list.get(0);
	}

}
