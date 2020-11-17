package metier.sessions;

import javax.ejb.Local;

import metier.entites.Compte;

@Local
public interface BanqueLocal {
	public Compte[] consulterComptes();
	public Compte consulterCompte(long code);
	public void verser(long code, double montant);
	public void retirer(long code, double montant);
}
