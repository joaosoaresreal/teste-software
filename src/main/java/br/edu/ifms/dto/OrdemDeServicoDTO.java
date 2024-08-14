package br.edu.ifms.dto;

import java.io.Serializable;
import java.sql.Date;

import br.edu.ifms.entities.Equipamento;
import br.edu.ifms.entities.OrdemDeServico;
import br.edu.ifms.entities.Tecnico;
import br.edu.ifms.entities.enums.Prioridade;
import br.edu.ifms.entities.enums.Status;

public class OrdemDeServicoDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String descricaoProblema;
	private String descricaoSolucao;
	private Date dataCadastro;
	private Status status;
	private Prioridade prioridade;
	private Tecnico tecnico;
	private Equipamento equipamento;
	//private List<EquipamentoDTO> equipamentos = new ArrayList<>();
	
	public OrdemDeServicoDTO() {
		// TODO Auto-generated constructor stub
	}

	public OrdemDeServicoDTO(Long id, String descricaoProblema, String descricaoSolucao, Date dataCadastro,
			Status status, Prioridade prioridade, Tecnico tecnico, Equipamento equipamento) {
		this.id = id;
		this.descricaoProblema = descricaoProblema;
		this.descricaoSolucao = descricaoSolucao;
		this.dataCadastro = dataCadastro;
		this.status = status;
		this.prioridade = prioridade;
		this.tecnico = tecnico;
		this.equipamento = equipamento;
	}
	
	public OrdemDeServicoDTO(OrdemDeServico entity) {
		this.id = entity.getId();
		this.descricaoProblema = entity.getDescricaoProblema();
		this.descricaoSolucao = entity.getDescricaoSolucao();
		this.dataCadastro = entity.getDataCadastro();
		this.status = entity.getStatus();
		this.prioridade = entity.getPrioridade();
		this.tecnico = entity.getTecnico();
		this.equipamento = entity.getEquipamento();
	}
	
	/*public OrdemDeServicoDTO(OrdemDeServico entity, Set<Equipamento> equipamentos) {
		this(entity);
		equipamentos.forEach(equipamento -> new EquipamentoDTO(equipamento));
	}*/

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

	/*public List<EquipamentoDTO> getEquipamentos() {
		return equipamentos;
	}*/
	
}
