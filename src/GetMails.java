import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class GetMails {
    public static void main(String[] args) {

        try {
            //save to this filename
            String fileName = "allMails.json";
            File file = new File(fileName);

            //use FileWriter to write file
            FileWriter fw = new FileWriter(file.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fw);


            for (int i = 1; i <= 60; i++){
                // get URL content
                URL url = new URL("http://learn.knockoutjs.com/mail?mailId="+i);
                URLConnection conn = url.openConnection();

                // open the stream and put it into BufferedReader
                BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));

                String inputLine;

                if (!file.exists()) {
                    file.createNewFile();
                }

                while ((inputLine = br.readLine()) != null) {
                    bw.write(inputLine);
                }

                bw.write(",");
                br.close();

                System.out.println("Done for mail with id: " + i);
            }
            bw.close();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
