package service

import environmentutility.ConfigurationProperties.{master, shufflePartitions}
import org.apache.spark.SparkConf
import org.apache.spark.sql.SparkSession

object SparkService {

  def getSparkSession(appName: String) : SparkSession = {
    val conf = new SparkConf().setMaster("local[4]").setAppName(appName)


    val sparkSession = SparkSession.builder
            .config(conf)
            .config("spark.sql.shuffle.partitions", shufflePartitions)
        .config("spark.eventLog.enabled", "false")
//            .config("spark.sql.autoBroadcastJoinThreshold", "0")
//            .config("spark.serializer", "org.apache.spark.serializer.KryoSerializer")
      .getOrCreate()
//    sparkSession.sparkContext.setLogLevel("INFO")
//    System.setProperty("com.amazonaws.services.s3.enableV4", "true")
//    sparkSession.sparkContext.hadoopConfiguration.set("fs.s3a.impl","org.apache.hadoop.fs.s3a.S3AFileSystem")
//    sparkSession.sparkContext.hadoopConfiguration.set("com.amazonaws.services.s3.enableV4", "true")
//    sparkSession.sparkContext.hadoopConfiguration.set("fs.s3a.endpoint", "s3.ap-south-1.amazonaws.com")
    sparkSession
  }

}
