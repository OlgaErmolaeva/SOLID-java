package learning.solid.lsp.bad;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;

public abstract class AbstractForm<DTO, Query> {

    //initial requirements covered such a case
    protected abstract Callable<List<DTO>> getRequestTask();

    //later requirements
    protected abstract Callable<List<DTO>> getRequestTask(Query queryParameters);

    //other functionality of the class skipped for simplicity
}

//then such an implementation appeared
class RentalHistoryForm<Rental, TimeInterval> extends AbstractForm<Rental, TimeInterval> {

    @Override
    protected Callable<List<Rental>> getRequestTask(TimeInterval queryParameters) {
        //proper implementation here, details skipped: return service::findBy;
        return Collections::emptyList;
    }

    @Override
    protected Callable<List<Rental>> getRequestTask() {
        //oh my god, I don't want to allow fetching whole history without any filtering!!! Abort, abort!
        throw new NotImplementedException();//LSP violation
    }
}
