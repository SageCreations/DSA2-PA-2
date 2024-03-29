import java.util.Scanner;

public class Main {

    static Scanner scan = new Scanner(System.in);

    /* some words for testing use.
    * DRUNK
    * QUIZ
    * BLIMP
    * WALTZ
    * JUMBO
    * FLUX
    * ZEBRA
    * KNIGHT
    * MYTH
    * VIXEN
    * PLUMB
    */

    // Prompt the user for a word.
    public static String PromptUser() {
        System.out.print("Please input a word: ");
        return scan.nextLine();
    }
    // Display the tree with some extra formatting
    public static void displayTree(Tree localTree) {
        System.out.println("Tree: __________________________________");
        localTree.displayTree();
        System.out.println("");
    }
    // Display the heap with some extra formatting.
    public static void displayHeap(Heap localHeap, boolean withLevels) {
        System.out.println("Heap: __________________________________");
        localHeap.displayHeap(withLevels);
        System.out.println("");
    }
    // Display the Array made from the BST
    public static void displayBSTArray(Node[] arr) {
        for (Node element : arr) {
            if (element != null) {
                element.displayNode();
            } else {
                System.out.print("- -");
            }
            System.out.print("   ");
        }
        System.out.println("");
    }

    // Shift Nodes to make array GAPLESS
    public static void ShiftNodes(Node[] arr) {
        // traverse array left to right
        for (int i = 1; i < arr.length; i++) {
            Node temp = null;   // temp Node, null by default
            // if current index is not null than store it within temp
            if (arr[i] != null) {
                temp = arr[i];
            }

            // if temp ISN'T null and the previous index IS null
            if (temp != null && arr[i-1] == null) {
                // start shifting back untill the node before isn't null
                for (int j = i-1; j > 0; j--) {
                    if (arr[j-1] != null) {
                        arr[j] = temp;
                        arr[i] = null; // get rid of the original node that just got shifted
                        break; // shifting done.
                    }
                }
            }
        }
    }


    // Main program.
    public static void main(String[] args) {
        String word = PromptUser();

        Tree myTree = new Tree();
        Heap myHeap = new Heap(word.length());

        for (int i = 0; i < word.length(); i++) {
            myTree.insert(word.charAt(i));
            myHeap.insert(word.charAt(i));
        }


        displayTree(myTree);
        displayHeap(myHeap, true);
        
        
        System.out.println("\n\n\n");


        myTree.assignLevels();
        displayTree(myTree);

        myHeap.assignLevels();
        displayHeap(myHeap, true);


        System.out.println("\n\n\n");


        System.out.println("Experiment 1: ----------------------------------");
        System.out.print("BST: ");
        myTree.displayHalfTree(false);
        System.out.print("Heap: ");
        myHeap.displayHalfHeap();


        System.out.println("\n\n\n");


        System.out.println("BST to Array: ----------------------------------");
        Node[] myBSTArray = myTree.TreeToArray();
        displayBSTArray(myBSTArray);
        System.out.println("");


        System.out.println("\n\n\n");

        System.out.println("BSTArray Shifted: ------------------------------");
        ShiftNodes(myBSTArray);
        displayBSTArray(myBSTArray);


        System.out.println("\n\n\n");


        System.out.println("BSTArray to Heap: ------------------------------");
        Heap myHeap2 = new Heap(myBSTArray.length);
        // Fill the new heap from the Array
        for (Node element: myBSTArray) {
            if (element != null) {
                myHeap2.insert(element.getKey());
            } else {
                break;
            }
        }
        myHeap2.displayHeap(false);
    

        scan.close(); // end program
    }

}
