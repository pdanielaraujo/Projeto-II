/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Requisicoes;

import BLL.RequisicaoBLL;
import DAL.Entrega;
import DAL.ExemplarLivro;
import DAL.Leitor;
import DAL.Requisicao;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.URL;
import java.sql.Date;
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
public class RequisicoesController implements Initializable {

    @FXML
    private TableView<Requisicao> requisicoes_table;

    @FXML
    private TableColumn<Requisicao, BigDecimal> col_idRequisicao;

    @FXML
    private TableColumn<Requisicao, Leitor> col_leitor;

    @FXML
    private TableColumn<Requisicao, ExemplarLivro> col_exemplarLivro;

    @FXML
    private TableColumn<Requisicao, Date> col_dataRequisicao;

    @FXML
    private TableColumn<Requisicao, BigInteger> col_tempRequisicao;

    @FXML
    private TableColumn<Requisicao, Date> col_dataPrevEntrega;

    @FXML
    private TableColumn<Requisicao, Entrega> col_entrega;
    
    void atualizarTabela(){
        ObservableList<Requisicao> lista_requisicoes = FXCollections.observableArrayList();
        List<Requisicao> requisicoes = RequisicaoBLL.readAll();
        
        for(Requisicao requisicoes_ : requisicoes){
            System.out.println("requisicao: " + requisicoes_.getIdRequisicao());
            lista_requisicoes.add(new Requisicao(requisicoes_.getIdRequisicao(), requisicoes_.getLeitorId(), requisicoes_.getExemplarId(), requisicoes_.getDataReq(), requisicoes_.getTempReq(), requisicoes_.getDataPrevEntrega(), requisicoes_.getEntregaId()));
            
        }
        
        col_idRequisicao.setCellValueFactory(new PropertyValueFactory<>("idRequisicao"));
        col_leitor.setCellValueFactory(new PropertyValueFactory<>("leitorId"));
        col_exemplarLivro.setCellValueFactory(new PropertyValueFactory<>("exemplarId"));
        col_dataRequisicao.setCellValueFactory(new PropertyValueFactory<>("dataReq"));
        col_tempRequisicao.setCellValueFactory(new PropertyValueFactory<>("tempReq"));
        col_dataPrevEntrega.setCellValueFactory(new PropertyValueFactory<>("dataPrevEntrega"));
        col_entrega.setCellValueFactory(new PropertyValueFactory<>("entregaId"));
        
        requisicoes_table.setItems(lista_requisicoes);
    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        atualizarTabela();
    }    
    
}
