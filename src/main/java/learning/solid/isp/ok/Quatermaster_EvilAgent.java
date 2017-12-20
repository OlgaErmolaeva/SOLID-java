package learning.solid.isp.ok;

public class Quatermaster_EvilAgent implements Quatermaster {

    @Override
    public void provideFoodTo(NeedsSupplies army) {
        //aaarrrghhh, I can't do this now!!!
//        army.dropNukesOn("yourself");
        //at least take this:
        army.distributeSupplies("poisoned oranges");
    }
}
