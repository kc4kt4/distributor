package ru.kc4kt4.data.distributor.feign;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

public interface BankDetailClient {
    @RequestMapping(method = RequestMethod.GET, value = "/version")
    String getVersion();
}
