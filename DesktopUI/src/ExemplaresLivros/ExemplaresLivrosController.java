/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ExemplaresLivros;

import BLL.EstadoBLL;
import BLL.ExemplarLivroBLL;
import DAL.Edicao;
import DAL.Estado;
import DAL.ExemplarLivro;
import DAL.Lingua;
import java.math.BigDecimal;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author Pedro
 */
public class ExemplaresLivrosController implements Initializable {
    
     @FXML
    private TableView<ExemplarLivro> exemplares_table;

    @FXML
    private TableColumn<ExemplarLivro, BigDecimal> col_idExemplar;

    @FXML
    private TableColumn<ExemplarLivro, String> col_titulo;

    @FXML
    private TableColumn<ExemplarLivro, Integer> col_numPaginas;

    @FXML
    private TableColumn<ExemplarLivro, Lingua> col_lingua;

    @FXML
    private TableColumn<ExemplarLivro, Edicao> col_edicao;

    @FXML
    private TableColumn<ExemplarLivro, Estado> col_estado;

    @FXML
    private ChoiceBox<Estado> choicebox_estados;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        //Preencher lista com todas instâncias de livro guardadas na base de dados
        ObservableList<ExemplarLivro> lista_exemplares = FXCollections.observableArrayList();
        List<ExemplarLivro> exemplares = ExemplarLivroBLL.readAll();
        
        for(ExemplarLivro exemplares_ : exemplares){
            lista_exemplares.add(new ExemplarLivro(exemplares_.getIdExemplar(), exemplares_.getLivroId(), exemplares_.getNumPaginas(), exemplares_.getLinguaId(), exemplares_.getEdicaoId(), exemplares_.getEstadoId()));
            System.out.println("Exemplar: " + lista_exemplares.toString());
        }
        
        col_idExemplar.setCellValueFactory(new PropertyValueFactory<>("idExemplar"));
        col_titulo.setCellValueFactory(new PropertyValueFactory<>("livroId"));
        col_numPaginas.setCellValueFactory(new PropertyValueFactory<>("numPaginas"));
        col_lingua.setCellValueFactory(new PropertyValueFactory<>("linguaId"));
        col_edicao.setCellValueFactory(new PropertyValueFactory<>("edicaoId"));
        col_estado.setCellValueFactory(new PropertyValueFactory<>("estadoId"));
        
        exemplares_table.setItems(lista_exemplares);
        
        // Preencher lista de escolhas do estado
        ObservableList<Estado> lista_estados = FXCollections.observableArrayList();
        List<Estado> estados = EstadoBLL.readAll();
        
        for(Estado estados_ : estados){
            //String nomeGenero = generos_.getNome();
            lista_estados.add(new Estado(estados_.getIdEstado(), estados_.getNome()));
            lista_estados.toString();
            //System.out.println("id: " + generos_.getIdGenero());
            //System.out.println("genero: " + generos_.getNome());
        }
        choicebox_estados.setItems(lista_estados);
    }    
    
}
