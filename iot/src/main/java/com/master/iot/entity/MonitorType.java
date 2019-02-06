package com.master.iot.entity;

public enum MonitorType {

	WARMER() {

		@Override
		public boolean on(float target, float limit, float read) {
			return (target - limit) >= read;
		}

		@Override
		public boolean off(float target, float limit, float read) {
			return target <= read;
		}

	},

	REFRIGERATOR() {
		@Override
		public boolean on(float target, float limit, float read) {
			return (target + limit) <= read;
		}

		@Override
		public boolean off(float target, float limit, float read) {
			return target >= read;
		}
	};

	public abstract boolean on(float target, float limit, float read);

	public abstract boolean off(float target, float limit, float read);

}
