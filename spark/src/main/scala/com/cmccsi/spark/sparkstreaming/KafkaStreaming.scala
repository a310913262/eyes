package com.cmccsi.spark.sparkstreaming

import com.cmccsi.spark.persistent.service.{DaoProxy, PersonService}
import com.cmccsi.spark.persistent.service.impl.PersonServiceImpl
import net.minidev.json.JSONObject
import net.minidev.json.parser.JSONParser
import org.apache.spark.sql.{DataFrame, ForeachWriter, Row, SparkSession}

object KafkaStreaming {
  val spark = SparkSession
    .builder
    .appName("StructuredNetworkWordCount").master("local[2]")
    .getOrCreate()

  import spark.implicits._

  def main(args: Array[String]): Unit = {
    var df = spark
      .readStream
      .format("kafka")
      .option("kafka.bootstrap.servers", "localhost:9092")
      .option("subscribe", "spark")
      .option("enable.auto.commit", false) // Cannot be set to true in Spark Strucutured Streaming https://spark.apache.org/docs/latest/structured-streaming-kafka-integration.html#kafka-specific-configurations
      .option("group.id", "spark")
      .option("failOnDataLoss", false) // when starting a fresh kafka (default location is temporary (/tmp) and cassandra is not (var/lib)), we have saved different offsets in Cassandra than real offsets in kafka (that contains nothing)
      .option("startingOffsets", "latest") //this only applies when a new query is started and that resuming will always pick up from where the query left off
      .load()
      .selectExpr("CAST(key AS STRING)", "CAST(value AS STRING)")
      .as[(String, String)]


    //    val df = spark
    //      .readStream
    //      .format("kafka")
    //      .option("kafka.bootstrap.servers", "localhost:9092")
    //      .option("subscribe", "spark")
    //      .option("enable.auto.commit", false) // Cannot be set to true in Spark Strucutured Streaming https://spark.apache.org/docs/latest/structured-streaming-kafka-integration.html#kafka-specific-configurations
    //      .option("group.id", "spark")
    //      .option("failOnDataLoss", false) // when starting a fresh kafka (default location is temporary (/tmp) and cassandra is not (var/lib)), we have saved different offsets in Cassandra than real offsets in kafka (that contains nothing)
    //      .option("startingOffsets", "earliest")
    //      .load()
    //      .selectExpr("CAST(key AS STRING)", "CAST(value AS STRING)")
    //      .as[(String, String)]


    //    val query = df.writeStream
    //      .queryName("Debug Stream Kafka")
    //      .format("console")
    //      .start()


    df
      .writeStream
      .foreach(new ForeachWriter[(String, String)] {
        override def open(partitionId: Long, epochId: Long): Boolean = {
          return true
        }

        override def process(row: (String, String)): Unit = {
          var value = row._2
          val jsonParser = new JSONParser()
          var json: JSONObject = jsonParser.parse(value).asInstanceOf[JSONObject]
          var name = json.getAsString("name")
          var dp= DaoProxy.getInvoke((new PersonServiceImpl).getClass).asInstanceOf[PersonServiceImpl]
          dp.saveOrUpdate(name)
        }

        override def close(errorOrNull: Throwable): Unit = {

        }
      }).start()


    spark.streams.awaitAnyTermination()
  }
}
