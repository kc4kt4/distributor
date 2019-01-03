package ru.kc4kt4.data.distributor.response;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class BankStatusResponse {

    @ApiModelProperty(value = "Статсу существования банка по входящему БИКу")
    private boolean isBankExist;
}
