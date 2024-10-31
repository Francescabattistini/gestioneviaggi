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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private int viaggioId;
    private String destinazione;
    private LocalDate dataviaggio;
    private String inprogramma;
    private String completato;

    public Viaggio(String destinazione, LocalDate dataviaggio, String inprogramma, String completato) {
        this.destinazione = destinazione;
        this.dataviaggio = dataviaggio;
        this.inprogramma = inprogramma;
        this.completato = completato;
    }
}
