package learning.smells;

public class FeatureEnvy {

    static class IHaveStuff {
        private String one, two, three, four, five, six, seven, eight;

        String getOne() {
            return one;
        }

        String getTwo() {
            return two;
        }

        String getThree() {
            return three;
        }

        String getFour() {
            return four;
        }

        String getFive() {
            return five;
        }

        String getSix() {
            return six;
        }

        String getSeven() {
            return seven;
        }

        String getEight() {
            return eight;
        }
    }

    @SuppressWarnings("unused")
    static class IWantStuff {
        void doSomethingWith(IHaveStuff objectOfEnvy) {
            System.out.println(
                    String.format("%s%s%s%s%s%s%s%s",
                            objectOfEnvy.getOne(),
                            objectOfEnvy.getTwo(),
                            objectOfEnvy.getThree(),
                            objectOfEnvy.getFour(),
                            objectOfEnvy.getFive(),
                            objectOfEnvy.getSix(),
                            objectOfEnvy.getSeven(),
                            objectOfEnvy.getEight()
                    ));
        }
    }
}
