
package A3;
// Do NOT modify the package declaration
// Do NOT add any import
/**
 * Your own implementation of a HashTable class.
 * Add the appropriate fields and methods you want in order to solve this problem.
 */
class HashTable {
    private final int Initial_Capacity = 1000000;
    public int size;
    private String[] keys;
    private String[] values;

    public HashTable() {
        size = 0;
        keys = new String[Initial_Capacity];
        values = new String[Initial_Capacity];
    }

    public void Add(String key, String value) {
        int index = hash(key);
        while (keys[index] != null) {
            if (keys[index].equals(key)) {
                values[index] = value;
                return;
            }
            index = (index + 1) % Initial_Capacity;
        }
        keys[index] = key;
        values[index] = value;
        size++;
    }

    public boolean Remove(String key) {
        int index = hash(key);
        while (keys[index] != null) {
            if (keys[index].equals(key)) {
                keys[index] = null;
                values[index] = null;
                size--;
                return true;
            }
            index = (index + 1) % Initial_Capacity;
        }
        return false;
    }

    public String Get(String key) {
        int index = hash(key);
        while (keys[index] != null) {
            if (keys[index].equals(key)) {
                return values[index];
            }
            index = (index + 1) % Initial_Capacity;
        }
        return null;
    }

    /**
     * Compute the hash code for the given key.
     * Use the MAD method to reduce collisions.
     */
    private int hash(String key) {
        int a = 7, b = 3, p = 10000019;
        int hash = 0;
        for (int i = 0; i < key.length(); i++) {
            hash = (hash * a + key.charAt(i)) % p;
        }
        return ((hash * b) % p) % Initial_Capacity;
    }

    // TODO: implement your own HashTable class
}

    

public class IntSol {

    /**
     * Return the number of unique integer solutions where each integer is in the range [-50, 50].
     * The equation is given in the handout.
     * Precondition: A, B, C, D, E, S are integers in the range [-5000, 5000]
     * Note that the return value of this function is a long integer, not just an int.
     *
     * Do NOT modify the signature of this function.
     */
    
    static long solve(int A, int B, int C, int D, int E, int S) {
        long count = 0;
        long set = 0;
        for (int x1 = -50; x1 <= 50; x1++) {
            for (int x2 = -50; x2 <= 50; x2++) {
                for (int x3 = -50; x3 <= 50; x3++) {
                    int remainingSum = S - A * x1 - B * x2 * x2 - C * x3 * x3 * x3;
                    if (remainingSum < -50 * E || remainingSum > 50 * E || remainingSum % E != 0) {
                        continue;
                    }

                    int x5 = remainingSum / E;
                    if (x5 < -50 || x5 > 50) {
                        continue;
                    }
                    int x4 = (int) Math.round(Math.pow((double)(remainingSum - E * x5) / D, 1.0 / 4.0));
                    if (x4 < -50 || x4 > 50) {
                        continue;
                    }
                    long hash = (((long) x2 * 1000000L + (long) x3) * 100000L + (long) x4) * 100L + (long) x5;
                    if (hash == set) {
                        continue;
                    }
                    set = hash;
                    count++;
                }
            }
        }
        return count;
    }
       

    public static void main(String[] args) {

        // Below are a few test cases.
        // Printing "true" means the return value is correct.
        // Each call to solve() must take less than 5 seconds.

        System.out.println(340 == solve(12, 34, 56, 78, 9, 10));
        System.out.println(16665 == solve(20, -11, -2, 0, 11, -2011));
        System.out.println(10510100501l == solve(0, 0, 0, 0, 0, 0));
        
    }
}
