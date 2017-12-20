package learning.patterns.visitor._1ugly;

import java.util.*;
import java.util.function.Supplier;

interface Event {}
class CarAdded implements Event{}
class CarRented implements Event{}
class CarReturned implements Event{}


interface Handler {
    void handleEvent(Event event);
}

class EventLogger implements Handler{
    @Override
    public void handleEvent(Event event) {
        if(event instanceof CarAdded)       {System.out.println("LOG: car added");      return;}
        if(event instanceof CarRented)      {System.out.println("LOG: car rented");     return;}
        if(event instanceof CarReturned)    {System.out.println("LOG: car returned");   return;}
        throw new RuntimeException("Unknown event: " + event.getClass().getName());

    }
}

class EventPersister implements Handler {
    @Override
    public void handleEvent(Event event) {
        if(event instanceof CarAdded)       {System.out.println("PERSIST: car added");      return;}
        if(event instanceof CarRented)      {System.out.println("PERSIST: car rented");     return;}
        if(event instanceof CarReturned)    {System.out.println("PERSIST: car returned");   return;}
        throw new RuntimeException("Unknown event: " + event.getClass().getName());
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

        //uncomment below to add new Event to the system and watch the hell break loose
        //class CarCrashed implements Event{} events.add(CarCrashed::new);

        processor.eventLoop();
    }
}


