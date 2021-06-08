package com.teste.repository.cadastro;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.teste.model.cadastro.Usuario;


@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

	//método para listar usuarios ao digitar o nome no campo de login
    @Query("select u from Usuario u where u.nome = :nome")
    public Usuario findByNome(@Param("nome")String nome);

    //método para pesquisar usuario por nome ou id
    @Query("select u from Usuario u " +
            "where cast(u.id as string) like %:pesquisa% or " +
            "u.nome like %:pesquisa% "
   
    )
    public List<Usuario> findAllTable(@Param("pesquisa")String pesquisa);
}

