package learning.solid.isp.bad;


public class General {
    Army myArmy;

    void inCaseOfWarWith(String country) {
        myArmy.dropNukesOn(country);
    }

}
