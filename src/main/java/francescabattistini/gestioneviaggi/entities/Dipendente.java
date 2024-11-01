package francescabattistini.gestioneviaggi.entities;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
@Table(name="dipendente")
public class Dipendente {
    @Id
    @GeneratedValue
    @Setter(AccessLevel.NONE)
    @Column(name = "dipendente_id")
    private Long id;

    private String username;
    private String nome;
    private String cognome;
    private String email;
    private String imgProfilo;

    public Dipendente(String username, String nome, String cognome, String email, String imgProfilo) {
        this.username = username;
        this.nome = nome;
        this.cognome = cognome;
        this.email = email;
        this.imgProfilo = imgProfilo;
    }
}
