package com.dztt.bigdata.spark.core

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object Spark01_Oper3 {
  def main(args: Array[String]): Unit = {

    val config: SparkConf = new SparkConf().setMaster("local[*]").setAppName("WorldCount")

    //创建spark创建上下文对象
    val sc = new SparkContext(config)

    val listRDD: RDD[Int] = sc.makeRDD(1 to 10)

    //mapPartitions 可以对一个RDD所有的分区进行遍历
    //效率优于map，可能会出现oom
    val tupleRDD: RDD[(Int, String)] = listRDD.mapPartitionsWithIndex({
      case (num, datas) => {
        datas.map((_, "分区号：" + num))
      }
    })

    tupleRDD.collect().foreach(println)

  }
}
