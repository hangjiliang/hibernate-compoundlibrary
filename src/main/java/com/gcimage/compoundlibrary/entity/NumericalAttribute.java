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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((pk == null) ? 0 : pk.hashCode());
		long temp;
		temp = Double.doubleToLongBits(value);
		result = prime * result + (int) (temp ^ (temp >>> 32));
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
		NumericalAttribute other = (NumericalAttribute) obj;
		if (pk == null) {
			if (other.pk != null)
				return false;
		} else if (!pk.equals(other.pk))
			return false;
		if (Double.doubleToLongBits(value) != Double
				.doubleToLongBits(other.value))
			return false;
		return true;
	}
	
	
}

