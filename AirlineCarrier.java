package assignment11;
/**
 * This class stores the carrier of the flights
 * @author Ajay Bagali & Shap Neupane
 * @version April 19th, 2018
 *
 */
public class AirlineCarrier {

    private String carrier;
    /**
     * this constructor sets the string to the carrier
     * @param carrier
     */
    public AirlineCarrier(String carrier) {
        this.carrier = carrier;
    }
    /**
     * this method retrieves the carrier from the fight
     * @return
     */
    public String getCarrier() {
        return carrier;
    }
    /**
     * this method sets the flights carrier
     * @param carrier
     */
    public void setCarrier(String carrier) {
        this.carrier = carrier;
    }

    @Override
    /**
     * this to string method prints out the carrier as a string
     */
    public String toString() {
        return "AirlineCarrier{" +
                "carrier='" + carrier + '\'' +
                '}';
    }
}

