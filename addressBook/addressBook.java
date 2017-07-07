import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.stage.Stage;
import javafx.scene.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.Pane;
import javafx.scene.input.MouseEvent;
import javafx.event.ActionEvent;
import javafx.stage.WindowEvent;

import java.util.ArrayList;

import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.IOException;
public class addressBook {
    private static TextField tfName;
    private static TextField tfLine1;
    private static TextField tfTown;
    private static TextField tfCounty;
    private static TextField tfPost;
    private static ListView<Person> personListView;
    private static ArrayList<Person> personArrayList = new ArrayList<Person>();
    private static Person currentlySelectedPerson = null;
    
    public static void main(String[] args) throws IOException {
        readFile();
        launchFX();
    }
    
    private static void launchFX() throws IOException {
        new JFXPanel();
        Platform.runLater(() -> initialiseGUI());
    }
    
    private static void initialiseGUI() {
        Stage stage = new Stage();
        stage.setTitle("Address Book");
        stage.setResizable(false);
        Pane rootPane = new Pane();
        stage.setScene(new Scene(rootPane));
        stage.setWidth(1024);
        stage.setHeight(768);
        stage.setOnCloseRequest((WindowEvent we) -> terminate());
        stage.show();
        
        personListView = new ListView<Person>();
        personListView.setLayoutX(400);
        personListView.setLayoutY(100);
        personListView.setOnMouseClicked((MouseEvent me) -> {
                currentlySelectedPerson = personListView.getSelectionModel().getSelectedItem();
            });
        rootPane.getChildren().add(personListView);
        updateListView();
        
        Label lbl = new Label();
        lbl.setText("Enter details:");
        lbl.setLayoutX(100);
        lbl.setLayoutY(100);
        rootPane.getChildren().add(lbl);
        
        tfName = new TextField();
        tfName.setLayoutX(100);
        tfName.setLayoutY(140);
        tfName.setPrefWidth(200);
        tfName.setPromptText("Name");
        rootPane.getChildren().add(tfName);
        
        tfLine1 = new TextField();
        tfLine1.setLayoutX(100);
        tfLine1.setLayoutY(180);
        tfLine1.setPrefWidth(200);
        tfLine1.setPromptText("Address Line 1");
        rootPane.getChildren().add(tfLine1);
        
        tfTown = new TextField();
        tfTown.setLayoutX(100);
        tfTown.setLayoutY(220);
        tfTown.setPrefWidth(200);
        tfTown.setPromptText("Town");
        rootPane.getChildren().add(tfTown);
        
        tfCounty = new TextField();
        tfCounty.setLayoutX(100);
        tfCounty.setLayoutY(260);
        tfCounty.setPrefWidth(200);
        tfCounty.setPromptText("County");
        rootPane.getChildren().add(tfCounty);
        
        tfPost = new TextField();
        tfPost.setLayoutX(100);
        tfPost.setLayoutY(300);
        tfPost.setPrefWidth(200);
        tfPost.setPromptText("Postcode");
        rootPane.getChildren().add(tfPost);
        
        Button btnAdd = new Button();
        btnAdd.setText("Add");
        btnAdd.setLayoutX(100);
        btnAdd.setLayoutY(340);
        btnAdd.setOnAction((ActionEvent ae) -> addNewItem());
        rootPane.getChildren().add(btnAdd);
        
        Button btnEdit = new Button();
        btnEdit.setText("Edit");
        btnEdit.setLayoutX(150);
        btnEdit.setLayoutY(340);
        btnEdit.setOnAction((ActionEvent ae) -> editItem());
        rootPane.getChildren().add(btnEdit);
        
        Button btnDel = new Button();
        btnDel.setText("Delete");
        btnDel.setLayoutX(200);
        btnDel.setLayoutY(340);
        btnDel.setOnAction((ActionEvent ae) -> deleteItem());
        rootPane.getChildren().add(btnDel);
    }
    
    private static void addNewItem() {
        String name = tfName.getText();
        String line1 = tfLine1.getText();
        String town = tfTown.getText();
        String county = tfCounty.getText();
        String postCode = tfPost.getText();
        personArrayList.add(new Person(name, line1, town, county, postCode));
        
        tfName.setText(null);
        tfLine1.setText(null);
        tfTown.setText(null);
        tfCounty.setText(null);
        tfPost.setText(null);
        
        updateListView();
    }
    
    private static void editItem() {
        tfName.setText(currentlySelectedPerson.getName());
        tfLine1.setText(currentlySelectedPerson.getLine1());
        tfTown.setText(currentlySelectedPerson.getTown());
        tfCounty.setText(currentlySelectedPerson.getCounty());
        tfPost.setText(currentlySelectedPerson.getPostCode());
        
        personArrayList.remove(currentlySelectedPerson);
    }
    
    private static void deleteItem() {
        personArrayList.remove(currentlySelectedPerson);
        updateListView();
    }
    
    private static void readFile() {
        try {
            FileReader fr = new FileReader("addressFile.txt");
            BufferedReader br = new BufferedReader(fr);
            
            String line = null;
            while ((line = br.readLine()) != null) {
                String[] splitLine = line.split(",");
                Person person = new Person(splitLine[0], splitLine[1], splitLine[2], splitLine[3], splitLine[4]);
                personArrayList.add(person);
            }
            br.close();
        } catch (IOException error) {
            System.out.println(error);
        }
    }
    
    private static void writeFile() {
        try {
            FileWriter fw = new FileWriter("addressFile.txt");
            BufferedWriter bw = new BufferedWriter(fw);
            String line;
            
            for (int i=0; i<personArrayList.size(); i++) {
                Person person = personArrayList.get(i);
                line = person.getName() + "," + person.getLine1() + "," + person.getTown() + "," + person.getCounty() + "," + person.getPostCode();
                bw.write(line);
                bw.newLine();
            }
            bw.close();
        } catch (IOException error) {
            System.out.println(error);
        }
    }
    
     private static void terminate() {
         writeFile();
         System.exit(0);
    }
 
    private static void updateListView() {
        personListView.getItems().clear();
        for (Person person: personArrayList) {
            personListView.getItems().add(person);
        }
    }
    
    private static void sortArrayList() {
        int j = 0;
        String key = "";
        for (int i=0; i<personArrayList.size(); i++) {
            j = i-1;
            key = personArrayList.get(i).getName();
        }
    }
}
