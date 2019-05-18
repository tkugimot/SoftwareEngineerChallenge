import java.util.ArrayDeque;
import java.util.Arrays;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ImmutableQueueTest {

    private ImmutableQueue<Integer> integerImmutableQueue;
    private ImmutableQueue<String> stringImmutableQueue;
    private ImmutableQueue emptyImmutableQueue;

    @BeforeEach
    void setUp() {
        integerImmutableQueue = new ImmutableQueue<>(
                new ArrayDeque<>(Arrays.asList(1, 2, 3))
        );

        stringImmutableQueue = new ImmutableQueue<>(
                new ArrayDeque<>(Arrays.asList("a", "b", "c"))
        );

        emptyImmutableQueue = new ImmutableQueue<>(new ArrayDeque<>());
    }

    @Test
    @DisplayName("test isEmpty()")
    void test_isEmpty() {
        assertTrue(emptyImmutableQueue.isEmpty());

        assertFalse(integerImmutableQueue.isEmpty());
        assertFalse(stringImmutableQueue.isEmpty());
    }

    @Test
    @DisplayName("test head()")
    void test_head() {
        assertThat(integerImmutableQueue.head()).isEqualTo(1);
        assertThat(stringImmutableQueue.head()).isEqualTo("a");
    }

    /**
     * ImmutableQueue instance does not change after deQueue().
     */
    @Test
    @DisplayName("test deQueue(). An immutableQueue instance does not change.")
    void test_deQueue_an_immutableQueue_do_not_change() {
        assertThat(integerImmutableQueue.head()).isEqualTo(1);
        integerImmutableQueue.deQueue();
        integerImmutableQueue.deQueue();
        integerImmutableQueue.deQueue();
        assertThat(integerImmutableQueue.head()).isEqualTo(1);
    }

    /**
     * deQueue() removes an element from the front of the values in ImmutableQueue
     * and create a new ImmutableQueue instance from it.
     */
    @Test
    @DisplayName("test deQueue(). Create a new ImmutableQueue instance")
    void test_deQueue_create_a_new_ImmutableQueue_instance() {
        assertThat(stringImmutableQueue.head()).isEqualTo("a");
        Queue<String> deQueuedStringImmutableQueue = stringImmutableQueue.deQueue();
        assertThat(stringImmutableQueue.head()).isEqualTo("a");
        assertThat(deQueuedStringImmutableQueue.head()).isEqualTo("b");
    }

    /**
     * ImmutableQueue instance does not change after enQueue() and deQueue().
     */
    @Test
    @DisplayName("test enQueue(). An immutableQueue instance does not change.")
    void test_enQueue_an_immutableQueue_do_not_change() {
        assertThat(integerImmutableQueue.head()).isEqualTo(1);
        integerImmutableQueue.deQueue();
        integerImmutableQueue.deQueue();
        integerImmutableQueue.deQueue();
        integerImmutableQueue.enQueue(4);
        assertThat(integerImmutableQueue.head()).isEqualTo(1);
    }

    /**
     * enQueue() adds an element at the back of the values in ImmutableQueue
     * and create a new ImmutableQueue instance from it.
     */
    @Test
    @DisplayName("test enQueue(). Create a new ImmutableQueue instance")
    void test_enQueue_create_a_new_ImmutableQueue_instance() {
        assertThat(stringImmutableQueue.head()).isEqualTo("a");
        Queue<String> deQueuedStringImmutableQueue1 = stringImmutableQueue.deQueue();
        Queue<String> deQueuedStringImmutableQueue2 = deQueuedStringImmutableQueue1.deQueue();
        Queue<String> deQueuedStringImmutableQueue3 = deQueuedStringImmutableQueue2.deQueue();
        assertTrue(deQueuedStringImmutableQueue3.isEmpty());

        Queue<String> enQueuedStringImmutableQueue = deQueuedStringImmutableQueue3.enQueue("d");
        assertThat(enQueuedStringImmutableQueue.head()).isEqualTo("d");
    }
}
