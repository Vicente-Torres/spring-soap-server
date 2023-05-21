package br.com.vicentetorres.soap.server.model;

import jakarta.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "RegisterRequest")
public class Request {

    @XmlElement(required = true, namespace = "http://example.org/myservice")
    private String param;

    public String getParam() {
        return param;
    }
}
