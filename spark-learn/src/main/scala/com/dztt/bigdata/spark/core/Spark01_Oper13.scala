package com.dztt.bigdata.spark.core

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object Spark01_Oper13 {
  def main(args: Array[String]): Unit = {

    val config: SparkConf = new SparkConf().setMaster("local[*]").setAppName("WorldCount")

    //创建spark创建上下文对象
    val sc = new SparkContext(config)

    val arrayRDD: RDD[String] = sc.makeRDD(Array("one", "two", "two", "three", "four"))

    val wordParisRDD: RDD[(String, Int)] = arrayRDD.map(word => (word, 1))

    val group = wordParisRDD.groupByKey()

    group.collect().foreach(println)

    group.map(t => (t._1, t._2.sum)).collect().foreach(println)

    //直接根据key处理
    //在shuffle之前有combine(预聚合)操作，返回结果是RDD[k,v]
    wordParisRDD.reduceByKey(_ + _).collect().foreach(println)
  }
}
