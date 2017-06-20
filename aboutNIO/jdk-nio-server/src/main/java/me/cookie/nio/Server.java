package me.cookie.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.spi.SelectorProvider;
import java.util.Set;

/**
 * Created by cookie on 2017/6/20.
 */
public class Server {

    private ServerSocketChannel serverSocketChannel;

    private Selector selector;

    public void Server(int port) throws IOException {
        selector = SelectorProvider.provider().openSelector();
        serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.configureBlocking(false);
        serverSocketChannel.bind(new InetSocketAddress(port));
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
    }

    public void start(){
        try {
            selector.select(1000);
            Set<SelectionKey> selectionKeySet = selector.selectedKeys();
            for(SelectionKey selectionKey : selectionKeySet){
                selectionKeySet.remove(selectionKey);

            }
            selectionKeySet.iterator();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
