package learning.patterns.visitor._4good;

import java.util.*;
import java.util.function.Supplier;

interface Event {
    void dispatchTo(Handler handler);

//    default void dispatchToInBaseClass(Handler handler) {
//        handler.handle(this);
//    }
}

class CarAdded implements Event {
    @Override
    public void dispatchTo(Handler handler) {
        handler.handle(this);
    }
}
class CarRented implements Event {
    @Override
    public void dispatchTo(Handler handler) {
        handler.handle(this);
    }
}
class CarReturned implements Event {
    @Override
    public void dispatchTo(Handler handler) {
        handler.handle(this);
    }
}

interface Handler {
    void handle(CarAdded event);
    void handle(CarRented event);
    void handle(CarReturned event);
}

class EventLogger implements Handler {
    @Override
    public void handle(CarAdded event) {
        System.out.println("LOG: car added");
    }

    @Override
    public void handle(CarRented event) {
        System.out.println("LOG: car rented");
    }

    @Override
    public void handle(CarReturned event) {
        System.out.println("LOG: car returned");
    }
}

class EventPersister implements Handler {
    @Override
    public void handle(CarAdded event) {
        System.out.println("PERSIST: car added");
    }

    @Override
    public void handle(CarRented event) {
        System.out.println("PERSIST: car rented");
    }

    @Override
    public void handle(CarReturned event) {
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
            handlers.forEach(event::dispatchTo);
        }
    }


    public static void main(String[] args) throws InterruptedException {
        EventProcessor processor = new EventProcessor();
        processor.handlers = Arrays.asList(new EventLogger(), new EventPersister());
        Random random = new Random();
        List<Supplier<Event>> events = new ArrayList<>(Arrays.asList(CarAdded::new, CarRented::new, CarReturned::new));
        processor.eventSource = () -> events.get(random.nextInt(events.size())).get();

        //uncomment below to add new Event to the system and watch... how compiler nicely guides you to fix everything
        //class CarCrashed implements Event {} events.add(CarCrashed::new);

        processor.eventLoop();
    }
}


