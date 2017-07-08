import java.util.*;

abstract class Entity {
   
    static final int INFINITY = 999;
    final int id;
    List<Integer> neighbours;

    protected int[][] distanceTable = new int[NetworkSimulator.numberofRouters]
            [NetworkSimulator.numberofRouters];

    public Entity(int id1, int[] initialDistances, List<Integer> initialNeighbours) {
        this.id = id1;
        this.neighbours = initialNeighbours;

        for (int i = 0; i < NetworkSimulator.numberofRouters; i++)
            for (int j = 0; j < NetworkSimulator.numberofRouters; j++) {
                if (i == id) {
                    distanceTable[i][j] = initialDistances[j];
                    distanceTable[j][i] = initialDistances[j];
                }
                else
                    distanceTable[i][j] = INFINITY;
            }

        transmit();
    }

    public abstract void update(Packet p);

    public abstract void linkCostChangeHandler(int whichLink, int newCost);

    protected abstract void printDT();


    protected void updateTable() {

        for (int col = 0; col < NetworkSimulator.numberofRouters; col++) {

            for (int row = 0; row < NetworkSimulator.numberofRouters; row++) {

                if (distanceTable[row][col] < distanceTable[id][col]) {
                    int tmp = distanceTable[row][col] + distanceTable[id][row];

                    if (tmp < distanceTable[id][col]) {
                        distanceTable[id][col] = tmp;
                        distanceTable[col][id] = tmp;
                    }
                }
            }
        }
    }

    protected boolean isCostsEqual(int from, int[] paths) {

        for (int i = 0; i < NetworkSimulator.numberofRouters; i++)
            if (paths[i] != distanceTable[from][i])
                return false;
        return true;
    }

    public void transmitTable(int src, int dest, int[] row) {
        Packet packet = new Packet(src, dest, row);
        NetworkSimulator.toLayer2(packet);
    }

  
    public void transmit() {
        for (int asdf: neighbours) {
            transmitTable(id, asdf, distanceTable[id]);
        }
    }

    protected int[] extractPayload(Packet p){
        int[] payload = new int[NetworkSimulator.numberofRouters];

        for (int i = 0; i < NetworkSimulator.numberofRouters; i++)
            payload[i] = p.getMincost(i);

        return payload;
    }


    protected void addNewPaths(int source, int[] paths){
        System.arraycopy(paths, 0, distanceTable[source], 0, NetworkSimulator.numberofRouters);

        //
        updateTable();
        transmit();
    }


  

  

}
