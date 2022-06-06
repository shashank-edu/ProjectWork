// package com.webapp.springbootweb.Users.Hibernate;

// import javax.transaction.Transactional;

// import com.webapp.springbootweb.Users.entity.User;

// import org.hibernate.HibernateException;
// import org.hibernate.Session;
// import org.hibernate.SessionFactory;
// import org.hibernate.cfg.Configuration;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.data.jpa.repository.Modifying;
// import org.springframework.data.jpa.repository.Query;
// import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


// public class JpaHibernate {

//     @Autowired 
//     private static BCryptPasswordEncoder bCryptPasswordEncoder;

//     public static void main(String[] args) {
     
        
        
//         SessionFactory sf= new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(User.class).buildSessionFactory();
//         Session session =sf.getCurrentSession();

//         // ACID 
        
//         try{
//             // creating new instance
//             User user = new User();
//             user.setEmail("normal@gmail.com");
//             user.setPassword(bCryptPasswordEncoder.encode("password"));
//             user.setRole("USER");
//             user.setUsername("user");
//             user.setUserId(1);
            
//             User user1 = new User();
//             user1.setEmail("ADMIN@gmail.com");
//             user1.setPassword(bCryptPasswordEncoder.encode("password"));
//             user1.setRole("ADMIN");
//             user1.setUsername("admin");
//             user1.setUserId(2);
            


//             // start a transaction
//             session.beginTransaction();
            

          
//             session.save(user);
//             session.save(user1);
           

//              // commit transaction
//             session.getTransaction().commit();
//             System.out.println("Done!");

//         }catch (HibernateException e) {
//             e.printStackTrace();
//             session.getTransaction().rollback();
//         }finally{
//             session.close();
//         }

       

//     }
    
// }


