package eventservicetest

import constant.KeyConstants._
import org.junit.runner.RunWith
import org.scalatest._
import org.scalatest.junit.JUnitRunner
import service.{EventService}
import utility.TestWrapper

@RunWith(classOf[JUnitRunner])
class EventServiceTest extends FunSuite with TestWrapper {

  import spark.implicits._

  test("event_count_test"){
    val expectedFirstAction = 2
    val expectedFirstActionCount = 3
    val expectedSecondAction = 3
    val expectedSecondActionCount = 2
    val eventDf  = Seq(
      (100,2,1548765652448L),
      (100,2,1548765657523L),
      (100,2,1548765678331L),
      (100,3,1548765690024L),
      (100,3,1548766228855L),
      (100,4,1548766256038L),
      (100,4,1548767256038L)
    ).toDF(userId, eventLaenc, eventTs)
    val sequenceDf = EventService.getSequenceEvent(eventDf, 2)
    val row = sequenceDf.take(1)(0)
    assert(row.getAs[Int]("1_" + action) == expectedFirstAction)
    assert(row.getAs[Long]("1_" + actionCount) == expectedFirstActionCount)
    assert(row.getAs[Int]("2_"+action) == expectedSecondAction)
    assert(row.getAs[Long]("2_"+actionCount) == expectedSecondActionCount)
  }
  test("event_count_test_null_second"){
    val expectedFirstAction = 2
    val expectedFirstActionCount = 4
    val eventDf  = Seq(
      (100,2,1548765652448L),
      (100,2,1548765657523L),
      (100,2,1548765678331L),
      (100,2,1548765690024L),
      (102,2,1548767147898L),
      (102,3,1548767149735L)
    ).toDF(userId, eventLaenc, eventTs)
    val sequenceDf = EventService.getSequenceEvent(eventDf, 2)
    val row = sequenceDf.orderBy(userId).take(2)(0)
    assert(row.getInt(1) == expectedFirstAction)
    assert(row.getLong(2)== expectedFirstActionCount)
    assert(row.get(3) == null)
    assert(row.get(4) == null)
  }





}
