package telenav.src;

import org.junit.Test;

import static org.junit.Assert.*;

public class SparkSampleTest {
    @Test
    public void sparkTest(){
        SparkSample sparkSample = new SparkSample();
        sparkSample.sample();
    }

}