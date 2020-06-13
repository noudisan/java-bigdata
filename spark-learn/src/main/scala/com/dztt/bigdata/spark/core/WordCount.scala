package com.dztt.bigdata.spark.core

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object WorldCount {
  def main(args: Array[String]): Unit = {

    //使用开发工具完成spark world count

    //local模式
    //创建sparkConf对象
    //设定spark计算框架的运行（部署）环境
    //app id
    var config: SparkConf = new SparkConf().setMaster("local[*]").setAppName("WorldCount")

    //创建spark创建上下文对象
    val sc = new SparkContext(config)
    //读取文件内容，一行行读出来
    val line: RDD[String] = sc.textFile("in")
    //将一行行的分解成一个个单词
    val words: RDD[String] = line.flatMap(_.split(" "))
    //为了统计方便，将单词数据进行结构转换
    val wordToOne: RDD[(String, Int)] = words.map((_, 1))
    //对转换后的结构进行聚合分析
    val wordToSum: RDD[(String, Int)] = wordToOne.reduceByKey(_ + _)
    //将统计结果采集后打印到控制台
    val result: Array[(String, Int)] = wordToSum.collect()
    result.foreach(println)


  }
}
