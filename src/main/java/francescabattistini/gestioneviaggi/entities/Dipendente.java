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
    private int dipendenteId;
    private String username;
    private String nome;
    private String cognome;
    private String email;
    private String imgProfilo; //lo Uso per l'upload

    public Dipendente(String username, String nome, String cognome, String email) {
        this.username = username;
        this.nome = nome;
        this.cognome = cognome;
        this.email = email;
    }
}
