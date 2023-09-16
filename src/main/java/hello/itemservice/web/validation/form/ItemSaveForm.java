package hello.itemservice.web.validation.form;

import hello.itemservice.domain.item.SaveCheck;
import hello.itemservice.domain.item.UpdateCheck;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class ItemSaveForm {

    @NotBlank(message = "등록 시 상품명은 필수입니다.")
    private String itemName;

    @NotNull(message = "등록 시 가격은 필수입니다.")
    @Range(min = 1000, max = 1000000)
    private Integer price;

    @NotNull
    @Max(value = 9999)
    private Integer quantity;

}
