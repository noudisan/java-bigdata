package com.dztt.bigdata.spark.sql

import org.apache.spark.SparkConf
import org.apache.spark.sql.SparkSession

object SparkSql03_Sql {

  def main(args: Array[String]): Unit = {
    val config: SparkConf = new SparkConf().setMaster("local[*]").setAppName("SparkSql01_Demo")

    //val spark:SparkSession = new SparkSession(config)
    val spark = SparkSession.builder().config(config).getOrCreate()

    val frame = spark.read.json("in/user.json")

    frame.createOrReplaceTempView("user")

    spark.sql("select * from user").show

    spark.stop()
  }

}
