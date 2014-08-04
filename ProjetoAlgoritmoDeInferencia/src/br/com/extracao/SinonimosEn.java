package br.com.extracao;
import java.io.BufferedReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;

import org.json.simple.*; 

public class SinonimosEn {

/*        private static String key = "LY884krlgATbK1nDgYSn";
        private static String search = "SOFTWARE";
        private static String language = "en_US";
        private static String output = "json";*/
        public static ArrayList<String> listaSinonimosEn;

      /*  public static void main(String[] args) {
               sendRequest(search, language, key, output);
        }*/

		public void sendRequest(String search, String language, String key, String output){
                final String endpoint = "http://thesaurus.altervista.org/thesaurus/v1";
                listaSinonimosEn = new ArrayList<String>();
                try {
                        URL serverAddress = new URL(endpoint + "?word="+URLEncoder.encode(search, "UTF-8")+"&language="+language+"&key="+key+"&output="+output);
                        HttpURLConnection connection = (HttpURLConnection)serverAddress.openConnection();
                        connection.connect();
                        int rc = connection.getResponseCode();
                        if (rc == 200) {
                                String line = null;
                                BufferedReader br = new BufferedReader(new java.io.InputStreamReader(connection.getInputStream()));
                                StringBuilder sb = new StringBuilder();
                                while ((line = br.readLine()) != null)
                                        sb.append(line + '\n');
                                JSONObject obj = (JSONObject) JSONValue.parse(sb.toString());
                                JSONArray array = (JSONArray)obj.get("response");
                                for (int i=0; i < array.size(); i++) {
                                        JSONObject list = (JSONObject) ((JSONObject)array.get(i)).get("list");    
                                        //list.get("synonyms").toString().replace("|", ",");
                                        listaSinonimosEn.add(list.get("synonyms").toString().replace("|", ", "));
                                        //System.out.println(list.get("category")+":"+list.get("synonyms"));
                                }
                        } //else System.out.println("HTTP error:"+rc);
                        connection.disconnect();
                } catch (java.net.MalformedURLException e) {
                        e.printStackTrace();
                } catch (java.net.ProtocolException e) {
                        e.printStackTrace();
                } catch (java.io.IOException e) {
                        e.printStackTrace();
                }
        }
}