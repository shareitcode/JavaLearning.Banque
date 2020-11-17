package metier.sessions;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;

import metier.entites.Compte;

@Singleton
public class Banque implements BanqueLocal, BanqueRemote {

	private Compte[] comptes = new Compte[3];

	@PostConstruct
	private void ComptesInit() {
		comptes[0] = new Compte(1, 1000, LocalDateTime.now());
		comptes[1] = new Compte(2, 2000, LocalDateTime.now());
		comptes[2] = new Compte(3, 3000, LocalDateTime.now());
	}

	@Override
	public Compte[] consulterComptes() {
		return comptes;
	}

	@Override
	public Compte consulterCompte(long code) {
		return Arrays.stream(comptes).filter(c -> c.getCode() == code).findFirst().get();
	}

	@Override
	public void verser(long code, double montant) {
		Compte compte = Arrays.stream(comptes).filter(c -> c.getCode() == code).findFirst().get();
		int compteIndex = Arrays.stream(comptes).map(c -> c.getCode()).collect(Collectors.toList()).indexOf(code);
		
		if (compte != null && compteIndex >= 0) {
			compte.setSolde(compte.getSolde() + montant);
			comptes[compteIndex] = compte;
		}
	}

	@Override
	public void retirer(long code, double montant) {
		Compte compte = Arrays.stream(comptes).filter(c -> c.getCode() == code).findFirst().get();
		int compteIndex = Arrays.stream(comptes).map(c -> c.getCode()).collect(Collectors.toList()).indexOf(code);
		
		if (compte != null && compteIndex >= 0) {
			compte.setSolde(compte.getSolde() - montant);
			comptes[compteIndex] = compte;
		}
	}
}