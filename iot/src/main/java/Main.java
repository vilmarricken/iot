import com.master.core.exception.MasterException;
import com.master.core.resource.MasterContextTransaction;
import com.master.core.resource.MasterRunnable;
import com.master.core.resource.MasterThread;
import com.master.core.util.LogUtil;

public class Main implements MasterRunnable {

	public static void main(final String[] args) throws MasterException {
		LogUtil.config();
		new MasterThread(new Main(), new MasterContextTransaction()).run();
	}

	@Override
	public void run() throws MasterException {

	}

}
