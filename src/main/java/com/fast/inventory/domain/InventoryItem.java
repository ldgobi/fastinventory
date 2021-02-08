/**
 * 
 */
package com.fast.inventory.domain;

import org.springframework.data.annotation.Id;

/**
 * @author LucianoDiasGobi
 *
 */
public class InventoryItem {
	@Id
	private String id;

	private int totalQuantity;
	private int bookedQuantity;
	private String location;

	@Override
	public String toString() {
		return "InventoryItem [id=" + id + ", totalQuantity=" + totalQuantity + ", bookedQuantity=" + bookedQuantity
				+ ", location=" + location + "]";
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the totalQuantity
	 */
	public int getTotalQuantity() {
		return totalQuantity;
	}

	/**
	 * @param totalQuantity the totalQuantity to set
	 */
	public void setTotalQuantity(int totalQuantity) {
		this.totalQuantity = totalQuantity;
	}

	/**
	 * @return the bookedQuantity
	 */
	public int getBookedQuantity() {
		return bookedQuantity;
	}

	/**
	 * @param bookedQuantity the bookedQuantity to set
	 */
	public void setBookedQuantity(int bookedQuantity) {
		this.bookedQuantity = bookedQuantity;
	}

	/**
	 * @return the location
	 */
	public String getLocation() {
		return location;
	}

	/**
	 * @param location the location to set
	 */
	public void setLocation(String location) {
		this.location = location;
	}
}
