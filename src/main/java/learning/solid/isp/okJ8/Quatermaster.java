package learning.solid.isp.okJ8;

import java.util.function.Consumer;

public interface Quatermaster {
    void provideFoodTo(Consumer<String> army);
}
