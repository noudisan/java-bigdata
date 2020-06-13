package com.dztt.bigdata.spark.sql

import org.apache.spark.SparkConf
import org.apache.spark.rdd.RDD
import org.apache.spark.sql.expressions.{MutableAggregationBuffer, UserDefinedAggregateFunction}
import org.apache.spark.sql.types.{DataType, DoubleType, LongType, StructType}
import org.apache.spark.sql.{Dataset, Row, SparkSession}

object SparkSql05_UDAF {

  def main(args: Array[String]): Unit = {
    val config: SparkConf = new SparkConf().setMaster("local[*]").setAppName("SparkSql01_Demo")

    //val spark:SparkSession = new SparkSession(config)
    val spark = SparkSession.builder().config(config).getOrCreate()

    //进行转换之前，需要引入隐式转换规则
    //这里的spark部署报名含义，式sparksession对象的名字
    import spark.implicits._
    //自定义聚合函数
    //创建自定义聚合函数
    var uadf = new MyAgeAvgFunction
    //使用聚合函数
    spark.udf.register("avgAge",uadf)

    //使用聚合函数
    val frame = spark.read.json("in/user.json")
    frame.createOrReplaceTempView("user")
    spark.sql("select avgAge(age) from user").show

    spark.stop()
  }
}

//声明用户自定义聚合函数
//1、继承UserDefinedAggregateFunction
//2、实现方法
class MyAgeAvgFunction extends UserDefinedAggregateFunction{

  //函数输入的数据结构
  override def inputSchema: StructType = {
    new StructType().add("age",LongType)
  }

  //计算的数据结构
  override def bufferSchema: StructType = {
    new StructType().add("sum",LongType).add("count",LongType)
  }

  //函数返回的数据结构
  override def dataType: DataType = DoubleType

  //函数算法稳定
  override def deterministic: Boolean = true

  //函数计算前的缓冲区的初始化
  override def initialize(buffer: MutableAggregationBuffer): Unit = {
    buffer(0) =0L
    buffer(1) =0L
  }

  //根据查询结果更新缓冲区数据
  override def update(buffer: MutableAggregationBuffer, input: Row): Unit = {
    buffer(0) =buffer.getLong(0) + input.getLong(0)
    buffer(1) =buffer.getLong(1) + 1
  }

  //将多个节点的缓冲区合并
  override def merge(buffer1: MutableAggregationBuffer, buffer2: Row): Unit = {
    //sum
    buffer1(0) = buffer1.getLong(0) + buffer2.getLong(0)

    //count
    buffer1(1) = buffer1.getLong(1) + buffer2.getLong(1)

  }

  //计算
  override def evaluate(buffer: Row): Any = {
    buffer.getLong(0).toDouble/buffer.getLong(1)
  }
}
