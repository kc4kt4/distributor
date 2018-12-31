package ru.kc4kt4.data.distributor.dto;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.Data;

@Data
@JacksonXmlRootElement(localName = "biks")
public final class Biks {

    @JacksonXmlProperty(localName = "version", isAttribute = true)
    private String version;

    @JacksonXmlElementWrapper(localName = "bik", useWrapping = false)
    private Bik[] bik;
}
