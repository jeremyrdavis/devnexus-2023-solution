package io.arrogantprogrammer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.ws.rs.*;
import java.util.List;

@RestController
@RequestMapping("/springgreeting")
public class SpringGreetingApi {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(SpringGreetingApi.class);

    @GetMapping
    public List<Greeting> all() {
        return Greeting.listAll();
    }

    @GetMapping("/{id}")
    public Greeting getById(@PathVariable("id") Long id) {

        LOGGER.info("finding: {}", id);
        return Greeting.findById(id);
    }

    @PostMapping
    @Transactional
    public Greeting addGreeting(Greeting greetingToAdd) {
        greetingToAdd.persist();
        LOGGER.info("persisted: {}", greetingToAdd);
        return greetingToAdd;
    }

    @PatchMapping("/{id}")
    @Transactional
    public Greeting update(@PathVariable("id") Long id, Greeting updatedGreeting) {

        Greeting greeting = Greeting.findById(updatedGreeting.id);
        greeting.setText(updatedGreeting.text);
        greeting.persist();
        LOGGER.info("updated: {}", greeting);
        return greeting;
    }

    @DeleteMapping("/{id}")
    @Transactional
    public void delete(@PathVariable("id") Long id) {

        Greeting greeting = Greeting.findById(id);
        greeting.delete();
        LOGGER.info("deleted: {}", id);
        return;
    }

}
