package learn.ee.pj4w.pj4w12onecontextxmlconfig;

public class GreetingServiceImpl implements GreetingService {
    @Override
    public String getGreeting(String name) {
        return "Hello, " + name;
    }
}
