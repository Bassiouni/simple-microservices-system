package bm.userservice;


import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final RestTemplate restTemplate;

    public UserService(UserRepository userRepository, RestTemplate restTemplate) {
        this.userRepository = userRepository;
        this.restTemplate = restTemplate;
    }

    public UserEntity saveUser(UserEntity user) {
        return userRepository.save(user);
    }

    public ResponseDTO getUser(Long userId) {

        ResponseDTO ResponseDTO = new ResponseDTO();
        UserEntity user = userRepository.findById(userId).get();
        UserDTO UserDTO = mapToUser(user);

        ResponseEntity<DepartmentDTO> responseEntity = restTemplate
                .getForEntity("http://DEPARTMENT-SERVICE/api/departments/" + user.getDepartmentId(),
                        DepartmentDTO.class);

        DepartmentDTO DepartmentDTO = responseEntity.getBody();

        System.out.println(responseEntity.getStatusCode());

        ResponseDTO.setUser(UserDTO);
        ResponseDTO.setDepartment(DepartmentDTO);

        return ResponseDTO;
    }

    private UserDTO mapToUser(UserEntity user){
        UserDTO UserDTO = new UserDTO();
        UserDTO.setId(user.getId());
        UserDTO.setFirstName(user.getFirstName());
        UserDTO.setLastName(user.getLastName());
        UserDTO.setEmail(user.getEmail());
        return UserDTO;
    }
}
