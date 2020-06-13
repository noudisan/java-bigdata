package com.dztt.bigdata.spark.core

import java.util

import org.apache.spark.util.AccumulatorV2
import org.apache.spark.{SparkConf, SparkContext}

//行动算子，调用行动算子需要调用作业（JOB）

object Spark05_WordAccumulator {
  def main(args: Array[String]): Unit = {

    val config: SparkConf = new SparkConf().setMaster("local[*]").setAppName("WorldCount")

    //创建spark创建上下文对象
    val sc = new SparkContext(config)

    val dataRDD = sc.makeRDD(List("hadoop","spark","stream"), 2)

    //创建累加器
    val accumulator = new WordAccumulator()
    //注册累加器
    sc.register(accumulator)

    dataRDD.foreach {
      case i => {
        //执行累加器的累加功能
        accumulator.add(i)
      }
    }
    println(accumulator.value)

    sc.stop();

  }
}

//声明累加器
//1、继承AccumulatorV2
//2、实现抽象方法
class WordAccumulator extends AccumulatorV2[String, util.ArrayList[String]] {
  var list = new util.ArrayList[String]()

  //当前累加器是否出事状态
  override def isZero: Boolean = {
    list.isEmpty
  }

  //复制累加器
  override def copy(): AccumulatorV2[String, util.ArrayList[String]] = {
    new WordAccumulator()
  }

  //重置累加器对象
  override def reset(): Unit = {
    list.clear()
  }

  //向累加器增加数据
  override def add(v: String): Unit = {
    if (v.contains("h")) {
      list.add(v)
    }
  }

  //合并累加器
  override def merge(other: AccumulatorV2[String, util.ArrayList[String]]): Unit = {
    list.addAll(other.value)
  }

  //获取累加器的结果
  override def value: util.ArrayList[String] = list
}
