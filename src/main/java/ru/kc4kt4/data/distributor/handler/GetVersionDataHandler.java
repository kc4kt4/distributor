package ru.kc4kt4.data.distributor.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kc4kt4.data.distributor.response.UpdateBaseResponse;
import ru.kc4kt4.data.distributor.entity.DataVersion;
import ru.kc4kt4.data.distributor.feign.BankDetailClient;
import ru.kc4kt4.data.distributor.repository.DataVersionRepository;

import java.util.Optional;

@Service
public class GetVersionDataHandler {

    @Autowired
    private BankDetailClient bankDetailClient;
    @Autowired
    private DataVersionRepository dataVersionRepository;
    
    public UpdateBaseResponse handleRequest(String id) {
        String actualVersion = bankDetailClient.getVersion();
        Optional<DataVersion> dataVersionOptional = dataVersionRepository.findById(id);
        String version = dataVersionOptional.isPresent()
                ? dataVersionOptional.get().getVersion()
                : String.format("База данных с указанным id - %s не существует", id);
        return new UpdateBaseResponse(version, actualVersion);
    }
}
