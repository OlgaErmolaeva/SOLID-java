package learning.solid.isp.okJ8;

import java.util.function.Consumer;

public class Quatermaster_Real implements Quatermaster {

    @Override
    public void provideFoodTo(Consumer<String> army) {
        String food = buyFood();
        army.accept(food);
    }

    private String buyFood() {
        return "bananas";
    }

    //usage example
    public static void main(String[] args) {
        Army army = null;
        //noinspection ConstantConditions
        new Quatermaster_Real().provideFoodTo(army::distributeSupplies);
    }
}
