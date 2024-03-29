// tree.java
// demonstrates binary tree
// to run this program: C>java TreeApp
import java.io.*;
import java.util.*;
// for Stack class ////////////////////////////////////////////////////////////////
public class Tree {
    private Node root; // first node of tree
    // -------------------------------------------------------------
    // public Tree() // constructor
    {
        root = null;
    } // no nodes in tree yet CHAPTER 8 Binary Trees406
    // -------------------------------------------------------------
    public Node find(int key) // find node with given key
    {
        // (assumes non-empty tree)
        Node current = root; // start at root
        while(current.iData != key) // while no match,
        {
            if(key < current.iData) // go left?
                current = current.leftChild;
            else // or go right?
                current = current.rightChild;

            if(current == null) // if no child,
                    return null; // didn’t find it
        }
        return current; // found it
    } // end find()
    // -------------------------------------------------------------

    public void insert(char id) { // id was originally an integer, there was a second parameter for dData (double)
        Node newNode = new Node(); // make new node
        newNode.iData = id; // insert data
        //newNode.dData = dd;
        if(root==null) // no node in root
            root = newNode;
        else // root occupied
        {
            Node current = root; // start at root
            Node parent;
            while(true) // (exits internally)
            {
                parent = current;
                if(id < current.iData) // go left?
                {
                    current = current.leftChild;
                    if(current == null) // if end of the line,
                    {                   // insert on left
                        parent.leftChild = newNode; return;
                    }
                } // end if go left
                else // or go right?
                {
                    current = current.rightChild;
                    if(current == null) // if end of the line
                    { // insert on right
                        parent.rightChild = newNode;
                        return;
                    }
                } // end else go right
            } // end while
        } // end else not root
    } // end insert() // -------------------------------------------------------------

    public boolean delete(int key) // delete node with given key
    { // (assumes non-empty list)
        Node current = root;
        Node parent = root;
        boolean isLeftChild = true;
        while(current.iData != key) // search for node
        {
            parent = current;
            if(key < current.iData) // go left?
            {
                isLeftChild = true;
                current = current.leftChild;
            }
            else // or go right?
            {
                isLeftChild = false;
                current = current.rightChild;
            }
            if(current == null) // end of the line,
                return false; // didn’t find it
        } // end while
        // found node to delete
        // if no children, simply delete it
        if(current.leftChild==null && current.rightChild==null) {
            if(current == root) // if root,
                root = null; // tree is empty
            else if(isLeftChild)
                parent.leftChild = null; // disconnect
            else // from parent
                parent.rightChild = null;
        } // if no right child, replace with left subtree
        else if(current.rightChild==null)
            if(current == root)
                root = current.leftChild;
            else if(isLeftChild)
                parent.leftChild = current.leftChild;
            else
                parent.rightChild = current.leftChild; // if no left child, replace with right subtree
        else if(current.leftChild==null)
            if(current == root)
                root = current.rightChild;
            else if(isLeftChild)
                parent.leftChild = current.rightChild;
            else parent.rightChild = current.rightChild;
        else // two children, so replace with inorder successor
        { // get successor of node to delete (current)
            Node successor = getSuccessor(current); // connect parent of current to successor instead
            if(current == root)
                root = successor;
            else if(isLeftChild)
                parent.leftChild = successor;
            else
                parent.rightChild = successor; // connect successor to current’s left child
            successor.leftChild = current.leftChild;
        } // end else two children
        // (successor cannot have a left child)
        return true; // success
        } // end delete() // -------------------------------------------------------------
    // returns node with next-highest value after delNode
    // goes to right child, then right child’s left descendents
    private Node getSuccessor(Node delNode) {
        Node successorParent = delNode;
        Node successor = delNode;
        Node current = delNode.rightChild; // go to right child
        while(current != null) // until no more
        { // left children,
            successorParent = successor;
            successor = current;
            current = current.leftChild; // go to left child
        } // if successor not
        if(successor != delNode.rightChild) // right child,
        { // make connections
            successorParent.leftChild = successor.rightChild;
            successor.rightChild = delNode.rightChild;
        } return successor;
    } // -------------------------------------------------------------

    public void traverse(int traverseType) {
        switch(traverseType) {
            case 1:
                System.out.print("\nPreorder traversal: ");
                preOrder(root);
                break;
            case 2:
                System.out.print("\nInorder traversal: ");
                inOrder(root);
                break;
            case 3:
                System.out.print("\nPostorder traversal: ");
                postOrder(root);
                break;
        }
        System.out.println();
    } // -------------------------------------------------------------
    private void preOrder(Node localRoot) {
        if(localRoot != null) {
            //System.out.print(localRoot.iData + " ");
            localRoot.displayNode();
            System.out.print(" ");
            preOrder(localRoot.leftChild);
            preOrder(localRoot.rightChild);
        }
    } // -------------------------------------------------------------
    private void inOrder(Node localRoot) {
        if(localRoot != null) {
            inOrder(localRoot.leftChild);
            System.out.print(localRoot.iData + " ");
            inOrder(localRoot.rightChild);
        }
    } // -------------------------------------------------------------
    private void postOrder(Node localRoot) {
        if(localRoot != null) {
            postOrder(localRoot.leftChild);
            postOrder(localRoot.rightChild);
            System.out.print(localRoot.iData + " ");
        }
    } // -------------------------------------------------------------

    public void displayTree() {
        Stack globalStack = new Stack();
        globalStack.push(root);
        int nBlanks = 32;
        boolean isRowEmpty = false;
        System.out.println( "......................................................");
        while(isRowEmpty==false) {
            Stack localStack = new Stack();
            isRowEmpty = true;
            for(int j=0; j<nBlanks; j++)
                System.out.print(' ');
            while(globalStack.isEmpty()==false) {
                Node temp = (Node)globalStack.pop();
                if(temp != null) {
                    //System.out.print(temp.iData);
                    temp.displayNode();
                    localStack.push(temp.leftChild);
                    localStack.push(temp.rightChild);
                    if(temp.leftChild != null || temp.rightChild != null)
                        isRowEmpty = false;
                } else {
                    System.out.print("--");
                    localStack.push(null);
                    localStack.push(null);
                } for(int j=0; j<nBlanks*2-2; j++)
                    System.out.print(' ');
            } // end while globalStack not empty
            System.out.println();
            nBlanks /= 2;
            while(localStack.isEmpty()==false)
                globalStack.push( localStack.pop() );
        } // end while isRowEmpty is false
        System.out.println( "......................................................");
    } // end displayTree() // -------------------------------------------------------------


    // Programming Assignment 2 related stuff - PA2

    // method: assignLevels
    // populate the newly defined level attribute for the already positioned Node objects
    public void assignLevels() {
        assignLevels(root, 0);
    }

    private void assignLevels(Node localRoot, int counter) {
        //same logic as preorder traversal
        if (localRoot != null) {
            localRoot.level = counter;
            assignLevels(localRoot.leftChild, ++counter);
            assignLevels(localRoot.rightChild, counter);
        }
    }

    // Experiment 1:
    // Display the root and the nodes/letters in its right 
    // subtree in the BST on one line as a set of letters and not a tree
    //    used the parameter to see if what I observed for the expierment was reflected
    public void displayHalfTree(boolean displayLeftSide) {
        // display root
        //System.out.print(root.iData + " ");
        root.displayNode();
        System.out.print(" ");
        // if true start a preorder recursion with the roots left child
        if (displayLeftSide) {
            preOrder(root.leftChild);
        } else { // otherwise start the recursion with the roots right child
            preOrder(root.rightChild);
        }
        
        System.out.println("");
    }

    public Node[] TreeToArray() {
        int maxSize = GetMaxArraySize(); // went on a side quest here
        Node[] arr = new Node[maxSize]; 
        
        // same concept from the Option 6 I created from the previous assignment
        LinkQueue traversal = new LinkQueue(); 
        traversal.insert(root);

        // Using the LinkQueue to traverse through the tree in order of left-to-right, top-down;
        // I believe this makes it easier to insert nodes into the array while keeping null spacing
        // by just inserting temporary nodes. At least without using standard libraries. (like the displayTree from the book.)
        int index = 0;
        arr[index] = root;        
        while (!traversal.isEmpty()) {
            Node current = traversal.remove(); // current node, updates at same rate with index
            
            // perform left child insertion or just keep track of spacing
            if (current.leftChild != null) {
                traversal.insert(current.leftChild);
                arr[2*index+1] = current.leftChild;
            } else { 
                if (2*index+1 < maxSize) {
                    Node temp = new Node('-');
                    traversal.insert(temp);
                }
            }
            
            // perform right child insertion or just keep track of spacing
            if (current.rightChild != null) {
	            traversal.insert(current.rightChild);
                arr[2*index+2] = current.rightChild;
	        } else { 
                if (2*index+2 < maxSize) {
                    Node temp = new Node('-');
                    traversal.insert(temp);
                }
            }

            index++; // update index
        }
        return arr; // return the newly made array.
    }

    private int GetMaxArraySize() {
        return power(2, GetMaxLevel(root, 0) + 1) - 1;
    }

    private int power(int base, int exp) {
        if (exp == 0) {
            return 1;
        } else {
            return base * power(base, exp - 1);
        }
    }
    
    // Essentially the same thing as the assignLevels logic just 
    // actually returned the highest counter gets recorded at.
    private int GetMaxLevel(Node localRoot, int counter) {
        if (localRoot != null) {
            int left = GetMaxLevel(localRoot.leftChild, ++counter);
            int right = GetMaxLevel(localRoot.rightChild, counter);

            if (left >= right) {
                return left;
            } else {
                return right;
            }
        }
        return counter - 1;
    }



} // end class Tree ////////////////////////////////////////////////////////////////
