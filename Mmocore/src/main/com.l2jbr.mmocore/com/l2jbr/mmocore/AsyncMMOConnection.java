package com.l2jbr.mmocore;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;

import static com.l2jbr.mmocore.ResourcePool.getPooledBuffer;
import static com.l2jbr.mmocore.ResourcePool.recycleBuffer;
import static java.util.Objects.isNull;

public class AsyncMMOConnection<T extends AsyncMMOClient<AsyncMMOConnection<T>>> {

    private final AsynchronousSocketChannel channel;
    private final ReadHandler<T> readHandler;
    private final WriteHandler<T> writeHandler;
    private T client;

    private ByteBuffer readingBuffer;
    private ByteBuffer writingBuffer;

    public AsyncMMOConnection(AsynchronousSocketChannel channel, ReadHandler<T> readHandler, WriteHandler<T> writeHandler) {
        this.channel = channel;
        this.readHandler = readHandler;
        this.writeHandler = writeHandler;
    }

    public void setClient(T client) {
        this.client = client;
    }

    void read() {
        channel.read(getReadingBuffer(), client, readHandler);
    }

    void write(byte[] data, int offset, int limit) {
        ByteBuffer buffer = getWritingBuffer();
        buffer.put(data, offset, limit);
        buffer.flip();
        write();
    }

    void write() {
        channel.write(writingBuffer, client, writeHandler);
    }

    ByteBuffer getReadingBuffer() {
        if(isNull(readingBuffer)) {
            readingBuffer = getPooledBuffer();
        }
        return readingBuffer;
    }

    private ByteBuffer getWritingBuffer() {
        if(isNull(writingBuffer)) {
            writingBuffer =  getPooledBuffer();
        }
        return writingBuffer;
    }

    void releaseReadingBuffer() {
        recycleBuffer(readingBuffer);
        readingBuffer=null;
    }

    void releaseWritingBuffer() {
        recycleBuffer(writingBuffer);
        writingBuffer = null;
    }

    void close() {
        releaseReadingBuffer();
        releaseWritingBuffer();
        try {
            channel.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}