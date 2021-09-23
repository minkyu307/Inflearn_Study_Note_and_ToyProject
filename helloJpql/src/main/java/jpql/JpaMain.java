package jpql;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.Collection;
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

            Team team = new Team();
            team.setName("TeamA");
            em.persist(team);

            Member member = new Member();
            member.setUsername("member1");
            member.setAge(10);
            member.setTeam(team);
            member.setType(MemberType.ADMIN);
            em.persist(member);

            em.flush();
            em.clear();

            //namedQuery
            List<Member> members = em.createNamedQuery("Member.findByUsername", Member.class).setParameter("username", "member1").getResultList();
            for (Member member1 : members) {
                System.out.println("member1 = " + member1);
            }


            //페치 조인 - 지연로딩을 무시하고 처음 sql을 보낼때 연관된 테이블도 한번에 불러옴. 
            /*Team teamA = new Team();
            teamA.setName("A");
            em.persist(teamA);

            Team teamB = new Team();
            teamB.setName("B");
            em.persist(teamB);

            Member memberA = new Member();
            memberA.setUsername("memberA");
            memberA.setTeam(teamA);
            em.persist(memberA);

            Member memberB = new Member();
            memberB.setUsername("memberB");
            memberB.setTeam(teamA);
            em.persist(memberB);

            Member memberC = new Member();
            memberC.setUsername("memberC");
            memberC.setTeam(teamB);
            em.persist(memberC);

            em.flush();
            em.clear();

            String query = "select m from Member m join fetch m.team";
            List<Member> result = em.createQuery(query, Member.class).getResultList();

            for (Member member : result) {
                System.out.println("member.getUsername() = " + member.getUsername());
                System.out.println("member.getTeam().getName() = " + member.getTeam().getName());
            }*/



            //조인
            /*String query = "select m from Member m inner join m.team t";
            List<Member> result = em.createQuery(query, Member.class).getResultList();*/

            //타입 조회
            /*String query = "select m.username from Member m where m.type = jpql.MemberType.ADMIN";
            List<Object> result = em.createQuery(query,Object.class).getResultList();
            for (Object o :
                    result) {
                System.out.println("o = " + o);
            }*/

            //조건식
            /*String query = "select " +
                    "case when m.age <= 10 then 'student'" +
                    "     when m.age >= 60 then 'oldman'" +
                    "     else 'normal' end " +
                    "from Member m";
            List<String> result = em.createQuery(query, String.class).getResultList();

            for (String s : result) {
                System.out.println("s = " + s);
            }*/


            //파라미터 바인딩 쿼리
            /*List<Member> result = em.createQuery("select m from Member m where m.username =?1",
                    Member.class).setParameter("1","member1").getResultList();*/

            //select 엔티티 조인 프로젝션
            /*List<Team> result = em.createQuery(
                    "select t from Member m join m.team t",
                    Team.class).getResultList();
*/
            //select 값이 다양할때 해당 값만을 속성으로 가지는 클래스를 새로 생성
            /*List<MemberDTO> result1 = em.createQuery(
                    "select new jpql.MemberDTO(m.username, m.age) from Member m",
                    MemberDTO.class).getResultList();

            for (MemberDTO m :
                    result1) {
                System.out.println("m.getUsername() = " + m.getUsername());
                System.out.println("m.getAge() = " + m.getAge());
            }*/

            //페이징
            /*List<Member> resultList = em.createQuery(
                    "select m from Member m order by m.age desc", Member.class
            ).setFirstResult(0).setMaxResults(10).getResultList();
            System.out.println("resultList = " + resultList.size());
            for (Member m :
                    resultList) {
                System.out.println("m = " + m);
            }*/

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally{
            em.close();
        }

        emf.close();
    }
}
