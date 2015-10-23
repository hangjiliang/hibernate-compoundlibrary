package com.gcimage.compoundlibrary;

import static org.junit.Assert.*;

import java.io.File;
import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;
import org.junit.Before;
import org.junit.Test;

import com.gcimage.compoundlibrary.DerbyDatabaseService;
import com.gcimage.compoundlibrary.entity.AttributeEntity;
import com.gcimage.compoundlibrary.entity.CompoundRecordEntity;
import com.gcimage.test.TestFileResource;
import com.gcimage.util.SystemUtil;

public class HibernateTest {
	
	DerbyDatabaseService service = new DerbyDatabaseService();
	private File _origPropFileCopy;
	
	@Before
	public void setUp() throws Exception {
		
		File origPropFile = TestFileResource.getFile("CompoundLibrary/origCompLib.properties");
		
		_origPropFileCopy = new File("Copy of origCompLib.properties");
		SystemUtil.copyFile(origPropFile.getPath(), _origPropFileCopy.getPath(), true);
				
	}
	
	@Test
	public void testSaveLoadUpdate(){
		CompoundRecordEntity r1 = new CompoundRecordEntity("Hydrogen Fluoride", "7664-39-3", "HF", 20.01, "testDesc", CompoundRecord.UNDEF_BOILING_POINT_F, CompoundRecord.UNDEF_RESPONSE_FACTOR);
		CompoundRecordEntity r2 = new CompoundRecordEntity("Ammonia", "7664-41-7", "NH3", 17.031, "testDesc", CompoundRecord.UNDEF_BOILING_POINT_F, CompoundRecord.UNDEF_RESPONSE_FACTOR);
		CompoundRecordEntity r3 = new CompoundRecordEntity("Butane", "106-97-8", "C4H10", 58.12, "testDesc", CompoundRecord.UNDEF_BOILING_POINT_F, CompoundRecord.UNDEF_RESPONSE_FACTOR);
		service.save(r1);
		service.save(r2);
		service.save(r3);
		assertEquals(3, service.getAllCompoundRecords().size());
		assertEquals("7664-39-3", service.findByCompoundName("Hydrogen Fluoride").getCas());
		
		AttributeEntity a1 = new AttributeEntity("Library ID", 1, "[0,1000000)", "MAIN", "The compound ID from library MAIN.");
		AttributeEntity a2 = new AttributeEntity("Boiling Point (Celcius)", 2, ">=-273.15", "", "");
		service.save(a1);
		service.save(a2);
		assertEquals(2, service.getAllAttributes().size());
		assertEquals("The compound ID from library MAIN.", service.findByAttributeName("Library ID").getDescription());
		
		service.updateValue(r1.getName(), a2.getName(), 108.5);
		CompoundRecordEntity r = service.findByCompoundName(r1.getName());
		assertEquals(108.5, r.getNumbericalAttributes().get(0).getValue(), 0.0001);
		
		service.updateValue(r1.getName(), a2.getName(), 198.5);
		r = service.findByCompoundName(r1.getName());
		assertEquals(198.5, r.getNumbericalAttributes().get(0).getValue(), 0.0001);
	}
	
	@Test
	public void testPerformance() throws Exception{
		CompoundTableModel propModel = new CompoundTableModel();
		propModel.loadLegacyCsvTable(_origPropFileCopy);
		
		Long t1 = System.currentTimeMillis();
		service.batchSave(propModel.get_list());
		Long t2 = System.currentTimeMillis();
		System.err.println("save to database " + (t2-t1));
		
		t1 = System.currentTimeMillis();
		service.getAllCompoundRecords();
		t2 = System.currentTimeMillis();
		System.err.println("read form database " + (t2-t1));
	}
}
