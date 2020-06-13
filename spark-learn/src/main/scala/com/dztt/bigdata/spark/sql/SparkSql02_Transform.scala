package com.dztt.bigdata.spark.sql

import org.apache.spark.SparkConf
import org.apache.spark.rdd.RDD
import org.apache.spark.sql.{DataFrame, Dataset, Row, SparkSession}

object SparkSql02_Sql {

  def main(args: Array[String]): Unit = {
    val config: SparkConf = new SparkConf().setMaster("local[*]").setAppName("SparkSql01_Demo")

    //val spark:SparkSession = new SparkSession(config)
    val spark = SparkSession.builder().config(config).getOrCreate()

    //进行转换之前，需要引入隐式转换规则
    //这里的spark部署报名含义，式sparksession对象的名字
    import spark.implicits._

    //创建RDD
    val listRDD: RDD[(Int, String, Int)] = spark.sparkContext.makeRDD(List((1, "zhangsan", 10), (2, "lisi", 15), (3, "wangwu", 20)))
    //转换为DF
    val df:DataFrame = listRDD.toDF("id","name","age")

    //转换为DS
    val ds:Dataset[User] = df.as[User]

    //转换为DF
    val df1:DataFrame = ds.toDF()

    //转换为RDD
    val rdd1:RDD[Row] = df1.rdd

    rdd1.foreach(row=>{
      println(row.getString(1))
    })

    spark.stop()
  }
}

case class User(id:Int,name:String,age:Int)
