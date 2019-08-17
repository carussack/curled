import org.apache.commons.cli.*;
import java.io.*;
import java.net.*;
import java.text.ParseException;
import java.util.*;

public class curled {
    public static HttpURLConnection conn;
    public static void main(String[] args) throws IOException, org.apache.commons.cli.ParseException {
        String diycurl = "curl -d 'name=Bob&role=Developer' -XPOST url";
        ArrayList curl = new ArrayList(Arrays.asList(diycurl.split(" ")));
        String[] str = new String[curl.size()];
        //for loop filling array str with contents of curl
        for (int i = 0; i < curl.size(); i++) {
            str[i] = (String) curl.get(i);
        }

        // create Options object
        Options options = new Options();
        // add d option
        options.addOption("d", true, "post data");
        options.addOption("XPOST", true, "http verb");
        CommandLineParser parser = new DefaultParser();
        CommandLine cmd = parser.parse(options, str);
        if (cmd.hasOption("d")) {
            getHttpRequest(String.valueOf(curl.get(4)));
            postRequest(String.valueOf(curl.get(2)));
        }
        else{
            getHttpRequest(String.valueOf(curl.get(1)));
            getRequest(String.valueOf(curl.get(0)));
        }
    }

    public static void getHttpRequest(String url) throws IOException {
        URL newURL = new URL(url);
        conn = (HttpURLConnection) newURL.openConnection();
        System.out.println("Connected!");
    }

    public static void getRequest(String httpRequestMethod) throws IOException, NullPointerException {
        StringBuilder result = new StringBuilder();
        conn.setRequestMethod(httpRequestMethod);
        BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String line;
        while ((line = rd.readLine()) != null) {
            result.append(line);
        }
        rd.close();
        System.out.println(result.toString());
    }

    public static void postRequest(String postData) throws IOException {
        String[] post = postData.split("&");
        StringBuilder result = new StringBuilder();
        conn.setRequestMethod("POST");
        conn.setRequestProperty(post[0], post[1]);

        BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String line;
        while ((line = rd.readLine()) != null) {
            result.append(line);
        }
        rd.close();
        System.out.println(result.toString());
    }

}
