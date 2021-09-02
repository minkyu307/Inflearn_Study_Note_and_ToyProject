package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();

        /*트랜잭션 생성 및 시작*/
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        /*트랜잭션에 쿼리 생성하여 보내고 커밋 성공하면 엔티티매니저 닫음*/
        try {
            /*ManyToOne 연관관계 저장*/
           /* Team team = new Team();
            team.setName("TeamA");
            em.persist(team);

            Member member = new Member();
            member.setUsername("member1");
            *//*팀 객체를 먼저 만들고 영속하고 그 후에 멤버 mapedby로 연관된 멤버 객체에
            * 팀 객체를 연결시켜야한다. 객체지향적으로 양쪽 모두 연결시켜주는게 좋다*//*
            member.changeTeam(team);
            em.persist(member);*/

            /*이 팀쪽에서 멤버를 추가하여 연결하는 문장을 멤버의 setTeam메서드 안으로 넘기면 편하다*/
//            team.getMembers().add(member);

            /*영속성 컨테스트 1차캐시에 저장된 쿼리를 commit 없이 먼저 날리고 비운다*/
            /*em.flush();
            em.clear();*/

            /*조회*/
            /*Member findMember = em.find(Member.class, member.getId());
            List<Member> members = findMember.getTeam().getMembers();
            for (Member m : members) {
                System.out.println("m.getUsername() = " + m.getUsername());
            }*/

            /*Team findTeam = em.find(Team.class, team.getId());
            List<Member> members = findTeam.getMembers();
            for (Member m : members) {
                System.out.println("m.getUsername() = " + m.getUsername());
            }*/

            /*수정*/


            /*삭제*/

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally{
            em.close();
        }

        emf.close();
    }
}
