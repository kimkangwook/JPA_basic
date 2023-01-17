package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.transaction.Transactional;

public class MultiThread extends Thread {

    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

    public EntityManager em = emf.createEntityManager();

    private Member member;

    public void setMember(Member member) {
        this.member = member;
    }

    @Override
    @Transactional(Transactional.TxType.REQUIRED)
    public void run() {
        System.out.println("gg");
        em.persist(member);
        em.flush();
    }

}
