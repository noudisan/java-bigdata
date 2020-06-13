package com.dztt.bigdata.spark.core

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object Spark01_Oper14 {
  def main(args: Array[String]): Unit = {

    val config: SparkConf = new SparkConf().setMaster("local[*]").setAppName("WorldCount")

    //创建spark创建上下文对象
    val sc = new SparkContext(config)

    val listRDD: RDD[(String, Int)] = sc.makeRDD(List(("a", 3), ("a", 2), ("c", 4), ("b", 3), ("c", 6), ("c", 8)), 2)

    listRDD.glom().collect().foreach(data => print(data.mkString(",")))
    /*
        aggregateByKey()()使用了函数柯里化
          存在两个参数列表
        1）第一个参数列表表示分区内计算时的初始值（零值）
        2）第二个参数列表需要传两个参数：
        1.第一个参数表示分区内计算规则
        2.第二个参数表示分区间计算规则
    */
    val aggregateRDD = listRDD.aggregateByKey(0)(math.max(_, _), _ + _)

    //分区求最大值
    val rdd2: RDD[(String, Int)] = aggregateRDD.aggregateByKey(0)((x,y)=>{math.max(x,y)},(x,y)=>{x+y})

    rdd2.collect().foreach(println)

    sc.stop()

  }
}
