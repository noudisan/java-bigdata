package com.dztt.bigdata.spark.core

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object Spark01_Oper1 {
  def main(args: Array[String]): Unit = {

    val config: SparkConf = new SparkConf().setMaster("local[*]").setAppName("WorldCount")

    //创建spark创建上下文对象
    val sc = new SparkContext(config)

    val listRDD: RDD[Int] = sc.makeRDD(1 to 10)

    //所有RDD算子的计算功能全部由Executor执行
    //val mapRDD: RDD[Int] = listRDD.map(x => x * 2)
    val mapRDD: RDD[Int] = listRDD.map(_ * 2)

    mapRDD.collect().foreach(println)


  }
}
