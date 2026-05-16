package org.example.model;

import jakarta.persistence.*;

@Entity
@Table( name = "pedido" )
public class Pedido {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private int id;

    @Column( nullable = false )
    private String descricao;

    @Column( nullable = false )
    private double valor;

    @ManyToOne
    @JoinColumn( name = "cliente_id", nullable = false )
    private Cliente cliente;



    public void setId( int id ) {
        this.id = id;
    }

    public int getId() {
        return id;
    }



    public void setDescricao( String descricao ) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }



    public void setValor( double valor ) {
        this.valor = valor;
    }

    public double getValor() {
        return valor;
    }



    public void setCliente( Cliente cliente ) {
        this.cliente = cliente;
    }

    public Cliente getCliente() {
        return cliente;
    }

}
