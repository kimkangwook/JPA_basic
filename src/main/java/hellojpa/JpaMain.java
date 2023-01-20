package hellojpa;

import org.hibernate.Hibernate;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        try {

            Member member = new Member();
            member.setUsername("member1");
            member.setHomeAddress(new Address("homeCity","street","10000"));

            member.getFavoriteFoods().add("치킨");
            member.getFavoriteFoods().add("족발");
            member.getFavoriteFoods().add("피자");

            Address ad1 = new Address("old1", "street", "10000");
            member.getAddressHistory().add(new AddressEntity("old1", "street", "10000"));
            member.getAddressHistory().add(new AddressEntity("old2", "street", "10000"));
            em.persist(member);

            em.flush();
            em.clear();

            Member findMember = em.find(Member.class, member.getId());
            System.out.println("===============1===================");
            System.out.println(findMember.getFavoriteFoods().getClass());
            findMember.getFavoriteFoods().add("보쌈");
            System.out.println(findMember.getFavoriteFoods().getClass());
            System.out.println("===============2===================");
            System.out.println(findMember.getAddressHistory().getClass());
            System.out.println(findMember.getAddressHistory().getClass());
            System.out.println("===============3===================");
            findMember.getFavoriteFoods().remove("족발");
            System.out.println("===============4====================");
            findMember.getAddressHistory().remove(ad1);
            System.out.println("===============5====================");
//            Set<String> favoriteFoods = findMember.getFavoriteFoods();
//            System.out.println("favoriteFoods = " + favoriteFoods.getClass());
//            for (String favoriteFood : favoriteFoods) {
//                System.out.println(favoriteFood);
//            }
//            List<Address> addressHistory = findMember.getAddressHistory();
//            System.out.println("addressHistory = " + addressHistory.getClass());
//            for (Address address : addressHistory) {
//                System.out.println(address.getCity());
//            }


            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback();
        }
    }

}
