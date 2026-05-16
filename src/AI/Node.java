package AI;

public class Node {

    Node parent;
    public int level;
    public int room;
    public int col;
    public int row;
    public boolean open;
    public boolean checked;
    public boolean solid;
    int gCost;
    int hCost;
    int fCost;


    public Node(int level, int room, int col, int row){
        this.level = level;
        this.room = room;
        this.col = col;
        this.row = row;
    }
}
