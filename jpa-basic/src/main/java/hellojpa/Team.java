package hellojpa;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Team {

    @Id
    @GeneratedValue
    @Column(name = "TEAM_ID")
    private Long id;

    private String name;

    /*하나의 팀에 다수의 멤버가 있을때 다대일 관계
    * rdb 관점에선 fk하나로 양쪽에서 조인할수있지만 객체지향에서는
    * 서로 참조할것을 가지고 있어야 한다. */
    @OneToMany(mappedBy = "team")
    private List<Member> members=new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Member> getMembers() {
        return members;
    }

    public void setMembers(List<Member> members) {
        this.members = members;
    }
}
