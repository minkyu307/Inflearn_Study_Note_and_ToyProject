package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();

        /*트랜잭션 생성 및 시작*/
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        /*트랜잭션에 쿼리 생성하여 보내고 커밋 성공하면 엔티티매니저 닫음*/
        try {
            /*저장*/
            /*Member member = new Member();
            member.setId(2L);
            member.setName("hello2");
            em.persist(member);
            tx.commit();*/

            /*조회*/
            /*Member findMember=em.find(Member.class, 2L);
            System.out.println("findMember = " + findMember.getId());
            System.out.println("findMember = " + findMember.getName());
            tx.commit();*/

            /*수정*/
            /*데이터를 불러온 후에 findMember객체에 저장하고 그 객체에서만 set으로 바꾸면
            * 따로 update쿼리를 사용하지 않아도 자동으로 db에 업데이트 된다.*/
            Member findMember = em.find(Member.class,1L);
            findMember.setName("helloUpdate");
            tx.commit();

            /*삭제*/
            /*Member findMember=em.find(Member.class, 2L);
            em.remove(findMember);
            tx.commit();*/
        } catch (Exception e) {
            tx.rollback();
        } finally{
            em.close();
        }

        emf.close();
    }
}
