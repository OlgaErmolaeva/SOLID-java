package learning.solid.isp.bad;

public class Quatermaster_Real implements Quatermaster {

    @Override
    public void provideFoodTo(Army army) {
        String food = buyFood();
        army.distributeSupplies(food);
    }

    private String buyFood() {
        return "bananas";
    }
}
