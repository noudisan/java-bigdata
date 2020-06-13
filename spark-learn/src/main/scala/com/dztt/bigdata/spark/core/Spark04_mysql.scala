package com.dztt.bigdata.spark.core

import org.apache.spark.rdd.JdbcRDD
import org.apache.spark.{SparkConf, SparkContext}

object Spark04_mysql {
  def main(args: Array[String]): Unit = {

    val config: SparkConf = new SparkConf().setMaster("local[*]").setAppName("WorldCount")

    //创建spark创建上下文对象
    val sc = new SparkContext(config)

    val driver = "com.mysql.jdbc.Driver"
    val url = "jdbc:mysql://localhost:3306:rdd"
    val userName = "root"
    var passWd = "root"

    var sql = "select name,age from user where id >=? and id <=?"

    val jdbcRDD = new JdbcRDD(
      sc,
      () => {
        Class.forName(driver)
        java.sql.DriverManager.getConnection(url, userName, passWd)
      },
      sql,
      1,
      3,
      2,
      (rs) => {
        println(rs.getString(1) + " , " + rs.getInt(2))
      }
    )

    val dataRDD = sc.makeRDD(List(("zhangsan", 10), ("lisi", 15), "wangwu", 20), 2)


    dataRDD.foreachPartition(datas => {
      Class.forName(driver)
      val connection = java.sql.DriverManager.getConnection(url, userName, passWd)
      datas.foreach {
        case (userName: String, age: Int) => {
          val sql = "insert into "
          val statement = connection.prepareStatement(sql)
          statement.setString(1, userName)
          statement.setInt(2, age)
          statement.executeUpdate()
          statement.close()
        }
      }
      connection.close()

    })


    jdbcRDD.collect()

    sc.stop();

  }
}
