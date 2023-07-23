package view.sendsmsbean;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

import java.util.Date;

import javax.net.ssl.HttpsURLConnection;

public class SendSms {
    public SendSms() {
        super();
    }

    public static void main(String[] args) {
        SendSms sendSms = new SendSms();
        System.out.println(" program started");
        sendSms.sendSmsMethod("this message using java  "+new Date(), "7207231126");
    }
    public void sendSmsMethod(String message,String number){
//        System.out.println(message);
//        System.out.println(number);
        
        try {
            String apiKey = "xkO1iMEqrV7bIT0dJAhQjU5KoBtDzSe49sX2GmNYpv3Wag68LceFNwm63q2QoaKjIlOzZUrgf5Jbth0n";
           // String sendId= "FSTSMS";
            //important step .....
            message=URLEncoder.encode(message, "UTF-8");
            //String language = "english";
            String route = "q";
           String flash = "0";
            
            String myUrl = "https://www.fast2sms.com/dev/bulkV2?authorization="+apiKey+"&route="+route+"&message="+message+"&flash="+flash+"&numbers="+number;
            System.out.println(myUrl);
            
            //sending get request using java....apiKey
            URL url = new URL(myUrl);
            HttpsURLConnection con = (HttpsURLConnection)url.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("User-Agent", "Mozilla/5.0");
            con.setRequestProperty("cache-control", "no-cache");
            System.out.println("Wait..........");
            int code = con.getResponseCode();
            System.out.println("Response Code "+code);
            StringBuffer response = new StringBuffer();
            BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
            while(true){
                String line = br.readLine();
                if(line==null){
                    break;
                }
                response.append(line);
            }
            System.out.println(response);
            
            
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } 
        catch (MalformedURLException e) {
        } catch (IOException e) {
        }
    }
}
