package ru.kc4kt4.data.distributor.response;

import lombok.Data;
import ru.kc4kt4.data.distributor.dto.Bik;

@Data
public class BankInfoResponse {

    private Bik bankInfo;
}
