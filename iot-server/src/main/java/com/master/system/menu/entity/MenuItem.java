package com.master.system.menu.entity;

import java.util.List;

import javax.persistence.Entity;

import com.master.iot.entity.HouseDeviceState;
import com.master.platform.core.entity.MasterEntityObject;

@Entity
public class MenuItem extends MasterEntityObject {

	private List<MenuItem> children;

	private String page;

	private MenuItem parent;

	private HouseDeviceState state;

	private String title;

	public List<MenuItem> getChildren() {
		return this.children;
	}

	public String getPage() {
		return this.page;
	}

	public MenuItem getParent() {
		return this.parent;
	}

	public HouseDeviceState getState() {
		return this.state;
	}

	public String getTitle() {
		return this.title;
	}

	public void setChildren(final List<MenuItem> children) {
		this.children = children;
	}

	public void setPage(final String page) {
		this.page = page;
	}

	public void setParent(final MenuItem parent) {
		this.parent = parent;
	}

	public void setState(final HouseDeviceState state) {
		this.state = state;
	}

	public void setTitle(final String title) {
		this.title = title;
	}

}
