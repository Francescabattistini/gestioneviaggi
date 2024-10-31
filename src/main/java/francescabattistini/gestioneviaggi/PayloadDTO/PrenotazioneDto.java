package francescabattistini.gestioneviaggi.PayloadDTO;

import java.time.LocalDate;

public record PrenotazioneDto(

        LocalDate dataPrenotazione,
        String preferenzeDipendente,
        String idDipendente,
        String idViaggio
        )
{
}
