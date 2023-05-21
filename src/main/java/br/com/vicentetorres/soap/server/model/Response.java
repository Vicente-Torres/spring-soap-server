package br.com.vicentetorres.soap.server.model;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "RegisterResponse")
public class Response {

    private String operacaoReturn;

    public void setOperacaoReturn(String operacaoReturn) {
        this.operacaoReturn = operacaoReturn;
    }
}
