import java.util.NoSuchElementException;

public class LinkedQueue<E> {
	private Node<E> first;		//beginning of queue
	private Node<E> last;		//end of queue
	private int size = 0;

	/**
     * Initializes an empty queue.
     */
    public LinkedQueue() {
        first = null;
        last  = null;
    }

    /**
     * @return true if this queue is empty; false otherwise
     */
    public boolean isEmpty() {
        return (first == null);
    }
    
    /**
     * @return the number of items in this queue
     */
    public int size() {
    	return size;
    }
    
	/**
	 * Adds the item to this queue.
	 * @param the item to add
	 */
	public void enqueue(E item) {
	    if(first == null) {
	    	first = new Node<E>(item);
	    	last = first;
	    } else {
	    	last.next = new Node<E>(item);
	    	last = last.next; 	
	    }
	    size++;
	}

	  /**
	  * Removes and returns the last item on the queue
	  * @return the last item on the queue
	  * @throws NoSuchElementException if queue is empty
	  */
	 public E dequeue() {
	     if (isEmpty()) {
	    	 throw new NoSuchElementException("Queue underflow");
	     } else {
	    	 E item = first.data;
		     first = first.next;
		     size--;
		     if (isEmpty()) last = null;   // to avoid loitering
		     return item;
	     } 
	 }
	
}