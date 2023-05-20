package br.com.vicentetorres.soap.server;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.config.annotation.WsConfigurerAdapter;
import org.springframework.ws.server.EndpointInterceptor;
import org.springframework.ws.server.endpoint.interceptor.PayloadLoggingInterceptor;
import org.springframework.ws.server.endpoint.mapping.PayloadRootQNameEndpointMapping;
import org.springframework.ws.soap.server.endpoint.interceptor.PayloadValidatingInterceptor;
import org.springframework.ws.soap.server.endpoint.mapping.SoapActionEndpointMapping;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.WsdlDefinition;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.ws.wsdl.wsdl11.Wsdl4jDefinition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;

import java.util.List;
//import javax.xml.so

@EnableWs
@Configuration
public class WebServiceConfiguration extends WsConfigurerAdapter {

    @Bean
    public ServletRegistrationBean<MessageDispatcherServlet> messageDispatcherServlet(ApplicationContext applicationContext) {
        MessageDispatcherServlet servlet = new MessageDispatcherServlet();
        servlet.setApplicationContext(applicationContext);
        servlet.setTransformWsdlLocations(true);
        return new ServletRegistrationBean<>(servlet, "/ws/*");
    }

    @Bean
    public PayloadRootQNameEndpointMapping payloadRootQNameEndpointMapping() {
        PayloadRootQNameEndpointMapping mapping = new PayloadRootQNameEndpointMapping();
        mapping.setInterceptors(new EndpointInterceptor[]{payloadLoggingInterceptor()});
        return mapping;
    }

    @Bean
    public SoapActionEndpointMapping soapActionEndpointMapping() {
        SoapActionEndpointMapping mapping = new SoapActionEndpointMapping();
        mapping.setInterceptors(new EndpointInterceptor[]{payloadValidatingInterceptor()});
        return mapping;
    }

    @Bean
    public PayloadLoggingInterceptor payloadLoggingInterceptor() {
        return new PayloadLoggingInterceptor();
    }

    @Bean
    public PayloadValidatingInterceptor payloadValidatingInterceptor() {
        PayloadValidatingInterceptor interceptor = new PayloadValidatingInterceptor();
        interceptor.setValidateRequest(true);
        interceptor.setValidateResponse(true);
        interceptor.setXsdSchema(yourXsdSchema()); // Set your XSD schema here
        return interceptor;
    }

    @Bean(name = "myservice")
    public DefaultWsdl11Definition defaultWsdl11Definition(XsdSchema yourXsdSchema) {
        DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
        wsdl11Definition.setPortTypeName("MyServicePort");
        wsdl11Definition.setLocationUri("/ws");
        wsdl11Definition.setTargetNamespace("http://example.org/myservice");
        wsdl11Definition.setSchema(yourXsdSchema);
        return wsdl11Definition;
    }

    @Bean
    public XsdSchema yourXsdSchema() {
        // Implement the method to return your XSD schema
        // You can use SimpleXsdSchema or any other implementation based on your XSD file
        //TODO schemas/myservice.xsd
        return new SimpleXsdSchema(new ClassPathResource("schemas/myservice.xsd"));
    }

    @Override
    public void addInterceptors(List<EndpointInterceptor> interceptors) {
        interceptors.add(payloadLoggingInterceptor());
        interceptors.add(payloadValidatingInterceptor());
    }

}
