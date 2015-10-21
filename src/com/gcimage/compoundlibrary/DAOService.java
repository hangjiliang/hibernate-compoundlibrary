package com.gcimage.compoundlibrary;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;
import com.gcimage.compoundlibrary.entity.CompoundRecordEntity;

@SuppressWarnings("deprecation")
public class DAOService {
	
	private SessionFactory _sessionFactory;
	
	public DAOService(){
		try{
			_sessionFactory = new AnnotationConfiguration().configure().addAnnotatedClass(CompoundRecordEntity.class).buildSessionFactory();
	      }catch (Throwable ex) { 
	         System.err.println("Failed to create sessionFactory object." + ex);
	         throw new ExceptionInInitializerError(ex); 
	      }
	}
	
	public void add(CompoundRecordEntity compoundRecord){
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
	
	public List<CompoundRecordEntity> getAllRecords(){
		Session session = _sessionFactory.openSession();
		Transaction tx = null;
		List compoundRecords = null;
		try{
			tx = session.beginTransaction();
			compoundRecords = session.createQuery("FROM CompoundRecordEntity").list();
			tx.commit();
		}catch(HibernateException e){
			e.printStackTrace();
		}finally{
			session.clear();
		}
		return compoundRecords;
	}
	
	public CompoundRecordEntity findByName(String compoundName){
		Session session = _sessionFactory.openSession();
		Transaction tx = null;
		CompoundRecordEntity record = null;
		try{
			tx = session.beginTransaction();
			record  = (CompoundRecordEntity) session.get(CompoundRecordEntity.class, compoundName);
			tx.commit();
		}catch(HibernateException e){
			e.printStackTrace();
		}finally{
			session.clear();
		}
		return record;
	}
	
	public void update(CompoundRecordEntity compoundRecord){
		Session session = _sessionFactory.openSession();
		Transaction tx = null;
		CompoundRecordEntity record = null;
		try{
			tx = session.beginTransaction();
			record  = (CompoundRecordEntity) session.get(CompoundRecordEntity.class, compoundRecord.get_name());
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
		CompoundRecordEntity record = null;
		try{
			tx = session.beginTransaction();
			record  = (CompoundRecordEntity) session.get(CompoundRecordEntity.class, compoundName);
			session.delete(record);
			tx.commit();
		}catch(HibernateException e){
			e.printStackTrace();
		}finally{
			session.clear();
		}
	}

}
