/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package project;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMain.java to edit this template
 */
//package project;

//file imports
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.BufferedReader;


//importing shape objects
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Line;

//fx imports
import javafx.application.Application;
import javafx.animation.TranslateTransition;
import javafx.scene.layout.Pane;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;


/**
 *
 * @author amrom
 */

public class NewFXMain extends Application{
    
    
    @Override
    public void start(Stage primaryStage) throws IOException{
        //creating necessary objects for animation,files,etc.
        Scanner scanS = new Scanner(System.in);
        Pane pane = new Pane();
        TranslateTransition translate = new TranslateTransition();//for first shape object
        TranslateTransition translate2 = new TranslateTransition();//for second shape object
        TranslateTransition translate3 = new TranslateTransition();//for third shape object
        //Creating start and stop key values and keyframes
        
        AnimationTimer timer = new AnimationTimer(){
            //private final long startTime = System.nanoTime();
            public long frameCount = 0;
            public long prevTime = 0;
   
            @Override
             public void handle(long now) {
             long dt = now-prevTime;
                if (dt > 1e8){
                   prevTime=now;
                }  
            }
        };
        
        Timeline timeline = new Timeline();
        timeline.setCycleCount(1);
        
        
        
        //other necessary variables/arraylists
        ArrayList<Integer> integerList;
        ArrayList<String> stringList;
        String fileName;
        
        
        //prompting user
        System.out.println("Enter the name of your file: ");
        fileName = scanS.nextLine();
        
        //has integer data needed 
        integerList = getIntegerOptions(fileName);
        //has string data needed
        stringList = getStringOptions(fileName);
        //effects
        timer.start();
        
        if(integerList == null || stringList == null)
        {
            System.out.println("\nError. File does not exist.");
            System.exit(0);
        }
        else
        {
            for(int i  = 0; i < stringList.size(); i++)
            {
                if(stringList.get(i).equalsIgnoreCase("circle"))
                {
                    //required objects/functions
                    Circle circle = new Circle();
                    pane.getChildren().add(circle);
                    translate.setNode(circle);
                    
                    circle.setRadius(integerList.get(3));
                    circle.setCenterX(integerList.get(4));
                    circle.setCenterY(integerList.get(5));
                    circle.setFill(Color.rgb(integerList.get(6), integerList.get(7), integerList.get(8)));
                    circle.setStroke(Color.rgb(integerList.get(9), integerList.get(10), integerList.get(11)));
                    
                    translate.setDuration(Duration.seconds(integerList.get(0)/10));
                    translate.setCycleCount(1);
                    translate.setByX(200);
                    translate.setByY(20);
                    translate.setAutoReverse(false);
                    translate.play();
                    
                    
                    for(int j=i; j<i;j++){
                        if(stringList.get(j).equalsIgnoreCase("effect")){
                            switch(stringList.get(++j)){
                                case "Hide":
                                    //if(timeline.getCurrentTime().toSeconds()==integerList.get(12)/10)
                                    circle.setVisible(false);
                                    break;
                                case "Show":
                                    //if(timeline.getCurrentTime().toSeconds()==integerList.get(13)/10)
                                     circle.setVisible(true);
                                    break;
                                    
                                case "Jump":
                                    translate.setByX(integerList.get(12));
                                    translate.setByY(13);
                                    break;
                                case "ChangeColor":
                                     circle.setFill(Color.rgb(integerList.get(12), integerList.get(13), integerList.get(14)));
                                    break;
                                    
                                default:
                                    System.out.println("no effects");
                            }
                        }
                    }
                }
                else if(stringList.get(i).equalsIgnoreCase("rect"))
                {
                    //required objects/functions
                    Rectangle rectangle = new Rectangle();
                    pane.getChildren().add(rectangle);
                    translate2.setNode(rectangle);
                    
                    rectangle.setHeight(integerList.get(14));
                    rectangle.setWidth(integerList.get(15));
                    rectangle.setX(integerList.get(16));
                    rectangle.setY(integerList.get(17));
                    rectangle.setStrokeWidth(integerList.get(18));
                    rectangle.setFill(Color.rgb(integerList.get(19), integerList.get(20), integerList.get(21)));
                    rectangle.setStroke(Color.rgb(integerList.get(22), integerList.get(23), integerList.get(24)));
                    
                    translate2.setDuration(Duration.seconds(integerList.get(0)/10));
                    translate2.setCycleCount(1);
                    translate2.setByX(50);
                    translate2.setByY(0);
                    translate2.setAutoReverse(false);
                    translate2.play();
        
                        while(stringList.get(++i).equalsIgnoreCase("effect")){
                            switch(stringList.get(i)){
                                case "Hide":
                                     if(timeline.getCurrentTime().toSeconds()==integerList.get(25)/10)
                                    rectangle.setVisible(false);
                                    
                                    
                                case "Show":
                                     rectangle.setVisible(true);
                                    break;
                                    
                                case "Jump":
                                    if(timeline.getCurrentTime().toSeconds()==integerList.get(25)/10){
                                    rectangle.setX(integerList.get(26));
                                    rectangle.setY(integerList.get(27));
                                    }
                                    break;
                                case "ChangeColor":
                                     rectangle.setFill(Color.rgb(integerList.get(12), integerList.get(13), integerList.get(14)));
                                    break;
                                    
                                default:
                                    System.out.println("no effects");
                            }
                        }
                    
                }
                else if(stringList.get(i).equalsIgnoreCase("line"))
                {
                    Line line = new Line();
                    pane.getChildren().add(line);
                    translate3.setNode(line);
                    
                    line.setStartX(integerList.get(28));
                    line.setStartY(integerList.get(29));
                    line.setEndX(integerList.get(30));
                    line.setEndY(integerList.get(31));
                    line.setFill(Color.rgb(integerList.get(32), integerList.get(33), integerList.get(34)));
                    line.setStrokeWidth(integerList.get(35));
                    
                    translate.setDuration(Duration.seconds(integerList.get(0)/10));
                    translate3.setCycleCount(1);
                    translate3.setByX(0);
                    translate3.setByY(0);
                    translate3.setAutoReverse(false);
                    translate3.play();
                  
                        while(stringList.get(++i).equalsIgnoreCase("effect")){
                            switch(stringList.get(i)){
                                case "Hide":
                                    if(timeline.getCurrentTime().toSeconds()==integerList.get(36)/10)
                                    line.setVisible(false);
                                    
                                    
                                case "Show":
                                    if(timeline.getCurrentTime().toSeconds()==integerList.get(37)/10)
                                     line.setVisible(true);
                                    break;
                                    
//                                case "Jump":
//                                    line.setStartX(integerList.get(j));
//                                    line.setStartY(integerList.get(1+j));
//                                    line.setEndX(integerList.get(2+j));
//                                    line.setEndY(integerList.get(3+j));
//                                    break;
                                case "ChangeColor":
                                    if(timeline.getCurrentTime().toSeconds()==integerList.get(38)/10)
                                     line.setFill(Color.rgb(integerList.get(39), integerList.get(40), integerList.get(41)));
                                    break;
                                    
                                default:
                                    System.out.println("no effects");
                            }
                        }
                    
                }
            }   
        }
        
        Scene scene = new Scene(pane, 400, 300);
        primaryStage.setScene(scene);
        primaryStage.show();
        
        
        
        timer.stop();
    }
    
    private ArrayList<Integer> getIntegerOptions(String path) throws IOException, FileNotFoundException{
        File myFile = new File(path);
        ArrayList<Integer> integerList = new ArrayList<>();
        String line;//contains values of line
        
        if(!myFile.exists())
        {
            return null;
        }
        
        else
        {
            if(myFile.isFile())
            {
                System.out.println("File found. Integer data obtained.");
                BufferedReader br = new BufferedReader(new FileReader(path));
                while((line = br.readLine()) != null)
                {
                    String[] tokens = line.split("[ ,]+");//splitting values after commas and spaces
                    for(int i = 0; i < tokens.length; i++)
                    {
                        if(i == 1 || i == 2 || i == 3)
                        {
                            integerList.add(Integer.parseInt(tokens[i]));//parsing these strings into integers to be used later
                        }
                    }
                }
                return integerList;//returning
            }
        }
        return null;
    }
    
    private ArrayList<String> getStringOptions(String path) throws IOException, FileNotFoundException{
        File myFile = new File(path);
        ArrayList<String> stringList = new ArrayList<>();
        String line;//contains values of line
        
        
        if(!myFile.exists())
        {
            return null;
        }
        
        else
        {
            if(myFile.isFile())
            {
                System.out.println("File found. String data obtained.");
                BufferedReader br = new BufferedReader(new FileReader(path));
                while((line = br.readLine()) != null)
                {
                    String[] tokens = line.split("[:]+");//splitting values after commas and spaces
                    for(int i = 0; i < tokens.length; i++)
                    {
                        if(i == 0)
                        {
                            stringList.add(tokens[i]);//obtaining string values from file
                        }
                    }
                }
                return stringList;
            }
        }
        return null;
    }
    
    //this is our main class
    public static void main(String[] args) throws IOException{
        NewFXMain player = new NewFXMain();
        player.launch();        
    }

  
}
