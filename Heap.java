// heap.java
// demonstrates heaps
// to run this program: C>java HeapApp
import java.io.*;
////////////////////////////////////////////////////////////////

class Heap {
    private Node[] heapArray;
    private int maxSize; // size of array
    private int currentSize; // number of nodes in array
    // -------------------------------------------------------------

    public Heap(int mx) // constructor
    {
        maxSize = mx;
        currentSize = 0;
        heapArray = new Node[maxSize]; // create array
    } // -------------------------------------------------------------
    public boolean isEmpty() {
        return currentSize==0;
    } // -------------------------------------------------------------

    public boolean insert(char key) { // parameter was an int
        if(currentSize==maxSize)
            return false;
        Node newNode = new Node(key);
        heapArray[currentSize] = newNode;
        trickleUp(currentSize++);
        return true;
    } // end insert()
// -------------------------------------------------------------
    public void trickleUp(int index) {
        int parent = (index-1) / 2;
        Node bottom = heapArray[index];
        while( index > 0 && heapArray[parent].getKey() < bottom.getKey() ) {
            heapArray[index] = heapArray[parent]; // move it down
            index = parent;
            parent = (parent-1) / 2;
        } // end while
        heapArray[index] = bottom;
    } // end trickleUp() // -------------------------------------------------------------

    public Node remove() // delete item with max key
    { // (assumes non-empty list)
        Node root = heapArray[0];
        heapArray[0] = heapArray[--currentSize];
        trickleDown(0);
        return root;
    } // end remove() // -------------------------------------------------------------
    public void trickleDown(int index) {
        int largerChild;
        Node top = heapArray[index]; // save root
        while(index < currentSize/2) // while node has at
        { // least one child,
            int leftChild = 2*index+1;
            int rightChild = leftChild+1; // find larger child
            if(rightChild < currentSize && // (rightChild exists?)
                                heapArray[leftChild].getKey() <
                                heapArray[rightChild].getKey())
                largerChild = rightChild;
            else
                largerChild = leftChild; // top >= largerChild?

            if( top.getKey() >= heapArray[largerChild].getKey() )
                break; // shift child up
            heapArray[index] = heapArray[largerChild];
            index = largerChild; // go down
        } // end while
        heapArray[index] = top; // root to index
    } // end trickleDown() // -------------------------------------------------------------

    public boolean change(int index, char newValue) { // newValue was originally an integer
        if(index<0 || index>=currentSize)
            return false;

        int oldValue = heapArray[index].getKey(); // remember old
        heapArray[index].setKey(newValue); // change to new
        if(oldValue < newValue) // if raised,
            trickleUp(index); // trickle it up
        else // if lowered,
            trickleDown(index); // trickle it down
        return true;
    } // end change() // -------------------------------------------------------------

    public void displayHeap(boolean withLevels) {
        System.out.print("heapArray: "); // array format
        for(int m=0; m<currentSize; m++)
            if(heapArray[m] != null) {
                if (!withLevels) {
                    System.out.print( heapArray[m].getKey() + " ");
                } else {
                    heapArray[m].displayNode();
                    System.out.print(", ");
                }
                
            } else {
                System.out.print("-- : -- ");
            }

        System.out.println(); // heap format
        int nBlanks = 32;
        int itemsPerRow = 1;
        int column = 0;
        int j = 0; // current item

        String dots = "...............................";
        System.out.println(dots+dots); // dotted top line

        while(currentSize > 0) // for each heap item
        {
            if (column == 0) // first item in row?
                for (int k = 0; k < nBlanks; k++) // preceding blanks
                    System.out.print(" "); // display item

            
            if (!withLevels) {
                System.out.print(heapArray[j].getKey());
            } else {
                heapArray[j].displayNode();
            }

            if (++j == currentSize) // done?
                break;

            if (++column == itemsPerRow) // end of row?
            {
                nBlanks /= 2; // half the blanks
                itemsPerRow *= 2; // twice the items
                column = 0; // start over on
                System.out.println(); // new row
            } else // next item on row
                for (int k = 0; k < nBlanks * 2 - 2; k++)
                    System.out.print(" "); // interim blanks

        } // end for

        System.out.println("\n"+dots+dots); // dotted bottom line
    } // end displayHeap() // -------------------------------------------------------------


    // Programming Assignment 2 related stuff - PA2

    // method: assignLevels
    // populate the newly defined level attribute for the already positioned Node objects
    public void assignLevels() {
        if (currentSize > 0) {
            assignLevels(0, 0, 1, 0);
        }
    }

    // Same logic used in displayHeap(), just refactored the while loop into a recursive form.
    private void assignLevels(int index, int column, int itemsPerRow, int counter) {
        heapArray[index].level = counter;

        if (++index != currentSize) {
            if (++column == itemsPerRow) {
                assignLevels(index, 0, itemsPerRow * 2, ++counter);
            } else {
                assignLevels(index, column, itemsPerRow, counter);
            }
        }
    }


    // Experiment 1:
    // display the nodes in the first half of the array storing the Heap
    // on the next line. 
    public void displayHalfHeap() {
        if (currentSize > 0) {
            for (int i = 0; i < maxSize/2; i++) {
                //System.out.print(heapArray[i].iData + " ");
                heapArray[i].displayNode();
                System.out.print(" ");
            }
        }
        System.out.println(" ");
    }
    



} // end class Heap ////////////////////////////////////////////////////////////////
