package service

import org.apache.spark.sql.{DataFrame, SaveMode}

object WriterService {
  def saveDf(df: DataFrame, outputPath: String): Unit = {
      df.coalesce(1).write.option("header", "true").mode(SaveMode.Overwrite).csv(outputPath)
  }

  def consolePrint(df: DataFrame): Unit = {
      df.show(false)
  }



}
