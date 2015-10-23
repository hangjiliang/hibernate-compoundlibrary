package com.gcimage.compoundlibrary.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table (name = "NUMERICALATTRIBUTE")
@IdClass(NumericalAttributeId.class)
public class NumericalAttribute {	
	@Id
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "Compound_Id", referencedColumnName = "Id")
	private CompoundRecord compound;
	
	@Id
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "Attribute_Id", referencedColumnName = "Id")
	private Attribute attribute;
	
	@Column(name = "Value")
	private double value;
	
	public NumericalAttribute(){
		
	}

	public NumericalAttribute(CompoundRecord compound, Attribute attribute,
			double value) {
		super();
		this.compound = compound;
		this.attribute = attribute;
		this.value = value;
	}

	public CompoundRecord getCompound() {
		return compound;
	}

	public void setCompound(CompoundRecord compound) {
		this.compound = compound;
	}

	public Attribute getAttribute() {
		return attribute;
	}

	public void setAttribute(Attribute attribute) {
		this.attribute = attribute;
	}

	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
	}
	
}

