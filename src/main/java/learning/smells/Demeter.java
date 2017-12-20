package learning.smells;

@SuppressWarnings("unused")
public class Demeter {

    static class A {
        String a() {
            return "finally here";
        }
    }

    static class B {
        A a;

        A getA() {
            return a;
        }
    }

    static class C {
        B b;

        B getB() {
            return b;
        }
    }

    static void dontTalkToStrangers(C c) {
        c.getB().getA().a();
    }
}
