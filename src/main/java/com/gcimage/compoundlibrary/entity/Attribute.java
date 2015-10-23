package com.gcimage.compoundlibrary.entity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table (name="ATTRIBUTE")
public class Attribute {
	@Id
	@GeneratedValue
	@Column (name = "Id")
	private int id;
	
	@Column (name = "Description")
	private String description;
	
	@Column (name = "Unit")
	private String unit;
	
	@Column (name = "Range")
	private String range;
	
	@Column (name = "Type")
	private int type;
	
	@Column (name = "Name", unique=true)
	private String name;
	
	@OneToMany(fetch=FetchType.EAGER, mappedBy = "attribute", cascade = CascadeType.ALL)
	private List<NumericalAttribute> numbericalAttributes = new ArrayList<NumericalAttribute>();
	
	public Attribute(){
		
	}

	public Attribute(String name, int type, String range, String unit, String description) {
		super();
		this.description = description;
		this.unit = unit;
		this.range = range;
		this.type = type;
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getRange() {
		return range;
	}

	public void setRange(String range) {
		this.range = range;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<NumericalAttribute> getNumbericalAttributes() {
		return numbericalAttributes;
	}

	public void setNumbericalAttributes(List<NumericalAttribute> numbericalAttributes) {
		this.numbericalAttributes = numbericalAttributes;
	}
    
	
}
