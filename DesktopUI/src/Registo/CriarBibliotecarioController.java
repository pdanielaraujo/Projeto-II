/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Registo;

import BLL.BibliotecarioBLL;
import BLL.UtilizadorBLL;
import DAL.Bibliotecario;
import DAL.Utilizador;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Pedro
 */
public class CriarBibliotecarioController implements Initializable {

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
    
    @FXML
    void criarBibliotecario(ActionEvent event) {
        System.out.println("click do botao");
        //System.out.println("Data: " + Date.from(Instant.now()));
        //data_nascimentoLD = data_nascimento_picker.getValue();
        System.out.println("data nascimento: " + username_txt.getText());
        System.out.println("data nascimento: " + email_txt.getText());
        System.out.println("data nascimento: " + password_txt.getText());
        System.out.println("data nascimento: " + morada_txt.getText());
        LocalDate dataLC = data_nascimento_picker.getValue();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String data_string = dataLC.format(formatter);
        LocalDate data_parsed = LocalDate.parse(data_string, formatter);
        Date data_nascimento = Date.valueOf(data_parsed);
        
        //Date data_nascimentoD = java.sql.Date.valueOf(data_nascimento_picker.getValue());
        //data_nascimentoLD = data_nascimento_picker.getValue();
        System.out.println("data nascimento: " + data_nascimento);
        
        
        Utilizador utilizador = new Utilizador();
        utilizador.setUsername(username_txt.getText());
        utilizador.setEmail(email_txt.getText());
        utilizador.setPassword(password_txt.getText());
        UtilizadorBLL.create(utilizador);
        
        Bibliotecario bibliotecario = new Bibliotecario();
        bibliotecario.setNome(nome_txt.getText());
        bibliotecario.setMorada(morada_txt.getText());
        bibliotecario.setDataNascimento(data_nascimento);
        bibliotecario.setUtilizadorId(utilizador);
        BibliotecarioBLL.create(bibliotecario);
        //bibliotecario.setMorada();
        //bibliotecario.setDataNascimento();
    }
    
}
