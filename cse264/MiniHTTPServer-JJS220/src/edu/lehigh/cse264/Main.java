/* CSE 264
 * Homework Extra Credit - Mini HTTP Server
 * Name: Jake Schinto (jjs220)
 * Date: 4/30/19
 */
package edu.lehigh.cse264;

import java.io.*;
import java.net.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Main {

    // The port can be any small integer that isn't being used by another program
    // See: https://en.wikipedia.org/wiki/List_of_TCP_and_UDP_port_numbers for a list
    // of already assigned port numbers
    private static final int port = 8567;

    public static void main(String[] args) {
        String log = "access.log";
        try {
            System.out.println("Mini HTTP Server Starting Up");
            // Create a new Server Socket to listen on port for a new connection request
            ServerSocket s = new ServerSocket(port);
            try {
                File file = new File(log);
                if (!file.exists()) {
                    BufferedWriter out = new BufferedWriter(new FileWriter(log));
                    out.write("");
                    out.close();
                }
            } catch (Exception e) {
                System.out.println(e.toString());
            }
            for (;;) {

                // Wait for a new TCP connection to come in from a client and 
                // accept it when it does. Return a reference to the socket 
                // that will be used to communicate with the client.
                Socket newSocket = s.accept();
                String addressOfConnectingSocket = ((InetSocketAddress) newSocket.getRemoteSocketAddress()).getAddress().getHostAddress();
                System.out.println("New connection from: " + addressOfConnectingSocket);

                // Create a new handler object to handle the requests of the 
                // client that just connected.
                ClientHandler handler = new ClientHandler(newSocket, log);

                // Give the handler its own thread to handle requests to that 
                // the server can handle multiple clients simultaneously.
                new Thread(handler).start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

class ClientHandler implements Runnable {

    // Socket used to handle the client requests.
    private Socket socket;
    private String log;

    public ClientHandler(Socket s, String log) {
        this.socket = s;
        this.log = log;
    }

    private void writeToLog(String message) {
        try {
            message = ((InetSocketAddress) socket.getRemoteSocketAddress()).getAddress().getHostAddress() + " - " + message;
            BufferedWriter out = new BufferedWriter(new FileWriter(log, true));
            out.write(message + "\n");
            out.close();
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    @Override
    public void run() {
        try {
            BufferedReader request = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            DataOutputStream response = new DataOutputStream(socket.getOutputStream());
            List<String> headers = new ArrayList<>();

            try {
                String firstLine = request.readLine();
                if (firstLine.length() > 0) {
                    // Read Headers, one per line of the request
                    String line;
                    while ((line = request.readLine()).length() > 0) {
                        headers.add(line);
                    }

                    // Break the request line up unto individual token
                    String[] tokens = firstLine.split(" ");

                    // The first token is the method name (GET, POST, etc.)
                    String method = tokens[0];

                    // The second token is the resource being requested (eg. /index.html)
                    String resource = tokens[1];

                    String contentType = "Content-Type: ";
                    if (resource.contains(".gif")) {
                        contentType += "image/gif";
                        headers.add(contentType);
                    } else if (resource.contains(".jpg") || resource.contains(".jpeg")) {
                        contentType += "image/jpeg";
                        headers.add(contentType);
                    } else if (resource.contains(".png")) {
                        contentType += "image/png";
                        headers.add(contentType);
                    } else if (resource.contains(".pdf")) {
                        contentType += "application/pdf";
                        headers.add(contentType);
                    } else if (resource.contains(".xls")) {
                        contentType += "application/vnd.ms-excel";
                        headers.add(contentType);
                    } else if (resource.contains(".xlsx")) {
                        contentType += "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
                        headers.add(contentType);
                    } else if (resource.contains(".htm") || resource.contains(".html") || resource.endsWith("/")) {
                        contentType += "text/html";
                        headers.add(contentType);
                    }

                    String lastModified = "Last-Modified: ";
                    String resource2 = resource;
                    if (resource2.endsWith("/")) {
                        resource2 += "index.html";
                    }
                    String path = ("." + resource).replace('/', File.separatorChar);
                    Long d = (new File(path)).lastModified();
                    SimpleDateFormat sdf = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss z");
                    headers.add(lastModified + sdf.format(d));

                    // Dump the entire request to the console for debugging
                    dumpRequest(firstLine, headers);

                    // Process the request based on the method used (GET is the only
                    // one we're implementing for now
                    int responseCode = 0;
                    switch (method) {
                        case "GET":
                            responseCode = processGET(resource, headers, response);
                            break;
                        case "POST":
                            System.err.println(method + " method not implemented.");
                            break;
                        case "HEAD":
                            System.err.println(method + " method not implemented.");
                            break;
                        case "PUT":
                            System.err.println(method + " method not implemented.");
                            break;
                        case "DELETE":
                            System.err.println(method + " method not implemented.");
                            break;
                        case "TRACE":
                            System.err.println(method + " method not implemented.");
                            break;
                        case "OPTIONS":
                            System.err.println(method + " method not implemented.");
                            break;
                        default:
                            System.err.println("Unknown method: " + method);
                            break;
                    }
                    writeToLog("\"" + firstLine + "\" " + responseCode);
                }
            } catch (Exception e) {
                // If we get an i/o error, tell the user that the resource is unavailable
                response.writeBytes("HTTP/1.1 404 ERROR\n\n");
            }
            // Clean up once the request has been processed
            request.close();
            response.close();
        } catch (Exception ex1) {
            System.out.println("Internal error: " + ex1.getMessage());
        }
    }

    // Write out the request header lines to the console
    private void dumpRequest(String firstLine, List<String> headers) {
        System.out.println(firstLine);
        for (String headerLine : headers) {
            System.out.println(headerLine);
        }
        System.out.println();
    }

    private int processGET(String resource, List<String> headers, DataOutputStream out) {
        try {

            // Default to index.html
            if (resource.endsWith("/")) {
                resource += "index.html";
            }

            // Create file path from requested resource compatable with the host OS
            String path = ("." + resource).replace('/', File.separatorChar);
            int length = (int) new File(path).length();
            byte[] b = new byte[length];

            // Read the requested resource into an array of bytes
            FileInputStream resourceStream;
            try {
                resourceStream = new FileInputStream(path);
                resourceStream.read(b);
            } catch (IOException ex) {
                out.writeBytes("HTTP/1.1 404 ERROR\n\n");
                return 404;
            }

            // Write HTTP response line to client
            out.writeBytes("HTTP/1.1 200 OK\n");

            // Write out the headers
            out.writeBytes("Content-Length:" + length + "\n");
            out.writeBytes("Connection: close\n");

            // Blank line ends the header section
            out.writeBytes("\n");

            // Send the requested resource to the client
            out.write(b, 0, length);

            // Return code 200 means "Successful"
            return 200;
        } catch (IOException ex) {
            try {
                out.writeBytes("HTTP/1.1 500 ERROR\n\n");
                return 500;
            } catch (IOException ex1) {
                System.out.println("Internal error: " + ex1.getMessage());
                return 500;
            }
        }
    }
}
