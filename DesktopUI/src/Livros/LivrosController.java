/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Livros;

import BLL.AutorBLL;
import BLL.GeneroBLL;
import BLL.LivroBLL;
import DAL.Autor;
import DAL.Genero;
import DAL.Livro;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
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
    private TextField filter_livro_txt;

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
        
        atualizarTabela();
        pesquisar();
        preencherCheckBoxGenero();
        
        /*// Preencher lista de escolhas de género
        ObservableList<Autor> lista_autores = FXCollections.observableArrayList();
        List<Autor> autores = AutorBLL.readAll();
        
        for(Autor autores_ : autores){
            //String nomeGenero = generos_.getNome();
            lista_autores.add(new Autor(autores_.getIdAutor(), autores_.getNome()));
            lista_autores.toString();
            //System.out.println("id: " + generos_.getIdGenero());
            //System.out.println("genero: " + generos_.getNome());
        }
        choicebox_autores.setItems(lista_autores);*/
        
    }    
    
    void preencherCheckBoxGenero(){
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
    
    void pesquisar(){
        ObservableList<Livro> lista_livros_pesquisa = FXCollections.observableArrayList();
        
        col_titulo.setCellValueFactory(new PropertyValueFactory<>("titulo"));
        col_dataPublicacao.setCellValueFactory(new PropertyValueFactory<>("dataPublicacao"));
        col_editora.setCellValueFactory(new PropertyValueFactory<>("editora"));
        col_linguaOficial.setCellValueFactory(new PropertyValueFactory<>("linguaOficial"));
        col_genero.setCellValueFactory(new PropertyValueFactory<>("generoId"));
        
        List<Livro> livros = LivroBLL.readAllWithGenero();
        
        for(Livro livros_ : livros){
            System.out.println("Livro: " + livros_.getAutorList());
            lista_livros_pesquisa.add(new Livro(livros_.getTitulo(), livros_.getDataPublicacao(), livros_.getEditora(), livros_.getLinguaOficial(), livros_.getGeneroId()));
        }
        
        livros_table.setItems(lista_livros_pesquisa);
        FilteredList<Livro> filteredData = new FilteredList<>(lista_livros_pesquisa, b -> true);
        
        filter_livro_txt.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(livro -> {
                
                if(newValue == null || newValue.isEmpty()){
                    return true;    
                }
                
                String lowerCaseFilter = newValue.toLowerCase();
                
                if(livro.getTitulo().toLowerCase().indexOf(lowerCaseFilter) != -1){
                    return true;
                } else if(livro.getGeneroId().toString().toLowerCase().indexOf(lowerCaseFilter) != -1){
                    return true;
                } else if(livro.getLinguaOficial().toLowerCase().indexOf(lowerCaseFilter) != -1){
                    return true;
                }else
                return false;
            });
        });
        SortedList<Livro> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(livros_table.comparatorProperty());
        livros_table.setItems(sortedData);
    }
    
    void atualizarTabela(){
        ObservableList<Livro> lista_livros = FXCollections.observableArrayList();
        List<Livro> livros = LivroBLL.readAllWithGenero();
        
        for(Livro livros_ : livros){
            lista_livros.add(new Livro(livros_.getTitulo(), livros_.getDataPublicacao(), livros_.getEditora(), livros_.getLinguaOficial(), livros_.getGeneroId()));
        }
        
        col_titulo.setCellValueFactory(new PropertyValueFactory<>("titulo"));
        col_dataPublicacao.setCellValueFactory(new PropertyValueFactory<>("dataPublicacao"));
        col_editora.setCellValueFactory(new PropertyValueFactory<>("editora"));
        col_linguaOficial.setCellValueFactory(new PropertyValueFactory<>("linguaOficial"));
        col_genero.setCellValueFactory(new PropertyValueFactory<>("generoId"));
        
        livros_table.setItems(lista_livros);
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
        System.out.println("aaaaaabbbbbbb" + choicebox_generos.getValue());
        livro.setGeneroId(choicebox_generos.getValue());
        LivroBLL.create(livro);
        atualizarTabela();
        titulo_txt.clear();
        //autor_txt.clear();
        editora_txt.clear();
        linguaOficial_txt.clear();
        dataPublicacao_datePicker.setValue(null);
        choicebox_generos.setValue(null);
        
        
        
        System.out.println("nome genero: " + livro.getGeneroId().getNome());
        System.out.println("titulo: " + livro.getTitulo());
        System.out.println("data_publicacao: " + data_publicacao);
    }
    
    
}
