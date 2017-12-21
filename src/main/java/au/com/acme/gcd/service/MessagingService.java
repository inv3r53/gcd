package au.com.acme.gcd.service;



import au.com.acme.gcd.model.persistence.Number;
import au.com.acme.gcd.repository.NumberRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
public class MessagingService {

    private static final Logger LOG = LoggerFactory.getLogger(MessagingService.class);


    @Autowired
    private JmsTemplate jmsTemplate;

    @Autowired
    private NumberRepository numberRepository;


    public long send( au.com.acme.gcd.model.Message m) {
        Number num = new Number();
        num.setN(m.getNumber());
        LOG.info("Saving the number : {}",m.getNumber());
        numberRepository.save(num);
        jmsTemplate.convertAndSend("testQueue", m);
        LOG.info("Finished a message : {}",m);
        return num.getId();
    }

    public au.com.acme.gcd.model.Message pull() {
        Object message = jmsTemplate.receiveAndConvert("testQueue");
        if(message==null){
            throw new IllegalStateException("No Message exists");
        }
       return (au.com.acme.gcd.model.Message)message;
    }
}
