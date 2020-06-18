package HdfsTest;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;
import org.apache.hadoop.util.Progressable;
import java.io.*;
import java.net.URI;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class FileCopy {

//    private Logger logger = LogManager.getLogger(this.getClass());
    public static void main(String[] args) throws IOException, InterruptedException {
//        Logger logger = LogManager.getLogger(FileCopy.class);
        String localSrc = "/Users/zhaojixun/Desktop/大数据相关/备注.txt";
        String dst = "hdfs://60.205.184.214:9000";
        System.setProperty("hadoop.home.dir", "/usr/local/hadoop-2.9.2");
        InputStream in = new BufferedInputStream(new FileInputStream(localSrc));

        Configuration conf = new Configuration();
        FileSystem fs = FileSystem.get(URI.create(dst),conf);

        String str = fs.getCanonicalServiceName();
        System.out.println(str);
        //创建文件夹
        try {
            boolean result = fs.mkdirs(new Path("/demo"));
            System.out.println("创建文件夹结果："+result);
        } catch (IllegalArgumentException | IOException e) {
            System.out.println("创建文件夹出错");
        }
//        OutputStream out = fs.create(new Path(dst), new Progressable() {
//            @Override
//            public void progress() {
//                System.out.println(".");
//            }
//        });
//
//        IOUtils.copyBytes(in,out,4096,true);
//        System.out.println("拷贝成功！");
    }
}
