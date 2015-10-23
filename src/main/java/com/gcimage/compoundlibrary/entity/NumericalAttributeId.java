package com.gcimage.compoundlibrary.entity;

import java.io.Serializable;

public class NumericalAttributeId implements Serializable{
	private int compound;
	private int attribute;
	public int getCompound() {
		return compound;
	}
	public void setCompound(int compound) {
		this.compound = compound;
	}
	public int getAttribute() {
		return attribute;
	}
	public void setAttribute(int attribute) {
		this.attribute = attribute;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + attribute;
		result = prime * result + compound;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		NumericalAttributeId other = (NumericalAttributeId) obj;
		if (attribute != other.attribute)
			return false;
		if (compound != other.compound)
			return false;
		return true;
	}
	
}
