package br.com.mouzetech.clinicamedica.api.model.disassembler;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.mouzetech.clinicamedica.api.model.input.UsuarioAtualizarInput;
import br.com.mouzetech.clinicamedica.api.model.input.UsuarioInput;
import br.com.mouzetech.clinicamedica.api.model.representation.UsuarioModel;
import br.com.mouzetech.clinicamedica.domain.model.Usuario;

@Component
public class UsuarioDisassembler {

	@Autowired
	private ModelMapper modelMapper;

	public Usuario toEntity(UsuarioModel usuario) {
		return this.modelMapper.map(usuario, Usuario.class);
	}

	public Usuario toEntityFromInput(UsuarioInput usuario) {
		return this.modelMapper.map(usuario, Usuario.class);
	}

	public void copyFromInput(Usuario usuario, UsuarioInput usuarioInput) {
		this.modelMapper.map(usuarioInput, usuario);
	}

	public void copyFromInputAtualizar(Usuario usuario, UsuarioAtualizarInput usuarioInput) {
		this.modelMapper.map(usuarioInput, usuario);
	}

	public void copyFromEntity(Usuario usuarioExistente, Usuario usuarioNova) {
		this.modelMapper.map(usuarioNova, usuarioExistente);
	}

	public List<Usuario> toCollectionEntity(List<UsuarioModel> permissoes) {
		return permissoes.stream().map(this::toEntity).collect(Collectors.toList());
	}
}