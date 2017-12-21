package au.com.acme.gcd.service;

import au.com.acme.gcd.model.persistence.Number;
import au.com.acme.gcd.repository.NumberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class PersistenceService {

    @Autowired
    private NumberRepository numberRepository;


    public void save(String n){
        Number num = new Number();
        num.setN(n);
        numberRepository.save(num);
    }

    public Number getInput(long id){
       return  numberRepository.getOne(id);
    }



}
