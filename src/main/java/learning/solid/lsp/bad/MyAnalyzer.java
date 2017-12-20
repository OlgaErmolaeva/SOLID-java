package learning.solid.lsp.bad;

import learning.solid.lsp.AbstractAnalyzer;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.HashMap;
import java.util.Map;

public class MyAnalyzer extends AbstractAnalyzer<String, Data> {

    public MyAnalyzer(ObjectInputStream source) {
        super(source);
    }

    @Override
    protected Data readData(ObjectInputStream input) throws IOException, ClassNotFoundException {
        String content = (String) input.readObject();
        long timestamp = cache.containsKey(content) ? cache.get(content) : System.currentTimeMillis();
        cache.put(content, timestamp);
        Data data = new Data(timestamp, content);
        input.close();
        return data;
    }

    @Override
    protected String analyze(Data data) {
        return "PI is " + Math.PI;//dummy implementation
    }

    @Override
    protected void cleanup(ObjectInputStream input) {
        if(!cache.isEmpty()) throw new IllegalStateException();
        cache.clear();
    }

    @Override
    protected ObjectInputStream prepareSource(ObjectInputStream source) {
        return this.source;
    }

    Map<String, Long> cache = new HashMap<>();
}

class Data {
    final long timestamp;
    final String content;

    Data(long timestamp, String content) {
        this.timestamp = timestamp;
        this.content = content;
    }
}
