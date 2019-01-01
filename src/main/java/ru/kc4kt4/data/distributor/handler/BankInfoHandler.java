package ru.kc4kt4.data.distributor.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kc4kt4.data.distributor.dto.Bik;
import ru.kc4kt4.data.distributor.entity.BankDetail;
import ru.kc4kt4.data.distributor.exception.DataNotFoundException;
import ru.kc4kt4.data.distributor.repository.BankDetailRepository;
import ru.kc4kt4.data.distributor.response.BankInfoResponse;

import java.util.Optional;

@Service
public class BankInfoHandler {

    @Autowired
    private BankDetailRepository bankDetailRepository;
    @Autowired
    private ObjectMapper mapper;

    public BankInfoResponse handleRequest(String id) {
        Optional<BankDetail> bankInfo = bankDetailRepository.findById(id);
        Bik bik = null;
        if (bankInfo.isPresent()) {
            bik = mapper.convertValue(bankInfo, Bik.class);
        } else {
            throw new DataNotFoundException(String.format("По данному id - %s банк не найден", id));
        }
        BankInfoResponse response = new BankInfoResponse();
        response.setBankInfo(bik);
        return response;
    }
}
