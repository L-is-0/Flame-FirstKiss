package com.hazel.myfirstkiss;

import android.os.Bundle;
import android.util.Log;
import androidx.appcompat.app.AppCompatActivity;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

public class FileStorage extends AppCompatActivity {
    String authorizationString = "bWVkaWFzZXJ2aWNlMTpmbGFtZTIwMTg=";
    String connectionString = "storage.hackathon.ict-flame.eu:8080";
    String boundary = "------------------------" + Long.toHexString(System.currentTimeMillis()); // Just generate some unique random value.
    String CRLF = "\r\n"; // Line separator required by multipart/form-data.
    String charset = "UTF-8";
    String lastUploadID = null;


    //    String fileName = "C:\\Users\\User\\Downloads\\android-hello-flame-2\\FLAMEHelloWorld\\app\\src\\main\\java\\com\\example\\flamehelloworld\\test.txt";
    HttpURLConnection connection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public String uploadFile(String filePath) throws IOException, JSONException {
        System.out.println(filePath);
        String[] splitString = filePath.split("/");
        String fileName = splitString[splitString.length - 1];
        connection = (HttpURLConnection) new URL("http://" + connectionString + "/storage/service/flame/pushContent").openConnection();
        connection.setDoOutput(true);
        connection.setRequestMethod("POST");
        connection.setRequestProperty ("Authorization", authorizationString);
        connection.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + boundary);

        OutputStream output = connection.getOutputStream();
        PrintWriter writer  = new PrintWriter(new OutputStreamWriter(output, charset), true);

        // header
        writer.append("--").append(boundary).append(CRLF);
        writer.append("Content-Disposition: form-data; name=\"file\"; filename=\"").append(fileName).append("\"").append(CRLF);
        writer.append("Content-Type: application/octet-stream").append(CRLF);
        writer.append(CRLF).flush();

        // data
        DataOutputStream out = new DataOutputStream(output);
        int bytesRead;
        byte[] buf = new byte[1024];
        System.out.println(filePath);
        System.out.println(new File(filePath).exists());
        BufferedInputStream bufInput = new BufferedInputStream(new FileInputStream(new File(filePath)));
        while ((bytesRead = bufInput.read(buf)) != -1) {
            out.write(buf, 0, bytesRead);
            out.flush();
        }
        bufInput.close();

        // end of multipart/form-data.
        writer.append(CRLF).append("--").append(boundary).append("--").flush();

        // server response
        System.out.println(connection.getResponseCode());
        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String line;
        StringBuilder builder = new StringBuilder();
        while((line = reader.readLine()) != null) {
            builder.append(line);
        }
        reader.close();

        JSONObject parsedResponse = new JSONObject(builder.toString());
        lastUploadID = parsedResponse.getString("id");
        Log.d("FileStorage ID", lastUploadID);

        System.out.println(lastUploadID);

        System.out.println(builder.toString());

        return lastUploadID;
    }
}
