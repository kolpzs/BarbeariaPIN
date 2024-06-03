package model.entities;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Agendar")
public class Agendar {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "funcionario_id_fk", nullable = false)
    private Funcionario funcionario;

    @ManyToOne
    @JoinColumn(name = "cliente_id_fk", nullable = false)
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "servico_id_fk", nullable = false)
    private Servico servico;

    @Column(name = "horario")
    private java.util.Date horario;

    @Column(name = "ativo")
    private Boolean ativo;

    public Agendar(Long id, Funcionario funcionario, Cliente cliente, Servico servico, Date horario, Boolean ativo) {
        this.id = id;
        this.funcionario = funcionario;
        this.cliente = cliente;
        this.servico = servico;
        this.horario = horario;
        this.ativo = ativo;
    }

    public Agendar(){}

    // Getters e setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Servico getServico() {
        return servico;
    }

    public void setServico(Servico servico) {
        this.servico = servico;
    }

    public Date getHorario() {
        return horario;
    }

    public void setHorario(Date horario) {
        this.horario = horario;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }
}
