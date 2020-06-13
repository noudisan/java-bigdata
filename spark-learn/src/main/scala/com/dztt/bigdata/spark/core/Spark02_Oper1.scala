package com.dztt.bigdata.spark.core

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object Spark02_Oper1 {
  def main(args: Array[String]): Unit = {

    val config: SparkConf = new SparkConf().setMaster("local[*]").setAppName("WorldCount")

    //创建spark创建上下文对象
    val sc = new SparkContext(config)

    val listRDD: RDD[Int] = sc.makeRDD(1 to 10,2)

    val rdd:Int = listRDD.reduce(_ + _)
    println(rdd)


    var arrayRDD = sc.makeRDD(Array(("a",1),("a",3),("c",3),("d",1)))

    val tuple:(String, Int) = arrayRDD.reduce((x, y) => (x._1 + y._1, x._2 + y._2))
    println(tuple)

    val count = listRDD.count()
    println(count)

    val ints = listRDD.take(3)
    println(ints.foreach(print))

    val orderInts =listRDD.takeOrdered(3);
    println(orderInts.foreach(print))

  }
}
