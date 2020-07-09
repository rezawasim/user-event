package userservice

import constant.KeyConstants._
import org.junit.runner.RunWith
import org.scalatest._
import org.scalatest.junit.JUnitRunner
import service.{SessionService, UserService}
import utility.TestWrapper

@RunWith(classOf[JUnitRunner])
class UserServiceTest extends FunSuite with TestWrapper {

  import spark.implicits._

  test("region_userId_count_test"){
    val expectedUserCount = 6
    val expectedDistinctUserIdCount = 2
    val eventDf  = Seq(
      ("SIKKIM", 1671),
      ("SIKKIM", 1671),
      ("SIKKIM", 1671),
      ("SIKKIM", 1671),
      ("SIKKIM", 31),
      ("SIKKIM", 31)
      ).toDF(region, userId)
    val regionDf = UserService.getRegionUserCount(eventDf)
    val row = regionDf.take(1)(0)
    assert(row.getAs[Long](userIdCount) == expectedUserCount)
    assert(row.getAs[Long](distinctUserIdCount) == expectedDistinctUserIdCount)
  }

  test("region_filteration_test"){
    val eventDf  = Seq(
      (null, 1671),
      (null, 1671),
      ("-", 1671),
      ("-", 1671)
    ).toDF(region, userId)
    val regionDf = UserService.getRegionUserCount(eventDf)
    val rowList = regionDf.take(2)
    assert(rowList.length == 0)
  }

}
