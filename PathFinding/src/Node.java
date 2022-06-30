import kotlin.reflect.jvm.internal.impl.renderer.DescriptorRenderer;

public class Node {

    //Coordinates
    private int x;
    private int y;

    //Parent node for path
    public Node parent;


    private Status status = Status.DEFAULT;

    //Costs, used for AStar
    private int gCost; //cost from starting node
    private int hCost; //cost to destination node (priority)
    private int fCost; // G + H

    public Node(int x, int y) {
        this.x = x;
        this.y = y;
    }

    //setters
    public void setStatus(Status status) { this.status = status; }
    public void setHCost(int hCost) {
        this.hCost = hCost;
    }
    public void setGCost(int gCost) {
        this.gCost = gCost;
    }
    public void setFCost() {
        this.fCost = this.gCost + this.hCost;
    }
    public void setParent(Node node) {
        this.parent = node;
    }

    //getters
    public Status getStatus() { return this.status; }
    public final int getGCost() { return this.gCost; }
    public int getFCost() { return this.fCost; }
    public int getX() { return this.x; }
    public int getY() { return this.y; }
    public Node getParent() { return this.parent; }
    public int getHCost() { return this.hCost; }


    @Override
    public String toString() {
        return "[" + x + ", " + y + "]";
    }

    public final void printCosts() {
        System.out.println("G cost is: " + this.gCost +
                "\nH cost is: " + this.hCost +
                "\nF cost is: " + this.fCost);
    }


}

enum Status {
    OPEN, //set of nodes yet to be evaluated
    CLOSED, //set of nodes that have been evaluated
    PATH, //set of nodes that belong on the path
    OBSTACLE,
    DEFAULT,
}

