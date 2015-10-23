package com.gcimage.compoundlibrary;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.criterion.Restrictions;

import com.gcimage.compoundlibrary.entity.AttributeEntity;
import com.gcimage.compoundlibrary.entity.CompoundRecordEntity;
import com.gcimage.compoundlibrary.entity.NumericalAttribute;
import com.gcimage.compoundlibrary.entity.NumericalAttributeId;

@SuppressWarnings("deprecation")
public class DerbyDatabaseService {
	
	private SessionFactory _sessionFactory;
	
	public DerbyDatabaseService(){
		try{
			_sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
	      }catch (Throwable ex) { 
	         System.err.println("Failed to create sessionFactory object." + ex);
	         throw new ExceptionInInitializerError(ex); 
	      }
	}
	
	public SessionFactory getSessionFactory(){
		return _sessionFactory;
	}
	
	public void batchSave(List<CompoundRecord> records){	
		Session session = _sessionFactory.openSession();
		Transaction tx = null;
		try{
			tx = session.beginTransaction();
			for(CompoundRecord r : records){
				CompoundRecordEntity re = new CompoundRecordEntity(r.getName(), r.getCASNo(), r.getMolFormula(), r.getMolWt(), r.getDescription(), r.getTempF(), r.getRespFactor());
				session.save(re);
			}
			tx.commit();
		}catch(HibernateException e){
			e.printStackTrace();
		}finally{
			session.clear();
		}

	}
	
	public void save(CompoundRecordEntity compoundRecord){
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
	
	public void save(AttributeEntity att){
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
		
	public List<CompoundRecordEntity> getAllCompoundRecords(){
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
	
	public List<AttributeEntity> getAllNumericalAttributes(){
		Session session = _sessionFactory.openSession();
		Transaction tx = null;
		List numAttributes = null;
		try{
			tx = session.beginTransaction();
			numAttributes = session.createQuery("FROM NumericalAttribute").list();
			tx.commit();
		}catch(HibernateException e){
			e.printStackTrace();
		}finally{
			session.clear();
		}
		return numAttributes;
	}
	
	public List<AttributeEntity> getAllAttributes(){
		Session session = _sessionFactory.openSession();
		Transaction tx = null;
		List attributes = null;
		try{
			tx = session.beginTransaction();
			attributes = session.createQuery("FROM AttributeEntity").list();
			tx.commit();
		}catch(HibernateException e){
			e.printStackTrace();
		}finally{
			session.clear();
		}
		return attributes;
	}
		
	public CompoundRecordEntity findByCompoundName(String compoundName){
		Session session = _sessionFactory.openSession();
		Transaction tx = null;
		CompoundRecordEntity record = null;
		try{
			tx = session.beginTransaction();
			Criteria criteria = session.createCriteria(CompoundRecordEntity.class);
			record = (CompoundRecordEntity) criteria.add(Restrictions.eq("name", compoundName)).uniqueResult();
			Hibernate.initialize(record.getNumbericalAttributes());
			tx.commit();
		}catch(HibernateException e){
			e.printStackTrace();
		}finally{
			session.clear();
		}
		return record;
	}
	
	public AttributeEntity findByAttributeName(String attributeName){
		Session session = _sessionFactory.openSession();
		Transaction tx = null;
		AttributeEntity att = null;
		try{
			tx = session.beginTransaction();
			Criteria criteria = session.createCriteria(AttributeEntity.class);
			att = (AttributeEntity) criteria.add(Restrictions.eq("name", attributeName)).uniqueResult();
			tx.commit();
		}catch(HibernateException e){
			e.printStackTrace();
		}finally{
			session.clear();
		}
		return att;
	}
	
	public void updateCompoundRecord(CompoundRecordEntity newRecord){
		Session session = _sessionFactory.openSession();
		Transaction tx = null;
		CompoundRecordEntity record = null;
		try{
			tx = session.beginTransaction();
			Criteria criteria = session.createCriteria(CompoundRecordEntity.class);
			record = (CompoundRecordEntity) criteria.add(Restrictions.eq("name", newRecord.getName())).uniqueResult();
			if(record != null){
				record.setCas(newRecord.getCas());
				record.setDescription(newRecord.getDescription());
				record.setFormula(newRecord.getFormula());
				record.setMolWt(newRecord.getMolWt());
				record.setNumbericalAttributes(newRecord.getNumbericalAttributes());
				record.setBpF(newRecord.getBpF());
				record.setRespFactor(newRecord.getRespFactor());
				session.update(record);
			}else{
				session.save(newRecord);
			}
			tx.commit();
		}catch(HibernateException e){
			e.printStackTrace();
		}finally{
			session.clear();
		}
	}
	
	public void updateAttribute(AttributeEntity newAtt){
		Session session = _sessionFactory.openSession();
		Transaction tx = null;
		AttributeEntity att = null;
		try{
			tx = session.beginTransaction();
			Criteria criteria = session.createCriteria(AttributeEntity.class);
			att = (AttributeEntity) criteria.add(Restrictions.eq("name", newAtt.getName())).uniqueResult();
			if(att != null){
				att.setDescription(newAtt.getDescription());
				att.setName(newAtt.getName());
				att.setRange(newAtt.getRange());
				att.setType(newAtt.getType());
				att.setUnit(newAtt.getUnit());
				att.setNumbericalAttributes(newAtt.getNumbericalAttributes());
			}else{
				session.save(att);
			}
			tx.commit();
		}catch(HibernateException e){
			e.printStackTrace();
		}finally{
			session.clear();
		}
	}
	
	public void updateValue(String compoundName, String attName, double value){
		CompoundRecordEntity record = findByCompoundName(compoundName);
		if(record != null){
			AttributeEntity att = findByAttributeName(attName);
			if(att != null){
				NumericalAttributeId pk = new NumericalAttributeId();
				pk.setAttribute(att.getId());
				pk.setCompound(record.getId());
				Session session = _sessionFactory.openSession();
				Transaction tx = null;
				NumericalAttribute numAtt = null;
				try{
					tx = session.beginTransaction();
					numAtt = (NumericalAttribute) session.get(NumericalAttribute.class, pk);
					if(numAtt != null){
						numAtt.setValue(value);
					}else{
						session.update(record);
						session.update(att);
						numAtt = new NumericalAttribute(record, att, value);
						session.save(numAtt);
					}
					tx.commit();
				}catch(HibernateException e){
					e.printStackTrace();
				}finally{
					session.clear();
				}
				
			}
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
