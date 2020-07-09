package service

import constant.KeyConstants._
import org.apache.spark.sql.DataFrame
import org.apache.spark.sql.expressions.Window
import org.apache.spark.sql.functions._

object EventService {
  def getSequenceEvent(eventDF: DataFrame, seqLength: Int): DataFrame = {
    val windowSpec = Window.partitionBy(userId).orderBy(col(eventTs))
    val df = eventDF
      .withColumn(eventSequenceNum, when(col(eventLaenc) === lag(col(eventLaenc), 1).over(windowSpec),
        0).otherwise(1))
    df.withColumn(eventSequenceNum, sum(col(eventSequenceNum)).over(windowSpec))
      .filter(col(eventSequenceNum) <= lit(seqLength))
      .groupBy(userId).pivot(eventSequenceNum)
      .agg(min(eventLaenc).alias(action), count(eventSequenceNum).alias(actionCount))
  }

}
