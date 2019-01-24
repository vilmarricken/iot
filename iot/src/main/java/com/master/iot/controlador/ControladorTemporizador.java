package com.master.iot.controlador;

import com.master.iot.entity.Historico;
import com.master.iot.entity.Situacao;
import com.master.iot.entity.Temporizador;
import com.master.iot.entity.TemporizadorTipo;
import com.master.iot.entity.dao.HistoricoInsertDaoDefault;

public class ControladorTemporizador extends Controlador {

	private final Temporizador temporizador;

	public ControladorTemporizador(final Temporizador temporizador) {
		super();
		this.temporizador = temporizador;
	}

	private void ligar(Historico historico) {
		final HistoricoInsertDaoDefault dao = new HistoricoInsertDaoDefault(historico);
		// dao.
	}

	@Override
	public void run() {
		new Thread(this.temporizador.getNome()) {
			@Override
			public void run() {
				try {
					ControladorTemporizador.this.setExecutando(true);
					final boolean iniciar = TemporizadorTipo.START_ON.equals(ControladorTemporizador.this.temporizador.getTipo());
					final Historico historico = new Historico(ControladorTemporizador.this.temporizador, Situacao.EXECUTANDO);
					if (iniciar) {
						ControladorTemporizador.this.ligar(historico);
					}
				} finally {
					ControladorTemporizador.this.setExecutando(false);
				}
			}

		}.start();
	}

}
