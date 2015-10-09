package br.ages.crud.model;

import java.io.Serializable;
import java.util.Date;

/**
 * Entity Usuario - Data Transfer Objeto 
 * @author C�ssio Trindade
 *
 */
public class Usuario implements Serializable {

	private static final long serialVersionUID = 1L;
		private int idUsuario;
		private String usuario;
		private String senha;
		private String administrador;
		private String matricula;
		private String nome;
		private String email;
		private Date dataCadastro;
		private Date dataInclusao;
									
		public Usuario() {
			// TODO Auto-generated constructor stub
		}			
		
		public Usuario(String usuario, String senha, String administrador,
				String matricula, String nome, String email, Date dataCadastro) {
			super();
			this.usuario = usuario;
			this.senha = senha;
			this.administrador = administrador;
			this.matricula = matricula;
			this.nome = nome;
			this.email = email;
			this.dataCadastro = dataCadastro;
			this.dataInclusao = new Date();
		}



		public int getIdUsuario(){
			 return idUsuario;
		}
	 
		public void  setIdUsuario( int value){
			 idUsuario = value;
		}
		public String getUsuario(){
			 return usuario;
		}
	 
		public void  setUsuario( String value){
			 usuario = value;
		}
		public String getSenha(){
			 return senha;
		}
	 
		public void  setSenha( String value){
			 senha = value;
		}
		public String getAdministrador(){
			 return administrador;
		}
	 
		public void  setAdministrador( String value){
			 administrador = value;
		}
		public String getMatricula(){
			 return matricula;
		}
	 
		public void setMatricula( String value){
			 matricula = value;
		}
		public String getNome(){
			 return nome;
		}
	 
		public void setNome( String value){
			 nome = value;
		}
		public String getEmail(){
			 return email;
		}
	 
		public void setEmail( String value){
			 email = value;
		}
		public Date getDataCadastro(){
			 return dataCadastro;
		}
	 
		public void  setDataCadastro( Date value){
			 dataCadastro = value;
		}
		
		public Date getDataInclusao() {
			return dataInclusao;
		}
				
	}

