package environmentutility

import environmentutility.property.{PropertyNames, PropertyUtil}

object ConfigurationProperties {
    val master:String = PropertyUtil.getEnvironmentProperties.getProperty(PropertyNames.SPARK_MASTER)
    val shufflePartitions: String = PropertyUtil.getEnvironmentProperties.getProperty(PropertyNames.SHUFFLE_PARTITIONS)
    val dateTimeFormatter = "yyyy-MM-dd HH:mm:ss.SSS"
    val timeZone = "Asia/Kolkata"
    val tsvSeparator = "\t"
}
