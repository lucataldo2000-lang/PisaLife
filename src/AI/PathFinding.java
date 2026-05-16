package AI;

import Main.GamePanel;

import java.util.ArrayList;

public class PathFinding {

    GamePanel gp;

    Node[][][][] node;
    public ArrayList<Node> nodeList = new ArrayList<>();
    public ArrayList<Node> pathList = new ArrayList<>();
    Node currentNode,startNode,goalNode;
    boolean finished = false;
    int steps = 0;

    public PathFinding(GamePanel gp) {
        this.gp = gp;

        createNode();
    }

    public void createNode(){
        node = new Node[gp.maxLevel][gp.maxRoom][gp.maxWorldCol][gp.maxWorldRow];

        int col = 0;
        int row = 0;
        int room = 0;

        while(room < gp.maxRoom - 1){

            while(col < gp.maxWorldCol && row < gp.maxWorldRow){

                node[gp.currentLevel][room][col][row] = new Node(gp.currentLevel,room,col,row);

                col++;

                if(col == gp.maxWorldCol){
                    col = 0;
                    row++;
                }
            }

            if(row == gp.maxWorldRow){
                row = 0;
                room++;
            }
        }
    }

    public void resetNode(){
        int col = 0;
        int row = 0;
        int room = 0;

        while(room < gp.maxRoom){

            while(col < gp.maxWorldCol && row < gp.maxWorldRow){

                if(node[gp.currentLevel][room][col][row] != null){
                    node[gp.currentLevel][room][col][row].open = false;
                    node[gp.currentLevel][room][col][row].checked = false;
                    node[gp.currentLevel][room][col][row].solid = false;
                }

                col++;

                if(col == gp.maxWorldCol){
                    col = 0;
                    row++;
                }
            }

            if(row == gp.maxWorldRow){
                row = 0;
                room++;
            }
        }

        nodeList.clear();
        pathList.clear();
        finished = false;
        steps = 0;
    }

    public void setNodes(int roomIn, int colIn, int rowIn, int colFin, int rowFin){

        resetNode();

        startNode = node[gp.currentLevel][roomIn][colIn][rowIn];
        currentNode = startNode;
        goalNode = node[gp.currentLevel][roomIn][colFin][rowFin];
        nodeList.add(currentNode);

        int col = 0;
        int row = 0;
        int room = 0;

        while(room < gp.maxRoom){

            while(col < gp.maxWorldCol && row < gp.maxWorldRow){

                int tileNum = gp.tileManager.tileNum[gp.currentLevel][room][col][row];
                if(gp.tileManager.tiles[tileNum] != null && node[gp.currentLevel][room][col][row] != null){
                    if(gp.tileManager.tiles[tileNum].collision || gp.tileManager.tiles[tileNum].mobCollision){
                        node[gp.currentLevel][room][col][row].solid = true;
                    }
                }

                for(int i = 0; i < gp.objects[gp.currentLevel][gp.currentRoom].length; i++){
                    if(gp.objects[gp.currentLevel][gp.currentRoom][i] != null && gp.objects[gp.currentLevel][gp.currentRoom][i].collisionOn && gp.objects[gp.currentLevel][gp.currentRoom][i].decoration){
                        int objCol = gp.objects[gp.currentLevel][gp.currentRoom][i].worldX / gp.tileSize;
                        int objRow = gp.objects[gp.currentLevel][gp.currentRoom][i].worldY / gp.tileSize;

                        if(node[gp.currentLevel][room][objCol][objRow] != null){
                            node[gp.currentLevel][room][objCol][objRow].solid = true;
                        }

                    }
                }

                getCost(node[gp.currentLevel][room][col][row]);

                col++;

                if(col == gp.maxWorldCol){
                    col = 0;
                    row++;
                }
            }

            if(row == gp.maxWorldRow){
                row = 0;
                room++;
            }
        }
    }

    public void getCost(Node node){

        if(node != null && startNode != null && goalNode != null){
            int xDistance = Math.abs(node.col - startNode.col);
            int yDistance = Math.abs(node.row - startNode.row);
            node.gCost = xDistance + yDistance;

            xDistance = Math.abs(node.col - goalNode.col);
            yDistance = Math.abs(node.row - goalNode.row);
            node.hCost = xDistance + yDistance;

            node.fCost = node.gCost + node.hCost;
        }
    }

    public boolean search(){

        while(!finished && currentNode != null){

            int col = currentNode.col;
            int row = currentNode.row;
            int room = currentNode.room;

            currentNode.checked = true;
            nodeList.remove(currentNode);

            if(row - 1 >= 0){
                openNode(node[gp.currentLevel][room][col][row - 1]);
            }

            if(col - 1 >= 0){
                openNode(node[gp.currentLevel][room][col - 1][row]);
            }

            if(row + 1 < gp.maxWorldRow){
                openNode(node[gp.currentLevel][room][col][row + 1]);
            }

            if(col + 1 < gp.maxWorldCol){
                openNode(node[gp.currentLevel][room][col + 1][row]);
            }

            int bestIndex = 0;
            int bestFCost = 999;

            for(int i = 0; i < nodeList.size(); i++){
                if(nodeList.get(i).fCost < bestFCost){
                    bestIndex = i;
                    bestFCost = nodeList.get(i).fCost;
                }
                else if(nodeList.get(i).fCost == bestFCost){
                    if(nodeList.get(i).gCost < nodeList.get(bestIndex).gCost){
                        bestIndex = i;
                    }
                }
            }

            if(nodeList.isEmpty()){
                break;
            }

            currentNode = nodeList.get(bestIndex);

            if(currentNode == goalNode){
                finished = true;
                trackPath();
            }

            steps++;
        }

        return finished;
    }

    public void trackPath(){

        Node current = currentNode;

        while(current != startNode){
            pathList.addFirst(current);
            current = current.parent;
        }
    }

    public void openNode(Node node){
        if(!node.open && !node.checked && !node.solid){

            node.open = true;
            node.parent = currentNode;
            nodeList.add(node);
        }
    }
}
