package com.example.demo.demo;

import com.example.demo.entity.Instructor;
import com.example.demo.entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class DeleteInstructorDemo {

    public static void main(String[] args) {
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class)
                .buildSessionFactory();

        Session session = factory.getCurrentSession();

        try {
            session.beginTransaction();

            int id = 1;
            System.out.println("Founding Instructor");

            Instructor instructor = session.get(Instructor.class, id);

            if (instructor != null) {
                System.out.println("Instructor founded: " + instructor);

                session.delete(instructor);

                session.getTransaction().commit();

                System.out.println("Instructor deleted...");

            } else {
                System.out.println("Instructor not found");
            }


        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
            factory.close();
        }


    }
}
