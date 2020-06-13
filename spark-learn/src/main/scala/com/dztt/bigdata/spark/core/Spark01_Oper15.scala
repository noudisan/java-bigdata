package com.dztt.bigdata.spark.core

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object Spark01_Oper15 {
  def main(args: Array[String]): Unit = {

    val config: SparkConf = new SparkConf().setMaster("local[*]").setAppName("WorldCount")

    //创建spark创建上下文对象
    val sc = new SparkContext(config)

    val listRDD: RDD[(String, Int)] = sc.makeRDD(List(("a", 88), ("b", 95), ("a", 91), ("b", 95), ("a", 95), ("b", 98)), 2)

    val combineRDD: RDD[(String, (Int, Int))] = listRDD.combineByKey((_, 1),
      (acc: (Int, Int), v) => (acc._1 + v, acc._2 + 1),
      (acc1: (Int, Int), acc2: (Int, Int)) => (acc1._1 + acc2._1, acc1._2 + acc2._2))

    combineRDD.collect().foreach(println)

    var resultRDD: RDD[(String, Double)] = combineRDD.map { case (key, value) => (key, value._1 / value._2.toDouble) }

    resultRDD.collect().foreach(println)


    sc.stop()

  }
}
