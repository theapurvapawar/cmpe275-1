package src.raft.comm.nodes;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class NodeData {
	static long port;
	static String host;
	static Node node;
	static ArrayList<Node> neighbours;
	
	private static String conf = null;
	
	public static void setConf(String conf){
		NodeData.conf = conf;
	}

	public static Node getSelfNode(){
		JSONParser jp = new JSONParser();
		Object obj;
		try {
			obj = jp.parse(new FileReader(conf));
			JSONObject jsonObject = (JSONObject) obj;  

			JSONObject nodeObject = (JSONObject) jsonObject.get("node");
			//long id = (long) selfObject.get("id");
			String host = (String) nodeObject.get("host");
			long port = (long) nodeObject.get("port");
			//System.out.println("Host: "+host+" Port: "+port);
			node = new Node(host, (int) port);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return node;
	}

	public static ArrayList<Node> getNeighbours()
	{
		ArrayList<Node> neighbours = new ArrayList<Node>();
		JSONParser jp = new JSONParser();
		Object obj;
		try {
			obj = jp.parse(new FileReader(conf));
			JSONObject jsonObject = (JSONObject) obj;  

			JSONObject nodeObject = (JSONObject) jsonObject.get("node");

			JSONArray listOfNeighours = (JSONArray) jsonObject.get("nodes");

			Iterator<JSONObject> iterator = listOfNeighours.iterator();
			while (iterator.hasNext()) {  
				nodeObject = iterator.next();
				host = (String) nodeObject.get("host");
				port = (long) nodeObject.get("port");
				//System.out.println("Host: "+host+" Port: "+port);
				Node newNode = new Node(host, (int) port);
				neighbours.add(newNode);
			} 

			//return neighbours;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return neighbours;
	}
}
