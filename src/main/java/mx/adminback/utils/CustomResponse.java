package mx.adminback.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomResponse<T> {
	private BusinessResponseCode status;
	private String message;
	private T bodyResponse;
	private int total;



public enum BusinessResponseCode {
    OK(1),
    ERROR(2),
    NOT_EXIST(3),
	EXCEEDS_MAX_ALLOWED(4);

    private int code;

    BusinessResponseCode(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}

}