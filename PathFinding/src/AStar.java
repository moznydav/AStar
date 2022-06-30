import java.util.Comparator;
import java.util.PriorityQueue;

public class AStar {

    private static final int[][] NEIGHBOR_OFFSETS = {
            {-1,-1},{-1, 0},{-1, 1},
            { 0,-1},        { 0, 1},
            { 1,-1},{ 1, 0},{ 1, 1}
    };

    public static final int DIAGONAL_COST = 14;
    public static final int V_A_H_COST = 10;

    private Grid grid;

    public AStar (Grid grid){
        this.grid = grid;
    }

    public final void calculatePath(){

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

        PriorityQueue<Node> openNodes = new PriorityQueue<>(fCostComparator);

        setCosts(grid.getStartingNode());
        openNodes.add(grid.getStartingNode());
        grid.getStartingNode().setStatus(Status.OPEN);
        Node current;

        while(!openNodes.isEmpty()){

            //this will be looped
            current = openNodes.poll();
            current.setStatus(Status.CLOSED);

            if(current == grid.getDestinationNode()){
                System.out.println("Found path!");
                executePath();
                return;
            }

            for(int[] offsets : NEIGHBOR_OFFSETS){
                int row = current.getX() + offsets[0];
                int col = current.getY() + offsets[1];

                if(row >= 0 && row < grid.getLength() && col >= 0 && col < grid.getWidth()){
                    Node neighbor = grid.getNode(row, col);


                    if((neighbor.getStatus() == Status.DEFAULT || isNewPath(neighbor, current)) && neighbor.getStatus() != Status.OBSTACLE){
                        neighbor.setParent(current);

                        openNodes.remove(neighbor);
                        setCosts(neighbor);
                        openNodes.add(neighbor);

                        if(neighbor.getStatus() == Status.DEFAULT){
                            neighbor.setStatus(Status.OPEN);
                        }
                    }
                }
            }
        }
        System.out.println("Couldn't find path.");
        return;
    }

    private final void executePath(){
        Node current = grid.getDestinationNode();
        while (grid.getStartingNode().getStatus() != Status.PATH){
            current.setStatus(Status.PATH);
            current = current.getParent();
        }
    }

    private final boolean isNewPath(Node checkedNode, Node newParent){
        if(checkedNode.getParent() == null){
            return true;
        }
        return calculateFCost(checkedNode) > (calculateGCost(checkedNode, newParent) + calculateHCost(checkedNode));
    }

    private final int calculateFCost(Node node){
        return calculateGCost(node, node.getParent())+calculateHCost(node);
    }

    private final int calculateGCost(Node node, Node parent){
        return calculateDistance(node, parent) + parent.getGCost();
    }
    private final int calculateHCost(Node node){
        return calculateDistance(node, grid.getDestinationNode());
    }

    public final void setCosts(Node node){
        node.setGCost(calculateGCost(node, node.getParent()));
        node.setHCost(calculateHCost(node));
        node.setFCost();
    }

    private final int calculateDistance(Node startingNode, Node destinationNode){
        int cost = 0;

        //destination of both dimensions
        int distanceX = Math.abs(startingNode.getX() - destinationNode.getX());
        int distanceY = Math.abs(startingNode.getY() - destinationNode.getY());

        //for every [1,1] destination is 14 and for every [0,1] or [1,0] it is 10
        if(distanceX > distanceY){ //
            cost = distanceY * 14 + (distanceX-distanceY) * 10;
        } else {
            cost = distanceX * 14 + (distanceY-distanceX) * 10;
        }
        return cost;
    }



}
