package learning.patterns.visitor._2stillugly;

import java.util.*;
import java.util.function.Supplier;

interface Event {
    void logYourself();
    void persistYourself();
}
class CarAdded implements Event {
    @Override
    public void logYourself() {
        System.out.println("LOG: car added");
    }

    @Override
    public void persistYourself() {
        System.out.println("PERSIST: car persisted");
    }
}
class CarRented implements Event {
    @Override
    public void logYourself() {
        System.out.println("LOG: car rented");
    }

    @Override
    public void persistYourself() {
        System.out.println("PERSIST: car rented");
    }
}
class CarReturned implements Event {
    @Override
    public void logYourself() {
        System.out.println("LOG: car returned");
    }

    @Override
    public void persistYourself() {
        System.out.println("PERSIST: car returned");
    }
}

interface Handler {
    void handleEvent(Event event);
}

class EventLogger implements Handler {
    @Override
    public void handleEvent(Event event) {
        event.logYourself();
    }
}

class EventPersister implements Handler {
    @Override
    public void handleEvent(Event event) {
        event.persistYourself();
    }
}


public class EventProcessor {

    Supplier<Event> eventSource;
    Collection<Handler> handlers;

    void eventLoop() throws InterruptedException {
        //noinspection InfiniteLoopStatement
        while(true) {
            Event nextEvent = eventSource.get();
            handlers.forEach(handler -> handler.handleEvent(nextEvent));
            Thread.sleep(10);
        }
    }


    public static void main(String[] args) throws InterruptedException {
        EventProcessor processor = new EventProcessor();
        processor.handlers = Arrays.asList(new EventLogger(), new EventPersister());
        Random random = new Random();
        List<Supplier<Event>> events = new ArrayList<>(Arrays.asList(CarAdded::new, CarRented::new, CarReturned::new));
        processor.eventSource = () -> events.get(random.nextInt(events.size())).get();

        //uncomment below to add new Event to the system and watch the compiler help you
//        class CarCrashed implements Event{} events.add(CarCrashed::new);

        processor.eventLoop();
    }
}


