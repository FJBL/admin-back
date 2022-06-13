package mx.adminback.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import mx.adminback.model.Users;
import mx.adminback.repository.UsersRepository;
import mx.adminback.utils.CustomResponse;
import mx.adminback.utils.CustomResponse.BusinessResponseCode;

@Service
@Slf4j
public class ActivoService implements IActivoService {

	@Autowired
	private UsersRepository userRepository;

	@Override
	public CustomResponse<List<Users>> findByActive(int desde) {

		CustomResponse<List<Users>> response = null;
		int inicio=desde+5;
		int totalRegistros=userRepository.findAll().size();

		if(inicio>=totalRegistros) {
			inicio=totalRegistros;
		}
		if(desde>=totalRegistros) {
			desde=(totalRegistros)-(totalRegistros%5);
		
		}
		log.info("desde -----> "+ (desde) +"suma "+ (inicio));

		List<Users> listApps = userRepository.findAll().subList(desde, inicio);
		if (!listApps.isEmpty()) {
			response = new CustomResponse(BusinessResponseCode.OK, "Listado de deudores", listApps,totalRegistros);
		}
		return response;

	}

	@Override
	public CustomResponse<Users> delete(Users about, String idUser) {

		Optional<Users> userdelete = userRepository.findById(idUser);
		CustomResponse customResponse = null;
		if (userdelete.isPresent()) {
			userRepository.deleteById(idUser);

			customResponse = new CustomResponse(BusinessResponseCode.OK, "Eliminado correctamente", userdelete,0);
		} else {
			customResponse = new CustomResponse(BusinessResponseCode.NOT_EXIST, "No se encontro registro", userdelete,0);
		}

		return customResponse;
	}

	@Override
	public CustomResponse<Users> save(Users about) {

		CustomResponse<Users> customResponse = null;
		// Validation of bussiness restrictions

		Users aboutSaved = userRepository.save(about);
		customResponse = new CustomResponse<>(BusinessResponseCode.OK, "Insertado correctamnete", aboutSaved,0);

		return customResponse;
	}

	@Override
	public CustomResponse<Users> update(String id, Users user) {

		CustomResponse<Users> customResponse = null;

		Optional<Users> userOpt = userRepository.findById(id);
		log.info("" + userOpt);
		if (userOpt.isPresent()) {

			Users userUpd = userOpt.get();
			userUpd.setApellido(user.getApellido());
			userUpd.setCantidad(user.getCantidad());
			userUpd.setFecha(user.getFecha());
			userUpd.setMotivo(user.getMotivo());
			userUpd.setName(user.getName());
			userRepository.save(userUpd);
			customResponse = new CustomResponse<>(BusinessResponseCode.OK, "Actualizacion exitosa", userUpd,0);

		} else {
			customResponse = new CustomResponse(BusinessResponseCode.NOT_EXIST, "Sin registro", null,0);
		}
		return customResponse;
	}

	@Override
	public CustomResponse<Users> findByActiveId(String id) {

		CustomResponse<Users> response = null;
		
		Optional<Users> user = userRepository.findById(id);

		if (user.isPresent()) {
			response = new CustomResponse(BusinessResponseCode.OK, "Listado de deudor", user,0);
		}
		return response;
	}

	@Override
	public CustomResponse<List<Users>>  findByActivename(String name) {
		CustomResponse<List<Users>> response = null;
		
		log.info("name-------------------> "+name);

		List<Users> listApps = userRepository.findByNameLikeOrderByNameAsc(name);
		if (!listApps.isEmpty()) {
		
			response = new CustomResponse(BusinessResponseCode.OK, "Listado de deudores", listApps,listApps.size());
		}
		return response;
	}
}
