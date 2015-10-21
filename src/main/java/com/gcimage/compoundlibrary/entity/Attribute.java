package com.gcimage.compoundlibrary.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table (name="ATTRIBUTE")
public class Attribute {
	@Column (name = "Id")
	@Id
	@GeneratedValue
	private long _id;
	
	@Column (name = "Description")
	private String _description;
	
	@Column (name = "Unit")
	private String _unit;
	
	@Column (name = "Range")
	private String _range;
	
	@Column (name = "Type")
	private int _type;
	
	@Column (name = "Name")
	private String _name;
	
	@ManyToOne (fetch = FetchType.LAZY)
	@JoinColumn(name="Compound_Id")
	private CompoundRecord _record;

	public long get_id() {
		return _id;
	}

	public void set_id(long _id) {
		this._id = _id;
	}

	public String get_description() {
		return _description;
	}

	public void set_description(String _description) {
		this._description = _description;
	}

	public String get_unit() {
		return _unit;
	}

	public void set_unit(String _unit) {
		this._unit = _unit;
	}

	public String get_range() {
		return _range;
	}

	public void set_range(String _range) {
		this._range = _range;
	}

	public int get_type() {
		return _type;
	}

	public void set_type(int _type) {
		this._type = _type;
	}

	public String get_name() {
		return _name;
	}

	public void set_name(String _name) {
		this._name = _name;
	}

	public CompoundRecord get_record() {
		return _record;
	}

	public void set_record(CompoundRecord _record) {
		this._record = _record;
	}
	
	

}
