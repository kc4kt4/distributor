package ru.kc4kt4.data.distributor.dto;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.Data;

@Data
@JacksonXmlRootElement(localName = "bik")
public class Bik {
    @JacksonXmlProperty(localName = "bik", isAttribute = true)
    private String bik;
    @JacksonXmlProperty(localName = "ks", isAttribute = true)
    private String ks;
    @JacksonXmlProperty(localName = "name", isAttribute = true)
    private String name;
    @JacksonXmlProperty(localName = "namemini", isAttribute = true)
    private String nameMini;
    @JacksonXmlProperty(localName = "index", isAttribute = true)
    private String index;
    @JacksonXmlProperty(localName = "city", isAttribute = true)
    private String city;
    @JacksonXmlProperty(localName = "address", isAttribute = true)
    private String address;
    @JacksonXmlProperty(localName = "phone", isAttribute = true)
    private String phone;
    @JacksonXmlProperty(localName = "okato", isAttribute = true)
    private String okato;
    @JacksonXmlProperty(localName = "okpo", isAttribute = true)
    private String okpo;
    @JacksonXmlProperty(localName = "regnum", isAttribute = true)
    private String regNum;
    @JacksonXmlProperty(localName = "srok", isAttribute = true)
    private String srok;
    @JacksonXmlProperty(localName = "dateadd", isAttribute = true)
    private String dateAdd;
    @JacksonXmlProperty(localName = "datechange", isAttribute = true)
    private String dateChange;
}
