import java.io.*;
import java.util.*;

class Runme {

    public final static void main(String[] argv) {

        NetworkSimulator simulator;
        
        int trace = -1;
        int hasLinkChange = -1;
        long seed = -1;
        String buffer = "";
        boolean hasChange;
    
        BufferedReader stdIn = new BufferedReader(
                                   new InputStreamReader(System.in));
                                   
        System.out.println("Distance Vector Algorithm, Homework 6");
        
        while (trace < 0) {

            System.out.print("Enter trace level (>= 0): ");

            try {

                buffer = stdIn.readLine();

            } catch (IOException ioe) {

                System.out.println("reading input error!");
                System.exit(1);
            }
            
            if (buffer.equals("")) {

                trace = 0;

            } else {            

                try {

                    trace = Integer.parseInt(buffer);
                    // trace = 2

                } catch (NumberFormatException nfe) {

                    trace = -1;
                }
            }
        }

        while ((hasLinkChange < 0) || (hasLinkChange > 1)) {

            System.out.print("Is the distance between Router changes? (1 = Yes, 0 = No): " );

            try {
                buffer = stdIn.readLine();

            } catch (IOException ioe) {

                System.out.println("reading input error!");
                System.exit(1);
            }
            
            if (buffer.equals("")) {

                hasLinkChange = 0;

            } else {            

                try {
                    hasLinkChange = Integer.parseInt(buffer);
                } catch (NumberFormatException nfe) {
                    hasLinkChange = -1;
                }
            }
        }

        if (hasLinkChange == 0) {

            hasChange = false;

        } else {

            hasChange = true;
        }


        if (hasLinkChange == 1) {
            // random number generator

            Random rand = new Random();
            seed = rand.nextInt((10 - 1) + 1) + 1;
            System.out.println("Random Number Seed: " + seed);
        }

         
        simulator = new NetworkSimulator(hasChange, trace, seed);
        simulator.runSimulator();

    } // main

} // class
