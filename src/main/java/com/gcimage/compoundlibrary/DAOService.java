package com.gcimage.compoundlibrary;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.criterion.Restrictions;

import com.gcimage.compoundlibrary.entity.Attribute;
import com.gcimage.compoundlibrary.entity.CompoundRecord;
import com.gcimage.compoundlibrary.entity.NumericalAttribute;
import com.gcimage.compoundlibrary.entity.NumericalAttributeId;

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
	
	public void add(NumericalAttribute recordAtt){
		Session session = _sessionFactory.openSession();
		Transaction tx = null;
		try{
			tx = session.beginTransaction();
			session.save(recordAtt);
			tx.commit();
		}catch(HibernateException e){
			e.printStackTrace();
		}finally{
			session.clear();
		}
	}
	
	public List<CompoundRecord> getAllCompoundRecords(){
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
	
	public List<Attribute> getAllNumericalAttributes(){
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
	
	public List<Attribute> getAllAttributes(){
		Session session = _sessionFactory.openSession();
		Transaction tx = null;
		List attributes = null;
		try{
			tx = session.beginTransaction();
			attributes = session.createQuery("FROM Attribute").list();
			tx.commit();
		}catch(HibernateException e){
			e.printStackTrace();
		}finally{
			session.clear();
		}
		return attributes;
	}
		
	public CompoundRecord findByCompoundName(String compoundName){
		Session session = _sessionFactory.openSession();
		Transaction tx = null;
		CompoundRecord record = null;
		try{
			tx = session.beginTransaction();
			Criteria criteria = session.createCriteria(CompoundRecord.class);
			record = (CompoundRecord) criteria.add(Restrictions.eq("name", compoundName)).uniqueResult();
			tx.commit();
		}catch(HibernateException e){
			e.printStackTrace();
		}finally{
			session.clear();
		}
		return record;
	}
	
	public Attribute findByAttributeName(String attributeName){
		Session session = _sessionFactory.openSession();
		Transaction tx = null;
		Attribute att = null;
		try{
			tx = session.beginTransaction();
			Criteria criteria = session.createCriteria(Attribute.class);
			att = (Attribute) criteria.add(Restrictions.eq("name", attributeName)).uniqueResult();
			tx.commit();
		}catch(HibernateException e){
			e.printStackTrace();
		}finally{
			session.clear();
		}
		return att;
	}
	
	public void updateCompoundRecord(CompoundRecord newRecord){
		Session session = _sessionFactory.openSession();
		Transaction tx = null;
		CompoundRecord record = null;
		try{
			tx = session.beginTransaction();
			Criteria criteria = session.createCriteria(CompoundRecord.class);
			record = (CompoundRecord) criteria.add(Restrictions.eq("name", newRecord.getName())).uniqueResult();
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
	
	public void updateAttribute(Attribute newAtt){
		Session session = _sessionFactory.openSession();
		Transaction tx = null;
		Attribute att = null;
		try{
			tx = session.beginTransaction();
			Criteria criteria = session.createCriteria(Attribute.class);
			att = (Attribute) criteria.add(Restrictions.eq("name", newAtt.getName())).uniqueResult();
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
		CompoundRecord record = findByCompoundName(compoundName);
		if(record != null){
			Attribute att = findByAttributeName(attName);
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
