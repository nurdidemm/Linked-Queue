
public class Node<E> {
	
	//reference to the next Node
	Node<E> next;
	
	//Data
	E data;
	
	//create a Node
	Node(E data){
		this.data = data;
	}
	
	Node(E data, Node<E> next){
		this.next = next;
		this.data = data;
	}
}
