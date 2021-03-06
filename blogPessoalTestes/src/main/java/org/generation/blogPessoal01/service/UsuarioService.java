package org.generation.blogPessoal01.service;

import java.nio.charset.Charset;
import java.time.LocalDate;
import java.time.Period;
import java.util.Optional;

import org.apache.commons.codec.binary.Base64;
import org.generation.blogPessoal01.model.UserLogin;
import org.generation.blogPessoal01.model.Usuario;
import org.generation.blogPessoal01.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class UsuarioService {
	
	@Autowired
	private UsuarioRepository usuarioRepository;

public Optional<Usuario> cadastrarUsuario(Usuario usuario) {
	
		
		if(usuarioRepository.findByUsuario(usuario.getUsuario()).isPresent())
			throw new ResponseStatusException(
			          	HttpStatus.BAD_REQUEST, "Usuário já existe!", null);

		int idade = Period.between(usuario.getDataNascimento(), LocalDate.now()).getYears();
		
		if(idade < 18)
			throw new ResponseStatusException(
						HttpStatus.BAD_REQUEST, "Usuário menor de 18 anos", null);
			
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

		String senhaEncoder = encoder.encode(usuario.getSenha());
		usuario.setSenha(senhaEncoder);

		return Optional.of(usuarioRepository.save(usuario));
	}

	public Optional<Usuario> atualizarUsuario(Usuario usuario){
	
		if(usuarioRepository.findById(usuario.getId()).isPresent()) {
		
			
			int idade = Period.between(usuario.getDataNascimento(), LocalDate.now()).getYears();
			
			if(idade < 18)
				throw new ResponseStatusException(
							HttpStatus.BAD_REQUEST, "Usuário menor de 18 anos", null); 
			
			BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
			
			String senhaEncoder = encoder.encode(usuario.getSenha());
			usuario.setSenha(senhaEncoder);
			
			return Optional.of(usuarioRepository.save(usuario));
		
		}else {
			
			throw new ResponseStatusException(
					HttpStatus.NOT_FOUND, "Usuário não encontrado!", null);
			
		}
		
	}
	
	public Optional<UserLogin> Logar(Optional<UserLogin> userLogin) {

		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		Optional<Usuario> usuario = usuarioRepository.findByUsuario(userLogin.get().getUsuario());

		if (usuario.isPresent()) {
			
			if (encoder.matches(userLogin.get().getSenha(), usuario.get().getSenha())) {

				String auth = userLogin.get().getUsuario() + ":" + userLogin.get().getSenha();
				byte[] encodedAuth = Base64.encodeBase64(auth.getBytes(Charset.forName("US-ASCII")));
				String authHeader = "Basic " + new String(encodedAuth);

				userLogin.get().setToken(authHeader);				
				userLogin.get().setNome(usuario.get().getNome());
				userLogin.get().setSenha(usuario.get().getSenha());

				return userLogin;

			}
		}
		
		throw new ResponseStatusException(
				HttpStatus.UNAUTHORIZED, "Usuário ou senha inválidos!", null);
		
	}

}