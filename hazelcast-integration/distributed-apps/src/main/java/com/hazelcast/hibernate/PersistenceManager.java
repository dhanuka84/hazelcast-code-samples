package com.hazelcast.hibernate;

import static com.hazelcast.examples.helper.CommonUtils.sleepMillis;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.concurrent.ConcurrentHashMap;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import com.hazelcast.hibernate.distributed.entity.Product;
import com.hazelcast.hibernate.distributed.entity.Test;

public class PersistenceManager {
	
	private static SessionFactory sessionFactory;
	private static ServiceRegistry serviceRegistry;
	
	private static Map<String,Long> productMap = new ConcurrentHashMap<>();
	
	public static SessionFactory createSF(){
	        try {
	        	 Configuration configuration = new Configuration();
	        	    configuration.configure();
	        	    serviceRegistry = new StandardServiceRegistryBuilder().applySettings(
	        	            configuration.getProperties()).build();
	        	    sessionFactory = configuration.buildSessionFactory(serviceRegistry);

	        } catch (Throwable ex) {
	            System.err.println("Failed to create sessionFactory object: " + ex.getMessage());
	            throw new ExceptionInInitializerError(ex);
	        }
	        
	        return sessionFactory;
	        
	        
	}
	
	public static void load(Session currentSession){
		 for(Entry<String, Long> product : productMap.entrySet()){
         	Product entry = (Product) currentSession.get(Product.class, product.getValue());
         	System.out.print("Id: " + entry.getId());
             System.out.print(", productId " + entry.getProductId());
             System.out.print(", tests " + entry.getTests());
             for(Test test : entry.getTests()){
             	System.out.println(", testlocation: " + test.getTestlocations());
             }
         }
	}
	
	public static void main(String ...arg){
		createSF();
		Scanner reader = new Scanner(System.in);
        Session session1 = sessionFactory.openSession();
        Transaction tx1 = session1.beginTransaction();
        Session session2 = sessionFactory.openSession();
        Transaction tx2 = session2.beginTransaction();
        Session currentSession = session1;
        Transaction currentTx = tx1;
        int current = 1;
        

        while (true) {
            sleepMillis(100);
            System.out.print("[" + current + ". session] Enter command: ");
            String command = reader.nextLine();
            if (command.equals("list")) {
                List<Product> products = currentSession.createQuery("FROM Product").list();
                for (Product entry : products) {
                	productMap.put(entry.getProductId(), entry.getId());
                   
                    System.out.print("Id: " + entry.getId());
                    System.out.print(", productId " + entry.getProductId());
                    System.out.print(", tests " + entry.getTests());
                    for(Test test : entry.getTests()){
                    	System.out.println(", testlocation: " + test.getTestlocations());
                    }
                    
                }
                
                load(currentSession);
                
               
            } else if (command.equals("add")) {
            }else if (command.equals("clear")) {
            	currentSession.clear();
            } else if (command.equals("load")) {
            	load(currentSession);
            } else if (command.equals("delete")) {
               
            } else if (command.equals("close")) {
                currentTx.commit();
                currentSession.close();
            } else if (command.equals("open")) {
                if (current == 1) {
                    session1 = sessionFactory.openSession();
                    tx1 = session1.beginTransaction();
                    currentSession = session1;
                    currentTx = tx1;
                } else {
                    session2 = sessionFactory.openSession();
                    tx2 = session2.beginTransaction();
                    currentSession = session2;
                    currentTx = tx2;
                }
            } else if (command.equals("help")) {
                System.out.println("help         this menu");
                System.out.println("list         list all employees");
                System.out.println("add          add an employee");
                System.out.println("delete       delete and employee");
                System.out.println("open         open session and begin transaction");
                System.out.println("close        commit transaction and close session");
                System.out.println("change       change between two sessions");
                System.out.println("exit         exit");
            } else if (command.equals("exit")) {
                if (!tx1.wasCommitted()) {
                    tx1.commit();
                    session1.close();
                }
                if (!tx2.wasCommitted()) {
                    tx2.commit();
                    session2.close();
                }
                sessionFactory.close();
                break;
            } else if (command.equals("change")) {
                if (currentSession.equals(session1)) {
                    currentSession = session2;
                    currentTx = tx2;
                    current = 2;
                } else {
                    currentSession = session1;
                    currentTx = tx1;
                    current = 1;
                }
            } else {
                System.out.println("Command not found. Use help.");
            }
        }
	}
	
	

}
