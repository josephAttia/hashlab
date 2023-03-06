import java.util.Random;

public class dataItem implements Comparable<dataItem> {

    private int number;
    private String word;

    public dataItem(int n) {
        number = n;
        word = randomWord();
    }

    public dataItem(String w)
    {
       word = w;
    }

    public dataItem(int n, String w)
    {
       number = n;
       word = w;
    }

    /// Getter Methods ///////
    public int getnumber() {
        return number;
    }

  
    public String getWord() {
        return word;
    }
    /////////////////////////


    /// Setter Methods ///////
    public void setnumber(int newnumber) {
        number = newnumber;
    }


    public void setWord(String newWord) {
        word = newWord;
    }
    /////////////////////////

    public int compareTo(dataItem x) {
        return word.compareTo(x.getWord());
    }

    public int hashCode() {
        int sum = 0;

        for (int i = 0; i < word.length(); i++) {
            char letter = word.charAt(i);
            sum += (int) (letter) * i;
        }

        return sum;
    }


    public String toString() {
        return number + " " + word;
    }


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

}
