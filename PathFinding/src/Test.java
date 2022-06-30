import java.awt.*;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Test {


public static void main(String[] args){

    Grid grid = new Grid(10, 10, 2, 2, 9, 6);
    AStar aStar = new AStar(grid);

    int[][] obstacles = {
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 1, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 1, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 1, 0, 0},
            {1, 1, 1, 1, 1, 1, 1, 1, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 1, 1, 1, 1, 1, 1, 1, 1},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {1, 1, 1, 0, 1, 1, 1, 1, 1, 1},
            {0, 0, 0, 1, 0, 0, 0, 0, 0, 0},
    };
    grid.setObstacles(obstacles);
    System.out.println(grid);
    aStar.calculatePath();
    System.out.println(grid);
    //grid.printOpenAndClosed();

    /*
    Comparator<Node> fCostComparator = new Comparator<Node>() {
        @Override
        public int compare(Node o1, Node o2) {
            int fCost1 = o1.getFCost();
            int fCost2 = o2.getFCost();
            if(fCost1 == fCost2){
                return o1.getHCost() - o2.getHCost();
            } else {
                return 10*(fCost1 - fCost2);
            }

        }
    };

    PriorityQueue<Node> testQueue = new PriorityQueue<>(fCostComparator);

    Node node1 = new Node(1,1);
    Node node2 = new Node(2,2);
    Node node3 = new Node(3,3);
    Node node4 = new Node(3,3);
    Node node5 = new Node(3,3);
    Node node6 = new Node(3,3);


    node1.setGCost(1);
    node1.setHCost(1);
    node1.setFCost();

    node2.setGCost(2);
    node2.setHCost(2);
    node2.setFCost();

    node3.setGCost(3);
    node3.setHCost(3);
    node3.setFCost();

    node4.setGCost(4);
    node4.setHCost(4);
    node4.setFCost();

    node5.setGCost(5);
    node5.setHCost(5);
    node5.setFCost();

    node6.setGCost(6);
    node6.setHCost(6);
    node6.setFCost();

    testQueue.add(node6);
    testQueue.add(node1);
    testQueue.add(node2);
    testQueue.add(node3);
    testQueue.add(node4);
    testQueue.add(node5);

    System.out.println(testQueue.poll().getGCost());
    System.out.println(testQueue.poll().getGCost());

    testQueue.remove(node4);
    node4.setGCost(2);
    node4.setHCost(2);
    node4.setFCost();
    testQueue.add(node4);

    System.out.println(testQueue.poll().getGCost());
    System.out.println(testQueue.poll().getGCost());

    System.out.println(testQueue.poll().getGCost());

    System.out.println(testQueue.poll().getGCost());
*/
    }
}
