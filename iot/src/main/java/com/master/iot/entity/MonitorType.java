package com.master.iot.entity;

public enum MonitorType {

	WARMER() {

		@Override
		public boolean isTurnOn(float target, float limit, float read) {
			return (target - limit) >= read;
		}

		@Override
		public boolean isTurnOff(float target, float limit, float read) {
			return target <= read;
		}

	},

	REFRIGERATOR() {
		@Override
		public boolean isTurnOn(float target, float limit, float read) {
			return (target + limit) <= read;
		}

		@Override
		public boolean isTurnOff(float target, float limit, float read) {
			return target >= read;
		}
	};

	public abstract boolean isTurnOn(float target, float limit, float read);

	public abstract boolean isTurnOff(float target, float limit, float read);

}
