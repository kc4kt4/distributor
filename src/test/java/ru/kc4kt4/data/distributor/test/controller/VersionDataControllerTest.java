package ru.kc4kt4.data.distributor.test.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import ru.kc4kt4.data.distributor.response.UpdateBaseResponse;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class VersionDataControllerTest extends AbstractControllerTest {
    private static final String DB_NAME = "test";
    private static final String DB_VERSION = "1.15";
    private static final String VERSION_CHECK_URL = "/version/";
    private static final String PATH_TO_SCRIPT_ADD_TEST_DB_VERSION = "classpath:sql/AddTestDbVersion.sql";

    @Test
    @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = PATH_TO_SCRIPT_ADD_TEST_DB_VERSION)
    public void checkVersionTest() {

        ResponseEntity<UpdateBaseResponse> response = restTemplate
                .exchange(createURLWithPort(VERSION_CHECK_URL + DB_NAME),
                HttpMethod.GET,
                createRequestEntity(null),
                UpdateBaseResponse.class);

        assertResponse(response);
        assertEquals(response.getBody().getVersion(), DB_VERSION);
    }
}
