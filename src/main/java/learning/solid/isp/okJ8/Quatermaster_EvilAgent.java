package learning.solid.isp.okJ8;

import java.util.function.Consumer;

public class Quatermaster_EvilAgent implements Quatermaster {

    @Override
    public void provideFoodTo(Consumer<String> army) {
        //aaarrrghhh, I can't do this now!!!
//        army.dropNukesOn("yourself");
        //at least take this:
        army.accept("poop");
    }
}
