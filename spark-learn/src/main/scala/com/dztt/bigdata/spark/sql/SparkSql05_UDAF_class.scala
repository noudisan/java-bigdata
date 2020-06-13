package com.dztt.bigdata.spark.sql

import org.apache.spark.SparkConf
import org.apache.spark.rdd.RDD
import org.apache.spark.sql.expressions.{Aggregator, MutableAggregationBuffer, UserDefinedAggregateFunction}
import org.apache.spark.sql.types.{DataType, DoubleType, LongType, StructType}
import org.apache.spark.sql.{DataFrame, Dataset, Encoder, Encoders, Row, SparkSession}

object SparkSql05_UDAF_class {

  def main(args: Array[String]): Unit = {
    val config: SparkConf = new SparkConf().setMaster("local[*]").setAppName("SparkSql01_Demo")

    //val spark:SparkSession = new SparkSession(config)
    val spark = SparkSession.builder().config(config).getOrCreate()

    //进行转换之前，需要引入隐式转换规则
    //这里的spark部署报名含义，式sparksession对象的名字
    import spark.implicits._

    val udaf = new MyAgeAvgClassFunction

    val avgCol = udaf.toColumn.name("avgAge")

    val frame: DataFrame = spark.read.json("in/user.json")

    val userDs: Dataset[UserBean] = frame.as[UserBean]

    userDs.select(avgCol).show

    spark.stop()
  }
}

case class UserBean(name: String, age: BigInt)

case class AvgBuffer(var sum: BigInt, var count: Int)

//声明用户自定义聚合函数(强类型）
//1、继承Aggregator 设定泛型
//2、实现方法
class MyAgeAvgClassFunction extends Aggregator[UserBean, AvgBuffer, Double] {
  //初始化
  override def zero: AvgBuffer = {
    AvgBuffer(0, 0)
  }

  //聚合合并
  override def reduce(b: AvgBuffer, a: UserBean): AvgBuffer = {
    b.sum = b.sum + a.age
    b.count = b.count + 1

    b
  }

  //缓冲区的合并操作
  override def merge(b1: AvgBuffer, b2: AvgBuffer): AvgBuffer = {
    b1.sum = b1.sum + b2.sum
    b1.count = b1.count + b2.count

    b1
  }

  //完成计算
  override def finish(reduction: AvgBuffer): Double = {
    reduction.sum.toDouble / reduction.count
  }

  override def bufferEncoder: Encoder[AvgBuffer] = Encoders.product

  override def outputEncoder: Encoder[Double] = Encoders.scalaDouble
}
