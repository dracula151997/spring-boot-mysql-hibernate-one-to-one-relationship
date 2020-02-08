package com.example.demo;

import com.example.demo.entity.Instructor;
import com.example.demo.entity.InstructorDetail;
import com.example.demo.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication {


    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);

        SessionFactory sessionFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Student.class)
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class)
                .buildSessionFactory();

        Session session = sessionFactory.openSession();

        try {


            session.beginTransaction();

            /**
             * Add new Student and new instructor
             */
            Student student = new Student("Hassan",
                    "Mohammed",
                    "hassan@gmail.com");

            Instructor tempInst = new Instructor("Eman", "Elgendy", "eman.elgendy@example.com");

            InstructorDetail instDetail = new InstructorDetail("https://www.youtube.com", "Coding");
            tempInst.setInstructorDetail(instDetail);

            /**
             * Deleting the instructor by his/her id.
             */
            int id = 7;
            Instructor instructor = session.get(Instructor.class, id);
            System.out.println("Found instructor: " + instructor);

            if (instructor != null) {
                System.out.println("Deleting " + instructor);

                session.delete(instructor);
            }

            /**
             * Fetch the instructor from instructor id (using bi-directional relationship)
             */
            int detailId = 11;
            InstructorDetail instructorDetail = session.get(InstructorDetail.class, detailId);
            if (instructorDetail != null) {
                System.out.println("Found instructor detail: " + instructorDetail);

                System.out.println("Instructor: " + instructorDetail.getInstructor());
            }


            /**
             * Methods for save the objects (Student and instructor).
             */
            session.save(student);
            session.save(instructor);

            /**
             * Commit the change
             */
            session.getTransaction().commit();


        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            sessionFactory.close();
            session.close();
        }


    }

}
