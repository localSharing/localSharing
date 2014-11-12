package pandha.swe.localsharing.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import pandha.swe.localsharing.model.Benutzer;
import pandha.swe.localsharing.model.BenutzerRolle;
import pandha.swe.localsharing.model.Rollen;
import pandha.swe.localsharing.model.dao.BenutzerDAO;
import pandha.swe.localsharing.model.dto.BenutzerRegisterDTO;

@Service("benutzerService")
public class BenutzerServiceImpl implements BenutzerService {

	@Autowired
	private BenutzerDAO benutzerDao;

	@Autowired
	private PasswordEncoder encoder;

	@Override
	public Benutzer findById(long id) {
		return benutzerDao.findById(id);
	}

	@Override
	public List<Benutzer> findAll() {
		return benutzerDao.findAll();
	}

	@Override
	public void save(Benutzer benutzer) {
		benutzerDao.save(benutzer);
	}

	@Override
	public void update(Benutzer benutzer) {
		benutzerDao.update(benutzer);
	}

	@Override
	public void delete(Benutzer benutzer) {
		benutzerDao.delete(benutzer);
	}

	@Override
	public void shutdown() {
		benutzerDao.shutdown();
	}

	@Override
	public void registerBenutzer(BenutzerRegisterDTO benutzerRegisterDto) {
		String encodedPassword = encoder.encode(benutzerRegisterDto
				.getPassword1());

		Set<BenutzerRolle> rollen = new HashSet<>();

		Benutzer benutzer = new Benutzer(null, encodedPassword, true,
				benutzerRegisterDto.getGeschlecht(),
				benutzerRegisterDto.getVorname(),
				benutzerRegisterDto.getNachname(),
				benutzerRegisterDto.getStrasse(),
				benutzerRegisterDto.getHausnummer(),
				benutzerRegisterDto.getPlz(), benutzerRegisterDto.getStadt(),
				benutzerRegisterDto.getEmail(),
				benutzerRegisterDto.getTelefonNummer(), rollen);
		rollen.add(new BenutzerRolle(null, benutzer, Rollen.USER));
		save(benutzer);

	}
}
