import com.master.core.exception.MasterException;
import com.master.core.persistence.PersistenceException;
import com.master.core.persistence.PersistenceManager;
import com.master.core.resource.MasterContext;
import com.master.core.resource.MasterContextTransaction;
import com.master.core.resource.MasterRunnable;
import com.master.core.resource.MasterThread;
import com.master.core.util.LogUtil;
import com.master.iot.entity.Componente;
import com.master.iot.entity.ComponenteTipo;
import com.master.iot.entity.Placa;
import com.master.iot.entity.Temporizador;
import com.master.iot.entity.dao.TemporizadorTipo;

public class Registry implements MasterRunnable {

	public static void main(final String[] args) throws Exception {
		LogUtil.config();
		final MasterContext context = new MasterContextTransaction();
		new MasterThread(new Registry(), context).run();
	}

	private Componente newComponenteRegrador(final Placa placa) {
		final Componente c = new Componente();
		c.setNome("Válvula selenóide");
		c.setPlaca(placa);
		c.setPorta(4);
		c.setTipo(ComponenteTipo.RELAY);
		return c;
	}

	private Placa newPlacaPiscina() throws MasterException {
		final Placa placa = new Placa();
		placa.setDescricao("Piscina");
		placa.setIp("1.1.1.3");
		placa.setNome("Piscina");
		placa.setVersao(1);
		return placa;
	}

	private Placa newPlacaRegrador() throws MasterException {
		final Placa placa = new Placa();
		placa.setDescricao("Regrador");
		placa.setIp("1.1.1.4");
		placa.setNome("Regrador");
		placa.setVersao(2);
		return placa;
	}

	private Temporizador newTemporizadorRegrador(final Placa placa) {
		final Temporizador t = new Temporizador();
		t.setComponente(this.newComponenteRegrador(placa));
		t.setDescricao("Regrador de plantas sobre o lavabo");
		t.setDesligado(11 * 60 + 55);
		t.setInicial(0);
		t.setIniciar(0);
		t.setLigado(5);
		t.setNome("Regrador lavado");
		t.setTipo(TemporizadorTipo.START_ON);
		return t;
	}

	@Override
	public void run() throws MasterException {
		final Placa placaRegrador = this.newPlacaRegrador();
		final Placa placaPiscina = this.newPlacaPiscina();
		this.save(placaRegrador);
		this.save(placaPiscina);
		this.save(this.newTemporizadorRegrador(placaRegrador));
	}

	private void save(final Object object) throws MasterException {
		try {
			PersistenceManager.getPersistence().save(object);
		} catch (final PersistenceException e) {
			throw new MasterException(e.getMessage(), e);
		}
	}

}
