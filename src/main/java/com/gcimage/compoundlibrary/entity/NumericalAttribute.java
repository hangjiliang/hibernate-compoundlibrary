package com.gcimage.compoundlibrary.entity;

import javax.persistence.AssociationOverride;
import javax.persistence.AssociationOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

@Entity
@Table (name = "NUMERICALATTRIBUTE")
@AssociationOverrides({
	@AssociationOverride(name = "pk.record", joinColumns = @JoinColumn(name = "Compound_Id")),
	@AssociationOverride(name = "pk.attribute", joinColumns = @JoinColumn(name = "Attribute_Id"))
	}		
)
public class NumericalAttribute {
	@EmbeddedId
	private NumericalAttributeId pk = new NumericalAttributeId();
	
	@Column (name = "value")
	private double value;

	public NumericalAttributeId getPk() {
		return pk;
	}

	public void setPk(NumericalAttributeId pk) {
		this.pk = pk;
	}

	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
	}
	
	
}

