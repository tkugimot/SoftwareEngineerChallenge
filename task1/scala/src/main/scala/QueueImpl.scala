class QueueImpl[T](val values: List[T]) extends Queue[T] {

    override def isEmpty: Boolean = {
        values.isEmpty
    }

    override def enQueue(t: T): Queue[T] = {
        val newQueue: List[T] = values :+ t
        new QueueImpl[T](newQueue)
    }

    override def deQueue(): Queue[T] = {
        val newQueue: List[T] = values.drop(1)
        new QueueImpl[T](newQueue)
    }

    override def head: Option[T] = {
        values.headOption
    }
}
