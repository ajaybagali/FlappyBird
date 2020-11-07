package assignment11;

import java.util.HashSet;

/**
 * This class holds the flight details.
 *
 * @author Ajay Bagali
 * @author Shap Neupane
 *
 */

public class Flight {
    private Airport origin;
    private Airport destination;

    public HashSet<String> carrierList;

    private double delay;
    private double cancelled;
    private double time;
    private double distance;
    private double price;
    private int numFlights;

    /**
     * This constructor sets the details in the flight object
     *
     * @param origin
     * @param destination
     * @param carrier
     * @param delay
     * @param cancelled
     * @param time
     * @param distance
     * @param price
     * @param numFlights
     */
    public Flight(Airport origin, Airport destination, String carrier, double delay, double cancelled, double time,
                  double distance, double price, int numFlights) {
        this.carrierList = new HashSet<String>();
        this.carrierList.add(carrier);

        this.origin = origin;
        this.destination = destination;
        this.delay = delay;
        this.cancelled = cancelled;
        this.time = time;
        this.distance = distance;
        this.price = price;
        this.numFlights = numFlights;

    }

    /**
     * this method simply resets the details of the flight once we update it
     *
     * @param carrier
     * @param delay
     * @param cancelled
     * @param time
     * @param distance
     * @param cost
     * @param numFlights
     */
    public void updateFlight(String carrier, double delay, double cancelled, double time, double distance, double cost,
                             int numFlights) {
        this.delay = delay;
        this.cancelled = cancelled;
        this.time = time;
        this.distance = distance;
        this.price = cost;
        this.numFlights = numFlights;

        this.carrierList.add(carrier);

    }

    /**
     * this keep a counter of duplicate flights
     *
     * @return
     */
    public int getDupliates() {
        return numFlights;
    }

    /**
     * this sets the duplicates to the flight object
     *
     * @param numFlights
     */
    public void setDupliates(int numFlights) {
        this.numFlights = numFlights;
    }

    /**
     *
     * @return origin airport of the flight
     */
    public Airport getOrigin() {
        return origin;
    }

    /**
     * this sets the origin airport for the flight
     *
     * @param origin
     */
    public void setOrigin(Airport origin) {
        this.origin = origin;
    }

    /**
     * @return destination of the flight
     */
    public Airport getDestination() {
        return destination;
    }

    /**
     * this sets the destination airport for the flight
     *
     * @param destination
     */
    public void setDestination(Airport destination) {
        this.destination = destination;
    }

    /**
     * this returns the flight delay detail
     *
     * @return
     */
    public double getDelay() {
        return delay;
    }

    /**
     * this sets the flight delay detail
     */
    public void setDelay(double delay) {
        this.delay = delay;
    }

    /**
     * @return the canceled detail of the flight
     */
    public double getCancelled() {
        return cancelled;
    }

    /**
     * this sets the flight canceled detail
     *
     * @param cancelled
     */
    public void setCancelled(double cancelled) {
        this.cancelled = cancelled;
    }

    /**
     * @return the time detail of the fight
     */
    public double getTime() {
        return time;
    }

    /**
     * this sets the flight time detail
     *
     * @param time
     */
    public void setTime(double time) {
        this.time = time;
    }

    /**
     * @return the distance detail of the fight
     */
    public double getDistance() {
        return distance;
    }

    /**
     * this sets the flight distance detail
     *
     * @param distance
     */
    public void setDistance(double distance) {
        this.distance = distance;
    }

    /**
     * @return the price detail of the fight
     */
    public double getPrice() {
        return price;
    }

    /**
     * this sets the flight price detail
     *
     * @param price
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * @return the string version of the fight object
     */
    @Override
    public String toString() {
        return "Flight{" + "origin=" + origin + ", destination=" + destination + ", carrier='" + carrierList.toString()
                + '\'' + ", delay=" + delay + ", cancelled=" + cancelled + ", time=" + time + ", distance=" + distance
                + ", price=" + price + '}';
    }
}

