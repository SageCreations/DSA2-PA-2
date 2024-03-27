import java.util.Scanner;

public class Main {

    static Scanner scan = new Scanner(System.in);

    /* some words for testing use.
    * QUICKBROWNFOX
    * JUMBO
    * VEXED
    * GYPSY
    * FLUX
    * DWELL
    * ZEBRA
    * KNIGHT
    * MYTH
    * VIXEN
    * PLUMB
    */
    public static String PromptUser() {
        System.out.print("Please input a word: ");
        return scan.nextLine();
    }
    public static void displayTree(Tree localTree) {
        System.out.println("Tree: __________________________________");
        localTree.displayTree();
        System.out.println("");
    }
    public static void displayHeap(Heap localHeap) {
        System.out.println("Heap: __________________________________");
        localHeap.displayHeap();
        System.out.println("");
    }

    public static void main(String[] args) {
        String word = PromptUser();

        Tree myTree = new Tree();
        Heap myHeap = new Heap(word.length());

        for (int i = 0; i < word.length(); i++) {
            myTree.insert(word.charAt(i));
            myHeap.insert(word.charAt(i));
        }


        displayTree(myTree);
        displayHeap(myHeap);
        System.out.println("\n\n\n");

        myTree.assignLevels();
        displayTree(myTree);

        myHeap.assignLevels();
        displayHeap(myHeap);

        System.out.println("");



        scan.close(); // end program
    }
}
