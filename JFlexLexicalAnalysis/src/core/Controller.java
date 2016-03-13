package core;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML private TextArea textArea;
    private FileChooser fileChooser;
    private File textFile;
    private Stage stage;
    private String data;
    private FileReader fr;
    private BufferedReader br;
    private String line;
    //>>>>>>>>>>>>>>>>>>>>>>>>>>>>
    private ArrayList<Token> allToken;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        allToken = new ArrayList<>();
        String filePath = null;
//        try {
            //filePath = new File("core\\lexer.flex").getCanonicalPath();
            filePath = "D:/Mis documentos/GitHub/Lexical_analysis/JFlexLexicalAnalysis/src/core//lexer.flex";
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        lexerGenerater(filePath);
    }

    public void lexerGenerater(String path){

        File file = new File(path);
        jflex.Main.generate(file);
    }

    @FXML private void openFile(){
        fileChooser = new FileChooser();
        fileChooser.setTitle("Open File");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("txt","*.txt")
        );
        textFile = fileChooser.showOpenDialog(stage);
        if (textFile != null){
            ReadFile(textFile);
        }
    }

    private void ReadFile(File textFile){
        data = "";
        try {
            fr = new FileReader(textFile);
            br = new BufferedReader(fr);

            while ((line = br.readLine()) != null){
                data+= line + "\n";
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //textArea.appendText(data);

    }

    @FXML private void readLexerFile(){
        File file = new File("core.txt");
        PrintWriter writer;
        try {
            writer = new PrintWriter(file);
            writer.print(data);
            writer.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        Reader reader = null;
        Lexer lexer = null;
        String result = null;
        Token token = null;
        Token lastToken = null;
        try {
            reader = new BufferedReader(new FileReader("core.txt"));
            lexer = new Lexer(reader);
            result = "";
            token = lexer.yylex();
            lastToken = token;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        while (token!=null){
            switch (token){
                case ERROR:
                    result = result + "Error, el simbolo no coincide \n";
                    break;
                case INT:
                    result = result+"Token: " + token + " " + lexer.lexeme + "\n";
                    break;
                default:
                    result = result + "Token: " + token + "\n";
            }
            lastToken = token;
            try {
                token = lexer.yylex();
            } catch (IOException e) {
                e.printStackTrace();
            }
            allToken.add(token);
        }
        result = result + "FIN";
        textArea.setEditable(false);
        textArea.setText("");
        textArea.appendText(result);
    }
}
