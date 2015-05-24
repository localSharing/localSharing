package pandha.swe.localsharing.service;

import java.security.Principal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import pandha.swe.localsharing.model.Benutzer;
import pandha.swe.localsharing.model.BenutzerRolle;
import pandha.swe.localsharing.model.dao.AngebotsDAO;
import pandha.swe.localsharing.model.dao.BenutzerDAO;
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
	private AngebotService angebotService;

	@Autowired
	private HashMap<String, AngebotsDAO<?>> angebotDAOs;

	@Override
	public Benutzer findById(long id) {
		return benutzerDao.findById(id);
	}

	@Override
	public Benutzer findByAngebotsIdAndType(Long id, String type) {

		AngebotsDAO<?> dao = angebotDAOs.get(type);
		if (dao != null) {
			return dao.findById(id).getBenutzer();
		}
		return null;
	}

	@Override
	public Benutzer getUserByPrincipal(Principal principal) {
		return findByEmail(principal.getName());
	}

	@Override
	public List<Benutzer> findAll() {
		return benutzerDao.findAll();
	}

	@Override
	public List<BenutzerDTO> findAllDTO() {
		return list_Benutzer_TO_BenutzerDTO(findAll());
	}

	@Override
	public void save(Benutzer benutzer) {
		benutzerDao.save(benutzer);
	}

	@Override
	public void update(Benutzer benutzer) {

		if (benutzer.isEnabled() == null) {
			benutzer.setEnabled(Boolean.TRUE);
		}

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
		BenutzerDTO benutzerDTO = new BenutzerDTO(benutzer.isEnabled(),
				benutzer.getGeschlecht(), benutzer.getEmail(),
				benutzer.getVorname(), benutzer.getNachname(),
				benutzer.getStrasse(), benutzer.getHausnummer(), benutzer
						.getPlz().toString(), benutzer.getStadt(),
				benutzer.getTelefonNr());

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

		return benutzerDTO_TO_Benutzer(benutzerDTO);
	}

	@Override
	public Benutzer benutzerDTO_TO_Benutzer(BenutzerDTO benutzerDTO) {

		Benutzer benutzer = findById(benutzerDTO.getId());

		benutzer.setEnabled(benutzerDTO.isEnabled());
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

	@Override
	public List<BenutzerDTO> list_Benutzer_TO_BenutzerDTO(
			List<Benutzer> listBenutzer) {
		List<BenutzerDTO> listBenutzerDTO = new ArrayList<BenutzerDTO>();
		for (Benutzer benutzer : listBenutzer) {
			listBenutzerDTO.add(benutzer_TO_BenutzerDTO(benutzer));
		}
		return listBenutzerDTO;
	}

	@Override
	public Boolean hatBenutzerRolle(Benutzer benutzer, Rollen rolle) {

		if (benutzer != null && rolle != null) {
			for (BenutzerRolle benutzerRolle : benutzer.getBenutzerRolle()) {
				if (rolle.equals(benutzerRolle.getRolle())) {
					return Boolean.TRUE;
				}
			}
		}
		return Boolean.FALSE;
	}

	@Override
	public Boolean sindDieBenutzerGleich(Benutzer benutzer1, Benutzer benutzer2) {

		if (benutzer1 != null && benutzer2 != null
				&& benutzer1.getId().equals(benutzer2.getId())) {
			return Boolean.TRUE;
		}

		return Boolean.FALSE;
	}
}
