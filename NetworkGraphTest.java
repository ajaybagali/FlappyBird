package assignment11;

import assignment11.BestPath;
import assignment11.FlightCriteria;
import assignment11.NetworkGraph;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.HashMap;

import static org.junit.Assert.*;

public class NetworkGraphTest {


    @Test
    public void testNoPath() throws FileNotFoundException {
        NetworkGraph graph = new NetworkGraph(new FileInputStream("airline/testFile.csv/"));
        BestPath path = graph.getBestPath("A", "B", FlightCriteria.DISTANCE);
        assertEquals(path.getPath().size(), 0);

    }


    @Test
    public void testSinglePathCancelled() throws FileNotFoundException {
        NetworkGraph graph = new NetworkGraph(new FileInputStream(new File("airline/singlePath.csv")));
        BestPath path = graph.getBestPath("LJM", "KRE", FlightCriteria.CANCELED, "AB");

        assertEquals(path.getPath().size(), 2);
        assertEquals(path.getPath().get(0), "LJM");
        assertEquals(path.getPath().get(1), "KRE");
        assertEquals(Double.valueOf(path.getPathCost()), Double.valueOf(1));
        assertEquals(path.getPath().size(), 2);
    }


    @Test
    public void testSinglePath() throws FileNotFoundException {
        NetworkGraph graph = new NetworkGraph(new FileInputStream(new File("airline/singlePath.csv")));
        BestPath path = graph.getBestPath("LJM", "KRE", FlightCriteria.DISTANCE, "AB");

        assertEquals(path.getPath().size(), 2);
        assertEquals(path.getPath().get(0), "LJM");
        assertEquals(path.getPath().get(1), "KRE");
        assertEquals(Double.valueOf(path.getPathCost()), Double.valueOf(1394));
        assertEquals(path.getPath().size(), 2);
    }

    @Test
    public void testNoPathOneFlightSameOriginSameDestinationPath() throws FileNotFoundException {
        NetworkGraph graph = new NetworkGraph(new FileInputStream(new File("airline/singlePathSameOD.csv")));
        BestPath path = graph.getBestPath("PTV", "PTV", FlightCriteria.DISTANCE, "AB");

        assertEquals(path.getPath().size(), 0);
        assertTrue(path.getPath().isEmpty());
        assertEquals(Double.valueOf(path.getPathCost()), Double.valueOf(0));
    }

    @Test
    public void testNoPathOneFlightSameOriginSameDestinationPrice() throws FileNotFoundException {
        NetworkGraph graph = new NetworkGraph(new FileInputStream(new File("airline/singlePathSameOD.csv")));
        BestPath path = graph.getBestPath("PTV", "PTV", FlightCriteria.PRICE, "AB");

        assertEquals(path.getPath().size(), 0);
        assertTrue(path.getPath().isEmpty());
        assertEquals(Double.valueOf(path.getPathCost()), Double.valueOf(0));
    }

    @Test
    public void testNoAirportFlights() throws FileNotFoundException {
        NetworkGraph graph = new NetworkGraph(new FileInputStream(new File("airline/singlePathSameOD.csv")));
        BestPath path = graph.getBestPath("A", "A", FlightCriteria.DISTANCE, "AB");

        assertEquals(path.getPath().size(), 0);
        assertTrue(path.getPath().isEmpty());
        assertEquals(Double.valueOf(path.getPathCost()), Double.valueOf(0));
    }

    @Test
    public void testTwoSamePaths1Distance() throws FileNotFoundException {
        NetworkGraph graph = new NetworkGraph(new FileInputStream(new File("airline/testTwoSamePaths1.csv")));
        BestPath path = graph.getBestPath("TOJ", "KSQ", FlightCriteria.DISTANCE, "AB");

        assertEquals(path.getPath().size(), 3);
        assertEquals(path.getPath().get(0), "TOJ");
        assertEquals(path.getPath().get(1), "IWI");
        assertEquals(path.getPath().get(2), "KSQ");
        assertEquals(Double.valueOf(path.getPathCost()), Double.valueOf(491));

    }

    @Test
    public void testTwoSamePathsDelay() throws FileNotFoundException {
        NetworkGraph graph = new NetworkGraph(new FileInputStream(new File("airline/testTwoSamePaths1.csv")));
        BestPath path = graph.getBestPath("TOJ", "KSQ", FlightCriteria.DELAY, "AB");

        assertEquals(path.getPath().size(), 3);
        assertEquals(path.getPath().get(0), "TOJ");
        assertEquals(path.getPath().get(1), "IWI");
        assertEquals(path.getPath().get(2), "KSQ");
        assertEquals(Double.valueOf(path.getPathCost()), Double.valueOf(588.0));

    }

    @Test
    public void testTwoSamePathsCancelled() throws FileNotFoundException {
        NetworkGraph graph = new NetworkGraph(new FileInputStream(new File("airline/testTwoSamePaths1.csv")));
        BestPath path = graph.getBestPath("TOJ", "KSQ", FlightCriteria.CANCELED, "AB");

        assertEquals(path.getPath().size(), 3);
        assertEquals(path.getPath().get(0), "TOJ");
        assertEquals(path.getPath().get(1), "IWI");
        assertEquals(path.getPath().get(2), "KSQ");
        assertEquals(Double.valueOf(path.getPathCost()), Double.valueOf(1.0));

    }


    @Test
    public void testTwoSamePaths1Price() throws FileNotFoundException {
        NetworkGraph graph = new NetworkGraph(new FileInputStream(new File("airline/testTwoSamePaths1.csv")));
        BestPath path = graph.getBestPath("TOJ", "KSQ", FlightCriteria.PRICE, "AB");

        assertEquals(path.getPath().size(), 3);
        assertEquals(path.getPath().get(0), "TOJ");
        assertEquals(path.getPath().get(1), "IAD");
        assertEquals(path.getPath().get(2), "KSQ");

    }

    @Test
    public void testTwoSamePaths1Time() throws FileNotFoundException {
        NetworkGraph graph = new NetworkGraph(new FileInputStream(new File("airline/testTwoSamePaths1.csv")));
        BestPath path = graph.getBestPath("TOJ", "KSQ", FlightCriteria.TIME, "AB");

        assertEquals(path.getPath().size(), 3);
        assertEquals(path.getPath().get(0), "TOJ");
        assertEquals(path.getPath().get(1), "IWI");
        assertEquals(path.getPath().get(2), "KSQ");
        //calculation problems
        assertEquals(Double.valueOf(path.getPathCost()), Double.valueOf(136));

    }

    @Test
    public void testTwoSamePaths1SameDelay() throws FileNotFoundException {
        NetworkGraph graph = new NetworkGraph(new FileInputStream(new File("airline/testTwoSamePaths1.csv")));
        BestPath path = graph.getBestPath("TOJ", "KSQ", FlightCriteria.DELAY, "AB");
        assertEquals(path.getPath().size(), 3);
        assertEquals(path.getPath().get(0), "TOJ");
        assertEquals(path.getPath().get(1), "IWI");
        assertEquals(path.getPath().get(2), "KSQ");
        assertEquals(Double.valueOf(path.getPathCost()), Double.valueOf(588));

    }

    @Test
    public void testTwoSamePaths1Canceled() throws FileNotFoundException {
        NetworkGraph graph = new NetworkGraph(new FileInputStream(new File("airline/testTwoSamePaths1.csv")));
        BestPath path = graph.getBestPath("TOJ", "KSQ", FlightCriteria.CANCELED, "AB");
        assertEquals(path.getPath().size(), 3);
        assertEquals(path.getPath().get(0), "TOJ");
        assertEquals(path.getPath().get(1), "IWI");
        assertEquals(path.getPath().get(2), "KSQ");
        assertEquals(Double.valueOf(path.getPathCost()), Double.valueOf(1));

    }

    @Test
    public void testTwoSamePathsCanceledMatt() throws FileNotFoundException {
        NetworkGraph graph = new NetworkGraph(new FileInputStream(new File("airline/testTwoSamePathsMatt.csv")));
        BestPath path = graph.getBestPath("A", "D", FlightCriteria.CANCELED, null);
        assertEquals(path.getPath().size(), 3);
        assertEquals(path.getPath().get(0), "A");
        assertEquals(path.getPath().get(1), "B");
        assertEquals(path.getPath().get(2), "D");
        assertEquals(Double.valueOf(path.getPathCost()), Double.valueOf(1.725));

    }

    @Test
    public void testTwoSamePathsNegativePrice() throws FileNotFoundException {
        NetworkGraph graph = new NetworkGraph(new FileInputStream(new File("airline/testTwoSamePathsMatt.csv")));
        BestPath path = graph.getBestPath("A", "D", FlightCriteria.PRICE, null);
        assertEquals(path.getPath().size(), 3);
        assertEquals(path.getPath().get(0), "A");
        assertEquals(path.getPath().get(1), "B");
        assertEquals(path.getPath().get(2), "D");
        assertEquals(Double.valueOf(path.getPathCost()), Double.valueOf(3.45));

    }

    @Test
    public void testTwoSamePathsNegativePriceDuplicates() throws FileNotFoundException {
        NetworkGraph graph = new NetworkGraph(new FileInputStream(new File("airline/testTwoSamePathsMatt.csv")));
        BestPath path = graph.getBestPath("A", "D", FlightCriteria.PRICE, "WN");
        assertEquals(path.getPath().size(), 3);
        assertEquals(path.getPath().get(0), "A");
        assertEquals(path.getPath().get(1), "B");
        assertEquals(path.getPath().get(2), "D");
        assertEquals(Double.valueOf(path.getPathCost()), Double.valueOf(3.45));

    }

    @Test
    public void simpleCancelTest() throws FileNotFoundException {
        NetworkGraph graph = new NetworkGraph(new FileInputStream(new File("airline/shap.csv")));
        BestPath path = graph.getBestPath("A", "B", FlightCriteria.CANCELED);
        assertEquals(path.getPath().size(), 2);
        assertEquals(path.getPath().get(0), "A");
        assertEquals(path.getPath().get(1), "B");

        assertEquals(Double.valueOf(path.getPathCost()), Double.valueOf(0.5));

    }

    @Test
    public void negativeCanceledCost() throws FileNotFoundException {
        NetworkGraph graph = new NetworkGraph(new FileInputStream(new File("airline/negetiveTests.csv")));
        BestPath path = graph.getBestPath("HEG", "ABA", FlightCriteria.PRICE, "DL");
        assertEquals(path.getPath().size(), 3);
        assertEquals(path.getPath().get(0), "HEG");
        assertEquals(path.getPath().get(1), "DJR");
        assertEquals(path.getPath().get(2), "ABA");
        assertEquals(Double.valueOf(path.getPathCost()), Double.valueOf(681.415));

    }


    @Test
    public void largeFileDistanceCheckPWPtoPHO() throws FileNotFoundException {
        NetworkGraph graph = new NetworkGraph(new FileInputStream(new File("airline/testfile.csv")));
        BestPath path = graph.getBestPath("PWP", "PHO", FlightCriteria.DISTANCE);
        assertEquals(path.getPath().size(), 3);
        assertEquals(path.getPath().get(0), "PWP");
        assertEquals(path.getPath().get(1), "MPT");
        assertEquals(path.getPath().get(2), "PHO");

        assertEquals(Double.valueOf(path.getPathCost()), Double.valueOf(888.0));

    }


    @Test
    public void largeFileDistanceCheckFromXHGToCWX() throws FileNotFoundException {
        NetworkGraph graph = new NetworkGraph(new FileInputStream(new File("airline/testfile.csv")));
        BestPath path = graph.getBestPath("XHG", "CWX", FlightCriteria.DISTANCE);
        assertEquals(path.getPath().size(), 5);

        assertEquals(Double.valueOf(path.getPathCost()), Double.valueOf(1339.0));

    }


    @Test
    public void largeFilePriceCheckFromXHGToCWX() throws FileNotFoundException {
        NetworkGraph graph = new NetworkGraph(new FileInputStream(new File("airline/testfile.csv")));
        BestPath path = graph.getBestPath("XHG", "CWX", FlightCriteria.PRICE);
        assertEquals(path.getPath().size(), 3);

        assertEquals(Double.valueOf(path.getPathCost()), Double.valueOf(579.06));

    }

    @Test
    public void largeFileCancelledCheckFromXHGToCWX() throws FileNotFoundException {
        NetworkGraph graph = new NetworkGraph(new FileInputStream(new File("airline/testfile.csv")));
        BestPath path = graph.getBestPath("XHG", "CWX", FlightCriteria.CANCELED);
        assertEquals(path.getPath().size(), 6);

        assertEquals(Double.valueOf(path.getPathCost()), Double.valueOf(0.0));

    }

    @Test
    public void largeFileDelayCheckFromXHGToCWX() throws FileNotFoundException {
        NetworkGraph graph = new NetworkGraph(new FileInputStream(new File("airline/testfile.csv")));
        BestPath path = graph.getBestPath("XHG", "CWX", FlightCriteria.DELAY);
        assertEquals(path.getPath().size(), 7);

        assertEquals(path.getPath().get(0), "XHG");
        assertEquals(path.getPath().get(1), "MTC");
        assertEquals(path.getPath().get(2), "CKE");
        assertEquals(Double.valueOf(path.getPathCost()), Double.valueOf(219.0));

    }


    @Test
    public void largeFileTimeCheckFromXHGToCWX() throws FileNotFoundException {
        NetworkGraph graph = new NetworkGraph(new FileInputStream(new File("airline/testfile.csv")));
        BestPath path = graph.getBestPath("XHG", "CWX", FlightCriteria.TIME);
        assertEquals(path.getPath().size(), 5);
        assertEquals(Double.valueOf(path.getPathCost()), Double.valueOf(191.0));

    }
}