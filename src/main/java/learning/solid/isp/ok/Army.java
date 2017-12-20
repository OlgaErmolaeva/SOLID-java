package learning.solid.isp.ok;

public interface Army extends NeedsSupplies, CanTellAStory {

    void dropNukesOn(String country);
}
