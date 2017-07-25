package httpclient.access;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;

/**
 * Has the access methods for the server including login, signup, and logout
 * @author rNatarajan
 *
 */
public class HttpClientAccess {

	
	/**
	 * Connects to the server and registers a new user
	 * @param name the username of registration
	 * @param password the password of registration
	 * @param email the email address of registration
	 */
	public void signup(String name, String password, String email) {

		try {

			DefaultHttpClient httpClient = new DefaultHttpClient();
			HttpPost postRequest = new HttpPost(
					"http://localhost:3000/login");

			StringEntity input = new StringEntity(
					"{\"name\":\""+name+"\",\"password\":\""+password+"\",\"email\":\""+email+"\"}");
			input.setContentType("application/json");
			postRequest.setEntity(input);

			HttpResponse response = httpClient.execute(postRequest);

			if (response.getStatusLine().getStatusCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : "
						+ response.getStatusLine().getStatusCode());
			}

			BufferedReader br = new BufferedReader(new InputStreamReader(
					(response.getEntity().getContent())));

			String output;
			System.out.println("Output from Server .... \n");
			while ((output = br.readLine()) != null) {

				System.out.println(output);
			}

			httpClient.getConnectionManager().shutdown();

		} catch (MalformedURLException e) {

			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();

		}

	}
	
	/**
	 * Provides those who have signed up access to server-based features.
	 * Returns the ID number of the username password field if true for future reference
	 * @param username the username field
	 * @param password the password the username corresponds to
	 * @return true if the username-password combination is in the database
	 */
	public boolean login(String username, String password){
		
		try {

			DefaultHttpClient httpClient = new DefaultHttpClient();
			HttpPost postRequest = new HttpPost(
					"http://localhost:3000/login");

			StringEntity input = new StringEntity(
					"{\"name\":\""+username+"\",\"password\":\""+password+"\"}");
			/*input.setContentType("application/json");
			postRequest.setEntity(input);

			HttpResponse response = httpClient.execute(postRequest);

			if (response.getStatusLine().getStatusCode() != 201) {
				throw new RuntimeException("Failed : HTTP error code : "
						+ response.getStatusLine().getStatusCode());
			}

			BufferedReader br = new BufferedReader(new InputStreamReader(
					(response.getEntity().getContent())));

			String output;
			System.out.println("Output from Server .... \n");
			while ((output = br.readLine()) != null) {

				System.out.println(output);
			}*/

			httpClient.getConnectionManager().shutdown();

		} catch (MalformedURLException e) {

			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();

		}
		return false;
		
	}
	
	
	/**
	 * Disconnects users from the server
	 */
	public void logout(){
		
	}
	
	/**
	 * Checks if pre-existing users have the same name
	 * @param username the username to be checked
	 */
	public void userCheck(String username){
		//checks database to see if username already exists
	}
	
	/**
	 * Checks if the password is valid
	 * @param password the password to be checked
	 */
	public void passCheck(String password){
		if(password.length() < 6){
			throw new IllegalArgumentException("Password too short");
		}
	}
	
}
