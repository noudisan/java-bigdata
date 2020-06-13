package com.dztt.bigdata.spark.core

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object Spark01_Oper7 {
  def main(args: Array[String]): Unit = {

    val config: SparkConf = new SparkConf().setMaster("local[*]").setAppName("WorldCount")

    //创建spark创建上下文对象
    val sc = new SparkContext(config)

    val listRDD:RDD[Int] = sc.makeRDD(1 to 16)

    val filterRDD:RDD[Int] = listRDD.filter(i => i % 2 == 0)

    filterRDD.collect().foreach(println)


  }
}
