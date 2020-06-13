package com.dztt.bigdata.spark.steaming

import org.apache.spark.SparkConf
import org.apache.spark.rdd.RDD
import org.apache.spark.sql.catalyst.expressions.Second
import org.apache.spark.streaming.dstream.{DStream, ReceiverInputDStream}
import org.apache.spark.streaming.{Seconds, StreamingContext}

object SparkStreaming01_WordCount {
  def main(args: Array[String]): Unit = {
    val config: SparkConf = new SparkConf().setMaster("local[*]").setAppName("SparkSql01_Demo")

    //环境对象
    //采集周期，指定的时间为周期进行采集数据
    val streamingContext = new StreamingContext(config, Seconds(3))

    val socketLineDStream: ReceiverInputDStream[String] = streamingContext.socketTextStream("localhost", 9999)

    //将采集的数据扁平化
    val wordDStream = socketLineDStream.flatMap(line => line.split(" "))

    //将数据进行结构转换
    val mapDStream:DStream[(String, Int)] = wordDStream.map((_, 1))

    val wordToSumStream:DStream[(String, Int)] = mapDStream.reduceByKey(_ + _)

    //打印结果
    wordToSumStream.print()

    //不能停止采集
    // streamingContext.stop()
    //启动采集器
    streamingContext.start()
    //driver等待采集器执行
    streamingContext.awaitTermination()
  }
}
