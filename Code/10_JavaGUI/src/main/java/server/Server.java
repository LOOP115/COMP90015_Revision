package server;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import javax.net.ServerSocketFactory;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Server {

    // Declare the port number
    private static int port = 4321;

    // Identifies the user number connected
    private static int counter = 0;

    public static void main(String[] args)
    {
        ServerSocketFactory factory = ServerSocketFactory.getDefault();
        try(ServerSocket server = factory.createServerSocket(port)){
            System.out.println("Waiting for client connection..");

            // Wait for connections.
            while(true){
                Socket client = server.accept();
                counter++;
                System.out.println("Client "+counter+": Applying for connection!");

                // Start a new thread for a connection
                Thread t = new Thread(() -> serveClient(client));
                t.start();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void serveClient(Socket client)
    {
        try(Socket clientSocket = client)
        {

            // The JSON Parser
            JSONParser parser = new JSONParser();
            // Input stream
            DataInputStream input = new DataInputStream(clientSocket.getInputStream());
            // Output Stream
            DataOutputStream output = new DataOutputStream(clientSocket.getOutputStream());

            System.out.println("CLIENT: "+input.readUTF());

            output.writeUTF("Server: Hi Client "+counter+" !!!");

            // Receive more data..
            while(true){
                if(input.available() > 0){
                    // Attempt to convert read data to JSON
                    JSONObject command = (JSONObject) parser.parse(input.readUTF());
                    System.out.println("COMMAND RECEIVED: "+command.toJSONString());
                    Integer result = parseCommand(command);
                    JSONObject resObj = new JSONObject();
                    resObj.put("result", result);

                    output.writeUTF(resObj.toJSONString());
                }
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }
    private static Integer parseCommand(JSONObject command) {

        int result = 0;

        if(command.containsKey("command_name")){
            System.out.println("IT HAS A COMMAND NAME");
        }

        if (command.get("command_name").equals("Math"))
        {
            Math math = new Math();
            Integer firstInt = Integer.parseInt(command.get("first_integer").toString());
            Integer secondInt = Integer.parseInt(command.get("second_integer").toString());

            switch((String) command.get("method_name"))
            {
                case "add":
                    result = math.add(firstInt,secondInt);
                    break;
                case "multiply":
                    result = math.multiply(firstInt,secondInt);
                    break;
                case "subtract":
                    result = math.subtract(firstInt,secondInt);
                    break;
                default:
                    try
                    {
                        throw new Exception();
                    } catch (Exception e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
            }
        }
        // TODO Auto-generated method stub
        return result;
    }

}
