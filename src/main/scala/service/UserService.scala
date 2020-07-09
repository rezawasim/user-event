package service

import constant.KeyConstants._
import org.apache.spark.sql.DataFrame
import org.apache.spark.sql.functions._

object UserService {
  def getRegionUserCount(eventDF: DataFrame) : DataFrame = {
    val filterDf = eventDF.filter(col(region).isNotNull).filter(col(region) =!= "-")
    filterDf.groupBy(col(region)).agg(count(userId).alias(userIdCount),
      countDistinct(userId).alias(distinctUserIdCount))
  }

}
