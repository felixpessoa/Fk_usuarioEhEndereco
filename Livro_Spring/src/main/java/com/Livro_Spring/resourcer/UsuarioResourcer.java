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
import com.Livro_Spring.domain.model.Usuario;
import com.Livro_Spring.repository.UsuarioRepository;
import com.fasterxml.jackson.annotation.JsonAutoDetect;


@RestController
@RequestMapping("/usuario")
public class UsuarioResourcer {
	
	@Autowired
	private UsuarioRepository repository;
	
	UsuarioResourcer (UsuarioRepository usuarioRepository){
		this.repository = usuarioRepository;
	}
	
	@PostMapping
	public Usuario save(@RequestBody Usuario usuario) {
		return repository.save(usuario);
	}
	
	
	@GetMapping
	public List findAll() {
		return repository.findAll();
	}
	
	
	@GetMapping (path = {"/{id}"})
	public ResponseEntity findById(@PathVariable (value = "id") Long id_usuario) {
		return repository.findById(id_usuario).map(record -> ResponseEntity.ok().body(record))
				.orElse(ResponseEntity.notFound().build());
	}
	
	
	@PutMapping(value = "/{id}")
	public ResponseEntity update (@PathVariable ("id") Long id_usuario, @RequestBody Usuario usuario) {
		return repository.findById(id_usuario).map(record -> {
			record.setNome(usuario.getNome());
			record.setEndereco(usuario.getEndereco());
			Usuario updated = repository.save(record);
			return ResponseEntity.ok().body(updated);
		}).orElse(ResponseEntity.notFound().build());
	}
	
	@DeleteMapping(path = {"/{id}"})
	public ResponseEntity<?> delete(@PathVariable(value = "id") Long id_usuario){
		return repository.findById(id_usuario).map(record -> {
			repository.deleteById(id_usuario);
			return ResponseEntity.ok().build();
		}).orElse(ResponseEntity.notFound().build());
	}

}
