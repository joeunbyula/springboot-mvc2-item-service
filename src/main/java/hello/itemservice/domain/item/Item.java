package hello.itemservice.domain.item;

import lombok.Data;
import org.hibernate.validator.constraints.Range;
import org.hibernate.validator.constraints.ScriptAssert;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data

//@ScriptAssert(lang = "javascript", script = "_this.price * _this.quantity >= 10000", message = "가격과 수량의 곱이 10000이 넘게 설정해주세요.")
/*
    JVM 이 지원하는 스크립트 엔진의 차이 때문에 오류가 발생
    jdk8 ~ jdk14의 JVM 상에서 사용되는Nashorn 엔진은 javascript를 지원하는데,
    jdk14 이후 버전부터는 javascript가 지원되지 않는 GraalVM 을 사용
    이런 복합 object는 로직 단에서 체크하는게 낫다!
 */
public class Item {

//    @NotNull(groups = UpdateCheck.class)
    private Long id;

//    @NotBlank(message = "상품명은 필수입니다.", groups = {SaveCheck.class, UpdateCheck.class})
    private String itemName;

//    @NotNull(message = "가격은 필수입니다.", groups = {SaveCheck.class, UpdateCheck.class})
//    @Range(min = 1000, max = 1000000)
    private Integer price;

//    @NotNull(groups = {SaveCheck.class, UpdateCheck.class})
//    @Max(value = 9999 , groups = SaveCheck.class)
    private Integer quantity;

    public Item() {
    }

    public Item(String itemName, Integer price, Integer quantity) {
        this.itemName = itemName;
        this.price = price;
        this.quantity = quantity;
    }
}
