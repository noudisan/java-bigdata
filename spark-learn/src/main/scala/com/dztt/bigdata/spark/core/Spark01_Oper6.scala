package com.dztt.bigdata.spark.core

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object Spark01_Oper6 {
  def main(args: Array[String]): Unit = {

    val config: SparkConf = new SparkConf().setMaster("local[*]").setAppName("WorldCount")

    //创建spark创建上下文对象
    val sc = new SparkContext(config)

    val listRDD:RDD[Int] = sc.makeRDD(1 to 16)

    val groupByRDD:RDD[(Int, Iterable[Int])]= listRDD.groupBy(i => i % 2)

    groupByRDD.collect().foreach(println)


  }
}
