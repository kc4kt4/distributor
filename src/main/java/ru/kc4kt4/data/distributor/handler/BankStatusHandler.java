package ru.kc4kt4.data.distributor.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kc4kt4.data.distributor.repository.BankDetailRepository;
import ru.kc4kt4.data.distributor.response.BankStatusResponse;

@Service
public class BankStatusHandler {

    @Autowired
    private BankDetailRepository bankDetailRepository;

    public BankStatusResponse handleRequest(String id) {
        boolean isExist = bankDetailRepository.existsById(id);
        BankStatusResponse response = new BankStatusResponse();
        response.setBankExist(isExist);
        return response;
    }
}
