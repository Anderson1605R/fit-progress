package br.com.rochadev.fit_progress.data;

import br.com.rochadev.fit_progress.model.UsuarioModel;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

public class DetalhesUsuarioData implements UserDetails {

    private final Optional<UsuarioModel> usuario;

    public DetalhesUsuarioData(Optional<UsuarioModel> usuario) {
        this.usuario = usuario;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return new  ArrayList<>();
    }

    @Override
    public String getPassword() {
        return usuario.orElse(new UsuarioModel()).getSenha();
    }

    @Override
    public String getUsername() {
        return usuario.orElse(new UsuarioModel()).getEmail();
    }

    //Esses métodos retornam um boolean para verificar o acesso do usuário
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
