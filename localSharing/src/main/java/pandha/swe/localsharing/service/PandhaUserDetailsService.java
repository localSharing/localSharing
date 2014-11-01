package pandha.swe.localsharing.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pandha.swe.localsharing.dao.BenutzerDao;
import pandha.swe.localsharing.model.Benutzer;
import pandha.swe.localsharing.model.BenutzerRolle;


@Service("userDetailsService")
public class PandhaUserDetailsService implements UserDetailsService {

	@Autowired
	private BenutzerDao benutzerDao;

	@Transactional(readOnly = true)
	@Override
	public UserDetails loadUserByUsername(String email)
			throws UsernameNotFoundException {

		Benutzer benutzer = benutzerDao.findByEmail(email);
		List<GrantedAuthority> authorities = buildUserAuthority(benutzer
				.getBenutzerRolle());

		return buildUserForSpringSecurityAuthentication(benutzer, authorities);
	}

	public User buildUserForSpringSecurityAuthentication(Benutzer benutzer,
			List<GrantedAuthority> authorities) {
		User user = new User(benutzer.getEmail(), benutzer.getPasswort(),
				benutzer.isEnabled(), true, true, true, authorities);
		return user;
	}

	public List<GrantedAuthority> buildUserAuthority(
			Set<BenutzerRolle> benutzerRollen) {

		Set<GrantedAuthority> setAuths = new HashSet<GrantedAuthority>();

		// Build user's authorities
		for (BenutzerRolle benutzerRolle : benutzerRollen) {
			setAuths.add(new SimpleGrantedAuthority(benutzerRolle.getRolle()
					.toString()));
		}

		List<GrantedAuthority> result = new ArrayList<GrantedAuthority>(
				setAuths);

		return result;
	}
}
