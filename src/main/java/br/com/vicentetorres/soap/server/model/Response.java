package br.com.vicentetorres.soap.server.model;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {"operacaoReturn"})
@XmlRootElement(name = "RegisterResponse")
public class Response {

    private String operacaoReturn;

    public String getOperacaoReturn() {
        return operacaoReturn;
    }

    public void setOperacaoReturn(String operacaoReturn) {
        this.operacaoReturn = operacaoReturn;
    }
}
