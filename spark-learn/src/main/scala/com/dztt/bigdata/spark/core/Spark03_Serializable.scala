package com.dztt.bigdata.spark.core

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

//行动算子，调用行动算子需要调用作业（JOB）

object Spark03_Serializable {
  def main(args: Array[String]): Unit = {

    val config: SparkConf = new SparkConf().setMaster("local[*]").setAppName("WorldCount")

    //创建spark创建上下文对象
    val sc = new SparkContext(config)

    val listRDD: RDD[String] = sc.makeRDD(Array("hadoop", "spark", "hive", "stream"))

    //driver 端执行 且 serach需要序列号
    val search = new Search("h")

    //在executor端执行
    val match1: RDD[String] = search.getMatch1(listRDD)
    match1.collect().foreach(println)

    sc.stop()

  }
}

//class Search(query: String) {
class Search(query: String) extends Serializable {
  def isMatch(s: String): Boolean = {
    s.contains(query)
  }

  def getMatch1(rdd: RDD[String]): RDD[String] = {
    rdd.filter(isMatch)
  }

  def getMatch2(rdd: RDD[String]): RDD[String] = {
    val q = query; //driver中执行
    rdd.filter(x => x.contains(q)) //executor中执行
  }
}
