package learning.solid.isp.bad;


public class Journalist {

    void publishPressReleaseAbout(Army army) {
        String aStory = army.askForAStory();
        publish(aStory);
    }

    void publish(String aStory){}
}
