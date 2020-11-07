
package assignment11;

import java.io.*;
import java.util.*;

/**
 * <p>This class represents a graph of flights and airports along with specific
 * data about those flights. It is recommended to create an airport class and a
 * flight class to represent nodes and edges respectively. There are other ways
 * to accomplish this and you are free to explore those.</p>
 * <p>
 * <p>Testing will be done with different criteria for the "best" path so be
 * sure to keep all information from the given file. Also, before implementing
 * this class (or any other) draw a diagram of how each class will work in
 * relation to the others. Creating such a diagram will help avoid headache and
 * confusion later.</p>
 * <p>
 * <p>Be aware also that you are free to add any member variables or methods
 * needed to completed the task at hand</p>
 *
 * @author CS2420 Teaching Staff - Spring 2018
 * @author Ajay Bagali & Shap Neupane
 */
public class NetworkGraph {

    private HashMap<String, Airport> airportHashMap;
    private Djikstras graph;

    /**
     * <p>Constructs a NetworkGraph object and populates it with the information
     * contained in the given file. See the sample files or a randomly generated
     * one for the proper file format.</p>
     * <p>
     * <p>You will notice that in the given files there are duplicate flights with
     * some differing information. That information should be averaged and stored
     * properly. For example some times flights are canceled and that is
     * represented with a 1 if it is and a 0 if it is not. When several of the same
     * flights are reported totals should be added up and then reported as an
     * average or a probability (value between 0-1 inclusive).</p>
     *
     * @param flightInfo - The inputstream to the flight data. The format is a
     *                   *.csv(comma separated value) file
     */
    public NetworkGraph(InputStream flightInfo) {
        //TODO: Implement a constructor that reads in the file and stores the information
        // 		appropriately in this object
        //we make an airport hashmap that stores a hashmap of flights
        airportHashMap = new HashMap<>();
        //buffered reader to read in the data
        BufferedReader br = new BufferedReader(new InputStreamReader(flightInfo));


        try {
            String line;
            // skipping first line
            br.readLine();
            while ((line = br.readLine()) != null) {
                //we split it to separate the commas in details
                String[] flightArray = line.split(",");
                // the length is not 8, we skip over
                if (flightArray.length != 8) {
                    continue;
                }
                //we add the flight array into the flight hashmap
                addFlightIntoHashMap(flightArray);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        //we make a new graph using the djikstras algrothim
        graph = new Djikstras(airportHashMap);

    }

    /**
     * This method adds a flight array  into the airport hashmap
     * @param flightArray
     */
    private void addFlightIntoHashMap(String[] flightArray) {
        //we read it in based off the array
        Airport airport = new Airport(flightArray[0]);
        Airport destination = new Airport(flightArray[1]);
        String carrier = flightArray[2];
        double delay = Double.parseDouble(flightArray[3]);
        double cancelled = Double.parseDouble(flightArray[4]);
        double time = Double.parseDouble(flightArray[5]);
        double distance = Double.parseDouble(flightArray[6]);
        double cost = Double.parseDouble(flightArray[7]);

        //check if the airport is contained in the hashmap
        Airport containsAirport = airportHashMap.get(airport.getAirport());
        //generate new flight
        Flight flight = new Flight(airport, destination, carrier, delay, cancelled, time, distance, cost, 1);

        if (containsAirport == null) {
            // no aiport means no flight
            //add flight to the airport, then add the airport to the airport hashmap
            airport.addFlight(key(airport, destination), flight);
            airportHashMap.put(airport.getAirport(), airport);

        } else {
            // check for duplicate flights
            Flight containsFlight = containsAirport.getFlightList().get(key(airport, destination));

            if (containsFlight == null) {
                // add a unique flight
                containsAirport.addFlight(key(airport, destination), flight);
            } else {
                //update details of the duplicate flight
                containsFlight.updateFlight(
                        carrier,
                        containsFlight.getDelay() + flight.getDelay(),
                        containsFlight.getCancelled() + flight.getCancelled(),
                        containsFlight.getTime() + flight.getTime(),
                        containsFlight.getDistance() + flight.getDistance(),
                        containsFlight.getPrice() + flight.getPrice(),
                        containsFlight.getDupliates() + 1
                );

                // replaces with the updated flight details
                containsAirport.getFlightList().put(key(airport, destination), containsFlight);

            }


        }
        //Adds the destination airport if not already present in the hashmap
        airportHashMap.putIfAbsent(destination.getAirport(), destination);



    }

    /**
     * this method creates a key for the flight
     * @param origin starting airport
     * @param dest goal airport
     * @return key for the hashmap
     */
    private String key(Airport origin, Airport dest) {
        return origin.getAirport() + dest.getAirport();
    }

    /**
     * This method returns a BestPath object containing information about the best
     * way to fly to the destination from the origin. "Best" is defined by the
     * FlightCriteria parameter <code>enum</code>. This method should throw no
     * exceptions and simply return a BestPath object with information dictating
     * the result. If the destination or origin is not contained in this instance of
     * NetworkGraph, simply return a BestPath object with an empty path
     * (not a <code>null</code> path) and a path cost of 0. If origin or destination
     * are <code>null</code>, also return a BestPath object with an empty path and a
     * path cost of 0 .  If there is no path in this NetworkGraph from origin to
     * destination, also return a BestPath with an empty path and a path cost of 0.
     *
     * @param origin      - The starting location to find a path from. This should be a
     *                    3 character long string denoting an airport.
     * @param destination - The destination location from the starting airport.
     *                    Again, this should be a 3 character long string denoting an airport.
     * @param criteria    - This enum dictates the definition of "best". Based on this
     *                    value a path should be generated and return.
     * @return - An object containing path information including origin, destination,
     * and everything in between.
     */
    public BestPath getBestPath(String origin, String destination, FlightCriteria criteria) {
        //TODO: First figure out what kind of path you need to get (HINT: Use a switch!) then
        //		Search for the shortest path using Dijkstra's algorithm.
        //calls the best path with the carrier being null
        return getBestPath(origin, destination, criteria, null);
    }


    /**
     * <p>This overloaded method should do the same as the one above only when looking for paths
     * skip the ones that don't match the given airliner.</p>
     *
     * @param origin      - The starting location to find a path from. This should be a
     *                    3 character long string denoting an airport.
     * @param destination - The destination location from the starting airport.
     *                    Again, this should be a 3 character long string denoting an airport.
     * @param criteria    - This enum dictates the definition of "best". Based on this
     *                    value a path should be generated and return.
     * @param airliner    - a string dictating the airliner the user wants to use exclusively. Meaning
     *                    no flights from other airliners will be considered.
     * @return - An object containing path information including origin, destination,
     * and everything in between.
     */
    public BestPath getBestPath(String origin, String destination, FlightCriteria criteria, String airliner) {

        // find the shortest path
        Airport path = graph.shortestPath(new Airport(origin), new Airport(destination), criteria, airliner);
        if (path == null) {
            // if no path, no best path (empty path).
            return new BestPath(new ArrayList<>(), 0);
        }

        // keeping track of the total cost from the path
        double cost = path.getCost();

        ArrayList<String> bestPathList = new ArrayList<>();

        int pathLength = 0;
        while ((path.getPrevious()) != null) {
            // get the previous path that the airport took
            bestPathList.add(path.getAirport());
            path = path.getPrevious();
            pathLength++;
        }

        bestPathList.add(path.getAirport());

        if (bestPathList.get(0).equals(origin)) {
            //
            return new BestPath(new ArrayList<>(), 0);
        }


        // reverse the path, because the current bestPathList contains items from end to start
        // the resulting dataset should to start to end
        // hence, reverse.
        Collections.reverse(bestPathList);

        BestPath bestPath;
        if (criteria == FlightCriteria.CANCELED) {
            // if cancelled cost, need to average the cost out
            bestPath = new BestPath(bestPathList, cost / pathLength);
        } else bestPath = new BestPath(bestPathList, cost);

        return bestPath;

    }

}


