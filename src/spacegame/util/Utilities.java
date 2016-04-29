package spacegame.util;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class Utilities {
	public static void sleep(int toSleep) {
		try {
			long start = System.currentTimeMillis();
			Thread.sleep(toSleep);
			long now;
			while (start + toSleep > (now = System.currentTimeMillis()))
				Thread.sleep(start + toSleep - now);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public static boolean sendPostToPage(String uri, String data,
			String expectedResult) {
		URL url;
		URLConnection urlConn;
		DataOutputStream output;
		BufferedReader input;
		try {
			url = new URL(uri);
			urlConn = url.openConnection();
			urlConn.setDoOutput(true);
			urlConn.setDoInput(true);

			output = new DataOutputStream(urlConn.getOutputStream());
			output.writeBytes(data);
			output.flush();
			output.close();

			DataInputStream in = new DataInputStream(urlConn.getInputStream());
			input = new BufferedReader(new InputStreamReader(in));
			String str;
			boolean toReturn = false;
			while ((str = input.readLine()) != null) {
				if (str == expectedResult)
					toReturn = true;
			}
			input.close();
			return toReturn;
		} catch (Exception e) {
		}
		return false;
	}

	public static String getInfoFromPage(String uri, String data) {
		URL url;
		URLConnection urlConn;
		DataOutputStream output;
		BufferedReader input;
		try {
			url = new URL(uri);
			urlConn = url.openConnection();
			urlConn.setDoInput(true);
			if (data != null) {
				urlConn.setDoOutput(true);
				output = new DataOutputStream(urlConn.getOutputStream());
				output.writeBytes(data);
				output.flush();
				output.close();
			}
			DataInputStream in = new DataInputStream(urlConn.getInputStream());
			input = new BufferedReader(new InputStreamReader(in));
			String str;
			String toReturn = "";
			while ((str = input.readLine()) != null) {
				toReturn += str + "\n";
			}
			input.close();
			return toReturn;
		} catch (Exception e) {
		}
		return null;
	}
}
