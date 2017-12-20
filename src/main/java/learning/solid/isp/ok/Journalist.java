package learning.solid.isp.ok;


public class Journalist {

    void publishPressReleaseAbout(CanTellAStory army) {
        String aStory = army.askForAStory();
        publish(aStory);
    }

    protected void publish(String aStory){}
}
