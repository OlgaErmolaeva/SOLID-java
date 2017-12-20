package learning.solid.lsp;

import java.io.IOException;
import java.io.ObjectInputStream;

public abstract class AbstractAnalyzer<Analysis, Data> {

    protected final ObjectInputStream source;

    protected AbstractAnalyzer(ObjectInputStream source) {
        this.source = source;
    }

    public Analysis execute() throws IOException, ClassNotFoundException {
        ObjectInputStream input = prepareSource(source);
        Data data = readData(input);
        cleanup(input);
        return analyze(data);
    }

    protected void cleanup(ObjectInputStream input) {
        try {
            input.close();
        } catch (IOException e) {
            e.printStackTrace();//can only log the exception
        }
    }

    protected abstract ObjectInputStream prepareSource(ObjectInputStream source);

    protected abstract Data readData(ObjectInputStream input) throws IOException, ClassNotFoundException;

    protected abstract Analysis analyze(Data data);
}
