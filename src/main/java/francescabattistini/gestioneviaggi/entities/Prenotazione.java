package francescabattistini.gestioneviaggi.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
@Table(name="prenotazione")
public class Prenotazione {
    @Id
    @GeneratedValue
    @Setter(AccessLevel.NONE)
    private Long id;
    private LocalDate dataPrenotazione;
    private String preferenzeDipendente;//tipo volo ,alloggio
@ManyToOne
@JoinColumn(name = "idviaggio")
private Viaggio viaggio;

@ManyToOne
@JoinColumn(name="iddipendente")
private Dipendente dipendente;


    public Prenotazione(LocalDate dataPrenotazione, String preferenzeDipendente, Viaggio viaggio, Dipendente dipendente) {
        this.dataPrenotazione = dataPrenotazione;
        this.preferenzeDipendente = preferenzeDipendente;
        this.viaggio = viaggio;
        this.dipendente = dipendente;
    }
}
