package com.ttnd.linksharing.Validators;

import java.io.Serializable;
import java.util.List;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;

import com.ttnd.linksharing.validations.Unique;
 

public class UniqueIDValidator implements ConstraintValidator<Unique, Serializable> {
 
    HibernateTemplate hibernateTemplate;
 
    private Class<?> entityClass;
    private String uniqueField;
 
    public void initialize(Unique unique) {
        entityClass = unique.entity();
        uniqueField = unique.property();
    }
 
    public boolean isValid(Serializable property, ConstraintValidatorContext cvContext) {
 
    	//System.out.println("In validator");
        String query = String.format("from %s where %s = ? ", entityClass.getName(), uniqueField);
       // System.out.println("inside validator.");
        List<?> list = hibernateTemplate.find(query, property);
 
        //System.out.println("In validator");
        boolean retValue = !(list != null && list.size() > 0);
       // System.out.println(retValue);
        return (retValue);
    }
 
    public SessionFactory getSessionFactory() {
        return hibernateTemplate != null ? hibernateTemplate.getSessionFactory() : null;
    }
 
    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.hibernateTemplate = new HibernateTemplate(sessionFactory);
    }
}

