package com.elections.elections.service;

import com.elections.elections.model.entity.Elector;
import com.elections.elections.model.enums.Role;
import com.elections.elections.repository.ElectorRepository;
import com.elections.elections.repository.VoteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ElectorService implements UserDetailsService {
    private final ElectorRepository electorRepository;
    private final VoteRepository voteRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return electorRepository.findByLogin(username)
                .orElseThrow(() -> new UsernameNotFoundException("Elector with username: " + username + ", doesn't exist"));
    }

    @Transactional(readOnly = true)
    public String getLoginById(Long electorId) {
        return electorRepository.findById(electorId)
                .orElseThrow(() -> new IllegalArgumentException("User with provided Id has not been found"))
                .getLogin();
    }

    @Transactional
    public Elector createNewElector(String login, String password, String name, String surname, Role role) {
        if (electorRepository.existsByLogin(login)) {
            throw new IllegalArgumentException("This login is already occupied!");
        }
        Elector elector = new Elector();
        elector.setLogin(login);
        elector.setPassword(passwordEncoder.encode(password));
        elector.setName(name);
        elector.setSurname(surname);
        elector.setRole(role);
        return electorRepository.save(elector);
    }

    @Transactional
    public Elector lockElector(Long id) {
        Elector elector = electorRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Elector has not been found"));
        if (!elector.isLocked()) {
            elector.setLocked(true);
        }
        return elector;
    }

    @Transactional
    public Elector unlockElector(Long id) {
        Elector elector = electorRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Elector has not been found"));
        if (elector.isLocked()) {
            elector.setLocked(false);
        }
        return elector;
    }

    @Transactional
    public void deleteElector(Long electorId) {
        if (!electorRepository.existsById(electorId)) {
            throw new IllegalArgumentException("Elect with provided ID has not been found");
        }
        voteRepository.deleteByElectorId(electorId);
        electorRepository.deleteById(electorId);
    }

}
