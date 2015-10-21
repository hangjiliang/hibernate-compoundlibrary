package com.gcimage.compoundlibrary.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table (name="COMPOUND")
public class CompoundRecord {
	@Column (name = "Id")
	@Id
	@GeneratedValue
	private long _id;
	
	@Column (name = "Name")
    private String _name;
	
	@Column (name = "CAS")
    private String _cas;
	
	@Column (name = "Molecular_Weight")
    private double _molWt;
	
	@Column (name = "Formula")
    private String _formula;
	
	@Column (name = "Description")
    private String _description;
	
	@OneToMany (fetch = FetchType.EAGER, mappedBy="_record")
	private Set<Attribute> _attributes = new HashSet<Attribute>();
	
	public long get_id() {
		return _id;
	}

	public void set_id(long _id) {
		this._id = _id;
	}

	public Set<Attribute> get_attributes() {
		return _attributes;
	}

	public void set_attributes(Set<Attribute> _attributes) {
		this._attributes = _attributes;
	}

	public String get_name() {
		return _name;
	}

	public void set_name(String _name) {
		this._name = _name;
	}

	public String get_cas() {
		return _cas;
	}

	public void set_cas(String _cas) {
		this._cas = _cas;
	}

	public double get_molWt() {
		return _molWt;
	}

	public void set_molWt(double _molWt) {
		this._molWt = _molWt;
	}

	public String get_formula() {
		return _formula;
	}

	public void set_formula(String _formula) {
		this._formula = _formula;
	}

	public String get_description() {
		return _description;
	}

	public void set_description(String _description) {
		this._description = _description;
	}
	
	
}
