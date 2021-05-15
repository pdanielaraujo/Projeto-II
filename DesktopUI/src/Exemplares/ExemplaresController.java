/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Exemplares;

import BLL.ExemplarLivroBLL;
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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

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
    
    void atualizarTabela(){
        ObservableList<ExemplarLivro> lista_exemplares = FXCollections.observableArrayList();
        List<ExemplarLivro> exemplares = ExemplarLivroBLL.readAll();
        
        for(ExemplarLivro exemplares_ : exemplares){
            //System.out.println("Livro: " + exemplares_.getAutorList());
            lista_exemplares.add(new ExemplarLivro(exemplares_.getIdExemplar(), exemplares_.getLivroId(), exemplares_.getNumPaginas(), exemplares_.getLinguaId(), exemplares_.getEdicaoId(), exemplares_.getEstadoId()));
            
        }
        
        col_idExemplar.setCellValueFactory(new PropertyValueFactory<>("idExemplar"));
        col_titulo.setCellValueFactory(new PropertyValueFactory<>("livroId"));
        col_numPag.setCellValueFactory(new PropertyValueFactory<>("numPaginas"));
        col_lingua.setCellValueFactory(new PropertyValueFactory<>("linguaId"));
        col_edicao.setCellValueFactory(new PropertyValueFactory<>("edicaoId"));
        col_estado.setCellValueFactory(new PropertyValueFactory<>("estadoId"));
        
        exemplares_table.setItems(lista_exemplares);
    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        atualizarTabela();
    }    
    
}
