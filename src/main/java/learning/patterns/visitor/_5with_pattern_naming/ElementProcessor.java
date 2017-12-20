package learning.patterns.visitor._5with_pattern_naming;

import java.util.*;
import java.util.function.Supplier;

interface Element { //was: Event
    void accept(Visitor visitor); //was: dispatchTo
}
class CarAdded implements Element {
    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
class CarRented implements Element {
    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
class CarReturned implements Element {
    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}

interface Visitor { //was: Handler
    void visit(CarAdded event); //was: handle
    void visit(CarRented event); //was: handle
    void visit(CarReturned event); //was: handle
}

class ElementLogger implements Visitor {
    @Override
    public void visit(CarAdded event) {
        System.out.println("LOG: car added");
    }

    @Override
    public void visit(CarRented event) {
        System.out.println("LOG: car rented");
    }

    @Override
    public void visit(CarReturned event) {
        System.out.println("LOG: car returned");
    }
}

class ElementPersister implements Visitor {
    @Override
    public void visit(CarAdded event) {
        System.out.println("PERSIST: car added");
    }

    @Override
    public void visit(CarRented event) {
        System.out.println("PERSIST: car rented");
    }

    @Override
    public void visit(CarReturned event) {
        System.out.println("PERSIST: car returned");
    }
}


public class ElementProcessor {

    Supplier<Element> eventSource;
    Collection<Visitor> visitors;

    void eventLoop() throws InterruptedException {
        //noinspection InfiniteLoopStatement
        while (true) {
            Thread.sleep(10);
            Element element = eventSource.get();
            visitors.forEach(element::accept);
        }
    }


    public static void main(String[] args) throws InterruptedException {
        ElementProcessor processor = new ElementProcessor();
        processor.visitors = Arrays.asList(new ElementLogger(), new ElementPersister());
        Random random = new Random();
        List<Supplier<Element>> events = new ArrayList<>(Arrays.asList(CarAdded::new, CarRented::new, CarReturned::new));
        processor.eventSource = () -> events.get(random.nextInt(events.size())).get();

        //uncomment below to add new Element to the system and watch... how compiler nicely guides you to fix everything
        //class CarCrashed implements Element {} events.add(CarCrashed::new);

        processor.eventLoop();
    }
}


