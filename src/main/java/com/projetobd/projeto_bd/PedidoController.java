package com.projetobd.projeto_bd;

import Models.DAO.PedidoDAO;
import Models.Pedido;
import com.projetobd.projeto_bd.util.Constraints;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.time.LocalDate;
import java.util.Date;
import java.util.ResourceBundle;

public class PedidoController implements Initializable {

    @FXML
    private Button salvo;
    @FXML
    private DatePicker datePickerdataPedido;
    @FXML
    private TextField txtModoEncomenda;
    @FXML
    private TextField txtClientePedido;
    @FXML
    private TextField txtStatusPedido;
    @FXML
    private DatePicker datePickerdataEntegra;
    @FXML
    private TextField txtIdCliente;

    @FXML
    protected void onSalvarButtonClick() {
        try {
            LocalDate dataPedido = datePickerdataPedido.getValue();
            String modoEncomenda = txtModoEncomenda.getText();
            String clientePedido = txtClientePedido.getText();
            String statusPedido= txtStatusPedido.getText();
            LocalDate dataEntrega = datePickerdataEntegra.getValue();
            int idCliente = Integer.parseInt(txtIdCliente.getText());

            Pedido pedido = new Pedido();
            pedido.setData_pedido(java.sql.Date.valueOf(dataPedido));
            pedido.setModo_encomenda(modoEncomenda);
            pedido.setCliente_pedido(clientePedido);
            pedido.setStatus_pedido(statusPedido);
            pedido.setData_entrega(java.sql.Date.valueOf(dataEntrega));
            pedido.setId_cliente(idCliente);

            try {
                PedidoDAO pedidoDAO = new PedidoDAO();
                pedidoDAO.create(pedido);
            }catch (Exception e){
                throw new RuntimeException(e);
            }

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Pedido cadastrado com sucesso!");
            alert.show();

        } catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType. ERROR, "Digite um numero valido!");
            alert.show();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Constraints.setTextFieldInteger(txtIdCliente); //para aceitar apenas inteiros no campo de id

    }


}
