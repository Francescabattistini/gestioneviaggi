package francescabattistini.gestioneviaggi.PayloadDTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public record DipendenteDto(
        @NotEmpty(message = "l'username è obbligatorio!")
        String username,
        @NotEmpty(message = "Il nome  è obbligatorio!")
        @Size(min = 2, max = 10, message = "Il nome  deve essere dai 2 ai 10 caratteri")
        String nome,
        @NotEmpty(message = "Il cognome è obbligatorio!")
        @Size(min = 2, max = 20, message = "Il cognome  deve essere dai 2 ai 10 caratteri")
        String cognome,
        @NotEmpty(message = "l'email è obbligatoria!")
        @Email(message = "inserire una email valida!")
        String email
)
{
}
