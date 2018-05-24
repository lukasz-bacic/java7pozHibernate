package sda.pl;

import org.hibernate.Session;

public class App {

    public static void main(String[] args){

        try {
            Session session = HibernateUtil.openSession();
        } catch (Exception e) {

        }

    }
}
