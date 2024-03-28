class LinkQueue {
	private FirstLastList theList;

	public LinkQueue() {
		theList = new FirstLastList();
	}

	public boolean isEmpty() {
		return theList.isEmpty();
	}

	public void insert(Node j) {
		theList.insertLast(j);
	}

	public Node remove() {
		return theList.deleteFirst();
	}

	public void displayQueue() {
		//System.out.print("Queue (front --> rear): ");
		theList.displayList();
	}
}