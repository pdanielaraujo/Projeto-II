/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Requisicoes;

import BLL.EstadoBLL;
import BLL.RequisicaoBLL;
import DAL.Bibliotecario;
import DAL.Entrega;
import DAL.Estado;
import DAL.ExemplarLivro;
import DAL.Leitor;
import DAL.Requisicao;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.Date;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
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
public class RequisicoesController implements Initializable {

    @FXML
    private TableView<Requisicao> requisicoes_table;

    @FXML
    private TableColumn<Requisicao, BigDecimal> col_idReq;

    @FXML
    private TableColumn<Requisicao, Date> col_dataRequisicao;

    @FXML
    private TableColumn<Requisicao, Integer> col_tempReq;

    @FXML
    private TableColumn<Requisicao, Date> col_dataPrevEntrega;

    @FXML
    private TableColumn<Requisicao, Leitor> col_leitor;

    @FXML
    private TableColumn<Requisicao, ExemplarLivro> col_exemplarLivro;

    @FXML
    private TableColumn<Requisicao, Bibliotecario> col_bibliotecario;

    @FXML
    private TableColumn<Requisicao, Entrega> col_entrega;
    
    @FXML
    private ChoiceBox<Estado> choicebox_estados;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        //Preencher lista com todas instâncias de livro guardadas na base de dados
        ObservableList<Requisicao> lista_requisicoes = FXCollections.observableArrayList();
        List<Requisicao> requisicoes = RequisicaoBLL.readAll();
        
        for(Requisicao requisicoes_ : requisicoes){
            lista_requisicoes.add(new Requisicao(requisicoes_.getIdRequisicao(), requisicoes_.getDataReq(), requisicoes_.getTempReq(), requisicoes_.getDataPrevEntrega(), requisicoes_.getLeitorId(), requisicoes_.getExemplarId(), requisicoes_.getBibliotecarioId()));
            System.out.println("Requisicao: " + lista_requisicoes.toString());
        }
        
        col_idReq.setCellValueFactory(new PropertyValueFactory<>("idRequisicao"));
        col_dataRequisicao.setCellValueFactory(new PropertyValueFactory<>("dataReq"));
        col_tempReq.setCellValueFactory(new PropertyValueFactory<>("tempReq"));
        col_dataPrevEntrega.setCellValueFactory(new PropertyValueFactory<>("dataPrevEntrega"));
        col_leitor.setCellValueFactory(new PropertyValueFactory<>("leitorId"));
        col_exemplarLivro.setCellValueFactory(new PropertyValueFactory<>("exemplarId"));
        col_bibliotecario.setCellValueFactory(new PropertyValueFactory<>("bibliotecarioId"));
        col_entrega.setCellValueFactory(new PropertyValueFactory<>("entregaId"));
        
        requisicoes_table.setItems(lista_requisicoes);
        
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
    
    @FXML
    void atualizarRequisicao(ActionEvent event){
        ExemplarLivro exemplar = new ExemplarLivro();
        
        exemplar.setEstadoId(choicebox_estados.getValue());
    }
    
}
