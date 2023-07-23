package view.sendsmsbean.adf;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

import javax.faces.event.ActionEvent;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.security.cert.X509Certificate;

import javax.faces.application.FacesMessage;

import javax.faces.context.FacesContext;

import javax.net.ssl.SSLSession;
//import weblogic.net.http.SOAPHttpsURLConnection;


//import javax.net.ssl.HttpsURLConnection;


public class SendSmsFromADF {
    private String entername;

    public void setEntername(String entername) {
        this.entername = entername;
    }

    public String getEntername() {
        return entername;
    }

    public void setEntermessage(String entermessage) {
        this.entermessage = entermessage;
    }

    public String getEntermessage() {
        return entermessage;
    }
    private String entermessage;

    public void sendingSms(ActionEvent actionEvent) {
        try {
            // Disable hostname verification for testing purposes (Not recommended for production)
                    disableHostnameVerification();
            String apiKey = "xkO1iMEqrV7bIT0dJAhQjU5KoBtDzSe49sX2GmNYpv3Wag68LceFNwm63q2QoaKjIlOzZUrgf5Jbth0n";
           // String sendId= "FSTSMS";
            //important step .....
            entermessage=URLEncoder.encode(entermessage, "UTF-8");
            //String language = "english";
            String route = "q";
           String flash = "0";
            
            String myUrl = "https://www.fast2sms.com/dev/bulkV2?authorization="+apiKey+"&route="+route+"&message="+entermessage+"&flash="+flash+"&numbers="+entername;
            System.out.println(myUrl);
            
            //sending get request using java....apiKey
            URL url = new URL(myUrl);
                   HttpURLConnection con = (HttpURLConnection) url.openConnection();
                         
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
            //this.showMessage();
            
            
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } 
        catch (MalformedURLException e) {
        } catch (IOException e) {
        }  
    }
    // Disable hostname verification using a custom SSL context (Not recommended for production)
    private void disableHostnameVerification() {
        try {
            TrustManager[] trustAllCertificates = new TrustManager[] {
                new X509TrustManager() {
                    public X509Certificate[] getAcceptedIssuers() {
                        return null;
                    }
                    public void checkClientTrusted(X509Certificate[] certs, String authType) {
                    }
                    public void checkServerTrusted(X509Certificate[] certs, String authType) {
                    }
                }
            };
            
            SSLContext sslContext = SSLContext.getInstance("TLS");
            sslContext.init(null, trustAllCertificates, new java.security.SecureRandom());
            HttpsURLConnection.setDefaultSSLSocketFactory(sslContext.getSocketFactory());
            
            HttpsURLConnection.setDefaultHostnameVerifier((String hostname, SSLSession session) -> true);
        } catch (Exception e) {
            e.printStackTrace();
        }
}
    public String showMessage() {
            String messageText= "message sent successfully";
            FacesMessage fm = new FacesMessage(messageText);
            /**
             * set the type of the message.
             * Valid types: error, fatal,info,warning
             */
            fm.setSeverity(FacesMessage.SEVERITY_INFO);
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, fm);
            return null;
        }
}
