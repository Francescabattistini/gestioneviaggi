package francescabattistini.gestioneviaggi.PayloadDTO;

import java.time.LocalDate;

public record ViaggioDto(
        String destinazione,
        LocalDate dataviaggio,
        String inprogramma,
        String completato
) {
}
