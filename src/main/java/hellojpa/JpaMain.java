package hellojpa;

import javax.persistence.*;
import javax.transaction.Transactional;

public class JpaMain {
    public static void main(String[] args) {
        MultiThread mtt = new MultiThread();
        String[] str={"김강욱","정원찬","김재원","장태원","임재현"};
        EntityTransaction tx = mtt.em.getTransaction();
        tx.begin();
        try {
            for(int i=0;i<5;i++) {
                Member member = new Member();
                member.setId(i + 1L);
                member.setName(str[i]);
                mtt.setMember(member);
                mtt.start();
                System.out.println("mlt.em.getClass() = " +  mtt.em.getClass());
                Member findMember = mtt.em.find(Member.class, member.getId());
                System.out.println("findMember = " + findMember.getName());
            }
            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback();
        }
    }
}
