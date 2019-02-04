package com.master.iot.entity;

public enum MonitorTipo {

	AQUECEDOR() {

		@Override
		public boolean ligar(float alvo, float limite, float leitura) {
			return (alvo - limite) >= leitura;
		}

		@Override
		public boolean desligar(float alvo, float limite, float leitura) {
			return alvo <= leitura;
		}

	},

	REFRIGERADOR() {
		@Override
		public boolean ligar(float alvo, float limite, float leitura) {
			return (alvo + limite) <= leitura;
		}

		@Override
		public boolean desligar(float alvo, float limite, float leitura) {
			return alvo >= leitura;
		}
	};

	public abstract boolean ligar(float alvo, float limite, float leitura);

	public abstract boolean desligar(float alvo, float limite, float leitura);

}
