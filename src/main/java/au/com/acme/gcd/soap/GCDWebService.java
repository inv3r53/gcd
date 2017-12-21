package au.com.acme.gcd.soap;

import au.com.acme.gcd.model.Result;
import au.com.acme.gcd.service.GCDService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

@javax.jws.WebService
public class GCDWebService {

    private static final Logger log = LoggerFactory.getLogger(GCDWebService.class);


    private GCDService service;

    public GCDWebService(){}

    @Autowired
    public GCDWebService(GCDService service){
        this.service=service;
    }

    @WebMethod()
    public Result computeGCD(@WebParam(name = "a") String a,
                             @WebParam(name = "b") String b)  {
      return service.calculate(a,b);
    }
}
