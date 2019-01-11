package ru.kc4kt4.data.distributor.test.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.test.annotation.DirtiesContext;
import ru.kc4kt4.data.distributor.test.AbstractTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@DirtiesContext
public abstract class AbstractControllerTest extends AbstractTest {
    protected static final String HOST_PATH = "http://localhost:";

    @Autowired
    protected TestRestTemplate restTemplate;

    @Value("${server.port}")
    protected int port;

    protected String createURLWithPort(String uri) {
        return HOST_PATH + port + uri;
    }

    protected void assertResponse(ResponseEntity<?> responseEntity) {
        assertNotNull(responseEntity);
        assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
        assertNotNull(responseEntity.getBody());
    }

    public HttpEntity<?> createRequestEntity(Object o) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        return new HttpEntity<>(o, headers);
    }
}
