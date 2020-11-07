package assignment11;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
/**
 *
 * The class to find the shortest path from a staring airport to the destination airport.
 *
 * @author Ajay Bagali
 * @author Shap Neupane
 *
 *
 */
public class Djikstras {

    private PriorityQueue<Airport> minPriorityQueue;
    private HashMap<String, Airport> airportHashMap;

    /**
     * Creates a priorty queue and sets the airportHashMap for djikstras to find the shortest path
     *
     * @param airportHashMap the airporthashmap should contains airport with departing flights.
     */
    public Djikstras(HashMap<String, Airport> airportHashMap) {
        this.airportHashMap = airportHashMap;

        minPriorityQueue = new PriorityQueue<>(1000000, new Comparator<Airport>() {
            @Override
            public int compare(Airport o1, Airport o2) {
                return Double.compare(o1.getCost(), o2.getCost());
            }

        });

    }


    /**
     * Gets the path cost according to the FlightCriteria
     *
     * @param flightCriteria the type of cost, FlightCriteria
     * @param flight the flight to do the cost lookup
     *
     * @return the cost according to the specific criteria.
     *
     */
    private double getPathCost(FlightCriteria flightCriteria, Flight flight) {
        switch (flightCriteria) {
            case TIME:
                // time cost
                return flight.getTime() / flight.getDupliates();
            case DELAY:
                // delay cost
                return flight.getDelay() / flight.getDupliates();
            case DISTANCE:
                // distance cost
                return flight.getDistance() / flight.getDupliates();
            case PRICE:
                // price cost
                return flight.getPrice() / flight.getDupliates();
            case CANCELED:
                // cancelled case (special case)
                return flight.getCancelled() / flight.getDupliates();
        }

        // it should never reach here, because all the FlightCriteria cases are covered above.
        return Integer.MAX_VALUE;

    }


    /**
     * Cleans up the queue and the hashmap if it contains non initial values.
     */
    private void cleanup(){
        for (Map.Entry<String, Airport> airport : airportHashMap.entrySet()) {
            // airport.getValue().cleanup();

            airport.getValue().setPrevious(null);
            airport.getValue().setVisited(false);
            airport.getValue().setCost(Double.MAX_VALUE);

        }
        minPriorityQueue.clear();
    }


    public Airport shortestPath(Airport start, Airport goal, FlightCriteria flightCriteria, String airliner) {
        // cleanup queue and the map before starting the shortest path search
        cleanup();

        Airport startAirport = airportHashMap.get(start.getAirport());

        if (startAirport == null) {
            // no airport in the airporthashmap. Hence, no flights.
            return null;
        }

        startAirport.setCost(0.0);

        // adding the first item to the queue
        minPriorityQueue.add(startAirport);

        while (!minPriorityQueue.isEmpty()) {
            Airport curr = minPriorityQueue.remove();


            if (curr.getAirport().equals(goal.getAirport())) {
                // destination reached
                return curr;
            }

            curr.setVisited(true);

            // neighbouring flighs -> edges.
            HashMap<String, Flight> neighbouringAirports = curr.getFlightList();

            // loop over every edge
            for (String neighbours : neighbouringAirports.keySet()) {

                // get the flight details through the departing flights hashmap
                Flight flight = neighbouringAirports.get(neighbours);

                // get the departing flights of the airport through the airport hashmap
                Airport airport = airportHashMap.get(flight.getDestination().getAirport());

                if (!airport.isVisited()) {
                    // if the airport is not visited, calculate costs.

                    if (getPathCost(flightCriteria, flight) >= 0) {
                        // only do djikstras with positive costs

                        if (airliner == null) {
                            // do this if specific airline is NOT requested.
                            if (airport.getCost() > curr.getCost() + getPathCost(flightCriteria, flight)) {

                                airport.setPrevious(curr);
                                // adds the path cost to the current cost.
                                airport.setCost(curr.getCost() + getPathCost(flightCriteria, flight));
                                minPriorityQueue.add(airport);
                            }
                        } else {
                            // do this if specific airline IS requested.
                            if (flight.carrierList.contains((airliner))) {
                                // special check if flight requires a specific carrier
                                if (airport.getCost() > curr.getCost() + getPathCost(flightCriteria, flight)) {
                                    airport.setPrevious(curr);
                                    // adds the path cost to the current cost.
                                    airport.setCost(curr.getCost() + getPathCost(flightCriteria, flight));
                                    minPriorityQueue.add(airport);
                                }
                            }
                        }

                    }

                }
            }
        }

        // return the initial airport, if no path found.
        return startAirport;
    }

}

