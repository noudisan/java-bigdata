package com.dztt.bigdata.spark.core

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object Spark01_Oper11 {
  def main(args: Array[String]): Unit = {

    val config: SparkConf = new SparkConf().setMaster("local[*]").setAppName("WorldCount")

    //创建spark创建上下文对象
    val sc = new SparkContext(config)

    val arrayRDD: RDD[(Int, String)] = sc.makeRDD(Array((1, "aaa"), (2, "bbb"), (3, "ccc"), (4, "ddd")))

    val rdd2: RDD[(Int, String)] = arrayRDD.partitionBy(new org.apache.spark.HashPartitioner(2))

    rdd2.glom().collect().foreach(x => {
      println(x.mkString(","))
    })

  }
}
