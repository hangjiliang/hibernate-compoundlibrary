package com.gcimage.compoundlibrary.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table (name="COMPOUND")
public class CompoundRecord {
	@Transient
	public final static double UNDEF_BOILING_POINT_F = -460;
	@Transient
	public final static double UNDEF_RESPONSE_FACTOR = -1;
	@Transient
	public final static double UNDEF_MOLECULAR_WEIGHT = -1;
	@Transient
	public final static String UNDEF_FORMULA = "-";
	@Transient
	public final static String UNDEF_CAS_NUMBER = "-";
	
	@Column (name = "Compound_Id")
	@Id
	@GeneratedValue
	private long id;
	
	@Column (name = "Name")
    private String name;
	
	@Column (name = "CAS")
    private String cas = UNDEF_CAS_NUMBER;
	
	@Column (name = "Molecular_Weight")
    private double molWt = UNDEF_MOLECULAR_WEIGHT;
	
	@Column (name = "Formula")
    private String formula = UNDEF_FORMULA;
	
	@Column (name = "Description")
    private String description;
	
	@Column (name = "Bp(F)")
    private double bpF = UNDEF_BOILING_POINT_F;
	
	@Column (name = "Response_Factor")
    private double respFactor = UNDEF_RESPONSE_FACTOR; 
	
	@OneToMany(mappedBy = "pk.record", cascade = CascadeType.ALL)
	private Set<NumericalAttribute> numbericalAttributes = new HashSet<NumericalAttribute>();

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCas() {
		return cas;
	}

	public void setCas(String cas) {
		this.cas = cas;
	}

	public double getMolWt() {
		return molWt;
	}

	public void setMolWt(double molWt) {
		this.molWt = molWt;
	}

	public String getFormula() {
		return formula;
	}

	public void setFormula(String formula) {
		this.formula = formula;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getBpF() {
		return bpF;
	}

	public void setBpF(double bpF) {
		this.bpF = bpF;
	}

	public double getRespFactor() {
		return respFactor;
	}

	public void setRespFactor(double respFactor) {
		this.respFactor = respFactor;
	}

	public Set<NumericalAttribute> getAttributes() {
		return numbericalAttributes;
	}

	public void setAttributes(Set<NumericalAttribute> attributes) {
		this.numbericalAttributes = attributes;
	}

	
}
