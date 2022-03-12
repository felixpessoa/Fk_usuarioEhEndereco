package com.Livro_Spring.resourcer;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Livro_Spring.domain.model.Endereco;
import com.Livro_Spring.repository.EnderecoRepository;

@RestController
@RequestMapping("/endereco")
public class EnderecoResourcer {
	
	@Autowired
	private EnderecoRepository repository;
	
	EnderecoResourcer (EnderecoRepository enderecoRepository){
		this.repository = enderecoRepository; 
	}
	
	
	@PostMapping
	public Endereco endereco(@RequestBody Endereco endereco) {
		return repository.save(endereco);
	}
	
	@GetMapping
	public List findAll() {
		return repository.findAll();
	}
	
	@GetMapping(path = {"/{id}"})
	public ResponseEntity findById(@PathVariable (value = "id") Long id_endereco) {
		return repository.findById(id_endereco).map(record -> ResponseEntity.ok().body(record))
				.orElse(ResponseEntity.notFound().build());
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity update (@PathVariable ("id") Long id_endereco, @RequestBody Endereco endereco) {
		return repository.findById(id_endereco).map(record -> {
			record.setRua(endereco.getRua());
			record.setNumero(endereco.getNumero());
			Endereco updated = repository.save(record);
			return ResponseEntity.ok().body(updated);
		}).orElse(ResponseEntity.notFound().build());
	}
		
		
	@DeleteMapping(path = {"/{id}"})
	public ResponseEntity<?> delete(@PathVariable (value = "id") Long id_endereco){
		return repository.findById(id_endereco).map(record -> {
			repository.deleteById(id_endereco);
			return ResponseEntity.ok().build();
		}).orElse(ResponseEntity.notFound().build());
	}
	
	

	
	
	
}
