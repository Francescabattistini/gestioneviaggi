package francescabattistini.gestioneviaggi.Controllers;

import francescabattistini.gestioneviaggi.PayloadDTO.DipendenteDto;
import francescabattistini.gestioneviaggi.Services.DipendenteService;
import francescabattistini.gestioneviaggi.entities.Dipendente;
import francescabattistini.gestioneviaggi.exceptions.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;
/*1. GET http://localhost:3003/dipendente
2. POST http://localhost:3003/dipendente (+ dto)
3. GET http://localhost:3003/dipendente/{userId}
4. PUT http://localhost:3003/dipendente/{userId} (+ dto)
5. DELETE http://localhost:3003/dipendente/{userId}

 */

@RestController
@RequestMapping("/dipendente")
public class DipendenteController {

     @Autowired
        DipendenteService dipendenteService;

        // 1. GET
        @GetMapping
        public List<Dipendente> findAll(){
            return this.dipendenteService.findAll();
        }


        //2.POST

        @PostMapping
        @ResponseStatus(HttpStatus.CREATED)
        public Dipendente save(@RequestBody @Validated DipendenteDto body, BindingResult validationResult) {
            if (validationResult.hasErrors()) {
                String message = validationResult.getAllErrors().stream().map(objectError -> objectError.getDefaultMessage())
                        .collect(Collectors.joining(". "));
                throw new BadRequestException("Ci sono stati errori nel payload! " + message);
            }

            return this.dipendenteService.save(body);
        }

        //3. GET  ( dipendente specifico)
        @GetMapping("/{dipendenteId}")
        public Dipendente
        findAuthorByid(@PathVariable int authorId){
            return this.dipendenteService.findAuthorById(authorId);
        }



        //4 PUT
        @PutMapping("/{blogId}")
        public Dipendente findAuthorByIdAndUpdate(@PathVariable int authorId, @RequestBody DipendenteDto body){
            return this.dipendenteService.findAuthoreByIdAndUpdate(authorId,body);
        }




        //5 DELETE
        @DeleteMapping("/{blogId}")
        @ResponseStatus(HttpStatus.NO_CONTENT)//204
        public void findAuthorIdAndDelate(@PathVariable int authorId){
            this.dipendenteService.findauthorByIdAndDelete(authorId);
        }

}
