package mx.adminback.service;

import java.util.List;

import mx.adminback.model.Users;
import mx.adminback.utils.CustomResponse;

public interface IActivoService {
	public CustomResponse<Users> findByActiveId(String id);
	public CustomResponse<List<Users>> findByActive(int desde);
	public CustomResponse<Users> delete(Users about, String idUser);
	public CustomResponse<Users> save(Users about);
	public CustomResponse<Users> update(String id, Users about);
	public CustomResponse<List<Users>> findByActivename(String name);
	
}
