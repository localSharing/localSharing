package pandha.swe.localsharing.service;

import java.security.Principal;
import java.util.List;

import pandha.swe.localsharing.model.Benutzer;
import pandha.swe.localsharing.model.dto.BenutzerDTO;
import pandha.swe.localsharing.model.dto.BenutzerRegisterDTO;
import pandha.swe.localsharing.model.enums.Rollen;

public interface BenutzerService {
	public Benutzer findById(long id);

	public Benutzer findByEmail(String email);

	public Benutzer findByAngebotsIdAndType(Long id, String type);

	public Benutzer getUserByPrincipal(Principal principal);

	public List<Benutzer> findAll();

	public List<BenutzerDTO> findAllDTO();

	public void save(Benutzer benutzer);

	public void update(Benutzer benutzer);

	public void delete(Benutzer benutzer);

	public void registerBenutzer(BenutzerRegisterDTO benutzerRegisterDto);

	public Benutzer benutzerDTO_TO_Benutzer(BenutzerDTO benutzerDTO,
			Principal user);
	
	public Benutzer benutzerDTO_TO_Benutzer(BenutzerDTO benutzerDTO);

	public BenutzerDTO benutzer_TO_BenutzerDTO(Benutzer benutzer);

	public List<BenutzerDTO> list_Benutzer_TO_BenutzerDTO(
			List<Benutzer> listBenutzer);

	public Boolean hatBenutzerRolle(Benutzer benutzer, Rollen rolle);

	public void shutdown();

}
