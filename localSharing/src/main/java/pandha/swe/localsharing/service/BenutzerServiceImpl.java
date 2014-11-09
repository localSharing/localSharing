package pandha.swe.localsharing.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import pandha.swe.localsharing.dao.BenutzerDao;
import pandha.swe.localsharing.dto.BenutzerRegisterDto;
import pandha.swe.localsharing.model.Benutzer;

@Service("benutzerService")
public class BenutzerServiceImpl implements BenutzerService {

	@Autowired
	private BenutzerDao benutzerDao;

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
	public void registerBenzuter(BenutzerRegisterDto benutzerRegisterDto) {
		// TODO Implentieren
		String encodedPassword = encoder.encode(benutzerRegisterDto
				.getPassword1());

		// Benutzer benutzer = new
		// Benutzer(Long.valueOf(1l),encodedPassword,true,);

		Benutzer benutzer = null;
		save(benutzer);

	}
}
