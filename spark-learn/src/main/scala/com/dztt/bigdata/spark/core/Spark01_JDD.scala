package com.dztt.bigdata.spark.core

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object Spark01_JDD {
  def main(args: Array[String]): Unit = {

    val config: SparkConf = new SparkConf().setMaster("local[*]").setAppName("WorldCount")

    //创建spark创建上下文对象
    val sc = new SparkContext(config)
    //1、从内存中创建RDD 底层是parallelize，defaultParallelism默认并行度为cpu内核
    val listRDD: RDD[Int] = sc.makeRDD(List(1, 2, 3, 4, 5))

    listRDD.collect().foreach(println)

    //2、从内存中创建RDD
    val arrayRDD: RDD[Int] = sc.parallelize(Array(1, 2, 3, 4, 5))
    arrayRDD.collect().foreach(println)

    //3、从外部创建
    //默认情况下可以读取项目路径，也可以读取外部路径：HDFS  hdfs:hadoop:
    //默认从文件中读取都是字符串类型  minPartitions: Int = defaultMinPartitions 遵从 hadoopFile
    val result:RDD[String] = sc.textFile("in")

    //将RDD的数据保存到文件中
    listRDD.saveAsTextFile("output")

  }
}
