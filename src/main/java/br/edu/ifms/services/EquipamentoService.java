package br.edu.ifms.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.edu.ifms.dto.EquipamentoDTO;
import br.edu.ifms.entities.Equipamento;
import br.edu.ifms.repositories.EquipamentoRepository;
import br.edu.ifms.services.exceptions.DataBaseException;
import br.edu.ifms.services.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;

@Service
public class EquipamentoService {
	
	@Autowired
	private EquipamentoRepository repository;

	@Transactional(readOnly = true)
	public List<EquipamentoDTO> findAll(){
		List<Equipamento> list = repository.findAll();
		return list.stream().map(t -> new EquipamentoDTO(t)).collect(Collectors.toList());
	}
	
	@Transactional(readOnly = true)
	public Page<EquipamentoDTO> findAllPaged(Pageable pageable) {
		Page<Equipamento> list = repository.findAll(pageable);
		return list.map(x -> new EquipamentoDTO(x));
	}

	@Transactional(readOnly = true)
	public EquipamentoDTO findById(Long id) {
		Optional<Equipamento> obj = repository.findById(id);
		Equipamento entity = obj.orElseThrow(() -> new ResourceNotFoundException(
				                "A entidade consultada não foi localizada"));
		return new EquipamentoDTO(entity);
	}

	@Transactional
	public EquipamentoDTO insert(EquipamentoDTO dto) {
		Equipamento entity = new Equipamento();
		entity.setEquipamento(dto.getEquipamento());
		entity.setPatrimonio(dto.getPatrimonio());
		entity.setSetor(dto.getSetor());
		entity = repository.save(entity);
		return new EquipamentoDTO(entity);
	}

	@Transactional
	public EquipamentoDTO update(Long id, EquipamentoDTO dto) {
		try {
			Equipamento entity = repository.getReferenceById(id);
			entity.setEquipamento(dto.getEquipamento());
			entity.setPatrimonio(dto.getPatrimonio());
			entity.setSetor(dto.getSetor());
			entity = repository.save(entity);
			return new EquipamentoDTO(entity);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException("O recurso com o ID = "+id+" não foi localizado");
		}
	}

	public void delete(Long id) {
		try {
			repository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException("O recurso com o ID = "+id+" não foi localizado");
		} catch (DataIntegrityViolationException e) {
			throw new DataBaseException("Não é possível excluir o registro, pois o mesmo está em uso");
		}
	}
}