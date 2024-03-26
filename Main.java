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
    public static void main(String[] args) {
        String word = PromptUser();

        Tree myTree = new Tree();
        Heap myHeap = new Heap(word.length());

        for (int i = 0; i < word.length(); i++) {
            myTree.insert(word.charAt(i));
            myHeap.insert(word.charAt(i));
        }

        myTree.displayTree();
        myHeap.displayHeap();

        scan.close(); // end program
    }
}
