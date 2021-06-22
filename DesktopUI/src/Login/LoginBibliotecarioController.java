/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Login;

import BLL.BibliotecarioBLL;
import BLL.UtilizadorBLL;
import DAL.Bibliotecario;
import DAL.Utilizador;
import Requisicoes.RequisicoesController;
import desktopui.PaginaInicialController;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Pedro
 */
public class LoginBibliotecarioController implements Initializable {

    Stage stage = desktopui.DesktopUI.guiStage;
    @FXML
    private TextField username_txt;

    @FXML
    private TextField email_txt;

    @FXML
    private TextField password_txt;

    @FXML
    private TextField nome_txt;

    @FXML
    private TextField morada_txt;

    @FXML
    private DatePicker data_nascimento_picker;

    @FXML
    private Button criar_bibliotecario_btn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    
    boolean isLogged = false;
    
    @FXML
    void criarBibliotecario(ActionEvent event){
        Alert alert = new Alert(Alert.AlertType.NONE);
        Utilizador utilizador = new Utilizador();
        utilizador.setUsername(username_txt.getText());
        utilizador.setPassword(password_txt.getText());
        Utilizador username = UtilizadorBLL.readUsername(utilizador.getUsername());
        
        try{
            // Se o username e password inseridos existirem na BD
            if(utilizador.getUsername().equals(username.getUsername()) && utilizador.getPassword().equals(username.getPassword())){
                // Tipo utilizador 1 é Bibliotecário.
                if(username.getTipoUtilizador() == 1){
                    isLogged = true;
                    try {
                        // Enviar os dados do utilizador a fazer login para a página inicial
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("/desktopui/PaginaInicial.fxml"));
                        Parent root = loader.load();
   
                        //The following both lines are the only addition we need to pass the arguments
                        PaginaInicialController pgIniController = loader.getController();
                        pgIniController.receberUsername(username);
                        
                        stage.getScene().setRoot(root);
                        stage.show();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else{
                    username_txt.clear();
                    password_txt.clear();
                    alert.setAlertType(Alert.AlertType.ERROR);
                    alert.setTitle("Sem Permissões");
                    alert.setHeaderText("Não tem permissões para entrar aqui.");
                    alert.show(); 
                }
            } else{
                System.out.println("Credenciais erradas.");
                username_txt.clear();
                password_txt.clear();
                alert.setAlertType(Alert.AlertType.ERROR);
                alert.setTitle("Erro");
                alert.setHeaderText("Credenciais erradas.");
                alert.show(); 
            }
        } catch(NullPointerException npe){
            npe.printStackTrace();
            System.out.println("O username inserido não existe.");
            username_txt.clear();
            password_txt.clear();
            alert.setAlertType(Alert.AlertType.ERROR);
            alert.setTitle("Erro");
            alert.setHeaderText("Esta conta não existe.");
            alert.show();
        }
    }
}