package francescabattistini.gestioneviaggi.PayloadDTO;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record PrenotazioneDto(
        @NotNull(message = "la data di prenotazione è obbligatoria")
        LocalDate dataPrenotazione,
        @NotNull(message = "devi inserire le preferenze del dipendente come volo,alloggio! ")
        String preferenzeDipendente,
        @NotNull( message = "id dipendente obbligatorio ")
        String iddipendente,
        @NotNull( message = "id viaggio obbligatorio ")
        String idviaggio
        )
{
}
