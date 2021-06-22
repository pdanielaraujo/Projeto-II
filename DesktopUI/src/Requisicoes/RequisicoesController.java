/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Requisicoes;

import BLL.BibliotecarioBLL;
import BLL.EntregaBLL;
import BLL.EstadoBLL;
import BLL.ExemplarLivroBLL;
import BLL.RequisicaoBLL;
import BLL.UtilizadorBLL;
import DAL.Bibliotecario;
import DAL.Entrega;
import DAL.Estado;
import DAL.ExemplarLivro;
import DAL.Leitor;
import DAL.Requisicao;
import DAL.Utilizador;
import java.math.BigDecimal;
import java.net.URL;
import java.util.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
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
    private TableColumn<Requisicao, Long> col_tempRequisicao;

    @FXML
    private TableColumn<Requisicao, Date> col_dataPrevEntrega;

    @FXML
    private TableColumn<Requisicao, Entrega> col_entrega;
    
    @FXML
    private TextField filter_requisicao_txt;
    
    @FXML
    private Button confirmar_entrega_btn;
    
    @FXML
    private Label id_bibliotecario_txt;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        atualizarTabela();
        pesquisar();
    }    
    
    void atualizarTabela(){
        ObservableList<Requisicao> lista_requisicoes = FXCollections.observableArrayList();
        List<Requisicao> requisicoes = RequisicaoBLL.readAll();
        
        for(Requisicao requisicoes_ : requisicoes){
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
    
    void pesquisar(){
        ObservableList<Requisicao> lista_requisicoes_pesquisa = FXCollections.observableArrayList();
        
        col_idRequisicao.setCellValueFactory(new PropertyValueFactory<>("idRequisicao"));
        col_leitor.setCellValueFactory(new PropertyValueFactory<>("leitorId"));
        col_exemplarLivro.setCellValueFactory(new PropertyValueFactory<>("exemplarId"));
        col_dataRequisicao.setCellValueFactory(new PropertyValueFactory<>("dataReq"));
        col_tempRequisicao.setCellValueFactory(new PropertyValueFactory<>("tempReq"));
        col_dataPrevEntrega.setCellValueFactory(new PropertyValueFactory<>("dataPrevEntrega"));
        col_entrega.setCellValueFactory(new PropertyValueFactory<>("entregaId"));
        
        List<Requisicao> requisicoes = RequisicaoBLL.readAll();
        
        for(Requisicao requisicoes_ : requisicoes){
            lista_requisicoes_pesquisa.add(new Requisicao(requisicoes_.getIdRequisicao(), requisicoes_.getLeitorId(), requisicoes_.getExemplarId(), requisicoes_.getDataReq(), requisicoes_.getTempReq(), requisicoes_.getDataPrevEntrega(), requisicoes_.getEntregaId()));
        }
        
        requisicoes_table.setItems(lista_requisicoes_pesquisa);
        FilteredList<Requisicao> filteredData = new FilteredList<>(lista_requisicoes_pesquisa, b -> true);
        
        filter_requisicao_txt.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(requisicao -> {
                
                if(newValue == null || newValue.isEmpty()){
                    return true;    
                }
                
                String lowerCaseFilter = newValue.toLowerCase();
                
                if(requisicao.getIdRequisicao().toString().toLowerCase().indexOf(lowerCaseFilter) != -1){
                    return true;
                } else if(requisicao.getLeitorId().toString().toLowerCase().indexOf(lowerCaseFilter) != -1){
                    return true;
                } else if(requisicao.getExemplarId().toString().toLowerCase().indexOf(lowerCaseFilter) != -1){
                    return true;
                } else if(requisicao.getEntregaId().toString().toLowerCase().indexOf(lowerCaseFilter) != -1){
                    return true;
                }
                return false;
            });
        });
        SortedList<Requisicao> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(requisicoes_table.comparatorProperty());
        requisicoes_table.setItems(sortedData);
    }
    
    public void entregaUpdate(String username){
        id_bibliotecario_txt.setText(username);
    }
    
    @FXML
    void confirmarEntrega() throws ParseException{
        
        Alert alert = new Alert(Alert.AlertType.NONE);
        
        String username = id_bibliotecario_txt.getText();
        // ID de utilizador do bibliotecario com login
        Utilizador userBibliotecario = UtilizadorBLL.readUsername(username);
        
        // Obter o bibliotecario através do ID de utilizador
        Bibliotecario bibliotecarioId = BibliotecarioBLL.read(userBibliotecario.getIdUtilizador());
        
        // Obter requisição selecionada na tabela
        Requisicao requisicao = requisicoes_table.getSelectionModel().getSelectedItem();
       
        // Obter a data atual
        Date dataAtual = new Date();
        DateFormat outputFormatDataAtual = new SimpleDateFormat("dd-MM-yyyy");
        String dataReqFinalString = outputFormatDataAtual.format(dataAtual);
        Date dataReqFinal = outputFormatDataAtual.parse(dataReqFinalString);
        
        if(requisicao.getEntregaId() != null){
            alert.setAlertType(Alert.AlertType.ERROR);
            alert.setTitle("Entregas");
            alert.setHeaderText("Requisição já foi entregue.");
            alert.show(); 
        } else{
            // Criar Entrega
            Entrega entrega = new Entrega();
            entrega.setDataEntrega(dataReqFinal);
            entrega.setRequisicaoId(requisicao);
            entrega.setLeitorId(requisicao.getLeitorId());
            entrega.setBibliotecarioId(bibliotecarioId);
            EntregaBLL.create(entrega);
        
            // Atualização da Requisição
            RequisicaoBLL.updateEntrega(requisicao.getIdRequisicao(), entrega);
            
            // Buscar todos os estados para posteriormente atualizar o estado
            List<Estado> lista_estados = new ArrayList<>();
            List<Estado> estados = EstadoBLL.readAll();
            
            // Ir buscar todos os estados
            for(Estado estados_ : estados){
                //String nomeGenero = generos_.getNome();
                lista_estados.add(new Estado(estados_.getIdEstado(), estados_.getNome()));
                lista_estados.toString();
            }
            
            // Buscar o valor dos estados
            Estado estadoRequisitado = lista_estados.get(0);
            Estado estadoNaoRequisitado = lista_estados.get(1);
            
            // Atualizar estado para «Não Requisitado» depois de criar entrega e atualizara requisição
            ExemplarLivro exemplar = requisicao.getExemplarId();
            ExemplarLivroBLL.updateEstado(exemplar.getIdExemplar(), estadoNaoRequisitado);
            
            requisicoes_table.refresh();
            atualizarTabela();
        }   
    }
}