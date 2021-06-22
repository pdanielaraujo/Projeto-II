/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Exemplares;

import BLL.EdicaoBLL;
import BLL.EstadoBLL;
import BLL.ExemplarLivroBLL;
import BLL.LinguaBLL;
import BLL.LivroBLL;
import DAL.Edicao;
import DAL.Estado;
import DAL.ExemplarLivro;
import DAL.Lingua;
import DAL.Livro;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.binding.Bindings;
import javafx.beans.value.ObservableValue;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author Pedro
 */
public class ExemplaresController implements Initializable {

    @FXML
    private TableView<ExemplarLivro> exemplares_table;

    @FXML
    private TableColumn<ExemplarLivro, BigDecimal> col_idExemplar;

    @FXML
    private TableColumn<ExemplarLivro, Livro> col_titulo;

    @FXML
    private TableColumn<ExemplarLivro, BigInteger> col_numPag;

    @FXML
    private TableColumn<ExemplarLivro, Lingua> col_lingua;

    @FXML
    private TableColumn<ExemplarLivro, Edicao> col_edicao;

    @FXML
    private TableColumn<ExemplarLivro, Estado> col_estado;
    
    @FXML
    private Button upd_exemplar_btn;

    @FXML
    private ChoiceBox<Estado> choicebox_estados;
    
    @FXML
    private ChoiceBox<Livro> choicebox_livros;

    @FXML
    private ChoiceBox<Lingua> choicebox_linguas;

    @FXML
    private TextField paginas_txt;

    @FXML
    private ChoiceBox<Edicao> choicebox_edicoes;

    @FXML
    private ChoiceBox<Estado> choicebox_estados_criar;

    @FXML
    private Button criar_exemplar_btn;
    
    @FXML
    private TextField filter_exemplar_txt;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        atualizarTabela();
        pesquisar();
        preencherCheckBoxEstado();
        preencherCheckBoxLivro();
        preencherCheckBoxLingua();
        preencherCheckBoxEdicao();
    }    
    @FXML
    void atualizarTabela(){
        ObservableList<ExemplarLivro> lista_exemplares = FXCollections.observableArrayList();
        List<ExemplarLivro> exemplares = ExemplarLivroBLL.readAll();
        
        for(ExemplarLivro exemplares_ : exemplares){
            lista_exemplares.add(new ExemplarLivro(exemplares_.getIdExemplar(), exemplares_.getLivroId(), exemplares_.getNumPaginas(), exemplares_.getLinguaId(), exemplares_.getEdicaoId(), exemplares_.getEstadoId()));
        }
        
        col_idExemplar.setCellValueFactory(new PropertyValueFactory<>("idExemplar"));
        col_titulo.setCellValueFactory(new PropertyValueFactory<>("livroId"));
        col_numPag.setCellValueFactory(new PropertyValueFactory<>("numPaginas"));
        col_lingua.setCellValueFactory(new PropertyValueFactory<>("linguaId"));
        col_edicao.setCellValueFactory(new PropertyValueFactory<>("edicaoId"));
        col_estado.setCellValueFactory(new PropertyValueFactory<>("estadoId"));
        
        exemplares_table.setItems(lista_exemplares);
        exemplares_table.refresh();
    }
    
    void pesquisar(){
        ObservableList<ExemplarLivro> lista_exemplares_pesquisa = FXCollections.observableArrayList();
        
        col_idExemplar.setCellValueFactory(new PropertyValueFactory<>("idExemplar"));
        col_titulo.setCellValueFactory(new PropertyValueFactory<>("livroId"));
        col_numPag.setCellValueFactory(new PropertyValueFactory<>("numPaginas"));
        col_lingua.setCellValueFactory(new PropertyValueFactory<>("linguaId"));
        col_edicao.setCellValueFactory(new PropertyValueFactory<>("edicaoId"));
        col_estado.setCellValueFactory(new PropertyValueFactory<>("estadoId"));
        
        List<ExemplarLivro> exemplares = ExemplarLivroBLL.readAll();
        
        for(ExemplarLivro exemplares_ : exemplares){
            lista_exemplares_pesquisa.add(new ExemplarLivro(exemplares_.getIdExemplar(), exemplares_.getLivroId(), exemplares_.getNumPaginas(), exemplares_.getLinguaId(), exemplares_.getEdicaoId(), exemplares_.getEstadoId()));
        }
        
        exemplares_table.setItems(lista_exemplares_pesquisa);
        FilteredList<ExemplarLivro> filteredData = new FilteredList<>(lista_exemplares_pesquisa, b -> true);
        
        filter_exemplar_txt.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(exemplar -> {
                
                if(newValue == null || newValue.isEmpty()){
                    return true;    
                }
                
                String lowerCaseFilter = newValue.toLowerCase();
                
                if(exemplar.getIdExemplar().toString().toLowerCase().indexOf(lowerCaseFilter) != -1){
                    return true;
                } else if(exemplar.getLivroId().toString().toLowerCase().indexOf(lowerCaseFilter) != -1){
                    return true;
                } else if(exemplar.getLinguaId().toString().toLowerCase().indexOf(lowerCaseFilter) != -1){
                    return true;
                } else if(exemplar.getEdicaoId().toString().toLowerCase().indexOf(lowerCaseFilter) != -1){
                    return true;
                } else if(exemplar.getEstadoId().toString().toLowerCase().indexOf(lowerCaseFilter) != -1){
                    return true;
                }
                return false;
            });
        });
        SortedList<ExemplarLivro> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(exemplares_table.comparatorProperty());
        exemplares_table.setItems(sortedData);
    }
    
    void preencherCheckBoxEstado(){
        // Preencher lista de escolhas de estado
        ObservableList<Estado> lista_estados = FXCollections.observableArrayList();
        List<Estado> estados = EstadoBLL.readAll();
        
        for(Estado estados_ : estados){
            lista_estados.add(new Estado(estados_.getIdEstado(), estados_.getNome()));
            lista_estados.toString();
        }
        
        choicebox_estados.setItems(lista_estados);
        choicebox_estados_criar.setItems(lista_estados);
    }
    
    void preencherCheckBoxLivro(){
        // Preencher lista de escolhas de livro
        ObservableList<Livro> lista_livros = FXCollections.observableArrayList();
        List<Livro> livros = LivroBLL.readAll();
        
        for(Livro livros_ : livros){
            lista_livros.add(new Livro(livros_.getIdLivro(), livros_.getTitulo()));
            lista_livros.toString();
        }
        
        choicebox_livros.setItems(lista_livros);
    }
    
    void preencherCheckBoxLingua(){
        // Preencher lista de escolhas de língua
        ObservableList<Lingua> lista_linguas = FXCollections.observableArrayList();
        List<Lingua> linguas = LinguaBLL.readAll();
        
        for(Lingua linguas_ : linguas){
            lista_linguas.add(new Lingua(linguas_.getIdLingua(), linguas_.getNome()));
            lista_linguas.toString();
        }
        
        choicebox_linguas.setItems(lista_linguas);
    }
    
    void preencherCheckBoxEdicao(){
        // Preencher lista de escolhas de edição
        ObservableList<Edicao> lista_edicoes = FXCollections.observableArrayList();
        List<Edicao> edicoes = EdicaoBLL.readAll();
        
        for(Edicao edicoes_ : edicoes){
            lista_edicoes.add(new Edicao(edicoes_.getIdEdicao(), edicoes_.getNome()));
            lista_edicoes.toString();
        }
        
        choicebox_edicoes.setItems(lista_edicoes);
    }
    
    @FXML
    void atualizarEstado(ActionEvent event){
        col_estado.setEditable(true);
        
        // Obter exemplar selecionada na tabela
        ExemplarLivro exemplar = exemplares_table.getSelectionModel().getSelectedItem();
        
        // Atualizar exemplar selecionado
        ExemplarLivroBLL.updateEstado(exemplar.getIdExemplar(), choicebox_estados.getValue());
        
        exemplares_table.refresh();
        atualizarTabela();
        
        // Resetar a choicebox
        choicebox_estados.setValue(null);
    }
    
    @FXML
    void criarExemplar(ActionEvent event){
        
        Integer num_paginas = Integer.parseInt(paginas_txt.getText());
        
        // Criar exemplar
        ExemplarLivro exemplar = new ExemplarLivro();
        exemplar.setLivroId(choicebox_livros.getValue());
        exemplar.setNumPaginas(num_paginas);
        exemplar.setLinguaId(choicebox_linguas.getValue());
        exemplar.setEdicaoId(choicebox_edicoes.getValue());
        exemplar.setEstadoId(choicebox_estados_criar.getValue());
        ExemplarLivroBLL.create(exemplar);
        
        atualizarTabela();
        
        // Resetar as choice boxes e limpar os texts inputs
        paginas_txt.clear();
        choicebox_livros.setValue(null);
        choicebox_linguas.setValue(null);
        choicebox_edicoes.setValue(null);
        choicebox_estados_criar.setValue(null);
    }
    
}
