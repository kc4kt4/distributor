package ru.kc4kt4.data.distributor.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kc4kt4.data.distributor.exception.InternalServerErrorException;
import ru.kc4kt4.data.distributor.response.UpdateBaseResponse;
import ru.kc4kt4.data.distributor.entity.DataVersion;
import ru.kc4kt4.data.distributor.feign.BankDetailClient;
import ru.kc4kt4.data.distributor.repository.DataVersionRepository;

import java.util.Optional;

@Service
@Slf4j
public class GetVersionDataHandler {

    @Autowired
    private BankDetailClient bankDetailClient;
    @Autowired
    private DataVersionRepository dataVersionRepository;
    
    public UpdateBaseResponse handleRequest(String id) {
        String actualVersion;
        String version;
        try {
            actualVersion = bankDetailClient.getVersion();
            Optional<DataVersion> dataVersionOptional = dataVersionRepository.findById(id);
            version = dataVersionOptional.isPresent()
                    ? dataVersionOptional.get().getVersion()
                    : String.format("База данных с указанным id - %s не существует", id);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new InternalServerErrorException();
        }
        return new UpdateBaseResponse(version, actualVersion);
    }
}
