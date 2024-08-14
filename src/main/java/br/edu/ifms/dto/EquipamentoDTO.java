package br.edu.ifms.dto;

import java.io.Serializable;

import br.edu.ifms.entities.Equipamento;
import br.edu.ifms.entities.Setor;

public class EquipamentoDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String equipamento;
	private String patrimonio;
	private Setor setor;

	public EquipamentoDTO() {
		// TODO Auto-generated constructor stub
	}

	public EquipamentoDTO(Long id, String equipamento, String patrimonio, Setor setor) {
		this.id = id;
		this.equipamento = equipamento;
		this.patrimonio = patrimonio;
		this.setor = setor;
	}
	
	public EquipamentoDTO(Equipamento entity) {
		this.id = entity.getId();
		this.equipamento = entity.getEquipamento();
		this.patrimonio = entity.getPatrimonio();
		this.setor = entity.getSetor();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEquipamento() {
		return equipamento;
	}

	public void setEquipamento(String equipamento) {
		this.equipamento = equipamento;
	}

	public String getPatrimonio() {
		return patrimonio;
	}

	public void setPatrimonio(String patrimonio) {
		this.patrimonio = patrimonio;
	}

	public Setor getSetor() {
		return setor;
	}

	public void setSetor(Setor setor) {
		this.setor = setor;
	}
}