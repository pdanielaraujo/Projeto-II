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
    
    /*void enviarDados(){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/desktopui/PaginaInicial.fxml"));
            Parent root = loader.load();
   
            //The following both lines are the only addition we need to pass the arguments
            PaginaInicialController pgIniController = loader.getController();
            pgIniController.receberUsername(username_txt.getText());
   
            stage.setScene(new Scene(root));
            //stage.setTitle("Layout2 + Controller2");
            stage.show();
   
            } catch (IOException e) {
                e.printStackTrace();
            }
    }*/
    
    @FXML
    void criarBibliotecario(ActionEvent event){
        System.out.println("click do botao");
        //System.out.println("Data: " + Date.from(Instant.now()));
        //data_nascimentoLD = data_nascimento_picker.getValue();
        //System.out.println("data nascimento: " + username_txt.getText());
//        System.out.println("data nascimento: " + email_txt.getText());
        //System.out.println("data nascimento: " + password_txt.getText());
        //System.out.println("data nascimento: " + morada_txt.getText());
        //LocalDate dataLC = data_nascimento_picker.getValue();
        //DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        //String data_string = dataLC.format(formatter);
        //LocalDate data_parsed = LocalDate.parse(data_string, formatter);
        //Date data_nascimento = Date.valueOf(data_parsed);
        
        //Date data_nascimentoD = java.sql.Date.valueOf(data_nascimento_picker.getValue());
        //data_nascimentoLD = data_nascimento_picker.getValue();
        //System.out.println("data nascimento: " + data_nascimento);
        
        Alert alert = new Alert(Alert.AlertType.NONE);
        Utilizador utilizador = new Utilizador();
        utilizador.setUsername(username_txt.getText());
        utilizador.setPassword(password_txt.getText());
        Utilizador username = UtilizadorBLL.readUsername(utilizador.getUsername());
        
        try{
            if(utilizador.getUsername().equals(username.getUsername()) && utilizador.getPassword().equals(username.getPassword())){
                System.out.println("Credenciais inseridas corretamente.");
                isLogged = true;
                //enviarDados();
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/desktopui/PaginaInicial.fxml"));
                    Parent root = loader.load();
   
                    //The following both lines are the only addition we need to pass the arguments
                    PaginaInicialController pgIniController = loader.getController();
                    pgIniController.receberUsername(utilizador.getUsername());
                    
                    //stage.setScene(new Scene(root));
                    stage.getScene().setRoot(root);
                    //stage.setTitle("Layout2 + Controller2");
                    stage.show();
   
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else{
                System.out.println("O username inserido não existe.");
                alert.setAlertType(Alert.AlertType.ERROR);
                alert.setTitle("Erro");
                alert.setHeaderText("A palavra-passe inserida está incorreta.");
                alert.show(); 
            }
        } catch(NullPointerException npe){
            System.out.println("O username inserido não existe.");
            alert.setAlertType(Alert.AlertType.ERROR);
            alert.setTitle("Erro");
            alert.setHeaderText("O username inserido não existe.");
            username_txt.clear();
            password_txt.clear();
            alert.show();
        }
        
        System.out.println("Username: " + utilizador.getUsername());
        System.out.println("Username: " + utilizador.getPassword());
        //Bibliotecario bibliotecario = new Bibliotecario();
        //bibliotecario.setNome(nome_txt.getText());
        //bibliotecario.setMorada(morada_txt.getText());
        //bibliotecario.setDataNascimento(data_nascimento);
        //bibliotecario.setUtilizadorId(utilizador);
        //BibliotecarioBLL.create(bibliotecario);
        //bibliotecario.setMorada();
        //bibliotecario.setDataNascimento();
    }
}