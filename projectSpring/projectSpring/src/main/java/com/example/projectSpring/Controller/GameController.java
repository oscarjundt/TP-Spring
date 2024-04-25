package com.example.projectSpring.Controller;


import com.example.projectSpring.Model.Championship;
import com.example.projectSpring.Model.Day;
import com.example.projectSpring.Model.Game;
import com.example.projectSpring.Repository.repositoryDay;
import com.example.projectSpring.Repository.repositoryGame;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class GameController {
    @Autowired
    private repositoryGame game;

    @Autowired
    private repositoryDay day;

    /**
     * La fonction renvoie une liste de tous les jeux.
     * 
     * @return Une liste de tous les objets du jeu est renvoyée.
     */
    @GetMapping("/game")
    @ResponseBody
    public List<Game> getGameAll(){
        return this.game.findAll();
    }

   /**
    * Cette fonction Java récupère un objet de jeu par son ID dans un référentiel et le renvoie sous
    * forme de corps de réponse.
    * 
    * @param id Le paramètre `id` dans la méthode `getGameById` est la variable de chemin `idgame` qui
    * est transmise dans l'URL lors d'une requête GET à "/game/{idgame}". Il est utilisé pour
    * identifier le jeu spécifique que l'utilisateur souhaite récupérer en fonction de son identifiant.
    * @return Une instance de la classe `Game` avec l'ID spécifié est renvoyée.
    */
    @GetMapping("/game/{idgame}")
    @ResponseBody
    public Game getGameById(@PathVariable(name = "idgame") String id){
        return this.game.findById(Integer.parseInt(id));
    }

    /**
     * Cette fonction Java récupère une liste de jeux pour un jour spécifique en fonction de l'ID
     * fourni.
     * 
     * @param id Le paramètre `id` dans l'annotation `@GetMapping` correspond à la variable path dans
     * l'URL. Dans ce cas, il permet de récupérer les matchs programmés pour un jour précis identifié
     * par l'« id ».
     * @return Une liste d'objets de jeu pour l'ID de jour spécifié est renvoyée. Si l'ID du jour
     * existe dans la base de données, la méthode récupère la liste des jeux associés à ce jour. Si
     * l'ID du jour n'existe pas, la méthode renvoie null.
     */
    @GetMapping("/game/day/{id}")
    public List<Game> getGameDay(@PathVariable(name = "id") String id){
        if(this.day.findById(Integer.parseInt(id))!=null) {
            return this.day.findById(Integer.parseInt(id)).getGames();
        }
        return null;
    }

    /**
     * Cette fonction Java utilise l'annotation @PostMapping de Spring pour ajouter un nouveau jeu à la
     * base de données à l'aide de l'objet Game fourni.
     * 
     * @param game Le paramètre `game` dans la méthode `setGame` est de type `Game`. Il est annoté avec
     * `@RequestBody`, indiquant que la méthode s'attend à ce que les données soient transmises dans le
     * corps de la requête. La méthode enregistre ensuite l'objet `Game` en utilisant la méthode `save`
     * du `game
     */
    @PostMapping("/game/add")
    @ResponseBody
    public void setGame(@RequestBody Game game){
        this.game.save(game);
    }

    /**
     * Cette fonction supprime un jeu de la base de données en fonction de l'ID fourni.
     * 
     * @param id Le paramètre `id` dans la méthode `delGame` est une variable de chemin extraite de
     * l'URL. Il s'agit d'un identifiant unique du jeu qui doit être supprimé.
     */
    @DeleteMapping("/game/delete/{id}")
    public void delGame(@PathVariable(name = "id") String id){
        Game game = this.game.findById(Integer.parseInt(id));
        if(game!=null) {
            this.game.delete(this.game.findById(Integer.parseInt(id)));
        }
    }

    /**
     * Cette fonction Java définit un jour spécifique pour une entité de jeu et l'enregistre dans la
     * base de données.
     * 
     * @param id Le paramètre `id` dans l'annotation `@PostMapping` représente l'identifiant unique du
     * jour pour lequel vous souhaitez ajouter une partie. Il est extrait du chemin URI à l'aide de
     * l'annotation `@PathVariable`.
     * @param game Le paramètre `game` dans la méthode `setChamp` est un paramètre de corps de requête.
     * Il est transmis dans le corps de la requête POST au point de terminaison `/game/add/day/{id}`.
     * La méthode est responsable de la définition du « Jour » pour l'entité « Jeu » et de sa
     * sauvegarde.
     */
    @PutMapping("game/add/day/{id}")
    @ResponseBody
    public void setChamp(@PathVariable(name = "id") String id,@RequestBody Game game){

            Day days = this.day.findById(Integer.parseInt(id));
            if(days!=null) {
                game.setDay(days);
                this.game.save(game);
            }
    }

}
