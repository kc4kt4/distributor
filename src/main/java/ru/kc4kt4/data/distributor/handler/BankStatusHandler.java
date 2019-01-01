package ru.kc4kt4.data.distributor.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kc4kt4.data.distributor.exception.InternalServerErrorException;
import ru.kc4kt4.data.distributor.repository.BankDetailRepository;
import ru.kc4kt4.data.distributor.response.BankStatusResponse;

@Service
@Slf4j
public class BankStatusHandler {

    @Autowired
    private BankDetailRepository bankDetailRepository;

    public BankStatusResponse handleRequest(String id) {
        boolean isExist;
        try {
            isExist = bankDetailRepository.existsById(id);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new InternalServerErrorException();
        }
        BankStatusResponse response = new BankStatusResponse();
        response.setBankExist(isExist);
        return response;
    }
}
