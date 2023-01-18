package hellojpa;

import jdk.swing.interop.SwingInterOpUtils;
import org.hibernate.Hibernate;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class JpaMain {
    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            ChildParam child = new ChildParam();
            child.setAge(15);
            child.setName("김강욱");
            ChildParam child2 = new ChildParam();
            child2.setAge(18);
            child2.setName("김강욱2");
            em.persist(child);
            em.persist(child2);


            System.out.println("=================1=============");
            ArrayList<ChildParam> ar = new ArrayList<>();
            ar.add(child);
            ar.add(child2);

            System.out.println("==================2============");

            ParentEntity parent = new ParentEntity();
            parent.setChilds(ar);
            parent.setMbr("{\"ITEM\":\"MUSIC\",\"ITEM\":\"BOOK\"}");
//            parent.setMbr("{\"ITEM\":\"BOOK\"}");
//            parent.setMbr("{\"ITEM\":\"MOVIE\"}");
            System.out.println("========3==========");
            em.persist(parent);
            System.out.println("==============4================");

            em.flush();
            em.clear();
            System.out.println("===============5================");
            ParentEntity parent1 = em.find(ParentEntity.class, parent.getId());
            System.out.println("result : "+ (parent==parent1));
            System.out.println("parent1.getChilds().getClass() = " + parent1.getChilds().getClass());
            List<ChildParam> childs = parent1.getChilds();
            for (ChildParam childParam : childs) {
                System.out.println("childParam.NAME = " + childParam.getName());
                System.out.println("childParam.AGE = " + childParam.getAge());
            }
            String mbr = parent1.getMbr();
            System.out.println("mbr = " + mbr);

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
        emf.close();
    }
}
