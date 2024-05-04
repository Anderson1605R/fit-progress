package br.com.rochadev.fit_progress.services;

import br.com.rochadev.fit_progress.data.DetalhesUsuarioData;
import br.com.rochadev.fit_progress.model.UsuarioModel;
import br.com.rochadev.fit_progress.repositories.UsuarioRepo;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class DetalheUsuarioImpl implements UserDetailsService {
    private final UsuarioRepo usuarioRepo;

    public DetalheUsuarioImpl(UsuarioRepo usuarioRepo) {
        this.usuarioRepo = usuarioRepo;
    }

    //Aqui fazemos a busca do usuário
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<UsuarioModel> usuario = usuarioRepo.findByEmail(email);
        if (usuario.isEmpty()){
            throw new UsernameNotFoundException("Usuário ["+ usuario +"] não encontrado");
        }

        //ao final retornamos um UserDatail passando um optional como parametro, nesse caso o usuario que buscamos
        return new DetalhesUsuarioData(usuario);
    }
}
