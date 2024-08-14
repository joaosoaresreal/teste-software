package br.edu.ifms.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.edu.ifms.dto.OrdemDeServicoDTO;
import br.edu.ifms.entities.OrdemDeServico;
import br.edu.ifms.repositories.OrdemDeServicoRepository;
import br.edu.ifms.services.exceptions.DataBaseException;
import br.edu.ifms.services.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;

@Service
public class OrdemDeServicoService {
	
	@Autowired
	private OrdemDeServicoRepository repository;

	@Transactional(readOnly = true)
	public List<OrdemDeServicoDTO> findAll(){
		List<OrdemDeServico> list = repository.findAll();
		return list.stream().map(t -> new OrdemDeServicoDTO(t)).collect(Collectors.toList());
	}
	
	@Transactional(readOnly = true)
	public Page<OrdemDeServicoDTO> findAllPaged(PageRequest pageRequest) {
		Page<OrdemDeServico> list = repository.findAll(pageRequest);
		return list.map(x -> new OrdemDeServicoDTO(x));
	}

	@Transactional(readOnly = true)
	public OrdemDeServicoDTO findById(Long id) {
		Optional<OrdemDeServico> obj = repository.findById(id);
		OrdemDeServico entity = obj.orElseThrow(() -> new ResourceNotFoundException(
				                "A entidade consultada não foi localizada"));
		return new OrdemDeServicoDTO(entity);
	}

	@Transactional
	public OrdemDeServicoDTO insert(OrdemDeServicoDTO dto) {
		OrdemDeServico entity = new OrdemDeServico();
		entity.setDescricaoProblema(dto.getDescricaoProblema());
		entity.setDescricaoSolucao(dto.getDescricaoSolucao());
		entity.setDataCadastro(dto.getDataCadastro());
		entity.setPrioridade(dto.getPrioridade());
		entity.setStatus(dto.getStatus());
		entity.setTecnico(dto.getTecnico());
		entity = repository.save(entity);
		return new OrdemDeServicoDTO(entity);
	}

	@Transactional
	public OrdemDeServicoDTO update(Long id, OrdemDeServicoDTO dto) {
		try {
			OrdemDeServico entity = repository.getReferenceById(id);
			entity.setDescricaoProblema(dto.getDescricaoProblema());
			entity.setDescricaoSolucao(dto.getDescricaoSolucao());
			entity.setDataCadastro(dto.getDataCadastro());
			entity.setPrioridade(dto.getPrioridade());
			entity.setStatus(dto.getStatus());
			entity.setTecnico(dto.getTecnico());
			entity = repository.save(entity);
			return new OrdemDeServicoDTO(entity);
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