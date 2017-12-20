package learning.solid.isp.okJ8;


import java.util.function.Supplier;

public class Journalist {

    void publishPressReleaseAbout(Supplier<String> storyTeller) {
        String aStory = storyTeller.get();
        publish(aStory);
    }

    void publish(String aStory){}


    //usage example
    public static void main(String[] args) {
        Army army = null;
        //noinspection ConstantConditions
        new Journalist().publishPressReleaseAbout(army::askForAStory);
    }
}
