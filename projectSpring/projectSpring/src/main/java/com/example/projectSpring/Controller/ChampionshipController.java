package com.example.projectSpring.Controller;


import com.example.projectSpring.Model.Championship;
import com.example.projectSpring.Model.Day;
import com.example.projectSpring.Model.Team;
import com.example.projectSpring.Repository.repositoryChampionship;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ChampionshipController {
    @Autowired
    private repositoryChampionship championship;

    
    /**
     * La fonction renvoie une liste de tous les championnats.
     * 
     * @return Une liste de tous les objets du Championnat.
     */
    @GetMapping("/championship")
    public List<Championship> getAll(){
        return this.championship.findAll();
    }

    /**
     * Cette fonction Java récupère un objet Championship par son ID à partir d'une source de données.
     * 
     * @param id Le paramètre `id` dans l'annotation `@GetMapping` représente la valeur dynamique qui
     * fait partie du chemin de l'URL. Dans ce cas, il permet de récupérer un championnat spécifique
     * par son ID. L'annotation `@PathVariable` est utilisée pour lier la valeur du paramètre `id` du
     * @return Une instance de la classe `Championship` avec l'ID spécifié est renvoyée.
     */
    @GetMapping("/championship/{id}")
    public Championship getById(@PathVariable(name = "id") String id){
        return  this.championship.findById(Integer.parseInt(id));
    }

    /**
     * La fonction définit un championnat en enregistrant l'objet Championnat fourni.
     * 
     * @param championship Le paramètre « championship » dans l'extrait de code fait référence à un
     * objet de la classe Championship. Cet objet est reçu en tant que corps de requête dans une
     * requête POST adressée au point de terminaison "/championship/add". La méthode setChampionship
     * est responsable de la sauvegarde de cet objet Championship à l'aide du référentiel championnat
     * (en supposant que championnat soit
     */
    @PostMapping("/championship/add")
    public void setChampionship(@RequestBody Championship championship){
        this.championship.save(championship);
    }

    /**
     * La fonction `delchampionship` supprime une entité de championnat par son ID si elle existe.
     * 
     * @param id Le paramètre `id` dans la méthode `delchampionship` est une variable de chemin
     * extraite de l'URL. C'est un identifiant unique du championnat qui doit être supprimé.
     */
    @PutMapping("/championship/delete/{id}")
    public void delchampionship(@PathVariable(name = "id") String id){
       Championship champ = this.championship.findById(Integer.parseInt(id));
        if(champ!=null) {
            this.championship.delete(this.championship.findById(Integer.parseInt(id)));
        }
    }
   /**
    * La fonction addTeam dans une classe de contrôleur Java ajoute une équipe à un championnat en
    * fonction de l'ID fourni.
    * 
    * @param id Le paramètre `id` dans l'annotation `@GetMapping` représente l'identifiant unique du
    * championnat auquel une équipe est ajoutée. Il est extrait du chemin de l'URL en tant que variable
    * de chemin.
    * @param team Le paramètre `team` dans la méthode `addTeam` est de type `Team` et est annoté avec
    * `@RequestBody`. Cela signifie que l'objet « team » sera désérialisé du corps de la requête HTTP
    * envoyée à ce point de terminaison. L'objet `team` représente le
    */
    @PutMapping("/championship/add/team/{id}")
    public void addTeam(@PathVariable(name = "id") String id,@RequestBody Team team){
        Championship champ = this.championship.findById(Integer.parseInt(id));
        if(champ!=null) {
            List<Team> teams = champ.getTeam();
            teams.add(team);
            champ.setTeam(teams);
            championship.save(champ);
        }

    }


}
