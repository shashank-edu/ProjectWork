package com.webapp.springbootweb.Products.controller;


import com.webapp.springbootweb.Products.entities.Product;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class AddEntities {
    public static void main(String[] args) {
        
        SessionFactory sf= new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Product.class).buildSessionFactory();
        Session session =sf.getCurrentSession();

        // ACID 
        
        try{
            // creating new instance
            Product product = new Product();
            // product.setId(3);
            product.setName("maggie");
            product.setPrice(10);
            // product.setquantity(20);;
            // product.setbatchNo("#21");
            product.setBatchNo("#21");
            product.setQuantity(32);

            Product product1 = new Product();
            // product.setId(5);
            product1.setName("Big KitKat");
            product1.setPrice(20);
            // product1.setquantity(200);
            // product1.setbatchNo("#53");]
            product1.setBatchNo("#41");
            product1.setQuantity(54);

            // start a transaction
            session.beginTransaction();
            // Product sp = (Product)session.get(Product.class,id); 

            // save the student object
            session.save(product);
            session.save(product1);

             // commit transaction
            session.getTransaction().commit();
            System.out.println("Done!");

        }catch (HibernateException e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        }finally{
            session.close();
        }

       

    }
    
}
