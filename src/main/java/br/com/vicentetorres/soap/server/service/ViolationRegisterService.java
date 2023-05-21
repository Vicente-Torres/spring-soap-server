package br.com.vicentetorres.soap.server.service;

import br.com.vicentetorres.soap.server.model.Request;
import br.com.vicentetorres.soap.server.model.Response;
import org.springframework.stereotype.Service;

@Service
public class ViolationRegisterService {

    public Response processRequest(Request request) {
        var cod = Integer.valueOf(request.getParam().substring(0,4));
        var mod = cod % 2;
        Response response = new Response();
        if (mod == 0) {
            response.setOperacaoReturn("000 – Processamento Efetuado com sucesso");
        }else {
            response.setOperacaoReturn("999 – Processamentos com Erro");
        }
        return response;
    }

}
