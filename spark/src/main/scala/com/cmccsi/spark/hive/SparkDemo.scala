package com.cmccsi.spark.hive

import org.apache.spark.sql.hive.HiveContext
import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.sql.{Row, SQLContext, SparkSession}

object SparkDemo {
  def main(args: Array[String]): Unit = {
    //    val spark = SparkSession.builder().appName("UserData").master("spark://hadoop01:7077").enableHiveSupport().getOrCreate()
        val spark = SparkSession.builder().appName("UserData").master("local[2]").enableHiveSupport().getOrCreate()
//    val conf = new SparkConf().setAppName("UserData").setMaster("spark://hadoop01:7077")
//    val sc = new SparkContext(conf)
//    val spark = new SQLContext(sc)
//    var hive=new HiveContext(sc)
//    hive.sql("select count(SENDHOSTIP) count ,SENDHOSTIP FROM fm_stdevent GROUP BY SENDHOSTIP ORDER BY count desc").show()

    var df = spark.sql("select count(SENDHOSTIP) count ,SENDHOSTIP FROM fm_stdevent GROUP BY SENDHOSTIP ORDER BY count desc").toDF()
//    df.show()
    df.rdd.foreachPartition(fun)

  }

  def fun(result: Iterator[Row]): Unit = {
    result.foreach(t => {
      var name = t.getAs("count"): Long
      var count = t.getAs("SENDHOSTIP"): String
      println(name)
      println(count)
    })
  }
}
