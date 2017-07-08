// done
import java.util.*;

class Entity0 extends Entity {
 
    public Entity0() {        
        super(0, new int[] {0, 1, 3, 7}, Arrays.asList(1, 2, 3));
    }
    
    @Override
    public void update(Packet p) {

        int source = p.getSource();         
        int[] payload = extractPayload(p);  

        if (isCostsEqual(source, payload)) {
            printDT();
            return;
        }

        addNewPaths(source, payload);
    }

    @Override
    public void printDT()
    {
        System.out.println();
        System.out.println("Cost to Destination");
        System.out.println(" x |   x   y   z");
        System.out.println("----------------");

        for (int i = 1; i < NetworkSimulator.numberofRouters; i++) {
            System.out.print("   " + i + "|");
            for (int j = 1; j < NetworkSimulator.numberofRouters; j++)
            {
                if (distanceTable[i][j] < 10)
                {    
                    System.out.print("   ");
                }
                else if (distanceTable[i][j] < 100)
                {
                    System.out.print("  ");
                }
                else 
                {
                    System.out.print(" ");
                }
                
                System.out.print(distanceTable[i][j]);
            }
            System.out.println();
        }
    }
}
