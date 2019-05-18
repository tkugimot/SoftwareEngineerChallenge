import org.scalatest.{BeforeAndAfter, FunSuite}

class QueueTest extends FunSuite with BeforeAndAfter {

    val emptyQueue: Queue[Int] = Queue.empty
    val intQueue: Queue[Int] = new QueueImpl[Int](List(1, 2, 3))
    val stringQueue: Queue[String] = new QueueImpl[String](List("a", "b", "c"))

    test("test isEmpty()") {
        assert(emptyQueue.isEmpty === true)
        assert(intQueue.isEmpty === false)
        assert(stringQueue.isEmpty === false)
    }

    test("test head()") {
        assert(intQueue.head.get === 1)
        assert(stringQueue.head.get === "a")
        assert(emptyQueue.head === None)
    }

    /**
      * ImmutableQueue instance does not change after deQueue().
      */
    test("test deQueue(). An immutableQueue instance does not change.") {
        assert(intQueue.head.get === 1)
        intQueue.deQueue()
        intQueue.deQueue()
        intQueue.deQueue()
        assert(intQueue.head.get === 1)
    }

    /**
      * deQueue() removes an element from the front of the values in ImmutableQueue
      * and create a new ImmutableQueue instance from it.
      */
    test("test deQueue(). Create a new ImmutableQueue instance") {
        assert(stringQueue.head.get === "a")
        val deQueuedStringQueue: Queue[String] = stringQueue.deQueue()
        assert(deQueuedStringQueue.head.get === "b")
    }

    /**
      * ImmutableQueue instance does not change after enQueue() and deQueue().
      */
    test("test enQueue(). An immutableQueue instance does not change.") {
        assert(intQueue.head.get === 1)
        intQueue.deQueue()
        intQueue.deQueue()
        intQueue.deQueue()
        intQueue.enQueue(4)
        assert(intQueue.head.get === 1)
    }

    /**
      * enQueue() adds an element at the back of the values in ImmutableQueue
      * and create a new ImmutableQueue instance from it.
      */
    test("test enQueue(). Create a new ImmutableQueue instance") {
        assert(stringQueue.head.get === "a")
        val deQueuedStringQueue1: Queue[String] = stringQueue.deQueue()
        val deQueuedStringQueue2: Queue[String] = deQueuedStringQueue1.deQueue()
        val deQueuedStringQueue3: Queue[String] = deQueuedStringQueue2.deQueue()
        assert(deQueuedStringQueue3.isEmpty === true)

        val enQueuedStringQueue: Queue[String] = deQueuedStringQueue3.enQueue("d")
        assert(enQueuedStringQueue.head.get === "d")
    }
}
