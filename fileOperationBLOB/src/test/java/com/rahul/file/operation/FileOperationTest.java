package com.rahul.file.operation;

import com.rahul.file.operation.entities.FileEntity;
import com.rahul.file.operation.entities.repo.FileRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class FileOperationTest {


    @Autowired
    FileRepository fileRepository;

    @Test
    public void testFileSave() throws IOException {

        byte[] fileContent =Files.readAllBytes(Paths.get("/home/rahul/Downloads/java.png"));

        FileEntity fileEntity = new FileEntity();
        fileEntity.setFileName("java.png");
        fileEntity.setFileContent(fileContent);

        fileRepository.save(fileEntity);
    }


    @Test
    public void testReadFileAndSaveToDirectory() throws IOException {

        List<FileEntity> fileEntities =fileRepository.findAll();
        byte[] fileContent=fileEntities.stream().findFirst().get().getFileContent();

        RandomAccessFile stream = new RandomAccessFile("/home/rahul/Downloads/java2.png", "rw");
        FileChannel channel = stream.getChannel();
        ByteBuffer buffer = ByteBuffer.allocate(fileContent.length);
        buffer.put(fileContent);
        buffer.flip();
        channel.write(buffer);
        stream.close();
        channel.close();

    }



}
