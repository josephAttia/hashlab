import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;


public class HashTable {

    /*---------------------------------------------------------------------
    |  Method randomWord
    |
    |  Purpose: This method is used to generate a random word that starts with a consoant followed by a vowel etc.
    |
    |  Pre-condition: Nothing
    |
    |  Post-condition: Returns a word that starts with a consoant followed by a vowel etc.
    |
    |  Returns:  Word <Type : String> 
    *-------------------------------------------------------------------*/
    public static String randomWord() {

        StringBuilder wordBuilder = new StringBuilder();
        String[] consonants = new String[] { "B", "C", "D", "F", "G", "J", "K", "L", "M", "N", "P", "Q", "S", "T", "V",
                "X", "Z" };
        String[] vowels = new String[] { "A", "E", "I", "O", "U" };

        Random rand = new Random();

        for (int j = 0; j < rand.nextInt(8 - 3 + 1) + 3; j++) {
            if (j % 2 == 0) {
                int randIndex = rand.nextInt(consonants.length);
                wordBuilder.append(consonants[randIndex]);
            } else {
                int randIndex = rand.nextInt(vowels.length);
                wordBuilder.append(vowels[randIndex]);
            }
        }

        return wordBuilder.toString();
    }

    /*---------------------------------------------------------------------
    |  Method writeToFile
    |
    |  Purpose:  Used to write 10,000 words to the hashData file
    |
    |  Pre-condition: Nothing
    |
    |  Post-condition: A hashData.txt file is created with 10,000 lines containing random words according to the requirements.
    *-------------------------------------------------------------------*/
    public static void writeToFile() {
        try {
            // Sees if there is a file named hashData
            File newFile = new File("hashData.txt");
            // If the file is not found, then create it
            if (newFile.createNewFile()) {

                System.out.println("New file created!");
                FileWriter fileWriter = new FileWriter("hashData.txt");

                // Loops and adds the i value followed by a random word
                for (int i = 1; i <= 10000; i++) {
                    dataItem newElement = new dataItem(i);
                    fileWriter.write(newElement.toString() + "\n");
                }

                // Close the filewriter since we won't need it again
                fileWriter.close();
            }
            else{
                FileWriter fileWriter = new FileWriter("hashData.txt");
                fileWriter.write("");
                
                for (int i = 1; i <= 10000; i++) {
                    dataItem newElement = new dataItem(i);
                    fileWriter.write(newElement.toString() + "\n");
                }

                // Close the filewriter since we won't need it again
                fileWriter.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*---------------------------------------------------------------------
    |  Method addValue
    |
    |  Purpose: addValue is used to take any input and sort it in a BST in the right index
    |
    |  Pre-condition: 
    |   - @dataItem -> valuItem 
    |   - @Node[] -> hashTable
    |
    |  Post-condition: A correct hashTable that is composed of sorted BST's in the right index
    |
    |  Returns: The added node 
    *-------------------------------------------------------------------*/
    
   public static Node add(dataItem valuItem, Node[] hashTable)
   {
    
      Node tempNode = new Node(valuItem);
      Node curr = hashTable[valuItem.hashCode() % hashTable.length];
      
      while(curr != null){
          //If the temporary node's comparable value comes before the root or current node then we go left
         if(tempNode.getValue().compareTo(curr.getValue()) < 0) {
             //If the current node is a leaf along the left tree then we can set the new node to the left
            if(curr.getLeft() == null){
                //Sets the new node as a leaf and BOOM, it's added
               curr.setLeft(tempNode);
               return tempNode;
            }
            else{
                //Move along to the left is we still need to add something
               curr = curr.getLeft();
            }
         }
         //If the temporary node's comparable value comes before the root or current node then we go right
         else if(tempNode.getValue().compareTo(curr.getValue()) > 0){
             //If the current node is a leaf along the right tree then we can set the new node to the right
            if(curr.getRight() == null){
                //Sets the new node as a leaf and BOOM, it's added
               curr.setRight(tempNode);
               return tempNode;
            }
            else{
                //Move along to the right is we still need to add something
               curr = curr.getRight();
            }
         }
         else if(tempNode.getValue().compareTo(curr.getValue()) == 0){
             //If it is equal then we set the current node to the new node, kinda useless but it does the job
            curr.setValue(valuItem);
            return curr;
         }
      }
      
      //If, along the 1000 items, there is no node with the current hashcode index then we set it as a new one 
      hashTable[valuItem.hashCode() % hashTable.length] = tempNode;
      //Return what we have
      return hashTable[valuItem.hashCode() % hashTable.length];
   }
   
   /*---------------------------------------------------------------------
    |  Method search
    |
    |  Purpose: Searchs the hashTable either with a o(1) efficiency or a o(log n) efficiency
    |
    |  Pre-condition: 
    |   - @String -> x 
    |   - @Node[] -> hashTable
    |
    |  Post-condition: Either a line number where the node/item is found or a -1 if it's not found 
    |
    |  Returns: Line number
    *-------------------------------------------------------------------*/
   public static int search(String x, Node[] hashTable)
   {

      dataItem searchablItem = new dataItem(x);
      Node curr = hashTable[searchablItem.hashCode() % hashTable.length];
      
      while(curr != null){
          //Make a new comparable dataItem so we can start comapring
         dataItem compare = (dataItem) curr.getValue();
         
         //If the item is to be found then we return the item line number
         if(searchablItem.compareTo(compare) == 0) return compare.getnumber();
         //If the word comes after the current word/num then we start going right
         else if(searchablItem.compareTo(compare) > 0) curr = curr.getRight();
         //If the word comes before the current word/num then we start going left.
         else if(searchablItem.compareTo(compare) < 0) curr = curr.getLeft();
      }
      
      return -1;
   }

   public static void addAllValues(Node[] hashTable){
    try (BufferedReader reader = new BufferedReader(new FileReader("hashData.txt"))) {
        // Loop through every line
        while (reader.readLine() != null) {

            String currentString = reader.readLine();
            String currentLine = currentString;

            currentString = currentString.substring(currentString.indexOf(" ") + 1, currentString.length());

            int lineNumber = Integer.parseInt(currentLine.substring(0, currentLine.indexOf(" ")));

            dataItem newItem = new dataItem(lineNumber, currentString);

            add(newItem, hashTable);

        }
    } catch (Exception e) {
        e.printStackTrace();
    }
   }
    public static void main(String[] args) throws InterruptedException, FileNotFoundException, IOException {
        Node[] hashTable = new Node[1000];


        try (Scanner sc = new Scanner(System.in)) {
           

            System.out.println("Alright, here's the deal, there is a file named hashData.txt that already exits. You have two options: ");
            System.out.println("1) Clear the contents of the current hashData.txt and regnerate 10000 dataItems");
            System.out.println("2) Use the current 10000 dataItems in hashData.txt");
            System.out.println("(for testing purposes, option 2 is less of a hassle because text editors rarely automatically reload a overwritten txt file [basically you have to reopen it again])");

            System.out.print("Selection: ");
            int choice = sc.nextInt();

            if(choice == 1){
                writeToFile();
                addAllValues(hashTable);
                System.out.print("Enter a Word: ");
                String word = sc.next();
                word = word.toUpperCase();

                if (search(word, hashTable) == -1) {
                    System.out.println("Sorry, you word " + word + " wasn't found in hashData.txt file.");
                } else {
                    System.out.println("Your word " + word + " was found on line " + search(word, hashTable));
                }

            }
            else{
                addAllValues(hashTable);
                System.out.print("Enter a Word: ");
                String word = sc.next();
                word = word.toUpperCase();

                if (search(word, hashTable) == -1) {
                    System.out.println("Sorry, you word " + word + " wasn't found in hashData.txt file.");
                } else {
                    System.out.println("Your word " + word + " was found on line " + search(word, hashTable));
                }
            }
        }

    }
}
