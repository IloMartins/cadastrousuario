package com.teste.service.cadastro;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.teste.model.cadastro.Usuario;
import com.teste.repository.cadastro.UsuarioRepository;
import com.teste.service.GenericService;

@Service
public class UsuarioService implements GenericService<Usuario>{
	
	@Autowired
	private UsuarioRepository usuarioRepository;

	@Override
	public void salvar(Usuario t) {
		usuarioRepository.save(t);	
	}
	
	@Override
	public void excluir(Long id) {
		usuarioRepository.deleteById(id);
		
	}

	@Override
	public Usuario buscarPorId(Long id) {
	 return usuarioRepository.findById(id).get();
	}

	@Override
	public List<Usuario> buscarTodos() {
	 return usuarioRepository.findAll(Sort.by(Sort.Direction.ASC, "id"));
	}
	
	public Page<Usuario> listAll(int numeroPagina){
        Pageable pageable = PageRequest.of(numeroPagina - 1, 20);

        return usuarioRepository.findAll(pageable);
    }

    public Page<Usuario> listAll(int numeroPagina, String pesquisa){
        Pageable pageable = PageRequest.of(numeroPagina - 1, 20);
        List<Usuario> lista = null;

        lista = usuarioRepository.findAllTable(pesquisa.toUpperCase());

        return new PageImpl(lista, pageable, lista.size());
    }
    
    public void salvar(Usuario usuario, MultipartFile[] files) {
    	
        for(MultipartFile f : files){

            if (!f.getOriginalFilename().equals("")){
                System.out.println("Nome do arquivo ----> " + f.getOriginalFilename());
                String fileName = StringUtils.cleanPath(f.getOriginalFilename());

                if(fileName.contains("..")) {
				    System.out.println("Erro de path");
				    //throw new FileStorageException("Sorry! Filename contains invalid path sequence " + fileName);
				}

           
				usuarioRepository.save(usuario);
            }
        }

    }

}
