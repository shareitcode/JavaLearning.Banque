package metier.entites;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Compte implements Serializable {
	private long code;
	private double solde;
	private LocalDateTime dateCreation;
	
	public Compte(long code, double solde, LocalDateTime dateCreation) {
		this.code = code;
		this.solde = solde;
		this.dateCreation = dateCreation;
	}

	public long getCode() {
		return code;
	}

	public void setCode(long code) {
		this.code = code;
	}

	public double getSolde() {
		return solde;
	}

	public void setSolde(double solde) {
		this.solde = solde;
	}

	public LocalDateTime getDateCreation() {
		return dateCreation;
	}
	
	public void setDateCreation(LocalDateTime dateCreation) {
		this.dateCreation = dateCreation;
	}

	@Override
	public String toString() {
		return new StringBuilder()
				.append("Le compte ")
				.append(code)
				.append(" a un solde de ")
				.append(solde)
				.append(" et a été créé le ")
				.append(dateCreation.format(DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm:ss"))).toString();
	}
}