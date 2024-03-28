class Link {
	public Node dData;
	public Link next;

	public Link(Node d) {
		dData = d;	
	}

	public void displayLink() {
		System.out.print(dData.iData + " ");
	}
}