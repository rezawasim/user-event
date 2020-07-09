package utility

import org.apache.spark.sql.SparkSession
import org.scalatest.BeforeAndAfterAll

trait TestWrapper  {
  lazy val spark: SparkSession = {
    SparkSession
      .builder()
      .master("local")
      .appName("spark test example")
      .getOrCreate()
  }
}
