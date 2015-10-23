package com.gcimage.compoundlibrary.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;

@Embeddable
public class NumericalAttributeId implements Serializable{
	@ManyToOne(cascade = CascadeType.ALL)
	private CompoundRecord record;
	
	@ManyToOne(cascade = CascadeType.ALL)
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
