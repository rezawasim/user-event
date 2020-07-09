package sessionservice
import constant.KeyConstants._
import org.junit.runner.RunWith
import org.scalatest._
import service.SessionService
import utility.TestWrapper
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class SessionServiceTest extends FunSuite with TestWrapper {

  import spark.implicits._

  test("session duration test"){
    val expectedDuration = 275.494375
    val eventDf  = Seq(
      (1,2,1548765652448L),
      (1,2,1548765657523L),
      (1,2,1548765678331L),
      (1,2,1548765689929L),
      (1,2,1548765690024L),
      (1,2,1548766228855L),
      (1,2,1548766256038L),
      (1,2,1548766487031L),
      (1,2,1548766487116L),
      (8,2,1548765694616L),
      (19,2,1548767147279L),
      (19,2,1548767147898L),
      (19,2,1548767149735L),
      (20,2,1548765042002L),
      (20,2,1548765049889L),
      (20,2,1548765213621L),
      (20,2,1548765215238L),
      (20,2,1548765216104L),
      (20,2,1548765218956L),
      (27,2,1548765276854L),
      (50,2,1548765079562L),
      (50,2,1548765152634L),
      (50,2,1548765158586L),
      (55,2,1548765002511L),
      (55,2,1548765680714L),
      (55,2,1548765681301L),
      (55,2,1548765690359L),
      (55,2,1548765718061L),
      (55,2,1548765727175L),
      (55,2,1548765753170L),
      (55,2,1548765759760L),
      (55,2,1548766026914L),
      (55,2,1548766044028L),
      (55,2,1548766113364L),
      (58,2,1548765938073L)
      ).toDF(sessionId, appNameEnc, eventTs)
    val sessionDf = SessionService.getAverageSessionDuration(eventDf, List(1, 2))
    val row = sessionDf.take(1)
   assert(row(0).getAs[Double](averageDuration) == expectedDuration)

  }

  test("session duration test for appName"){
    val expectedDuration_1 = 834.668
    val expectedDuration_2 = 176.954
    val eventDf  = Seq(
      (1,1,1548765652448L),
      (1,1,1548766487116L),
      (20,2,1548765042002L),
      (20,2,1548765218956L),
      (50,3,1548765079562L),
      (50,3,1548765158586L),
      (55,3,1548765002511L),
      (55,3,1548766113364L)
    ).toDF(sessionId, appNameEnc, eventTs)
    val sessionDf = SessionService.getAverageSessionDuration(eventDf, List(1, 2))
    val rowList = sessionDf.orderBy(appNameEnc).take(2)
    assert(rowList(0).getAs[Double](averageDuration) == expectedDuration_1)
    assert(rowList(1).getAs[Double](averageDuration) == expectedDuration_2)
  }

  test("empty df test"){
    val eventDf  = Seq(
      (1,1,1548765652448L),
      (1,1,1548766487116L),
      (20,2,1548765042002L),
      (20,2,1548765218956L),
      (50,3,1548765079562L),
      (50,3,1548765158586L),
      (55,3,1548765002511L),
      (55,3,1548766113364L)
    ).toDF(sessionId, appNameEnc, eventTs)
    val sessionDf = SessionService.getAverageSessionDuration(eventDf, List(4))
    val rowList = sessionDf.take(1)
    assert(rowList.length == 0)
  }
}
