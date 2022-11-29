package application;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.net.HttpURLConnection;
//import java.awt.Button;
//import java.awt.Label;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.Map.Entry;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;


/**
 * 
 * @author fotis
 * 
 */

public class Controller implements Initializable {
	
	@FXML
	private Text info1;
	@FXML
	private Text info2;
	@FXML
	private Text info3;
	@FXML
	private Text info4;
	@FXML
	private Button button1;
	@FXML
	private Label mylabel;
	@FXML
	private TextField textfield1;
	@FXML
	private TextField textfield2;
	@FXML
	private Button button2;
	@FXML
	private Text giapontous;
	@FXML
	private Text giaepityxies;
	@FXML
	private MenuItem menu_start;
	@FXML
	private Label label1;
	@FXML
	private TextField textfield3;
	@FXML
	private Text menuinfo;
	@FXML
	private Button button3;
	@FXML
	private MenuItem menu_load;
	@FXML
	private MenuItem menu_create;
	@FXML
	private MenuItem exiting;
	@FXML
	private Text giadet;
	@FXML
	private MenuItem solution1;
	@FXML
	private MenuItem diction1;
	
	

	/**
	 * This class contains information about the dictionary, the points and the players guesses
	 * @author fotis
	 * 
	 */
	static class lexikon {
		
		
	
	static int count;
	static int mistakes = 0;
	static int pontoi = 0;
	static int swstes = 0;
	static String word = "potter";
	static boolean menu_choice; //true gia load , false gia create
	static List<Character> playerguesses = new ArrayList<>();
	static String Dictionary_ID = "";
	static List<Character> newguess = new ArrayList<>();
	static String position;
	static LinkedHashSet<String> lexiko = new LinkedHashSet<String>();
	static LinkedHashSet<String> diathesimes = new LinkedHashSet<String>();
	/**
	 * This method checks if the dictionary is valid 
	 * and if it isnt, it raises an exception
	 */
	static void checklexikon()  {
		try
		{
			//System.out.print(lexiko);
		     if(lexiko.size() < 20)
		     {
		          throw new Exception("UndersizedException");
		     }
		}
		catch(Exception ex)
		{
			System.out.print("UndersizedException");
			System.exit(1);
		}
		
	
	try 
	{
		Object[] array7 = new String[lexiko.size()];
        array7 = lexiko.toArray();
        int counter1 =0;
        for (int kop=0; kop < array7.length; kop++) {
        	if(array7[kop].toString().length() > 8) {
        		counter1++;
        	}
        }
		if((counter1*1.0 / array7.length) < 0.2)
		{
			throw new Exception("UnbalancedException");
		}
		}
		catch(Exception ex)
			{
			System.out.print("UnbalancedException");
			System.exit(1);
			}

}
	/**
	 * this method updates the players points
	 */
	static void updatepoints() {
		if(pontoi >= 10) {
			pontoi = pontoi - 10;
		}
		else {
			pontoi = 0;
		}
	}

	}
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}
	/**
	 * this method is used to note that option load has been chosen
	 * @param event
	 */
	public void loading_menu(ActionEvent event) {
		label1.setText("Please Insert the Dictionary-ID");	
		menuinfo.setText(" ");
		lexikon.menu_choice = true;
	}
	/**
	 * this method notes that option create has been chosen
	 * @param event
	 */
	public void creating_menu(ActionEvent event) {
		label1.setText("Please Insert the Dictionary-ID");	
		menuinfo.setText(" ");
		lexikon.menu_choice = false;
	}
	/**
	 * terminate the program when option exit is chosen
	 * @param event
	 */
	public void exiting_app(ActionEvent event) {
		Platform.exit();
	}
	/**
	 * gets dictionary id from the input and loads or creates the dictionary
	 * @param event
	 */
	public void getID(ActionEvent event) {
		String pop = new String();
	    pop = textfield3.getText();
	  //  System.out.println(pop);
	    label1.setText("Thank you!");   
	    textfield3.clear();
	    lexikon.Dictionary_ID = pop;
	    if(lexikon.menu_choice == false) {
	    	createit();
	    }
	    else {
	    	loadit();
	    	
	    }
		
	}
	/**
	 * this method gets the players guess for the letter and the letters position form the interface
	 * @param event
	 */
	public void submit(ActionEvent event) {
		String k  = textfield1.getText();
		lexikon.newguess.add(k.charAt(0));
		lexikon.position = textfield2.getText();
		lexikon.count++;
		giaepityxies.setText(String.valueOf(lexikon.swstes));
		togame();
		
		
	}
	

	/**
	 * the option start has been selected, the program resets and a new word is being selected
	 * @param event
	 */
	public void startit(ActionEvent event) {
		lexikon.playerguesses.clear();
		lexikon.count = 0;
		lexikon.swstes = 0;
		lexikon.pontoi = 0;
		lexikon.mistakes = 0;
		info3.setText("");
		textfield1.clear();
		textfield2.clear();
		make_word();
		
	}
	/**
	 * option solution has been selected, the player loses the game and the word is revealed
	 * @param event
	 */
	public void solution(ActionEvent event) {
		info3.setText(("O").concat("\n").concat("/|\\").concat("\n").concat("/").concat("\\"));
		info2.setText(lexikon.word);
		lexikon.count = lexikon.count + 6;
		textfield1.clear();
		textfield2.clear();
		
	}
	/**
	 * option dictionary from details menu has been selected and the number of words based on their letters are being displayed
	 * @param event
	 */
	public void diction(ActionEvent event) {
		Object[] arr = new String[lexikon.lexiko.size()];
        arr = lexikon.lexiko.toArray();
        int sixth = 0;
        int seventh = 0;
        int tenth = 0;
        for(int i =0; i  < arr.length; i++) {
        	if(arr[i].toString().length() == 6) {
        		sixth++;
        	}
        	else if(arr[i].toString().length() == 8 || arr[i].toString().length() == 9 || arr[i].toString().length() == 7 ) {
        		seventh++;
        	}
        	else {
        		tenth++;
        	}
        }
        giadet.setText(("words with 6 letters :").concat(String.valueOf(sixth)).concat("\n").concat("words with 7-9 letters :").concat(String.valueOf(seventh)).concat("\n").concat("words with 10 letters or more:").concat(String.valueOf(tenth)));
	}
	/**
	 * this is the method that is being run on every round. 
	 * It compares the players guess to the secret word and it updates the players points and the interface info based on the guess
	 */
	public void togame() {
		List<Character> toinfo = new ArrayList<>();
		boolean giacheck = false;
 		if(lexikon.newguess.get(0) == (lexikon.word.charAt(Integer.valueOf(lexikon.position) - 1))) {
 			lexikon.playerguesses.add(lexikon.word.charAt(Integer.valueOf(lexikon.position) - 1));
 			giacheck = true;
			lexikon.pontoi = lexikon.pontoi + 10;
 		}
		for(int i =0; i < lexikon.word.length(); i++) {
			if(lexikon.playerguesses.contains(lexikon.word.charAt(i))) {
				
				toinfo.add(lexikon.word.charAt(i));
				
				
			}
			else {
				toinfo.add('_');

			}
		}
		  info2.setText(String.valueOf(toinfo));
		  
		  lexikon.newguess.clear();
		  if(giacheck) {
			  lexikon.swstes++;
		  }
		  else {
			  lexikon.updatepoints();
		  }
		  
		  if(!toinfo.contains('_')) {
			  info3.setText("YOU WIN");
		  }
		  else {
			  checkloss();
		  }
		
		  giapontous.setText(String.valueOf(lexikon.pontoi));
		  giaepityxies.setText(String.valueOf(lexikon.swstes));
		  textfield1.clear();
		  textfield2.clear();
		  alpha();
	
			
		}
	/**
	 * this method displays the letters of the hidden word in alphabetical order 
	 */
	public void alpha() {
		String newword;
		  newword = lexikon.word;
		  List<Character> kainouria = new ArrayList<>();
		  char[] neos = new char[newword.length()];
		  neos = newword.toCharArray();
		  Arrays.sort(neos);
		  for(int j=0; j < neos.length ; j++) {
			kainouria.add(neos[j]);
		  }
		  info4.setText(kainouria.toString());
	}
	/**
	 * this method displays the hangman status based on the players wrong guesses 
	 */
	public void checkloss() {
		switch(lexikon.count - lexikon.swstes ) {
			case(0):
				info3.setText(("hangman"));
				break;
			case(1):
				info3.setText(("O"));
				break;
			case(2):
				info3.setText(("O").concat("\n").concat("|"));
				break;
			case(3):
				info3.setText(("O").concat("\n").concat("/|"));
				break;
			case(4):
				info3.setText(("O").concat("\n").concat("/|\\"));
				break;
			case(5):
				info3.setText(("O").concat("\n").concat("/|\\").concat("\n").concat("/"));
				break;	
			default:
				info3.setText(("O").concat("\n").concat("/|\\").concat("\n").concat("/").concat("\\"));
				info2.setText(lexikon.word);
				break;	
				
				
		}
	}
	/**
	 * this method creates a new dictionary based on the dictionary id from the open library api and stores it in medialab folder
	 */
	public void createit() {
		 try {
	            
			 //URL url = new URL("https://openlibrary.org/works/OL31390631M.json");
			 URL url = new URL("https://openlibrary.org/works/".concat(lexikon.Dictionary_ID).concat(".json"));
	            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	            conn.setRequestMethod("GET");
	            conn.connect();

	           

	                StringBuilder informationString = new StringBuilder();
	                Scanner scanner = new Scanner(url.openStream());

	                while (scanner.hasNext()) {
	                    informationString.append(scanner.nextLine());
	                }
	                
	                scanner.close();

	              
	               
	               JSONParser parse = new JSONParser();
	               JSONObject json = (JSONObject) parse.parse(String.valueOf(informationString));
	               JSONObject json1 = (JSONObject) json.get("description");
	               String k = new String();
	               k = (String) json1.get("value");
	               
	          
	              
	               String[] words = k.split("\\s+");
	               for (int i = 0; i < words.length; i++) {
	                   
	                   words[i] = words[i].replaceAll("[^\\w]", "");
	               }
	       
	          
	            int p = 0;
	            String[] warding = new String[words.length];
	            for(int r=0; r < words.length; r++) {
	            	if(words[r].length() >= 6) {
	            		warding[p] = words[r];
	            		p++;
	            	}
	            
	            }
	       
	       
	            String[] wards = new String[p];
	            for(int rot =0; rot<p; rot++) {
	            	wards[rot] = warding[rot].toUpperCase();
	            	
	            }
	        
	            
	            for (int rop = 0; rop < wards.length; rop++)
	                lexikon.lexiko.add(wards[rop]);
	           
	           Object[] arr = new String[lexikon.lexiko.size()];
	            arr = lexikon.lexiko.toArray();
	         
	           String fileContent = "";
	          
	             for(int  j =0; j < arr.length; j++) {
	           	  fileContent = fileContent.concat(arr[j].toString().concat("\n"));
	             }
	          
	       		
	            FileWriter writer = new FileWriter("src/medialab\\".concat("hangman_").concat(lexikon.Dictionary_ID).concat(".txt"));
	       		writer.write(fileContent);
	       		writer.close();
	       		make_word();
	       		
	      		} catch (Exception e) {
	            e.printStackTrace();
	        }
	}
	/**
	 * this method loads the dictionary from a file in medialab folder based on the dictionary id 	
	 */
	public void loadit() {
			File file = new File("src/medialab\\".concat("hangman_").concat(lexikon.Dictionary_ID).concat(".txt"));
			try {
				Scanner scan = new Scanner(file);
				while(scan.hasNext()) {
					lexikon.lexiko.add(scan.nextLine());
				}
				
				make_word();
				
				
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	/**
	 * this method selects a random word from the dictionary	
	 */
	public void make_word() {
			Random rand = new Random(); 
			lexikon.checklexikon();
			Object[] array1 = new String[lexikon.lexiko.size()];
            array1 = lexikon.lexiko.toArray();
            lexikon.word = array1[rand.nextInt(array1.length)].toString();
            String arxiko = " ";
            for(int l=0; l< lexikon.word.length(); l++) {
            	arxiko = arxiko.concat("-");
            }
            info2.setText(arxiko);
            info1.setText(String.valueOf(lexikon.lexiko.size()));
            giapontous.setText("0");
            giaepityxies.setText("0");
            alpha();
            
		}
		
	}
	
	

