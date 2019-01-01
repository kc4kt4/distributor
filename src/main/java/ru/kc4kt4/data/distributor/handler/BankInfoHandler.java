package ru.kc4kt4.data.distributor.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kc4kt4.data.distributor.dto.Bik;
import ru.kc4kt4.data.distributor.entity.BankDetail;
import ru.kc4kt4.data.distributor.exception.InternalServerErrorException;
import ru.kc4kt4.data.distributor.exception.NotFoundException;
import ru.kc4kt4.data.distributor.repository.BankDetailRepository;
import ru.kc4kt4.data.distributor.response.BankInfoResponse;

import java.util.Optional;

@Service
@Slf4j
public class BankInfoHandler {

    @Autowired
    private BankDetailRepository bankDetailRepository;
    @Autowired
    private ObjectMapper mapper;

    public BankInfoResponse handleRequest(String id) {
        Optional<BankDetail> bankInfo = bankDetailRepository.findById(id);
        Bik bik = null;
        if (bankInfo.isPresent()) {
            try {
                bik = mapper.convertValue(bankInfo, Bik.class);
            } catch (Exception e) {
                log.error(e.getMessage(), e);
                throw new InternalServerErrorException(String.format("Ошибка конвертации объекта %s в объект Bik.class",
                        bankInfo.toString()));
            }
        } else {
            throw new NotFoundException(String.format("По данному id - %s банк не найден", id));
        }
        BankInfoResponse response = new BankInfoResponse();
        response.setBankInfo(bik);
        return response;
    }
}
