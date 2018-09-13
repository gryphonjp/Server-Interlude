package com.l2jbr.mmocore;

import java.nio.channels.CompletionHandler;

public class WriteHandler<T extends  AsyncMMOClient<AsyncMMOConnection<T>>> implements CompletionHandler<Integer, T> {

    @Override
    public void completed(Integer result, T client) {
        if(result == -1) {
            client.disconnected();
            return;
        }

        var connection = client.getConnection();

        if(result < client.getDataSentSize()) {
            client.resumeSend(result);
        } else {
            connection.releaseWritingBuffer();
            client.tryWriteNextPacket();
        }
        
    }

    @Override
    public void failed(Throwable exc, T client) {
        client.disconnected();
    }
}