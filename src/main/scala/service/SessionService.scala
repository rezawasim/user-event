package service

import org.apache.spark.sql.DataFrame
import org.apache.spark.sql.functions._
import constant.KeyConstants._


object SessionService {
  def getAverageSessionDuration(eventDF: DataFrame, appNameEncList: List[Int]) : DataFrame = {
    val sessionDurationDf = eventDF.filter(col(appNameEnc).isin(appNameEncList:_*))
      .groupBy(sessionId, appNameEnc)
      .agg(min(eventTs).alias("first"), max(eventTs).alias("last"))
      .withColumn("duration", col("last") - col("first"))
    sessionDurationDf.groupBy(appNameEnc)
      .agg(count(sessionId).alias("sessionCount"), sum("duration").alias("total_duration"))
      .withColumn(averageDuration, col("total_duration")/(col("sessionCount") * lit(1000)))
      .select(appNameEnc, averageDuration)
  }


}
