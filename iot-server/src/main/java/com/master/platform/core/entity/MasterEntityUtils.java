package com.master.platform.core.entity;

public class MasterEntityUtils {

	public static void attach(final MasterEntityObject entity) {
		entity.attach();
	}

	public static void detach(final MasterEntityObject entity) {
		entity.detach();
	}

	public static boolean hasChanges(final MasterEntityObject entity) {
		return entity.hasChanges();
	}

	public MasterEntityUtils() {
	}

}
