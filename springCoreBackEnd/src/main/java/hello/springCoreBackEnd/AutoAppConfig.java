package hello.springCoreBackEnd;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
/*ComponentScan 어노테이션을 사용하면 Component 어노테이션이 붙은 클래스를 자동스캔하여
 스프링 빈으로 등록하는데 직접 빈을 등록하는 Configuration 어노테이션을 가지고 있는
 AppConfig클래스의 빈들도 중복등록되기 떄문에 제외한다. */
@ComponentScan(
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION,
                classes = Configuration.class)
)
public class AutoAppConfig {
}
