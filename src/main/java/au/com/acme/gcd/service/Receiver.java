package au.com.acme.gcd.service;

import au.com.acme.gcd.model.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class Receiver {
private static final Logger LOG = LoggerFactory.getLogger(Receiver.class);
    @JmsListener(destination = "numberQueue", containerFactory = "myFactory")
    public void receiveMessage(Message m) {
        LOG.info("Received : {} " , m );
    }

}
