package metier.sessions;

import javax.ejb.Remote;

import metier.entites.Compte;

@Remote
public interface BanqueRemote {
	public Compte[] consulterComptes();
	public Compte consulterCompte(long code);
	public void verser(long code, double montant);
	public void retirer(long code, double montant);
}
