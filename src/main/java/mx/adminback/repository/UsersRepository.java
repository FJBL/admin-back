package mx.adminback.repository;

import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;
import mx.adminback.model.Users;

public interface UsersRepository extends MongoRepository<Users, String>{
	

	 List<Users> findByNameLikeOrderByNameAsc(String nombre);
	 
	 List<Users> findByEstado( boolean lDelete);
}
