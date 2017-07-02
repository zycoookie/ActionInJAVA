package me.cookie.io.channel.file;

import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

/**
 * Created by cookie on 2017/6/23.
 */
public class TestFileChannel {

    static final String basePath = TestFileChannel.class.getClassLoader().getResource("").getPath();

    /**
     * 使用RandomAccessFile直接对文件进行读写操作
     * @throws IOException
     */
    @Test
    public void testRandomAccessFile() throws IOException {
        String filePath = basePath + "file1.txt";
        System.out.println(filePath);
        RandomAccessFile file = new RandomAccessFile(filePath,"rw");
        file.write(new String("1234567890").getBytes());
        file.seek(0);
        byte[] bytes = new byte[10];
        file.readFully(bytes);
        System.out.println(file.length());
        System.out.println(new String(bytes));
        file.close();
    }

    /**
     * 使用channel来进行文件读写操作
     * @throws FileNotFoundException
     */
    @Test
    public void testFileChannel() throws IOException {
        String filePath = basePath + "file2.txt";
        RandomAccessFile file = new RandomAccessFile(filePath,"rw");
        FileChannel fileChannel = file.getChannel();
        ByteBuffer byteBuffer = ByteBuffer.allocate(10);
        for (int i = 0; i < 3; i++) {
            byteBuffer.put(new String("1234567890").getBytes());
            byteBuffer.flip();
            try {
                fileChannel.write(byteBuffer);
            } catch (IOException e) {
                e.printStackTrace();
            }
            byteBuffer.clear();
        }
        int length = 0;
        fileChannel.position(0);
        byteBuffer = ByteBuffer.allocate(7);
        while((length = fileChannel.read(byteBuffer)) >= 0){
            byte[] bytes = new byte[length];
            byteBuffer.flip();
            byteBuffer.get(bytes);
            System.out.println(new String(bytes));
        }
        fileChannel.close();
        file.close();
    }

    /**
     * 内存映射用户内存映射到内核内存中，可以直接操作内核内存而不需要使用系统调用，减少上下文切换的损失
     * @throws IOException
     */
    @Test
    public void testFileChannelBuffer() throws IOException {
        String filePath = basePath + "file3.txt";
        RandomAccessFile file = new RandomAccessFile(filePath,"rw");
        FileChannel fileChannel = file.getChannel();
        MappedByteBuffer byteBuffer = fileChannel.map(FileChannel.MapMode.READ_WRITE, 0, 10);
        for (int i = 0; i < 10; i++) {
            byteBuffer.put(new String("1234567890").getBytes());
            fileChannel.position(fileChannel.position()+byteBuffer.limit());
            fileChannel.force(true);
            byteBuffer.flip();
        }
        fileChannel.position(0);
        byteBuffer.clear();

        while(fileChannel.size() > fileChannel.position()){
            byte[] bytes = new byte[10];
            byteBuffer.get(bytes);
            System.out.println(new String(bytes));
            byteBuffer.clear();
            fileChannel.position(fileChannel.position() + byteBuffer.limit());
        }

        fileChannel.close();
        file.close();

    }

}
