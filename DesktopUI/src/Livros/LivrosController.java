/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Livros;

import BLL.GeneroBLL;
import BLL.LivroBLL;
import DAL.Genero;
import DAL.Livro;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author Pedro
 */
public class LivrosController implements Initializable {

    @FXML
    private TableView<Livro> livros_table;

    @FXML
    private TableColumn<Livro, String> col_titulo;

    @FXML
    private TableColumn<Livro, Date> col_dataPublicacao;

    @FXML
    private TableColumn<Livro, String> col_editora;

    @FXML
    private TableColumn<Livro, String> col_linguaOficial;

    @FXML
    private TableColumn<Livro, Genero> col_genero;
    
    @FXML
    private TextField titulo_txt;

    @FXML
    private TextField editora_txt;

    @FXML
    private TextField linguaOficial_txt;

    @FXML
    private DatePicker dataPublicacao_datePicker;
    
    @FXML
    private ChoiceBox<Genero> choicebox_generos;
    
    @FXML
    private Button criar_livro_btn;

    @FXML
    private Button rmv_livro_btn;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList<Livro> lista_livros = FXCollections.observableArrayList();
        List<Livro> livros = LivroBLL.readAllWithGenero();
        
        for(Livro livros_ : livros){
            lista_livros.add(new Livro(livros_.getTitulo(), livros_.getDataPublicacao(), livros_.getEditora(), livros_.getLinguaOficial(), livros_.getGeneroId()));
            System.out.println("Livro: " + lista_livros.toString());
        }
        
        col_titulo.setCellValueFactory(new PropertyValueFactory<>("titulo"));
        col_dataPublicacao.setCellValueFactory(new PropertyValueFactory<>("dataPublicacao"));
        col_editora.setCellValueFactory(new PropertyValueFactory<>("editora"));
        col_linguaOficial.setCellValueFactory(new PropertyValueFactory<>("linguaOficial"));
        col_genero.setCellValueFactory(new PropertyValueFactory<>("generoId"));
        
        livros_table.setItems(lista_livros);
        
        // Preencher lista de escolhas de género
        ObservableList<Genero> lista_generos = FXCollections.observableArrayList();
        List<Genero> generos = GeneroBLL.readAll();
        
        for(Genero generos_ : generos){
            //String nomeGenero = generos_.getNome();
            lista_generos.add(new Genero(generos_.getIdGenero(), generos_.getNome()));
            lista_generos.toString();
            //System.out.println("id: " + generos_.getIdGenero());
            //System.out.println("genero: " + generos_.getNome());
        }
        choicebox_generos.setItems(lista_generos);
        
    }    
    
    @FXML
    void criarLivro(ActionEvent event){
        System.out.println("click do botao");
        LocalDate dataLC = dataPublicacao_datePicker.getValue();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String data_string = dataLC.format(formatter);
        LocalDate data_parsed = LocalDate.parse(data_string, formatter);
        //Date data_publicacao = Date.valueOf(data_parsed);
        
        Date data_publicacao = java.sql.Date.valueOf(dataPublicacao_datePicker.getValue());
        
        //data_nascimentoLD = data_nascimento_picker.getValue();
        //System.out.println("data nascimento: " + data_nascimento);
        
        Alert alert = new Alert(Alert.AlertType.NONE);
        Livro livro = new Livro();
        Genero idGenero = choicebox_generos.getValue();
        livro.setTitulo(titulo_txt.getText());
        livro.setEditora(editora_txt.getText());
        livro.setLinguaOficial(linguaOficial_txt.getText());
        livro.setDataPublicacao(data_publicacao);
        livro.setGeneroId(choicebox_generos.getValue());
        LivroBLL.create(livro);
        livros_table.refresh();
        
        
        
        System.out.println("nome genero: " + livro.getGeneroId().getNome());
        System.out.println("titulo: " + livro.getTitulo());
        System.out.println("data_publicacao: " + data_publicacao);
    }
    
    
}
