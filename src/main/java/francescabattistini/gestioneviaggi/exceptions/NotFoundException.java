package francescabattistini.gestioneviaggi.exceptions;

import java.util.UUID;

public class NotFoundException extends RuntimeException {
	public NotFoundException(long id) {
		super("l'elemento con " + id + "non è stato individuato,provare con un'altro id");
	}
}
