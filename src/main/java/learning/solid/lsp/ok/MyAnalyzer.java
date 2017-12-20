package learning.solid.lsp.ok;

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
        input.close(); //LSP violation #1: closing stream in 'read' method
        return data;
    }

    @Override
    protected String analyze(Data data) {
        return "PI is " + Math.PI;//dummy implementation
    }

    @Override
    protected void cleanup(ObjectInputStream input) {
        //LSP violation #2: skipped super.cleanup() (not always, depends on contract)
        if(!cache.isEmpty()) throw new IllegalStateException();//LSP violation #3: throwing exceptions from a 'cleanup' method
        cache.clear();
    }

    @Override
    protected ObjectInputStream prepareSource(ObjectInputStream source) {
        return this.source;//LSP violation #4: disregarding the parameter
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
