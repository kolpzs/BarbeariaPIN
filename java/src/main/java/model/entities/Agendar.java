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
    private FuncionarioEntity funcionarioEntity;

    @ManyToOne
    @JoinColumn(name = "cliente_id_fk", nullable = false)
    private ClienteEntity clienteEntity;

    @ManyToOne
    @JoinColumn(name = "servico_id_fk", nullable = false)
    private ServicoEntity servicoEntity;

    @Column(name = "horario")
    private java.util.Date horario;

    @Column(name = "ativo")
    private Boolean ativo;

    public Agendar(Long id, FuncionarioEntity funcionarioEntity, ClienteEntity clienteEntity, ServicoEntity servicoEntity, Date horario, Boolean ativo) {
        this.id = id;
        this.funcionarioEntity = funcionarioEntity;
        this.clienteEntity = clienteEntity;
        this.servicoEntity = servicoEntity;
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

    public FuncionarioEntity getFuncionario() {
        return funcionarioEntity;
    }

    public void setFuncionario(FuncionarioEntity funcionarioEntity) {
        this.funcionarioEntity = funcionarioEntity;
    }

    public ClienteEntity getCliente() {
        return clienteEntity;
    }

    public void setCliente(ClienteEntity clienteEntity) {
        this.clienteEntity = clienteEntity;
    }

    public ServicoEntity getServico() {
        return servicoEntity;
    }

    public void setServico(ServicoEntity servicoEntity) {
        this.servicoEntity = servicoEntity;
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
