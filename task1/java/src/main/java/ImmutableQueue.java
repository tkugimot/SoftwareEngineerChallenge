import java.util.ArrayDeque;
import java.util.Deque;

public class ImmutableQueue<T> implements Queue<T> {

    private final Deque<T> values;

    public ImmutableQueue(Deque<T> values) {
        this.values = values;
    }

    @Override
    public Queue<T> enQueue(T t){
        Deque<T> newQueue = new ArrayDeque<>(values);
        newQueue.addLast(t);
        return new ImmutableQueue<>(newQueue);
    }

    @Override
    public Queue<T> deQueue(){
        Deque<T> newQueue = new ArrayDeque<>(values);
        newQueue.removeFirst();
        return new ImmutableQueue<>(newQueue);
    }

    @Override
    public T head() {
        return values.getFirst();
    }

    @Override
    public boolean isEmpty() {
        return values.isEmpty();
    }
}
