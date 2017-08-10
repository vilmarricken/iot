package com.master.platform.core.entity.manager;

public class MasterEntityManagerController {

	private static MasterEntityManagerController INSTANCE = null;

	public static MasterEntityManagerController get() {
		if (MasterEntityManagerController.INSTANCE == null) {
			MasterEntityManagerController.INSTANCE = new MasterEntityManagerController();
		}
		return MasterEntityManagerController.INSTANCE;
	}

	private final MasterEntityManagerFactory entityManagerFactory;

	private MasterEntityManagerController() {
		this.entityManagerFactory = MasterEntityManagerCreator.create();
	}

	public MasterEntityManagerFactory getEntityManagerFactory() {
		return this.entityManagerFactory;
	}

}
