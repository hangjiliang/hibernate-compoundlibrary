package com.gcimage.compoundlibrary.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table (name="Compound_Record")
public class CompoundRecordEntity {
	@Column (name = "Name")
	@Id
    private String _name;
	
	@Column (name = "CAS")
    private String _cas;
	
	@Column (name = "Molecular_Weight")
    private double _molWt;
	
	@Column (name = "Formula")
    private String _formula;
	
	@Column (name = "Description")
    private String _description;

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
