package com.gcimage.compoundlibrary.test;

import java.util.List;

import org.junit.Test;

import com.gcimage.compoundlibrary.DAOService;
import com.gcimage.compoundlibrary.entity.CompoundRecordEntity;

public class HibernateTest {
	
	@Test
	public void test(){
		CompoundRecordEntity c1 = new CompoundRecordEntity();
		c1.set_cas("1100-0-0");
		c1.set_description("");
		c1.set_formula("CH4");
		c1.set_name("Methane");
		c1.set_molWt(16);
		
		CompoundRecordEntity c2 = new CompoundRecordEntity();
		c2.set_cas("2200-0-0");
		c2.set_description("");
		c2.set_formula("C2H6");
		c2.set_name("Ethane");
		c2.set_molWt(30);
		
		DAOService service = new DAOService();
		service.add(c1);
		service.add(c2);
		
		List data = service.getAllRecords();
		System.out.print(data.size());
	}
}
