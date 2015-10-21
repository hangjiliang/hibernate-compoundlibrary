package com.gcimage.compoundlibrary;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Test;

import com.gcimage.compoundlibrary.DAOService;
import com.gcimage.compoundlibrary.entity.Attribute;
import com.gcimage.compoundlibrary.entity.CompoundRecord;

public class HibernateTest {
	
	@Test
	public void test(){
		CompoundRecord c1 = new CompoundRecord();
		c1.set_cas("1100-0-0");
		c1.set_description("");
		c1.set_formula("CH4");
		c1.set_name("Methane");
		c1.set_molWt(16);
		
//		CompoundRecord c2 = new CompoundRecord();
//		c2.set_cas("2200-0-0");
//		c2.set_description("");
//		c2.set_formula("C2H6");
//		c2.set_name("Ethane");
//		c2.set_molWt(30);
		
		Attribute att = new Attribute();
		att.set_id(1L);
		att.set_description("");
		att.set_name("");
		att.set_range("");
		att.set_record(c1);
		att.set_type(0);
		HashSet<Attribute> atts = new HashSet<Attribute>();
		atts.add(att);
		c1.set_attributes(atts);
		
		DAOService service = new DAOService();
		service.add(c1);
		service.add(att);
		
		List atta = service.findAttribute();
		
		CompoundRecord r = service.findByName("Methane");
		
		List data = service.getAllRecords();
		Set<Attribute> a = ((CompoundRecord)data.get(0)).get_attributes();
		
		System.out.print(a.size());
	}
}
