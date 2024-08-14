package br.edu.ifms.entities;

import java.io.Serializable;
import java.sql.Date;
import java.util.Objects;

import br.edu.ifms.entities.enums.Prioridade;
import br.edu.ifms.entities.enums.Status;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_ordem_de_servico")
public class OrdemDeServico implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "descricao_problema")
	private String descricaoProblema;
	@Column(name = "descricao_solucao")
	private String descricaoSolucao;
	@Column(name = "data_cadastro")
	private Date dataCadastro;
	@Enumerated(EnumType.STRING)
	private Status status;
	@Enumerated(EnumType.STRING)
	private Prioridade prioridade;
	
	@ManyToOne
	@JoinColumn(name = "id_tecnico_fk")
	private Tecnico tecnico;
	
	@ManyToOne
	@JoinColumn(name = "id_equipamento_fk")
	private Equipamento equipamento;
	
	
	public OrdemDeServico() {
		// TODO Auto-generated constructor stub
	}

	public OrdemDeServico(Long id, String descricaoProblema, String descricaoSolucao, Date dataCadastro, Status status,
			Prioridade prioridade, Tecnico tecnico, Equipamento equipamento) {
		this.id = id;
		this.descricaoProblema = descricaoProblema;
		this.descricaoSolucao = descricaoSolucao;
		this.dataCadastro = dataCadastro;
		this.status = status;
		this.prioridade = prioridade;
		this.tecnico = tecnico;
		this.equipamento = equipamento;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescricaoProblema() {
		return descricaoProblema;
	}

	public void setDescricaoProblema(String descricaoProblema) {
		this.descricaoProblema = descricaoProblema;
	}

	public String getDescricaoSolucao() {
		return descricaoSolucao;
	}

	public void setDescricaoSolucao(String descricaoSolucao) {
		this.descricaoSolucao = descricaoSolucao;
	}

	public Date getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public Prioridade getPrioridade() {
		return prioridade;
	}

	public void setPrioridade(Prioridade prioridade) {
		this.prioridade = prioridade;
	}

	public Tecnico getTecnico() {
		return tecnico;
	}

	public void setTecnico(Tecnico tecnico) {
		this.tecnico = tecnico;
	}

	public Equipamento getEquipamento() {
		return equipamento;
	}

	public void setEquipamento(Equipamento equipamento) {
		this.equipamento = equipamento;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OrdemDeServico other = (OrdemDeServico) obj;
		return Objects.equals(id, other.id);
	}

}
