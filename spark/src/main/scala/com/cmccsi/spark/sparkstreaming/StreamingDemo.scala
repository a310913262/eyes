package com.cmccsi.spark.sparkstreaming

import org.apache.spark.sql.functions._
import org.apache.spark.sql.SparkSession


object StreamingDemo{
  def main(args: Array[String]): Unit = {





    val spark = SparkSession
      .builder
      .appName("StructuredNetworkWordCount").master("local[2]")
      .getOrCreate ()

    import spark.implicits._


    val lines = spark.readStream
      .format("socket")
      .option("host", "192.168.64.100")
      .option("port", 9999)
      .load()

    // Split the lines into words
    val words = lines.as[String].flatMap(_.split(","))

    // Generate running word count
    val wordCounts = words.groupBy("value").count()


    val query = wordCounts.writeStream
      .outputMode("complete")
      .format("console")
      .start()

    query.awaitTermination()

  }


}


