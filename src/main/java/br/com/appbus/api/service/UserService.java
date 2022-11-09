package br.com.appbus.api.service;

import br.com.appbus.api.model.dto.busTicket.CreateBusTicketDTO;
import br.com.appbus.api.model.dto.busTicket.ReadBusTicketDTO;
import br.com.appbus.api.model.dto.creditCard.CreateCreditCardDTO;
import br.com.appbus.api.model.dto.creditCard.ReadCreditCardDTO;
import br.com.appbus.api.model.dto.evaluation.ReadEvaluationDTO;
import br.com.appbus.api.model.dto.user.CreateUserDTO;
import br.com.appbus.api.model.dto.user.ReadUserDTO;
import br.com.appbus.api.model.dto.user.UpdateUserDTO;
import br.com.appbus.api.model.entity.BusTicket;
import br.com.appbus.api.model.entity.CreditCard;
import br.com.appbus.api.model.entity.User;
import br.com.appbus.api.model.mapper.BusTicketMapper;
import br.com.appbus.api.model.mapper.CreditCardMapper;
import br.com.appbus.api.model.mapper.EvaluationMapper;
import br.com.appbus.api.model.mapper.UserMapper;
import br.com.appbus.api.model.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements UserDetailsService {
    private final UserRepository repository;
    private final EvaluationService evaluationService;
    private final PasswordEncoder passwordEncoder;


    public UserService(UserRepository repository, PasswordEncoder passwordEncoder, EvaluationService evaluationService) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
        this.evaluationService = evaluationService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var user = repository.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException("Username not found"));
        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                true,
                true,
                true,
                true,
                user.getAuthorities());
    }

    public User create(CreateUserDTO userDTO) {
        var user = UserMapper.createUser(userDTO);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return repository.save(user);
    }

    public List<ReadUserDTO> getAll() {
        return repository.findAll().stream().map(UserMapper::readUser).toList();
    }

    public ReadUserDTO getById(Long id) throws Exception {
        var user = findUser(id);
        return UserMapper.readUser(user);
    }

    public List<ReadCreditCardDTO> getUserCreditCards(Long id) throws Exception {
        var user = findUser(id);
        return user.getCreditCards().stream().map(CreditCardMapper::readCreditCard).toList();
    }

    public CreditCard addNewCreditCard(CreateCreditCardDTO creditCardDTO, Long id) throws Exception {
        var user = findUser(id);
        var creditCard = CreditCardMapper.createCreditCard(creditCardDTO);
        user.addCreditCard(creditCard);
        var res = repository.save(user);
        var lastIndex = res.getCreditCards().size() - 1;
        return res.getCreditCards().get(lastIndex);
    }

    public List<ReadBusTicketDTO> getUserBusTickets(Long id) throws Exception {
        var user = findUser(id);
        return user.getBusTickets().stream().map(BusTicketMapper::readBusTicket).toList();
    }

    public List<ReadEvaluationDTO> getUserEvaluations(Long id) throws Exception {
        var user = findUser(id);
        return evaluationService.getAll()
                .stream()
                .filter(e -> e.getUser().getId() == user.getId())
                .map(EvaluationMapper::readEvaluation)
                .toList();
    }

    public BusTicket addNewBusTicket(CreateBusTicketDTO busTicketDTO, Long id) throws Exception {
        var user = findUser(id);
        var busTicket = BusTicketMapper.createBusTicket(busTicketDTO);
        user.addBusTicket(busTicket);
        var res = repository.save(user);
        var lastIndex = res.getBusTickets().size() - 1;
        return res.getBusTickets().get(lastIndex);
    }

    public void update(UpdateUserDTO userDTO, Long id) throws Exception {
        var user = findUser(id);

        user.setName(userDTO.name());
        user.setPassword(userDTO.password());
        user.setEmail(userDTO.email());
        user.setPhone(userDTO.phone());
        user.setIndicatedFriends(userDTO.indicatedFriends());
        user.setScore(userDTO.score());

        repository.save(user);
    }

    public void delete(Long id) throws Exception {
        var user = findUser(id);
        repository.delete(user);
    }

    private User findUser(Long id) throws Exception {
        return repository.findById(id).orElseThrow(() -> new Exception("User not found"));
    }
}
