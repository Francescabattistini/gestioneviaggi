package francescabattistini.gestioneviaggi.PayloadDTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record ViaggioDto(
        @NotNull( message = "la destinazione è necessaria per andare avanti")
        String destinazione,
        @NotNull(message = "è essenziale la data del viaggio,inseriscila !")
        LocalDate dataviaggio,
        @NotNull(message = "è obbligatorio sapere se è in programma o completato !")
        String statoprenotazione

) {
}
