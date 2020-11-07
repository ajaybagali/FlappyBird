package assignment11;

import java.util.HashMap;

/**
 *
 * Airport has contains departing flights.
 *
 *
 * @author Ajay Bagali
 * @author Shap Neupane
 */
public class Airport {
    private String airport;
    private HashMap<String, Flight> flightList;

    private double cost;
    private boolean visited;
    private Airport previous;

    /**
     * Creates airport, and sets defaults value.
     *
     * @param airport the airport name
     */
    public Airport(String airport) {
        this.airport = airport;
        this.cost = Float.MAX_VALUE;
        this.visited = false;

        this.flightList = new HashMap<>();
    }

    /**
     * Returns the preceding flight the person took
     * @return previous airport
     */
    public Airport getPrevious() {
        return previous;
    }


    /**
     * sets the previous flight airport, that the path took
     *
     * @param previous the previous flight
     */
    public void setPrevious(Airport previous) {
        this.previous = previous;
    }


    /**
     * The computation cost of the path
     *
     * @return the cost of the path (not price!)
     */
    public double getCost() {
        return cost;
    }

    /**
     * Sets the computation cost of the path (not price!)
     * @param cost the cost of the path
     */
    public void setCost(double cost) {
        this.cost = cost;
    }

    /**
     * Check if the airport is visited
     * @return true -> visited
     *         false -> not visited
     */
    public boolean isVisited() {
        return visited;
    }

    /**
     * Sets the airport to visted
     * @param visited true -> airport visited
     */
    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    /**
     * Returns the  airport name
     * @return airport name
     */
    public String getAirport() {
        return airport;
    }

    /**
     * Sets the airport name
     * @param airport airport name
     */
    public void setAirport(String airport) {
        this.airport = airport;
    }

    /**
     * Hashmap of connection flights from this airport
     * @return hashmap of flights
     */
    public HashMap<String, Flight> getFlightList() {
        return flightList;
    }

    /**
     * Creates a hashmap connection flights from this airport
     * @param flightList the hashmap of departing flights
     */
    public void setFlightList(HashMap<String, Flight> flightList) {
        this.flightList = flightList;
    }

    /**
     * Add a departing flight from the airport
     * @param key the flight key
     * @param flight the flight details
     */
    public void addFlight(String key, Flight flight){
        this.flightList.put(key, flight);
    }


    /**
     * Generates string of the object
     * @return string representation
     */
    @Override
    public String toString() {
        return "Airport{" +
                "airport='" + airport + '\'' +
                ", cost=" + cost +
                ", visited=" + visited +
                ", previous=" + previous +
                '}';

    }
}
