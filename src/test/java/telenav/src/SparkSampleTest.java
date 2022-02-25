package telenav.src;

import org.junit.BeforeClass;
import org.junit.Test;

public class SparkSampleTest {
    static SparkSample sparkSample;

    @BeforeClass
    public static void before(){
        sparkSample = new SparkSample();
    }

    @Test
    public void simpleTest(){
        sparkSample.sample();
    }

    @Test
    public void readParquetSampleTest(){
        sparkSample.readParquetSample();
    }


}