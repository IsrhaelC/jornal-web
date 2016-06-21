package br.jornal.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity(name="papel")
public class Papel {
	
	@Id
	@Column(name="papel_id", nullable=false)
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long papel_id;
	
	@Column(name="papel", nullable=false)
	@NotNull(message="{papel.papel.vazio}")
	@Size(min=5, message="{papel.papel.min}")
	private String papel;
	
	
	@ManyToMany(mappedBy="papeis", fetch=FetchType.LAZY)
	private List<Usuario> usuarios;

	public Long getPapel_id() {
		return papel_id;
	}

	public void setPapel_id(Long papel_id) {
		this.papel_id = papel_id;
	}

	public String getPapel() {
		return papel;
	}

	public void setPapel(String papel) {
		this.papel = papel;
	}

	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}
	
	public boolean equals(Object obj){
		if(!(obj instanceof Papel))
			return false;
		
		Papel ref = (Papel) obj;
		if(ref.getPapel_id() == this.papel_id)
			return true;
		return false;
	}
}
