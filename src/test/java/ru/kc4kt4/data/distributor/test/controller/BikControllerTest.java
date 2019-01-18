package ru.kc4kt4.data.distributor.test.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import ru.kc4kt4.data.distributor.entity.BankDetail;
import ru.kc4kt4.data.distributor.repository.BankDetailRepository;
import ru.kc4kt4.data.distributor.response.BankInfoResponse;
import ru.kc4kt4.data.distributor.response.UpdateBaseResponse;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class BikControllerTest extends AbstractControllerTest {
    private static final String PATH_TO_CREATE_BANK_INFO_DATA = "classpath:sql/CreateBankInfo.sql";
    private static final String PATH_TO_SCRIPT_TRUNCATE_TABLE = "classpath:sql/CleanBankInfoTable.sql";
    private static final String BIK = "046577909";
    private static final String BASE_CONTROLLER_URL = "/biks";
    private static final String SLASH = "/";
    private static final String BASE_UPDATE_SUCCESS = "База данных БИКов обновлена";
    private static final String BASE_UPDATE_NO_NEED = "База данных БИКов не требует обновления";
    private static final String BASE_NAME = "bank_detail";
    
    @Autowired
    private BankDetailRepository bankDetailRepository;
    @Autowired
    private ObjectMapper mapper;

    @Test
    @Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD, scripts = PATH_TO_SCRIPT_TRUNCATE_TABLE)
    public void updateDataTest() {
        ResponseEntity<UpdateBaseResponse> firstResponse = updateBikExchange();

        assertResponse(firstResponse);
        assertEquals(BASE_UPDATE_SUCCESS, firstResponse.getBody().getVersionStatus());
        assertEquals(BASE_NAME, firstResponse.getBody().getBaseName());

        ResponseEntity<UpdateBaseResponse> secondResponse = updateBikExchange();

        assertResponse(secondResponse);
        assertEquals(BASE_UPDATE_NO_NEED, secondResponse.getBody().getVersionStatus());
        assertEquals(BASE_NAME, secondResponse.getBody().getBaseName());
    }

    private ResponseEntity<UpdateBaseResponse> updateBikExchange() {
        return restTemplate
                .exchange(createURLWithPort(BASE_CONTROLLER_URL + SLASH),
                        HttpMethod.POST,
                        createRequestEntity(null),
                        UpdateBaseResponse.class);
    }

    @Test
    @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = PATH_TO_CREATE_BANK_INFO_DATA)
    @Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD, scripts = PATH_TO_SCRIPT_TRUNCATE_TABLE)
    public void getBankByIdTest() {
        ResponseEntity<BankInfoResponse> response = restTemplate
                .exchange(createURLWithPort(BASE_CONTROLLER_URL + SLASH + BIK),
                        HttpMethod.GET,
                        createRequestEntity(null),
                        BankInfoResponse.class);

        assertResponse(response);
        BankDetail findedBankInfo = bankDetailRepository.findById(BIK).get();
        assertEquals(findedBankInfo, mapper.convertValue(response.getBody().getBankInfo(), BankDetail.class));
    }
}
