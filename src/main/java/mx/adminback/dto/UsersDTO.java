package mx.adminback.dto;




import java.util.Date;

import org.springframework.data.annotation.Id;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class UsersDTO {
	
	
	 @Id
	private String id;
	private String name;
	private String apellido;
	private String motivo;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", locale = "es_MX",timezone = "America/Mexico_City") 
	private Date fecha;
	private Long cantidad;

}
