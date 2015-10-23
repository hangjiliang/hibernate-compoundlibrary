package com.gcimage.compoundlibrary.entity;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;

@Embeddable
public class NumericalAttributeId implements Serializable{
	@ManyToOne
	private CompoundRecord record;
	
	@ManyToOne
	private Attribute attribute;

	public CompoundRecord getRecord() {
		return record;
	}

	public void setRecord(CompoundRecord record) {
		this.record = record;
	}

	public Attribute getAttribute() {
		return attribute;
	}

	public void setAttribute(Attribute attribute) {
		this.attribute = attribute;
	}

	
	
}
