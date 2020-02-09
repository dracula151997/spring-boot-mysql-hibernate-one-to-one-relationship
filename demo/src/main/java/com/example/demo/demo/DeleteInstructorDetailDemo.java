package com.example.demo.demo;

import com.example.demo.entity.Instructor;
import com.example.demo.entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class DeleteInstructorDetailDemo {
    public static void main(String[] args) {
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class)
                .buildSessionFactory();

        Session session = factory.getCurrentSession();


        try {
            int id = 2;
            session.beginTransaction();


            InstructorDetail detail = session.get(InstructorDetail.class, id);

            System.out.println("Instructor detail: " + detail);

            detail.getInstructor().setInstructorDetail(null);

            session.delete(detail);

            session.getTransaction().commit();

            System.out.println("--------------Done------------");


        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
            factory.close();
        }


    }
}
