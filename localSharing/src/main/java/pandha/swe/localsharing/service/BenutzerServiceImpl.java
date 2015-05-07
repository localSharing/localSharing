package pandha.swe.localsharing.service;

import java.security.Principal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import pandha.swe.localsharing.model.Benutzer;
import pandha.swe.localsharing.model.BenutzerRolle;
import pandha.swe.localsharing.model.dao.AusleihartikelDAO;
import pandha.swe.localsharing.model.dao.BenutzerDAO;
import pandha.swe.localsharing.model.dao.HilfeleistungDAO;
import pandha.swe.localsharing.model.dao.TauschartikelDAO;
import pandha.swe.localsharing.model.dto.BenutzerDTO;
import pandha.swe.localsharing.model.dto.BenutzerRegisterDTO;
import pandha.swe.localsharing.model.enums.Rollen;

@Service("benutzerService")
public class BenutzerServiceImpl implements BenutzerService {

	@Autowired
	private BenutzerDAO benutzerDao;

	@Autowired
	private PasswordEncoder encoder;
	
	@Autowired
	private AusleihartikelDAO ausleihartikelDao;
	
	@Autowired
	private TauschartikelDAO tauschartikelDao;

	@Autowired
	private HilfeleistungDAO hilfeleistungDao;

	@Override
	public Benutzer findById(long id) {
		return benutzerDao.findById(id);
	}
	
	@Override
	public Benutzer findByAngebotsIdAndType(Long id, String type) {
		Benutzer benutzer = null;
		switch (type) {
		case "ausleihen":
			benutzer = ausleihartikelDao.findById(id).getBenutzer();
			break;

		case "tauschen":
			benutzer = tauschartikelDao.findById(id).getBenutzer();
			break;

		case "helfen":
			benutzer = hilfeleistungDao.findById(id).getBenutzer();
			break;
		}

		return benutzer;
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
				Integer.parseInt(benutzerRegisterDto.getPlz()),
				benutzerRegisterDto.getStadt(), benutzerRegisterDto.getEmail(),
				benutzerRegisterDto.getTelefonNummer(), rollen);
		rollen.add(new BenutzerRolle(null, benutzer, Rollen.USER));
		save(benutzer);

	}

	@Override
	public Benutzer findByEmail(String email) {
		return benutzerDao.findByEmail(email);
	}

	@Override
	public BenutzerDTO benutzer_TO_BenutzerDTO(Benutzer benutzer) {
		BenutzerDTO benutzerDTO = new BenutzerDTO(benutzer.getGeschlecht(),
				benutzer.getEmail(), benutzer.getVorname(),
				benutzer.getNachname(), benutzer.getStrasse(),
				benutzer.getHausnummer(), benutzer.getPlz().toString(),
				benutzer.getStadt(), benutzer.getTelefonNr());

		String plz = benutzerDTO.getPlz();

		for (int i = plz.length(); i < 5; i++) {
			plz += "0";
		}

		benutzerDTO.setPlz(plz);
		benutzerDTO.setId(benutzer.getId());

		return benutzerDTO;

	}

	@Override
	public Benutzer benutzerDTO_TO_Benutzer(BenutzerDTO benutzerDTO,
			Principal user) {

		Long id = findByEmail(user.getName()).getId();

		benutzerDTO.setId(id);

		Benutzer benutzer = benutzerDTO_TO_Benutzer(benutzerDTO);

		return benutzer;
	}

	private Benutzer benutzerDTO_TO_Benutzer(BenutzerDTO benutzerDTO) {

		Benutzer benutzer = findById(benutzerDTO.getId());

		benutzer.setEmail(benutzerDTO.getEmail());
		benutzer.setHausnummer(benutzerDTO.getHausnummer());
		benutzer.setNachname(benutzerDTO.getNachname());
		benutzer.setVorname(benutzerDTO.getVorname());
		benutzer.setStadt(benutzerDTO.getStadt());
		benutzer.setPlz(Integer.parseInt(benutzerDTO.getPlz()));
		benutzer.setStrasse(benutzerDTO.getStrasse());
		benutzer.setTelefonNr(benutzerDTO.getTelefonNummer());

		return benutzer;
	}
}
