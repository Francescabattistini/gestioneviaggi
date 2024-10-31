package francescabattistini.gestioneviaggi.entities;



import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
@Table(name="viaggio")
public class Viaggio {
    @Id
    @GeneratedValue
    @Setter(AccessLevel.NONE)
    private int id;
    private String destinazione;
    private LocalDate dataviaggio;
    private String statoPrenotazione;//in programma o completato

    public Viaggio(String destinazione, LocalDate dataviaggio, String statoPrenotazione) {
        this.destinazione = destinazione;
        this.dataviaggio = dataviaggio;
        this.statoPrenotazione = statoPrenotazione;
    }
}
