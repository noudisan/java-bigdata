package com.dztt.bigdata.spark.core

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object Spark01_Oper10 {
  def main(args: Array[String]): Unit = {

    val config: SparkConf = new SparkConf().setMaster("local[*]").setAppName("WorldCount")

    //创建spark创建上下文对象
    val sc = new SparkContext(config)

    val listRDD: RDD[Int] = sc.makeRDD(1 to 16, 4)

    println("缩减分区前 = " + listRDD.partitions.size)
    //数据会被打乱重组，称之为shuffle操作
    val coalesceRDD: RDD[Int] = listRDD.coalesce(3)
    println("缩减分区后 = " + coalesceRDD.partitions.size)

  }
}
