import kotlin.reflect.jvm.internal.impl.renderer.DescriptorRenderer;

import java.util.Arrays;

public class Grid {

    private final int length;
    private final int width;

    private final Node[][] grid;

    private Node startingNode;
    private int startX;
    private int startY;

    private Node destinationNode;
    private int destinationX;
    private int destinationY;


    public Grid(int length, int width, int startX, int startY, int destinationX, int destinationY){
        this.length = length;
        this.width = width;
        this.startX = startX;
        this.startY = startY;
        this.destinationX = destinationX;
        this.destinationY = destinationY;

        this.grid = new Node[length][width];
        fillTheGrid();
    }

    private void fillTheGrid() {
        //constructor helper
        for(int i = 0; this.grid.length > i; i++){
            for(int j = 0; this.grid[i].length > j; j++){
                grid[i][j] = new Node(i, j);
            }
        }
        this.startingNode = grid[startX][startY];
        this.startingNode.setParent(this.startingNode);
        this.destinationNode = grid[destinationX][destinationY];
    }

    public Node getNode(int x, int y){ return grid[x][y]; }
    public Node getStartingNode() { return this.startingNode; }
    public Node getDestinationNode() { return this.destinationNode; }
    public int getLength(){ return this.length; }
    public int getWidth(){ return this.width; }

    public void setStartingNode(int x, int y) {
        this.startingNode = this.grid[x][y];

        this.startX = x;
        this.startY = y;
    }
    public void setDestinationNode(int x, int y) {
        this.destinationNode = this.grid[x][y];

        this.destinationX = x;
        this.destinationY = y;
    }
    public void setObstacles(int[][] obstacles){
        for(int i = 0; this.grid.length > i; i++){
            for(int j = 0; this.grid[i].length > j; j++){
                if(obstacles[i][j] != 0){
                    grid[i][j].setStatus(Status.OBSTACLE);
                }
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder string = new StringBuilder();
        for(int i = 0; i < this.length; i++){
            string.append("\n");
            for(int j = 0; j < this.width; j++){
                if(grid[i][j] == destinationNode){
                    string.append("D");
                } else if (grid[i][j] == startingNode){
                    string.append("S");
                } else if (grid[i][j].getStatus() == Status.OBSTACLE){
                    string.append("#");
                } else if(grid[i][j].getStatus() == Status.PATH){
                    string.append("X");
                } else {
                    string.append("-");
                }
                string.append(" ");
            }
        }
        return string.toString();
    }

    public void printOpenAndClosed(){
        StringBuilder string = new StringBuilder();
        for(int i = 0; i < this.length; i++){
            string.append("\n");
            for(int j = 0; j < this.width; j++){
                if(grid[i][j] == destinationNode){
                    string.append("DD");
                } else if (grid[i][j] == startingNode){
                    string.append("SS");
                } else if (grid[i][j].getStatus() == Status.OBSTACLE){
                    string.append("##");
                } else if (grid[i][j].getStatus() == Status.OPEN){
                    string.append(grid[i][j].getFCost());
                } else if (grid[i][j].getStatus() == Status.CLOSED){
                    string.append("CC");
                } else {
                    string.append("--");
                }
                string.append(" ");
            }
        }
        System.out.println(string.toString());
    }
}
