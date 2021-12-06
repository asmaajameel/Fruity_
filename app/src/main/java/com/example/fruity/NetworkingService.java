package com.example.fruity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Looper;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class NetworkingService {

    public static final ExecutorService networkingExecutor = Executors.newFixedThreadPool(4);
    String fruitNameUrl = "https://tropicalfruitandveg.com/api/tfvjsonapi.php?search=";
    String fruitInfoUrl = "https://tropicalfruitandveg.com/api/tfvjsonapi.php?tfvitem=";
//    String imageUrl1 = "http://tropicalfruitandveg.com/images/";
//    String imageUrl2 = ".jpg";//http://tropicalfruitandveg.com/images/almondfruit.jpg
     String url1,url2;

    static Handler networkHander = new Handler(Looper.getMainLooper());

    interface NetworkingListener{
        void APINetworkListner(String jsonString);
        void APINetworkListnerForImage(Bitmap image);

    }
    NetworkingListener listener;
    /*private  void attemptstonumber(String attempt){
        System.out.println("Attempt in 86"+attempt);
        for(int i = 0; i< attempt.toCharArray().length;i++)
            if(attempt.toCharArray()[i] == '*')
            {   String totalques = attempt.substring(0,i);
                String totalans = attempt.substring(i+1,attempt.toCharArray().length);
                scoreslist.add(Integer.parseInt(totalans));
                totalqnslist.add(Integer.parseInt(totalques));
            }
    }*/
    ////http  ://tropicalfruitandveg.com/images/almondfruit.jpg

    public void makeURL(String url){
       // String url = "http://tropicalfruitandveg.com/thumb.php?image=images/almondfruit.jpg";
           String url1 = url.substring(0,4);
           String url2 = url.substring(4,url.length());
           String urlfinal = url1 + "s"+url2;
           fetchImage(urlfinal);
        System.out.println("This is my final url"+urlfinal);

    }





//    public String  fromStringToAttempt(){
//        //4!8#2!8#
//        attempts = 0;
//        int i;
//        for ( i = 0 ; i < data.toCharArray().length ; i++){
//            if (data.toCharArray()[i] == '#'){
//                attempts++;
//            }
//        }
//        return attempts;
//    }
public  void fetchFruitsName(String text){
    String completeURL = fruitNameUrl + text;
    connect(completeURL);
}
public  void fetchFruitsInfo(String fruitName){
    String completeURL = fruitInfoUrl + fruitName;
    connect(completeURL);
}
public void fetchImage(String imageurl){
    String completeURL = imageurl;//imageUrl1+image+imageUrl2;
    networkingExecutor.execute(new Runnable() {
        @Override
        public void run() {
            try {
                URL urlobj = new URL(completeURL);
             InputStream inputStream = ((InputStream)urlobj.getContent());
                Bitmap imageDATA = BitmapFactory.decodeStream(inputStream);
             networkHander.post(new Runnable() {
               @Override
               public void run() {
                listener.APINetworkListnerForImage(imageDATA);
                    }
               });
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    });
}
    private void connect(String url){
        networkingExecutor.execute(new Runnable() {
            String jsonString = "";
            @Override
            public void run() {

                HttpURLConnection httpURLConnection = null;
                try {
                    URL urlObject = new URL(url);
                    httpURLConnection = (HttpURLConnection) urlObject.openConnection();
                    httpURLConnection.setRequestMethod("GET");
                    httpURLConnection.setRequestProperty("Content-Type","application/json");
                    int statues = httpURLConnection.getResponseCode();

                    if ((statues >= 200) && (statues <= 299)) {
                        InputStream in = httpURLConnection.getInputStream();
                        InputStreamReader inputStreamReader = new InputStreamReader(in);
                        int read = 0;
                        while ((read = inputStreamReader.read()) != -1) {// json integers ASCII
                            char c = (char) read;
                            jsonString += c;
                        }
                        final String finalJson = jsonString;
                        networkHander.post(new Runnable() {
                            @Override
                            public void run() {
                                //send data to main thread
                                listener.APINetworkListner(finalJson);
                            }
                        });
                    }
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                finally {
                    httpURLConnection.disconnect();
                }
            }
        });
    }

}
