package ru.kc4kt4.data.distributor.response;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import ru.kc4kt4.data.distributor.dto.Bik;

@Data
public class BankInfoResponse {

    @ApiModelProperty(value = "Данные о банке")
    private Bik bankInfo;
}
