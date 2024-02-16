package br.com.rochadev.fit_progress.services;

import br.com.rochadev.fit_progress.model.CategoriaModel;
import br.com.rochadev.fit_progress.repositories.CategoriaRepo;
import br.com.rochadev.fit_progress.repositories.UsuarioRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriaService {
    private final CategoriaRepo categoriaRepo;
    private final UsuarioRepo usuarioRepo;

    public CategoriaService(CategoriaRepo categoriaRepo, UsuarioRepo usuarioRepo ) {
        this.categoriaRepo = categoriaRepo;
        this.usuarioRepo = usuarioRepo;
    }

    public List<CategoriaModel> buscarTodasCategorias(){
        return categoriaRepo.findAll();
    }

    public void salvarCategoria(CategoriaModel categoria, long id){
        var usuarioExistente = usuarioRepo.findById(id).orElseThrow(()-> new NullPointerException("Usuário não encontrado"));
        usuarioExistente.adicionarCategoria(categoria);


        usuarioRepo.save(usuarioExistente);
    }

    public CategoriaModel atualizarCategoria(CategoriaModel categoriaAtualizada, long id) {
        var categoriaExistente = categoriaRepo.findById(id).orElseThrow(() -> new NullPointerException("Categoria não encontrada"));
        categoriaExistente.setNome(categoriaAtualizada.getNome());
        categoriaRepo.save(categoriaExistente);
        return categoriaExistente;

    }

    public void apagarCategoria(long id){
        categoriaRepo.deleteById(id);
    }
}
