package hellojpa;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)//RDB에서 객체상속을 구현할때 상속받은 테이블을 모두 만들고
                                               //id를 동일하게 가지는 전략
@DiscriminatorColumn//상속을하는 부모타입에 하위타입 테이터의 타입명을 기입
public class Item {

    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private  int price;

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

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
