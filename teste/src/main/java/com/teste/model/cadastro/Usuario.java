package com.teste.model.cadastro;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Temporal;

@Entity
public class Usuario {
	
		@Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

	    @Column(columnDefinition = "varchar(100)")
	    private String nome;
	    
	    @Temporal(javax.persistence.TemporalType.DATE)
	    private Date dataNascimento;
	    
	    @Lob
	    @Column(columnDefinition = "bytea")
	    private byte[] foto;
	    
	  	public Long getId() {
		return id;
		}
	
		public void setId(Long id) {
			this.id = id;
		}
	
		public String getNome() {
			return nome;
		}
	
		public void setNome(String nome) {
			this.nome = nome;
		}
	
		public Date getDataNascimento() {
			return dataNascimento;
		}
	
		public void setDataNascimento(Date dataNascimento) {
			this.dataNascimento = dataNascimento;
		}
	
		public byte[] getFoto() {
			return foto;
		}
	
		public void setFoto(byte[] foto) {
			this.foto = foto;
		}

}
