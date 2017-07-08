import java.util.*;

class EventListerImplement implements EventLister 
{
    private Vector vector1;
    
    public EventListerImplement() {
        vector1 = new Vector();
    }
    
    public boolean add(Event e) {
        vector1.addElement(e);
        return true;
    }
    
    public Event removeNext() {

        if ( vector1.isEmpty() ) {
            return null;
        }
    
        int firstIndex = 0;

        double first = ((Event)vector1.elementAt(firstIndex)).getTime();

        for (int i = 0; i < vector1.size(); i++) {

            if (((Event)vector1.elementAt(i)).getTime() < first)
            {
                first = ((Event)vector1.elementAt(i)).getTime();
                firstIndex = i;
            }
        }
        
        Event next = (Event)vector1.elementAt(firstIndex);
        vector1.removeElement(next);
    
        return next;
    }
    
    public String toString() {
        return vector1.toString();
    }
    
    public double getLastPacketTime(int entityFrom, int entityTo) {

        double time = 0.0;

        for (int i = 0; i < vector1.size(); i++) {

            if ((((Event)(vector1.elementAt(i))).getType() == 
                                           NetworkSimulator.FROMLAYER2) &&
                (((Event)(vector1.elementAt(i))).getEntity() == entityTo) &&
                (((Event)(vector1.elementAt(i))).getPacket().getSource() ==
                                           entityFrom)
               )
            {
                time = ((Event)(vector1.elementAt(i))).getTime();
            }
        }
    
        return time;
    }

} // class EventListerImplement
