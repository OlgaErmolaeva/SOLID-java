package learning.solid.isp.bad;

public class Quatermaster_EvilAgent implements Quatermaster {

    @Override
    public void provideFoodTo(Army army) {
        army.dropNukesOn("yourself");
    }
}
