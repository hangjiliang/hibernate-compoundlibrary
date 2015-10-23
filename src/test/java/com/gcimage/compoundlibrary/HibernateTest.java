package com.gcimage.compoundlibrary;

import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Test;

import com.gcimage.compoundlibrary.DerbyDatabaseService;
import com.gcimage.compoundlibrary.entity.Attribute;
import com.gcimage.compoundlibrary.entity.CompoundRecord;

public class HibernateTest {
	
	DerbyDatabaseService service = new DerbyDatabaseService();
	
	@Test
	public void testSaveLoadUpdate(){
		CompoundRecord r1 = new CompoundRecord("Hydrogen Fluoride", "7664-39-3", "HF", 20.01, "testDesc", CompoundRecord.UNDEF_BOILING_POINT_F, CompoundRecord.UNDEF_RESPONSE_FACTOR);
		CompoundRecord r2 = new CompoundRecord("Ammonia", "7664-41-7", "NH3", 17.031, "testDesc", CompoundRecord.UNDEF_BOILING_POINT_F, CompoundRecord.UNDEF_RESPONSE_FACTOR);
		CompoundRecord r3 = new CompoundRecord("Butane", "106-97-8", "C4H10", 58.12, "testDesc", CompoundRecord.UNDEF_BOILING_POINT_F, CompoundRecord.UNDEF_RESPONSE_FACTOR);
		service.save(r1);
		service.save(r2);
		service.save(r3);
		assertEquals(3, service.getAllCompoundRecords().size());
		assertEquals("7664-39-3", service.findByCompoundName("Hydrogen Fluoride").getCas());
		
		Attribute a1 = new Attribute("Library ID", 1, "[0,1000000)", "MAIN", "The compound ID from library MAIN.");
		Attribute a2 = new Attribute("Boiling Point (Celcius)", 2, ">=-273.15", "", "");
		service.save(a1);
		service.save(a2);
		assertEquals(2, service.getAllAttributes().size());
		assertEquals("The compound ID from library MAIN.", service.findByAttributeName("Library ID").getDescription());
		
		service.updateValue(r1.getName(), a2.getName(), 108.5);
		CompoundRecord r = service.findByCompoundName(r1.getName());
		assertEquals(108.5, r.getNumbericalAttributes().get(0).getValue(), 0.0001);
		
		service.updateValue(r1.getName(), a2.getName(), 198.5);
		r = service.findByCompoundName(r1.getName());
		assertEquals(198.5, r.getNumbericalAttributes().get(0).getValue(), 0.0001);
	}
	
	@Test
	public void testPerformance(){
		
	}
}
