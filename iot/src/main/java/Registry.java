import com.master.core.exception.MasterException;
import com.master.core.persistence.PersistenceException;
import com.master.core.persistence.PersistenceManager;
import com.master.core.resource.MasterContext;
import com.master.core.resource.MasterContextTransaction;
import com.master.core.resource.MasterRunnable;
import com.master.core.resource.MasterThread;
import com.master.core.util.LogUtil;
import com.master.iot.entity.Board;
import com.master.iot.entity.Component;
import com.master.iot.entity.ComponentType;
import com.master.iot.entity.Timer;

public class Registry implements MasterRunnable {

	public static void main(final String[] args) throws Exception {
		LogUtil.config();
		final MasterContext context = new MasterContextTransaction();
		new MasterThread(new Registry(), context).run();
	}

	private Component newComponentRegrador(final Board board) {
		final Component c = new Component();
		c.setName("Válvula selenóide");
		c.setBoard(board);
		c.setDoor(4);
		c.setType(ComponentType.RELAY);
		return c;
	}

	private Board newPlacaPiscina() throws MasterException {
		final Board placa = new Board();
		placa.setDescription("Piscina");
		placa.setIp("1.1.1.3");
		placa.setName("Piscina");
		placa.setVersion(1);
		return placa;
	}

	private Board newPlacaRegrador() throws MasterException {
		final Board placa = new Board();
		placa.setDescription("Regrador");
		placa.setIp("1.1.1.4");
		placa.setName("Regrador");
		placa.setVersion(2);
		return placa;
	}

	private Timer newTemporizadorRegrador(final Board placa) {
		final Timer t = new Timer();
		t.setComponent(this.newComponentRegrador(placa));
		t.setDescription("Regrador de plantas sobre o lavabo");
		t.setOff(11 * 60 + 55);
		t.setOn(5);
		t.setName("Regrador lavado");
		return t;
	}

	@Override
	public void run() throws MasterException {
		final Board placaRegrador = this.newPlacaRegrador();
		final Board placaPiscina = this.newPlacaPiscina();
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
