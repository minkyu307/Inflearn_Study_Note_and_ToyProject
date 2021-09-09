package hellojpa;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.locks.Lock;

@Entity
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "MEMBER_ID")
    private Long id;

    @Column(name = "USERNAME")
    private String username;

    /*멤버 입장에서 여러명의 멤버가 하나의 팀에 소속되어있기 때문에 멤버가 팀의 아이디를 FK로 가지고있다. */
    @ManyToOne(fetch = FetchType.LAZY) //지연로딩
    //@ManyToOne(fetch = FetchType.EAGER)  //즉시로딩
    @JoinColumn(name = "TEAM_ID")
    private Team team;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "LOCKER_ID")
    private Locker locker;

    //    임베디드타입
    @Embedded
    private Address homeAddress;

    //    값 타입 컬렉션
    @ElementCollection
    @CollectionTable(name = "FAVORITE_FOOD", joinColumns =
        @JoinColumn(name = "MEMBER_ID"))
    @Column(name = "FOOD_NAME")
    private Set<String> favoriteFoods=  new HashSet<>();

    @ElementCollection
    @CollectionTable(name = "ADDRESS", joinColumns =
        @JoinColumn(name = "MEMBER_ID"))
    private List<Address> addressHistory = new ArrayList<>();


    public Member() {

    }

    //Getter, Setter …
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Team getTeam() {
        return team;
    }

    public void changeTeam(Team team) {
        this.team = team;
        team.getMembers().add(this);
    }
}
