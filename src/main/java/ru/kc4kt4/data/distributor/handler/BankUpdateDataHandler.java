package ru.kc4kt4.data.distributor.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import ru.kc4kt4.data.distributor.dto.Biks;
import ru.kc4kt4.data.distributor.entity.BankDetail;
import ru.kc4kt4.data.distributor.entity.DataVersion;
import ru.kc4kt4.data.distributor.exception.ConnectionException;
import ru.kc4kt4.data.distributor.exception.InternalServerErrorException;
import ru.kc4kt4.data.distributor.feign.BankDetailClient;
import ru.kc4kt4.data.distributor.repository.BankDetailRepository;
import ru.kc4kt4.data.distributor.repository.DataVersionRepository;
import ru.kc4kt4.data.distributor.response.UpdateBaseResponse;

import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;

@Service
@Slf4j
public class BankUpdateDataHandler {

    @Value("${distribution.bik.host}")
    private String host;

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private DataVersionRepository dataVersionRepository;
    @Autowired
    private BankDetailRepository bankDetailRepository;
    @Autowired
    private ObjectMapper mapper;
    @Autowired
    private BankDetailClient bankDetailClient;

    @Transactional
    public UpdateBaseResponse handleRequest() {
        Biks biks = restTemplate.getForObject(new StringBuilder()
                .append(host)
                .append("/base.xml")
                .toString(), Biks.class);

        if (biks == null || Arrays.asList(biks.getBik()).isEmpty()) {
            throw new ConnectionException("Не удается получить данные о актуальной базе");
        }

        Optional<DataVersion> dataVersionOptional = dataVersionRepository.findById("bik");

        String versionStatus;
        if (!dataVersionOptional.isPresent() || !Objects.equals(dataVersionOptional.get().getVersion(), biks.getVersion())) {
            try {
                DataVersion dataVersion = createAndUpdateEntity(biks.getVersion());
                BankDetail[] bankDetails = mapper.convertValue(biks.getBik(), BankDetail[].class);
                log.debug(String.format("Сохраняем актуализированные данные в базу данных %s",
                        dataVersion.getDataName()));
                bankDetailRepository.saveAll(Arrays.asList(bankDetails));
                log.debug(String.format("Данные в бахе данных %s успешно обновлены",
                        dataVersion.getDataName()));
                versionStatus = "обновлена";
            } catch (Exception e) {
                log.error(e.getMessage(), e);
                throw new InternalServerErrorException();
            }
        } else {
            versionStatus = "не требует обновления";
        }

        UpdateBaseResponse response = new UpdateBaseResponse();
        response.setBaseName("bank_detail");
        response.setVersion(biks.getVersion());
        response.setVersionStatus(new StringBuilder()
                .append("База данных БИКов")
                .append(" ")
                .append(versionStatus)
                .toString());
        return response;
    }

    private DataVersion createAndUpdateEntity(String version) {
        DataVersion dataVersion = new DataVersion();
        dataVersion.setDataName("bik");
        dataVersion.setVersion(version);
        log.debug(String.format("Обновление версии базы данных %s",
                dataVersion.getDataName()));
        DataVersion entity = dataVersionRepository.save(dataVersion);
        log.debug(String.format("Версия базы данных %s успено обновлена - %s",
                dataVersion.getDataName(),
                dataVersion.getVersion()));
        return entity;
    }
}
