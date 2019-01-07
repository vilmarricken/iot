import java.io.File;
import java.io.FileInputStream;
import java.util.logging.LogManager;

import com.master.core.exception.MasterException;
import com.master.core.persistence.PersistenceManager;
import com.master.core.resource.MasterContext;
import com.master.core.resource.MasterContextTransaction;
import com.master.core.resource.MasterRunnable;
import com.master.core.resource.MasterThread;
import com.master.iot.entity.Placa;

public class Registry implements MasterRunnable {

	public static void main(final String[] args) throws Exception {
		LogManager.getLogManager().readConfiguration(new FileInputStream(new File("log4j.properties")));
		final MasterContext context = new MasterContextTransaction();
		new MasterThread(new Registry(), context);
	}

	@Override
	public void run() throws MasterException {
		final Placa placa = new Placa();
		placa.setDescricao("Regrador");
		placa.setIp("1.1.1.1");
		placa.setNome("Regrador");
		placa.setVersao(1);
		System.out.println(PersistenceManager.getPersistence().save(placa));
	}

}
