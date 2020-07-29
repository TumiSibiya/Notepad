package notepadApp;
//app
import javafx.application.Application;
//pane
import javafx.scene.layout.StackPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
//fields
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
        //properties
        import javafx.geometry.Pos;
        import javafx.geometry.Orientation;
        import javafx.geometry.Insets;
//buttons
import javafx.scene.control.Button;
import javafx.scene.control.Label;
//stage and scene
import javafx.scene.Scene;
import javafx.stage.Stage;
//Handlers
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
//Exceoptions
//StringTokenizer
import java.util.StringTokenizer;
import javafx.scene.layout.Border;

public class Notepad extends Application{
//pane
    private StackPane spane = new StackPane();
    private BorderPane bpane = new BorderPane();
    private HBox hbox = new HBox();
//nodess
    //fields
    private TextArea wordProcessor = new TextArea();
    private TextField wordCountField = new TextField();
    //buttons
    Button wordCountButton = new Button("words");

    //Organise pane
    public void setupPane(){
   spane.setAlignment(Pos.CENTER);
            spane.setPadding(new Insets(1,2,1,2));
        spane.getChildren().add(wordProcessor);

        hbox.setAlignment(Pos.BOTTOM_RIGHT);
             hbox.setPadding(new Insets(2,10,2,10));
                //appearance
                hbox.setStyle("-fx-background-color:white;");
        hbox.getChildren().addAll(wordCountButton ,wordCountField);

        bpane.setCenter(spane);
        bpane.setBottom(hbox);

    }
    //Displayer class
    class Displayer{

        void displayNoWords(int totalWords){
            wordCountField.setText(String.valueOf(totalWords));
        }
    }
    //wordCound  class
    class WordCounter{
        private int no_Tokens;
        private int whiteSpaces;
        private String paragraphs;
        private int counter;

        public WordCounter(){
            this.whiteSpaces = 1;
            this.counter = 0;
        }
        //count strngs
       public int countWords(){

            if(wordProcessor.getText() != null){

                paragraphs = wordProcessor.getText();
                StringTokenizer tokens = new StringTokenizer(paragraphs);

                    while(tokens.hasMoreTokens()){
                        if(tokens.nextToken().equals(" ")){
                            whiteSpaces++;
                        }
                        counter++;
                    }
            }else{
                counter=0;
            }
        return counter;
        }
    }
    //Organise nodes
    public void setupNode(){

        //TextFields
        wordProcessor.borderProperty().setValue(Border.EMPTY);//setEditable(true);
        wordProcessor.setWrapText(true);

        wordProcessor.setEditable(true);


        wordCountField.setPrefColumnCount(4);
        wordCountField.setEditable(false);

            //appearance
            wordProcessor.setStyle("-fx-border-color:white;");
            wordCountField.setStyle("-fx-border-color:white;");

        //button
            wordCountButton.setMinHeight(wordCountField.getMaxHeight());
            wordCountButton.setOnAction((ActionEvent e)->{
                WordCounter access = new WordCounter();
                new Displayer().displayNoWords(access.countWords());
            });
    }
    @Override
    public void start(Stage primaryStage){
        setupPane();
        setupNode();

        Scene notepad = new Scene(bpane, 800,00);
        primaryStage.setScene(notepad);
        primaryStage.setTitle("Simple Notepad");
        primaryStage.show();

    }
    public static void main(String... args){

        Application.launch(args);
    }
}
