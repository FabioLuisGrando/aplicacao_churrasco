package com.fabioG.domains;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotNull;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Fábio Grando
 */

@Entity
@Table(name = "pedidos")
public class Pedidos implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @JoinColumn(name = "id_usuario", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Usuarios usuario;
   @NotNull
    @Column(name = "data_pedido")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataPedido;
    @Column(name = "status")
    private Integer status;
    @Column(name = "valor_total")
    private double valorTotal;
    @OneToMany(mappedBy = "pedidos", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProdutosXPedidos> listaDeProdutosxPedidos = new ArrayList<>();
    
    
public Pedidos() {
}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Usuarios getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuarios usuario) {
        this.usuario = usuario;
    }

    public Date getDataPedido() {
        return dataPedido;
    }

    public void setDataPedido(Date dataPedido) {
        this.dataPedido = dataPedido;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public List<ProdutosXPedidos> getListaDeProdutosxPedidos() {
        return listaDeProdutosxPedidos;
    }

    public void setListaDeProdutosxPedidos(List<ProdutosXPedidos> listaDeProdutosxPedidos) {
        this.listaDeProdutosxPedidos = listaDeProdutosxPedidos;
    }
}
