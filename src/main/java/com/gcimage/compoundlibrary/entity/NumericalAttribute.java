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
	private CompoundRecordEntity compound;
	
	@Id
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "Attribute_Id", referencedColumnName = "Id")
	private AttributeEntity attribute;
	
	@Column(name = "Value")
	private double value;
	
	public NumericalAttribute(){
		
	}

	public NumericalAttribute(CompoundRecordEntity compound, AttributeEntity attribute,
			double value) {
		super();
		this.compound = compound;
		this.attribute = attribute;
		this.value = value;
	}

	public CompoundRecordEntity getCompound() {
		return compound;
	}

	public void setCompound(CompoundRecordEntity compound) {
		this.compound = compound;
	}

	public AttributeEntity getAttribute() {
		return attribute;
	}

	public void setAttribute(AttributeEntity attribute) {
		this.attribute = attribute;
	}

	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
	}
	
}

