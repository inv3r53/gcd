package au.com.acme.gcd.config;

import au.com.acme.gcd.service.GCDService;
import au.com.acme.gcd.soap.GCDWebService;
import org.apache.cxf.Bus;
import org.apache.cxf.bus.spring.SpringBus;
import org.apache.cxf.jaxws.EndpointImpl;
import org.apache.cxf.transport.servlet.CXFServlet;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.ws.config.annotation.EnableWs;

import javax.xml.ws.Endpoint;

@Configuration
@EnableWs
public class WSConfig {

    @Bean
    public GCDWebService gcdWebService(GCDService service) {
        return new GCDWebService(service);
    }

    @Bean
    public ServletRegistrationBean wsDispatcherServlet() {
        CXFServlet cxfServlet = new CXFServlet();
        return new ServletRegistrationBean(cxfServlet, "/services/*");
    }

    @Bean(name= Bus.DEFAULT_BUS_ID)
    public SpringBus springBus() {
        return new SpringBus();
    }

    @Bean
    public Endpoint gcdEndpoint(SpringBus springBus, GCDWebService soapService) {
        EndpointImpl endpoint = new EndpointImpl(springBus, soapService);
        endpoint.publish("/gcdWS");
        return endpoint;
    }
}
