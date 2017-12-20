package learning.solid.isp.ok;

public class Quatermaster_Real implements Quatermaster {

    @Override
    public void provideFoodTo(NeedsSupplies army) {
        String food = buyFood();
        army.distributeSupplies(food);
    }

    private String buyFood() {
        return "bananas";
    }
}
