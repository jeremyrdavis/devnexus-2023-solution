package io.arrogantprogrammer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;

@ApplicationScoped
public class GreetingService {

    private static final Logger LOGGER = LoggerFactory.getLogger(GreetingService.class);

    @Inject
    GreetingRepository greetingRepository;

    @Transactional
    public void addGreeting(GreetingRecord greetingRecord) {

        Greeting greeting = new Greeting();
        greeting.setText(greetingRecord.text());
        greetingRepository.persist(greeting);
        LOGGER.info("persisted: {}", greeting);
    }
}
