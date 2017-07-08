class Packet
{
    private int source;
    private int dest;
    private int[] mincost;
    
    public Packet(Packet packet1)
    {
        source = packet1.getSource();
        dest = packet1.getDest();
        mincost = new int[NetworkSimulator.numberofRouters];
        for (int i = 0; i < NetworkSimulator.numberofRouters; i++)
        {
            mincost[i] = packet1.getMincost(i);
        }
    }
    
    public Packet(int s, int d, int[] mc)
    {
        source = s;
        dest = d;
        
        mincost = new int[NetworkSimulator.numberofRouters];
        if (mc.length != NetworkSimulator.numberofRouters)
        {
            System.out.println("Packet(): Invalid data format.");
            System.exit(1);
        }
        
        for (int i = 0; i < NetworkSimulator.numberofRouters; i++)
        {
            mincost[i] = mc[i];
        }
    }        
    
    public int getSource()
    {
        return source;
    }
    
    public int getDest()
    {
        return dest;
    }
    
    public int getMincost(int ent)
    {
        return mincost[ent];
    }

    public String toString()
    {
        String str;
        str = "source: " + source + "  dest: " + dest + "  mincosts: ";
        
        for (int i = 0; i < NetworkSimulator.numberofRouters; i++)
        {
            str = str + i + "=" + getMincost(i) + " ";
        }
        
        return str;
        
    }
}
