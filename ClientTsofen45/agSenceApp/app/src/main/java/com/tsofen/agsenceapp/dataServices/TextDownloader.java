package com.tsofen.agsenceapp.dataServices;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

public class TextDownloader{
    OnDataReadyHandler handler = null;

    public String getText(String urlAddress) {
        try {
            // Create a URL for the desired page
            URL url = new URL(urlAddress);
            // Read all the text returned by the server
            BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
            String str = "";
            String input = "";

            while ((input = in.readLine()) != null) {
                str += input;
            }
            if(this.handler!=null) // if there is a handler, we want to activate the completed downloaded
            {
                this.handler.onDataDownloadCompleted(str); // activating handlers function to set result(str)
            }
            in.close();
            return str;
        } catch (MalformedURLException e) {
            return null;
        } catch (IOException e) {
            return null;
        }
    }

    public void setOnDownloadCompletedListener(OnDataReadyHandler handler)
    {
        this.handler = handler;
    }

}
