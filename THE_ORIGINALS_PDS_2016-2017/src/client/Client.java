package client;


import java.io.IOException;
import java.net.Socket;

/**
 * Created by tearsyu on 15/03/17.
 *  This is client class, it will auto connect to the server when we launch the app.
 */
public class Client {
    private Socket client;
    private String localhost = "127.0.0.1";
    private int port = 20012;

    public void connectToServer() throws IOException {
        Socket client = new Socket("127.0.0.1", 20012);
    }
}
