
package A1;
// Do NOT modify the package declaration

import java.util.ArrayList;
// Do NOT add any import


// May implement a new class here, such as a Stack or a Queue.


public class BallSort {

    /**
     * DO NOT MODIFY the declaration of the function (its parameter types and return type).
     */
    public static int solve(int[] arr) { 
        
            Stack trash = new Stack();
            int K = 0; // initialize k
            for (int i = 0; 1<arr.length;i++){ //instantiating pucks array
                while(!trash.isEmpty() && trash.peek() == K+1){
                    trash.pop();
                    K++;
                }
                if (arr[i] == K+1){
                    K++;
                } 
                else {
                    trash.push(arr[i]);
                }
                return K;
            }
        
        class PuckStack{
        int head;
        int[] array;
        int size;
        PuckStack(){
            head = -1;
            size =10;
            arr = new int[size];
        
        }
        
        boolean isEmpty(){
            return (head ==-1);
        
        }
        int peek(){
            if (!isEmpty()){
                return arr[head];
            }
            else{
                return -1;
            }
        }
        public void push(int x){
            if (head == size -1){
                System.out.print("Not Cool, Overflow");
                return;
            }
            arr[head++]= x;
        } 
        public int pop(){
            if (isEmpty()){
                System.out.println("Ouch, Stack Underflow");
            }
            return arr[head--];
    
        // TODO: implement this function
        return 0;
    

        }
    }
}
    /**
     * This main function contains a few test cases that can be used to check
     * that the code compiles and runs. 
     * Test cases are not viable enough to properly ensure that this code actually works in a proper setting.
     */
    public static void main(String[] args) {

        // Printing "true" means the return value is correct.

        int[] arr = {4, 5, 2, 1, 3};
        System.out.println(3 == solve(arr));

        arr = new int[] {5, 4, 3, 1, 2};
        System.out.println(5 == solve(arr));

        arr = new int[] {1};
        System.out.println(1 == solve(arr));

        arr = new int[] {2, 3, 1};
        System.out.println(1 == solve(arr));

        arr = new int[] {7, 1, 5, 4, 3, 2, 8, 10, 9, 6};
        System.out.println(6 == solve(arr));
    }
}
