package br.com.vicentetorres.soap.server.endpoint;

import br.com.vicentetorres.soap.server.model.Request;
import br.com.vicentetorres.soap.server.model.Response;
import br.com.vicentetorres.soap.server.service.ViolationRegisterService;
import jakarta.xml.bind.JAXBElement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
public class ViolationRegisterEndpoint {

    private static final String NAMESPACE_URI = "http://example.org/myservice";

    private final ViolationRegisterService violationRegisterService;

    @Autowired
    public ViolationRegisterEndpoint(ViolationRegisterService violationRegisterService) {
        this.violationRegisterService = violationRegisterService;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "RegisterRequest")
    @ResponsePayload
    public Response processRequest(@RequestPayload JAXBElement<Request> request) {
        return  violationRegisterService.processRequest(request.getValue());
    }
}
