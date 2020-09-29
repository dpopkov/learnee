package learn.ee.pj4w.pj4w12hybridconfig;

import org.springframework.stereotype.Service;

@Service
public class GreetingServiceImpl implements GreetingService {
    @Override
    public String getGreeting(String name) {
        return "Hello, " + name;
    }
}
