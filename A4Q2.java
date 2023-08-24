/**
 * EECS 2011 Z, Winter 2023
 * Assignment 4, Question 2 starter code.
 * Please read carefully the instructions in the assignment handout
 * and the starter code.
 */
package A4;
// Do NOT modify the package declaration
// Do NOT add any import

// You may implement add any additional classes you want.
// For example, a Queue class, a Stack class, or a Node class.
// TODO: Here, implement any additional classes you need.


public class A4Q2 {
    private static class Node{
        int data;
        Node next;
        
    
        public Node(int data){
            this.data = data;
            
        }
       
    }
    
    private static class Queue{
        Node front;
        Node rear;
        
        public void offer(int data) {
            Node newNode = new Node(data);
            if (front == null) {
                front = rear = newNode;
            } else {
                if (rear == null) {
                    // This should not happen, but let's handle it just in case
                    front = rear = newNode;
                } else {
                    rear.next = newNode;
                    rear = newNode;
                }
            }
        }
    
        
        public int poll(){
            int data = front.data;
            front = front.next;
            if (front == null){
                rear = null;
            }
            return data;
        }
        
        public boolean isEmpty(){
            return front == null;
        }
        
        public int getSize(){
            int size = 0;
            Node temp = front;
            while (temp != null) {
                size++;
                temp = temp.next;
            }
            return size;
        }
    }

    public static int minimumButtonPushes(int N, int U, int D, int X, int Y) {
        if (X == Y) {
            return 0;
        }
        if (X > Y && D == 0) {
            return -1;
        }
    
        boolean[] visited = new boolean[N + 1];
        visited[X] = true;

    Queue queue = new Queue();
    queue.offer(X);

    int minPushes = 0;

    while (!queue.isEmpty()) {
        int size = queue.getSize();

        while (size-- > 0) {
            int currFloor = queue.poll();

            // try jumping
            int nextFloor = currFloor * 2;
            if (nextFloor <= N && !visited[nextFloor]) {
                if (nextFloor == Y) {
                    return minPushes + 1;
                }
                visited[nextFloor] = true;
                queue.offer(nextFloor);
            }

            // going up
            nextFloor = currFloor + U;
            if (nextFloor <= N && !visited[nextFloor]) {
                visited[nextFloor] = true;
                queue.offer(nextFloor);
            }

            // going down
            nextFloor = currFloor - D;
            if (nextFloor >= 1 && !visited[nextFloor]) {
                visited[nextFloor] = true;
                queue.offer(nextFloor);
            }
            if(nextFloor == Y){
                
                return minPushes +1;
            }
        }

        minPushes++;
    }
    if(!visited[Y]){
        return -1;
    }

    // impossible to reach destination floor
    return -1;
}
    public static void main(String[] args) {

        // Below are a few test cases.
        // Printing "true" means the return value is correct.
        // Each call to minimumButtonPushes() must take less than 1 second.

        System.out.println(3 == minimumButtonPushes(10, 2, 1, 1, 10));
        System.out.println(5 == minimumButtonPushes(100, 3, 7, 20, 11));
        System.out.println(90 == minimumButtonPushes(1000, 20, 11, 987, 21));
        System.out.println(19 == minimumButtonPushes(1000000, 2011, 3101, 12, 777777));
        System.out.println(-1 == minimumButtonPushes(20, 21, 3, 1, 19));
    }
}
