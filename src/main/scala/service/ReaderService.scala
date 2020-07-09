package service

import org.apache.spark.sql.{DataFrame, SparkSession}

object ReaderService {
  def getEventDf(sparkSession: SparkSession, inputPath: String) : DataFrame = {
    sparkSession.read.json(inputPath)
  }

}
