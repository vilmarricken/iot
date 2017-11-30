package com.master.platform.core.entity;

public class MasterEntityUtils {

	public static void attach(final MasterEntityObjectValue entity) {
		entity.attach();
	}

	public static void detach(final MasterEntityObjectValue entity) {
		entity.detach();
	}

	public static boolean hasChanges(final MasterEntityObjectValue entity) {
		return entity.hasChanges();
	}

	public MasterEntityUtils() {
	}

}
