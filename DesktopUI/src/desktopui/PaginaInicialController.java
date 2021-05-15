/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package desktopui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

/**
 *
 * @author Pedro
 */
public class PaginaInicialController implements Initializable {
    
    @FXML
    private Button goToLivros;

    @FXML
    private Button goToRequisicoes;

    @FXML
    private Button goToExemplares;

    @FXML
    private Label label_teste;
    
    @FXML
    private AnchorPane rootPane;

    
    @FXML
    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
        //label.setText("Hello World!");
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    public void receberUsername(String username){
        label_teste.setText(username);
    }
    
    @FXML
    void loadLivrosPane(ActionEvent event) throws IOException{
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/Livros/Livros.fxml"));
        rootPane.getChildren().setAll(pane);
    }
    
    @FXML
    void loadRequisicoesPane(ActionEvent event) throws IOException{
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/Requisicoes/Requisicoes.fxml"));
        rootPane.getChildren().setAll(pane);
    }
    
    @FXML
    void loadExemplaresPane(ActionEvent event) throws IOException{
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/Exemplares/Exemplares.fxml"));
        rootPane.getChildren().setAll(pane);
    }
}
