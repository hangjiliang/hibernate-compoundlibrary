package com.gcimage.compoundlibrary;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;

import com.gcimage.compoundlibrary.entity.Attribute;
import com.gcimage.compoundlibrary.entity.CompoundRecord;

@SuppressWarnings("deprecation")
public class DAOService {
	
	private SessionFactory _sessionFactory;
	
	public DAOService(){
		try{
			_sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
	      }catch (Throwable ex) { 
	         System.err.println("Failed to create sessionFactory object." + ex);
	         throw new ExceptionInInitializerError(ex); 
	      }
	}
	
	public void add(CompoundRecord compoundRecord){
		Session session = _sessionFactory.openSession();
		Transaction tx = null;
		try{
			tx = session.beginTransaction();
			session.save(compoundRecord);
			tx.commit();
		}catch(HibernateException e){
			e.printStackTrace();
		}finally{
			session.clear();
		}
	}
	
	public void add(Attribute att){
		Session session = _sessionFactory.openSession();
		Transaction tx = null;
		try{
			tx = session.beginTransaction();
			session.save(att);
			tx.commit();
		}catch(HibernateException e){
			e.printStackTrace();
		}finally{
			session.clear();
		}
	}
	
	public List<CompoundRecord> getAllRecords(){
		Session session = _sessionFactory.openSession();
		Transaction tx = null;
		List compoundRecords = null;
		try{
			tx = session.beginTransaction();
			compoundRecords = session.createQuery("FROM CompoundRecord").list();
			tx.commit();
		}catch(HibernateException e){
			e.printStackTrace();
		}finally{
			session.clear();
		}
		return compoundRecords;
	}
	
	public List findAttribute(){
		Session session = _sessionFactory.openSession();
		Transaction tx = null;
		List att = null;
		try{
			tx = session.beginTransaction();
			att  = session.createQuery("FROM Attribute").list();
			tx.commit();
		}catch(HibernateException e){
			e.printStackTrace();
		}finally{
			session.clear();
		}
		return att;
	}
	
	public CompoundRecord findByName(String compoundName){
		Session session = _sessionFactory.openSession();
		Transaction tx = null;
		CompoundRecord record = null;
		try{
			tx = session.beginTransaction();
			record  = (CompoundRecord) session.get(CompoundRecord.class, 1L);
			tx.commit();
		}catch(HibernateException e){
			e.printStackTrace();
		}finally{
			session.clear();
		}
		return record;
	}
	
	public void update(CompoundRecord compoundRecord){
		Session session = _sessionFactory.openSession();
		Transaction tx = null;
		CompoundRecord record = null;
		try{
			tx = session.beginTransaction();
			record  = (CompoundRecord) session.get(CompoundRecord.class, compoundRecord.get_name());
			if(record != null){
				record.set_cas(compoundRecord.get_cas());
				record.set_description(compoundRecord.get_description());
				record.set_formula(compoundRecord.get_formula());
				record.set_molWt(compoundRecord.get_molWt());
				session.update(record);
			}else{
				session.save(compoundRecord);
			}
			tx.commit();
		}catch(HibernateException e){
			e.printStackTrace();
		}finally{
			session.clear();
		}
	}
	
	public void delete(String compoundName){
		Session session = _sessionFactory.openSession();
		Transaction tx = null;
		CompoundRecord record = null;
		try{
			tx = session.beginTransaction();
			record  = (CompoundRecord) session.get(CompoundRecord.class, compoundName);
			session.delete(record);
			tx.commit();
		}catch(HibernateException e){
			e.printStackTrace();
		}finally{
			session.clear();
		}
	}

}
