package au.com.acme.gcd.service;

import au.com.acme.gcd.model.Result;
import au.com.acme.gcd.utils.Validator;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.math.BigInteger;

@Service
public class GCDService {

    public Result calculate(String a , String b){
        Result result = new Result();
        BigInteger n1 = Validator.validateAndConvert(a);
        BigInteger n2 = Validator.validateAndConvert(b);
        BigInteger gcd = n1.gcd(n2);
        result.setResponse(gcd.toString());
        return result;
    }

    public static void main(String[] args){

        GCDService service = new GCDService();
        Result r = service.calculate("1","");
        System.out.println(r.getResponse());

    }



}
