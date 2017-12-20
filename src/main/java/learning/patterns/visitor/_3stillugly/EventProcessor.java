package learning.patterns.visitor._3stillugly;

import java.util.*;
import java.util.function.Supplier;

interface Event {}
class CarAdded implements Event {}
class CarRented implements Event {}
class CarReturned implements Event {}

interface Handler {
    void handleCarAdded(CarAdded event);
    void handleCarRented(CarRented event);
    void handleCarReturned(CarReturned event);
}

class EventLogger implements Handler {
    @Override
    public void handleCarAdded(CarAdded event) {
        System.out.println("LOG: car added");
    }

    @Override
    public void handleCarRented(CarRented event) {
        System.out.println("LOG: car rented");
    }

    @Override
    public void handleCarReturned(CarReturned event) {
        System.out.println("LOG: car returned");
    }
}

class EventPersister implements Handler {
    @Override
    public void handleCarAdded(CarAdded event) {
        System.out.println("PERSIST: car added");
    }

    @Override
    public void handleCarRented(CarRented event) {
        System.out.println("PERSIST: car rented");
    }

    @Override
    public void handleCarReturned(CarReturned event) {
        System.out.println("PERSIST: car returned");
    }
}


public class EventProcessor {

    Supplier<Event> eventSource;
    Collection<Handler> handlers;

    void eventLoop() throws InterruptedException {
        //noinspection InfiniteLoopStatement
        while (true) {
            Thread.sleep(10);
            Event event = eventSource.get();
            handlers.forEach(handler -> {
                if (event instanceof CarAdded)      {handler.handleCarAdded((CarAdded) event);      return;}
                if (event instanceof CarRented)     {handler.handleCarRented((CarRented) event);    return;}
                if (event instanceof CarReturned)   {handler.handleCarReturned((CarReturned) event);return;}
                throw new RuntimeException("Unknown event: " + event.getClass().getName());
            });
        }
    }


    public static void main(String[] args) throws InterruptedException {
        EventProcessor processor = new EventProcessor();
        processor.handlers = Arrays.asList(new EventLogger(), new EventPersister());
        Random random = new Random();
        List<Supplier<Event>> events = new ArrayList<>(Arrays.asList(CarAdded::new, CarRented::new, CarReturned::new));
        processor.eventSource = () -> events.get(random.nextInt(events.size())).get();

        //uncomment below to add new Event to the system and watch the hell break loose
        //class CarCrashed implements Event {} events.add(CarCrashed::new);

        processor.eventLoop();
    }
}


