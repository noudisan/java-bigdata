package com.dztt.bigdata.spark.core

import org.apache.spark.{SparkConf, SparkContext}

object Spark05_ShareData {
  def main(args: Array[String]): Unit = {

    val config: SparkConf = new SparkConf().setMaster("local[*]").setAppName("WorldCount")

    //创建spark创建上下文对象
    val sc = new SparkContext(config)

    val dataRDD = sc.makeRDD(List(1, 2, 3, 4), 2)

    //val i = dataRDD.reduce(_+_)
    //println(i)

    var sum = 0
    //使用累加器共享变量，来累计数据

    //创建累加器
    val accumulator = sc.longAccumulator

    //dataRDD.foreach(i =>sum=sum+i)
    dataRDD.foreach {
      case i=>{
        //执行累加器的累加功能
        accumulator.add(i)
      }
    }
    println(accumulator.value)

    sc.stop();

  }
}
