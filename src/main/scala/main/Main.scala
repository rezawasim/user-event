package main
import service._
object Main {
  def main(args: Array[String]): Unit = {

    val inputPath = "./sampleFile/*.json"
    val applicationName = "user-session-aggregation"
    val eventSeqLength = 2
    val appNameEncList = List(1, 2)

    val sparkSession = SparkService.getSparkSession(applicationName)

    val eventDF = ReaderService.getEventDf(sparkSession, inputPath)



    val sessionDurationDf = SessionService.getAverageSessionDuration(eventDF, appNameEncList)

    val userRegionDf = UserService.getRegionUserCount(eventDF)
    val eventSequenceDF = EventService.getSequenceEvent(eventDF, eventSeqLength)
    WriterService.saveDf(sessionDurationDf, "./output/sessionDuration")
    WriterService.saveDf(userRegionDf, "./output/userRegion")
    WriterService.saveDf(eventSequenceDF, "./output/eventSequence")
    sparkSession.close()

  }

}
