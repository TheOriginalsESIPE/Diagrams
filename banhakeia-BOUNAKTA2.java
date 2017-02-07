import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.json.simple.JSONObject;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class Ecouteur2 implements ActionListener {
	 public Authentification s;
public Ecouteur2(Authentification s){
	this.s=s;
}
@SuppressWarnings("deprecation")
public void actionPerformed(ActionEvent e) {
	try {
		if (this.s.loginButton == e.getSource()){
			this.s.user=this.s.userText.getText();
			this.s.password=this.s.passwordText.getText();
			this.s.Age=this.s.agetext.getText();
			JsonObject json = new JsonObject();
			json.addProperty("Prenom",this.s.user);
			json.addProperty("Nom",this.s.password);
			json.addProperty("Age",this.s.Age);
			FileWriter file = new FileWriter("parseExample/src/main/java/files/example_1.json",true);
			file.write(json.toString());
			file.close(); 
			System.out.println("objet Json a etais copier avec succser");
			System.out.println("\nJSON Object: " + json);
			}
		else if (this.s.recupereButton == e.getSource()){
			JsonParser parser = new JsonParser();
			Object obj = parser.parse(new FileReader("parseExample/src/main/java/files/example_1.json"));
			JsonObject jsonObject1 = (JsonObject) obj;
			String prenom = jsonObject1.get("Prenom").getAsString();
			String nom = jsonObject1.get("Nom").getAsString();
			String age = jsonObject1.get("Age").getAsString();
			System.out.println("Prenom: " + prenom);
			System.out.println("Nom: " + nom);
			System.out.println("Age: " + age);
				}}
	catch (IOException e1) {
			e1.printStackTrace();
		}
}
public static void main(String[] args) {
	new Authentification();
}
}