package mx.adminback.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import mx.adminback.dto.UsersDTO;
import mx.adminback.model.Users;
import mx.adminback.service.IActivoService;
import mx.adminback.service.SequenceGeneratorService;
import mx.adminback.utils.CustomResponse;
import mx.adminback.utils.CustomResponse.BusinessResponseCode;


@RestController
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT,
		RequestMethod.DELETE })

@RequestMapping("admin")
@Slf4j
public class ActivoController {

	@Autowired
	private IActivoService userService;

	@Autowired
	private SequenceGeneratorService sequenceGeneratorService;

	@GetMapping("desde/{desde}/{tipo}")
	public ResponseEntity<CustomResponse> listAllDeudor(@PathVariable int desde,@PathVariable boolean tipo) {
		ResponseEntity<CustomResponse> response = null;

		log.debug("GET all usuarios con prestamo");

		CustomResponse customResponse = userService.findByActive(desde,tipo);
		if (customResponse != null) {
			response = ResponseEntity.ok().body(customResponse);
		} else {
			response = ResponseEntity.ok()
					.body(new CustomResponse(BusinessResponseCode.NOT_EXIST, "Sin registros", null, 0));
		}

		log.debug("GET all deudor desde response {}", response);
		return response;
	}
	
	@GetMapping("name/{name}")
	public ResponseEntity<CustomResponse> listAllDeudorlike(@PathVariable String name) {
		ResponseEntity<CustomResponse> response = null;

		log.debug("GET all usuarios con prestamo");

		CustomResponse customResponse = userService.findByActivename(name);
		if (customResponse != null) {
			response = ResponseEntity.ok().body(customResponse);
		} else {
			response = ResponseEntity.ok()
					.body(new CustomResponse(BusinessResponseCode.NOT_EXIST, "Sin registros", null, 0));
		}

		log.debug("GET deudor by nae response {}", response);
		return response;
	}

	@GetMapping("/{id}")
	public ResponseEntity<CustomResponse> listbyId(@PathVariable String id) {
		ResponseEntity<CustomResponse> response = null;

		log.debug("GET  usuario con prestamo by id");

		CustomResponse customResponse = userService.findByActiveId(id);
		if (customResponse != null) {
			response = ResponseEntity.ok().body(customResponse);
		} else {
			response = ResponseEntity.ok().body(new CustomResponse(BusinessResponseCode.NOT_EXIST, "", null, 0));
		}

		log.debug("GET deudor byId response {}", response);
		return response;
	}

	@PostMapping
	public ResponseEntity<CustomResponse> createAbout(@RequestBody UsersDTO userDto) {
		
		ModelMapper modelMapper = new ModelMapper();
		// Mapear objeto UsersDTO a objeto Entity
		Users users = modelMapper.map(userDto, Users.class);
				
		ResponseEntity response = null;
		CustomResponse<Users> customResponse = null;
		log.debug("POST create users {}", userDto);

		try {
			
			customResponse = userService.save(users);
			response = ResponseEntity.status(HttpStatus.OK).body(customResponse);

		} catch (Exception ex) {
			log.error(ex.getMessage(), ex);

		}
		log.debug("POST create deudor response {}", response);
		return response;
	}

	@PutMapping("/{id}")
	public ResponseEntity<CustomResponse> editAbout(@RequestBody UsersDTO userDto, @PathVariable String id) {
		
		ModelMapper modelMapper = new ModelMapper();
		// Mapear objeto UsersDTO a objeto Entity
		Users users = modelMapper.map(userDto, Users.class);
		
		ResponseEntity response = null;
		log.debug("PUT userDto by ID {}", users);
		try {
		
			CustomResponse customResponse = userService.update(id, users);
			if (BusinessResponseCode.OK == customResponse.getStatus()) {

				response = ResponseEntity.ok().body(customResponse);
			} else {
				response = ResponseEntity.notFound().build();
			}

		} catch (Exception ex) {
			response = null;
			log.error(ex.getMessage(), ex);
		}
		log.debug("PUT userDto byID response {}", response);
		return response;
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<CustomResponse> delete(@PathVariable String id) {
		log.debug("DELETE user_activo by ID {}", id);
		ResponseEntity response = null;
		Users about = new Users();

		CustomResponse customResponse = userService.delete(about, id);
		log.info("code ---->" + customResponse.getStatus());
		if (BusinessResponseCode.OK == customResponse.getStatus()) {
			response = ResponseEntity.ok().body(customResponse);
		} else {
			response = ResponseEntity.status(HttpStatus.NOT_FOUND).body(customResponse);
		}

		log.debug("DELETE user_activo by ID response {}", response);
		return response;
	}

}
