package cn.aio;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousFileChannel;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.concurrent.Future;

public class Demo {

    /**
     * 读取文件
     */
    public static void readFile(){

        try (AsynchronousFileChannel open = AsynchronousFileChannel.open(Paths.get("src/main/resources/bd.jpg"), StandardOpenOption.READ)) {
            while(open.isOpen()){
                ByteBuffer byteBuffer = ByteBuffer.allocate((int) open.size());
                Future<Integer> read = open.read(byteBuffer, 0);
                if(read.isDone()){
                    System.out.println(new String(byteBuffer.array()));
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("the end");
    }

    public static void main(String[] args) {

    }
}
