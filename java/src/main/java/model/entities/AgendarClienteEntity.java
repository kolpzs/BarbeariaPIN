package model.entities;

import javax.persistence.*;

@Entity
@Table(name = "Agendar_Cliente")
public class AgendarClienteEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long agendar_id;

    @ManyToOne
    @JoinColumn(name = "agendar_id", insertable = false, updatable = false)
    private AgendarEntity agendarEntity;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cliente_id;

    @ManyToOne
    @JoinColumn(name = "cliente_id", insertable = false, updatable = false)
    private ClienteEntity clienteEntity;

    public AgendarClienteEntity() {
    }

    public AgendarClienteEntity(Long agendar_id, Long cliente_id) {
        this.agendar_id = agendar_id;
        this.cliente_id = cliente_id;
    }

    public Long getAgendarId() {
        return agendar_id;
    }

    public void setAgendarId(Long agendar_id) {
        this.agendar_id = agendar_id;
    }

    public Long getClienteId() {
        return cliente_id;
    }

    public void setClienteId(Long cliente_id) {
        this.cliente_id = cliente_id;
    }
}
