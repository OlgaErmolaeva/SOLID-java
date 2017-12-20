package learning.smells;

public class SwitchStatements {

    static class Bad {
        enum AnimalType {FISH, BIRD, DOG}

        static class Animal {
            String name;
            AnimalType type;

            void move() {
                switch (type) {

                    case FISH:
                        System.out.println("swimming");
                        break;
                    case BIRD:
                        System.out.println("flying");
                        break;
                    case DOG:
                        System.out.println("chasing my tail");
                        break;
                }
            }
        }
    }

    static class Good {

        static class Animal {
            String name;
            MoveStrategy moveStrategy;

            void move() {
                moveStrategy.move();
            }

        }

        enum MoveStrategy {//can be an interface
            SWIMMING {
                @Override
                void move() {
                    System.out.println("swimming");
                }
            },

            FLYING {
                @Override
                void move() {
                    System.out.println("flying");
                }
            },
            CHASINGMYTAIL {
                @Override
                void move() {
                    System.out.println("chasing my tail");
                }
            };

            abstract void move();
        }
    }
}
