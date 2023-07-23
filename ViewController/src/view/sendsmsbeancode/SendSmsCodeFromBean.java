package view.sendsmsbeancode;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

//import java.util.Date;

import javax.net.ssl.HttpsURLConnection;


public class SendSmsCodeFromBean {
    private String number;
    private String message;

    public void setNumber(String number) {
        this.number = number;
    }

    public String getNumber() {
        return number;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
   

    public void sendSmsCode(ActionEvent actionEvent) {
        // Add event code here...
        try {
            String apiKey = "xkO1iMEqrV7bIT0dJAhQjU5KoBtDzSe49sX2GmNYpv3Wag68LceFNwm63q2QoaKjIlOzZUrgf5Jbth0n";
            message = URLEncoder.encode(message, "UTF-8");
            String route = "q";
            String flash = "0";

            String myUrl = "https://www.fast2sms.com/dev/bulkV2?authorization=" + apiKey + "&route=" + route + "&message=" + message + "&flash=" + flash + "&numbers=" + number;
            System.out.println(myUrl);

            // sending get request using java....apiKey
            URL url = new URL(myUrl);
            HttpsURLConnection con = (HttpsURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("User-Agent", "Mozilla/5.0");
            con.setRequestProperty("cache-control", "no-cache");
            System.out.println("Wait..........");
            int code = con.getResponseCode();
            System.out.println("Response Code " + code);
            StringBuffer response = new StringBuffer();
            BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
            while (true) {
                String line = br.readLine();
                if (line == null) {
                    break;
                }
                response.append(line);
            }
            System.out.println(response);

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
         FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "SMS Sent Successfully!", null));
           
    }

}
