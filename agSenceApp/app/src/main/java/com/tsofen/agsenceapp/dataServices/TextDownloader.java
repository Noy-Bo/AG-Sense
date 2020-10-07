package com.tsofen.agsenceapp.dataServices;

import com.tsofen.agsenceapp.BackgroundServices.CacheMgr;

import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;


public class TextDownloader {



    private static TextDownloader textDownloader = null;

    private TextDownloader() { }

    public static TextDownloader getInstance()
    {
        if (textDownloader == null)
            textDownloader = new TextDownloader(); // TODO - add synchronized.

        return textDownloader;
    }

    /**
     * this is the main downloading function. almost every task passes here inorder to download the received data from the server.
     * the function download all data into a string.
     * we receive a url and handler and return the downloaded data
     * @param urlAddress url address to download from.
     * @param handler OnDataReadyHandler api of failure and success
     * @return downloaded data in string.
     */
    public String getText(String urlAddress, OnDataReadyHandler handler) {
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

            if(handler!=null) // if there is a handler, we want to activate the completed downloaded
            {
                handler.onDataDownloadCompleted(str); // activating handlers function to set result(str)
            }

            in.close();
            return str;
        } catch (MalformedURLException e) {
            handler.onDownloadError();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            handler.onDownloadError();
            return null;
        }
    }



}