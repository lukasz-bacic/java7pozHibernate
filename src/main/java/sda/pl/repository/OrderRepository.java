package sda.pl.repository;

import org.hibernate.Session;
import sda.pl.HibernateUtil;
import sda.pl.domain.Order;

public class OrderRepository {

    public static boolean saveOrder(Order order){
        Session session = null;
        try {
            session = HibernateUtil.openSession();
            session.save(order);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }finally {
            if(session != null && session.isOpen()){
                session.close();
            }
        }


    }
}
