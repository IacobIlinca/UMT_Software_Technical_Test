import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;


public class Main {

    /**
     * a prunning function that checks if the lists B and C can be formed to have the same average
     *
     * @param m   : it is enough to check the length to be m = n/2, because having checked for all the ones up to this m, we know for sure if it can be or not split
     * @param sum : the sum of the list
     * @param n   : the length of the list that needs to be chcked
     * @return : false if it can not be split
     * but if it returns true it is not guaranteed that the list can be split ubder the criteria of the same average
     */
    public static boolean check_possible(int m, int sum, int n) {
        boolean poss = false;
        for (int i = 1; i <= m; ++i) {
            if (sum * i % n == 0) {
                poss = true;
                break;
            }
        }
        return poss;
    }

    /**
     * calculates the index at which is situated the element in the TreeSet
     *
     * @param set     : the TreeSet in which we want to know the position of the element
     * @param element : the element for which we want to find the position it is found in the TreeSet
     * @return : the index, the position where the element is found, if it is found in the TreeSet
     * -1, if the element is not found in the TreeSet
     */
    private static int indexOf(TreeSet<Integer> set, Integer element) {
        int index = -1;
        Iterator<Integer> itr = set.iterator();

        Integer currentElement = null;
        int currentIndex = 0;
        while (itr.hasNext()) {
            currentElement = itr.next();
            if (currentElement.equals(element)) {
                return currentIndex;
            }
            currentIndex++;
        }
        return index;
    }

    /**
     * the function that checks if the given list can be split in 2 sublists that have the same average
     * @param A : the list that we want to check if it can be split according to the criteria
     * @return : true if the list can be split in 2 sublists with the same average
     */
    public static boolean splitAvg(List<Integer> A) {
        int n = A.size();
        int m = n / 2;
        int sum = 0;
        for (int j : A) {
            sum = sum + j;
        }
        if (!check_possible(m, sum, n)) return false;

        List<TreeSet<Integer>> sums = new ArrayList<>(m + 1);
        for (int i = 0; i < m + 1; i++) {
            sums.add(new TreeSet<>());
        }
        sums.get(0).add(0);

        for (int item : A) {
            for (int poz = m; poz >= 1; --poz) {
                for (int i : sums.get(poz - 1)) {
                    sums.get(poz).add(i + item);
                }
            }
        }

        for (int i = 1; i <= m; i++) {
            if (sum * i % n == 0 && indexOf(sums.get(i), sum * i / n) != -1) {
                return true;
            }
        }
        return false;
    }

    /**
     * reads a list from a given file: the list must be on a single line with its elements separated by a space (" ") character.
     * @param myObj : the file from we want to read the list
     * @return : the list that was read from the file
     */
    public static List<Integer> readFromFile(File myObj) {
        List<Integer> A = new ArrayList<>();
        try {
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                var sir = data.split(" ");
                for (String s : sir) {
                    A.add(Integer.parseInt(s));
                }

            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return A;
    }

    /**
     * the main function that calls all the other
     * @param args
     */
    public static void main(String[] args) {
        //int[] A = {1, 2, 3, 4, 5, 6, 7, 8};
        File myObj = new File("src/date.txt");
        List<Integer> A = readFromFile(myObj);
        System.out.println(splitAvg(A));

    }
}