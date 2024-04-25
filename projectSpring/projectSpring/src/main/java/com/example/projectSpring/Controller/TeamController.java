package com.example.projectSpring.Controller;

import com.example.projectSpring.Model.Team;
import com.example.projectSpring.Repository.repositoryChampionship;
import com.example.projectSpring.Repository.repositoryTeam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class TeamController {
    @Autowired
    private repositoryTeam team;
    @Autowired
    private repositoryChampionship championship;

    /**
     * La fonction getAllTeam() renvoie une ArrayList de tous les objets Team.
     * 
     * @return Une ArrayList d'objets Team est renvoyée par la méthode getAllTeam().
     */
    @GetMapping("/team")
    @ResponseBody
    public ArrayList<Team> getAllTeam(){
        return this.team.findAll();
    }
    /**
     * Cette fonction Java récupère un objet d'équipe par son ID à partir d'un référentiel et le
     * renvoie sous forme de corps de réponse.
     * 
     * @param id Le paramètre `id` dans la méthode `getByIdTeam` est une variable String qui représente
     * l'ID de l'équipe demandée. Cet identifiant est extrait de la variable de chemin nommée « idteam
     * » dans l'URL de la requête.
     * @return La méthode getByIdTeam renvoie un objet Team avec l'identifiant spécifié à partir du
     * référentiel d'équipe.
     */
    @GetMapping("/team/{idteam}")
    @ResponseBody
    public Team getByIdTeam(@PathVariable(name = "idteam") String id){
        return this.team.findById(Integer.parseInt(id));
    }
    /**
     * La fonction définit une nouvelle équipe en enregistrant l'objet d'équipe fourni.
     * 
     * @param team L'annotation `@PostMapping` est utilisée pour mapper les requêtes HTTP POST à l'URL
     * spécifiée ("/team/add") à la méthode `setTeam` dans le contrôleur. L'annotation `@ResponseBody`
     * indique que la valeur de retour de la méthode doit être utilisée comme corps de réponse.
     */
    @PostMapping("/team/add")
    @ResponseBody
    public void setTeam(@RequestBody Team team){
        this.team.save(team);
    }

    /**
     * Cette fonction Java supprime une équipe en fonction de l'ID fourni.
     * 
     * @param id Le paramètre `id` dans l'annotation `@GetMapping` représente l'identifiant unique de
     * l'équipe ciblée pour la suppression. Il est extrait du chemin de l'URL et transmis à la méthode
     * `delTeam` sous forme de chaîne.
     */
    @DeleteMapping("/team/delete/{id}")
    public void delTeam(@PathVariable(name = "id") String id){
        Team team = this.team.findById(Integer.parseInt(id));
        if(team!=null) {
            this.team.delete(team);
        }
    }
    /**
     * Cette fonction Java récupère une liste d'équipes en fonction de l'ID de championnat fourni comme
     * variable de chemin.
     * 
     * @param id Le paramètre `id` dans l'annotation `@GetMapping` représente l'identifiant unique du
     * championnat pour lequel vous souhaitez récupérer les équipes. Il est extrait du chemin URI et
     * transmis à la méthode `getChamp` sous forme de chaîne.
     * @return Une liste d'objets « Team » associés à l'ID de championnat spécifié dans la variable de
     * chemin « id ».
     */
    @GetMapping("/team/champ/{id}")
    public List<Team> getChamp(@PathVariable(name = "id") String id){
        return  this.team.findTeamByChampionshipId(Integer.parseInt(id));//championship.findById(Integer.parseInt(id)).getTeam();
    }

}
