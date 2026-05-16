package org.example.model;

import jakarta.persistence.*;

@Entity
@Table( name = "Cliente" )
public class Cliente {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private int id;

    @Column( nullable = false )
    private String nome;

    @Column( nullable = false )
    private String cpf;

    @Column( nullable = false )
    private String cep;

    private String cidade;
    private String estado;



    public void setId( int id ) {
        this.id = id;
    }

    public int getId() {
        return id;
    }



    public void setNome( String nome ) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }



    public void setCpf( String cpf ) {
        this.cpf = cpf;
    }

    public String getCpf() {
        return cpf;
    }



    public void setCep( String cep ) {
        this.cep = cep;
    }

    public String getCep() {
        return cep;
    }



    public void setCidade( String cidade ) {
        this.cidade = cidade;
    }

    public String getCidade() {
        return cidade;
    }



    public void setEstado( String estado ) {
        this.estado = estado;
    }

    public String getEstado() {
        return estado;
    }

}
