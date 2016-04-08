package com.yuta.examples;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

import java.io.IOException;
import java.util.Map;

public class TestAction {

    public static void main (String[] args) throws IOException {
        Configuration conf = new Configuration();
        FileSystem fs = FileSystem.get(conf);
        Path file = new Path("/tmp/test");
        if(fs.exists(file)){
            fs.delete(file);
        }
        FSDataOutputStream out = fs.create(file);
        System.out.println("-------------");
        System.out.println(fs.toString());
        out.write("-------------\n".getBytes());
        out.write(fs.toString().getBytes());
        Map<String, String> env = System.getenv();
        for (String envName : env.keySet()) {
            out.write(String.format("%s=%s%n", envName, env.get(envName)).getBytes());
        }
        out.close();
    }
}
