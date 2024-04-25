package com.example.projectSpring.Controller;

import com.example.projectSpring.Model.Championship;
import com.example.projectSpring.Model.Day;
import com.example.projectSpring.Model.Game;
import com.example.projectSpring.Repository.repositoryChampionship;
import com.example.projectSpring.Repository.repositoryDay;
import com.example.projectSpring.Repository.repositoryGame;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
public class DayController {
    @Autowired
    private repositoryDay day;

    @Autowired
    private repositoryChampionship championship;

    @Autowired
    private repositoryGame game;

    /**
     * La fonction renvoie une liste de tous les objets Day.
     * 
     * @return Une liste de tous les objets Day est renvoyée.
     */
    @GetMapping("/day")
    @ResponseBody
    public List<Day> getDayAll(){
        return this.day.findAll();
    }

    /**
     * Cette fonction Java récupère un objet Day par son ID à partir d'un référentiel et le renvoie
     * sous forme de corps de réponse.
     * 
     * @param id Le paramètre `id` dans la méthode `getByIdDay` est la valeur extraite de la variable
     * de chemin `idday` dans l'URL de la requête. Il est ensuite utilisé pour rechercher et renvoyer
     * un objet « Jour » avec l'ID correspondant de la source de données.
     * @return Une instance de la classe `Day` est renvoyée. La méthode `findById` est utilisée pour
     * récupérer un objet `Day` en fonction du paramètre `id` transmis dans le chemin de l'URL.
     */
    @GetMapping("/day/{idday}")
    @ResponseBody
    public Day gatByIdDay(@PathVariable(name = "idday") String id){
        return this.day.findById(Integer.parseInt(id));
    }
    /**
     * Cette fonction Java récupère une liste de jours associés à un championnat en fonction de l'ID de
     * jour fourni.
     * 
     * @param id Le paramètre `id` dans la méthode `getByIdChamp` est l'identifiant de la journée de
     * championnat que vous souhaitez récupérer. Il est extrait de la variable de chemin nommée "idday"
     * dans l'URL de la requête.
     * @return Une liste d'objets Day correspondant aux jours d'un championnat avec l'ID spécifié.
     */
    @GetMapping("/day/champ/{idday}")
    @ResponseBody
    public List<Day> gatByIdChamp(@PathVariable(name = "idday") String id){
        return this.championship.findById(Integer.parseInt(id)).getDays();
    }
    /**
     * La fonction définit un nouveau jour en enregistrant l'objet jour fourni.
     * 
     * @param day Le paramètre "day" dans l'extrait de code représente un objet de la classe "Day". Cet
     * objet est reçu en tant que corps de requête dans un mappage de requête POST vers "/day/add". La
     * méthode "setDay" prend cet objet "Day" en paramètre et l'enregistre grâce à la méthode "save"
     */
    @PostMapping("/day/add")
    @ResponseBody
    public void setDay(@RequestBody Day day){
        this.day.save(day);
    }

   /**
    * Cette fonction Java supprime une journée et tous les jeux associés par ID.
    * 
    * @param id Le paramètre `id` dans l'annotation `@GetMapping` représente l'identifiant unique du
    * jour qui est ciblé pour la suppression dans la méthode `delDay`.
    */
    @DeleteMapping("/day/delete/{id}")
    public void delDay(@PathVariable(name = "id") String id){
        Day days = this.day.findById(Integer.parseInt(id));
        if(days!=null) {
            List<Game> gams = days.getGames();
            if (!gams.isEmpty()) {
                for (Game game : gams) {
                    this.game.delete(game);

                }
            }
            this.day.delete(this.day.findById(Integer.parseInt(id)));
        }
    }

    /**
     * Cette fonction Java définit un championnat pour une entité journalière donnée en fonction de
     * l'ID fourni.
     * 
     * @param id Le paramètre `id` dans l'annotation `@PostMapping` représente l'ID du championnat
     * auquel la journée sera ajoutée. Il est extrait du chemin de l'URL à l'aide de
     * `@PathVariable(name = "id")`.
     * @param day Le paramètre `day` dans la méthode `setChamp` est de type `Day` et est annoté avec
     * `@RequestBody`. Cela signifie que l'objet « jour » sera rempli avec les données du corps de la
     * requête HTTP lorsque ce point de terminaison est appelé.
     */
    @PutMapping("day/add/champ/{id}")
    @ResponseBody
    public void setChamp(@PathVariable(name = "id") String id,@RequestBody Day day){
        Championship champ = this.championship.findById(Integer.parseInt(id));
        if(champ!=null) {
            day.setChampionships(champ);
            this.day.save(day);
        }
    }

}
