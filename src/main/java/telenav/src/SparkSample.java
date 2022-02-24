package telenav.src;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;

import java.io.File;

import static org.apache.spark.sql.functions.*;

/**
 * @program: spark_sample
 * @description:
 * @author: Lin.wang
 * @create: 2022-01-04 14:29
 **/
public class SparkSample {

    public void sample() {
        File f = new File("src/main/resources/tables");
        String waysPath = "file:///" + f.getAbsolutePath() + "/*WAYS*";
        String nodesPath = "file:///" + f.getAbsolutePath() + "/*NODES*";
        String relationsPath = "file:///" + f.getAbsolutePath() + "/*RELATIONS*";
        String relationMembersPath = "file:///" + f.getAbsolutePath() + "/*RELATION_MEMBERS*";
        // startup spark
        SparkSession sparkSession = SparkSession.builder().master("local[*]").appName("handle data").getOrCreate();
        sparkSession.conf().set("spark.sql.crossJoin.enabled", "true");
        sparkSession.sparkContext().setLogLevel("WARN");

        // read files
        // Dataset<Row> csvData = sparkSession.read().jdbc();  connect with DB if you want
        Dataset<Row> waysData = sparkSession.read().option("delimiter", "`").csv(waysPath);
        Dataset<Row> nodesData = sparkSession.read().option("delimiter", "`").csv(nodesPath);
        Dataset<Row> relationsData = sparkSession.read().option("delimiter", "`").csv(relationsPath);
        Dataset<Row> relationMembersData = sparkSession.read().option("delimiter", "`").csv(relationMembersPath);

        // display dataset
        waysData.show(false);
        relationsData.show(false);
        relationMembersData.show(false);
        // table join
        // sample r -> rm -> w
        relationsData.as("r")
                .joinWith(relationMembersData.as("rm"), col("r._c0").equalTo(col("rm._c0")), "inner").as("r_rm")
                .joinWith(waysData.as("w"), col("w._c0").equalTo(col("r_rm._2._c1")))
                .show(false);
        // sample select
        waysData.createOrReplaceTempView("ways");
        sparkSession.sql("select * from ways limit 3").show(false);
        // sample filter
        waysData.filter(lit(not(col("_c5").equalTo("\\N")))).show(false);

        // close spark
        sparkSession.close();

    }
}
