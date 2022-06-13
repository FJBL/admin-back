package mx.adminback.model;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
@Document(collection = "user_activo")
public class Users {
	
	@Id
	private String id;
	private String name;
	private String apellido;
	private String motivo;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", locale = "es_MX",timezone = "America/Mexico_City") 
	private Date fecha;
	private Long cantidad;

}
