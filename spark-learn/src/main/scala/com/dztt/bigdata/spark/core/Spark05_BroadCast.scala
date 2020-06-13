package com.dztt.bigdata.spark.core

import org.apache.spark.{SparkConf, SparkContext}

object Spark05_BroadCast {
  def main(args: Array[String]): Unit = {

    val config: SparkConf = new SparkConf().setMaster("local[*]").setAppName("WorldCount")

    //创建spark创建上下文对象
    val sc = new SparkContext(config)

    val dataRDD = sc.makeRDD(List(1, 2, 3, 4), 2)

    //val i = dataRDD.reduce(_+_)
    //println(i)

    var sum = 0
    val value = sc.broadcast(sum)

    //dataRDD.foreach(i =>sum=sum+i)
    dataRDD.foreach {
      case i=>{
        //执行累加器的累加功能
        value
      }
    }

    sc.stop();
  }
}
