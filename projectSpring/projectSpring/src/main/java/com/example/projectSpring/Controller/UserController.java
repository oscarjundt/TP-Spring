package com.example.projectSpring.Controller;

import com.example.projectSpring.Model.User;
import com.example.projectSpring.Repository.repositoryUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
public class UserController {
    @Autowired
    private repositoryUser user;
    /**
     * Cette fonction Java utilise l'annotation @GetMapping de Spring pour récupérer tous les
     * utilisateurs et les renvoyer sous forme de ArrayList.
     * 
     * @return Une ArrayList d’objets User est renvoyée.
     */
    @GetMapping("/user")
    @ResponseBody
    
    // La méthode `getAll()` dans la classe UserController utilise l'annotation Spring `@GetMapping`
    // pour gérer les requêtes HTTP GET vers le point de terminaison "/user". Lors de l'accès à ce
    // point de terminaison, la méthode récupère tous les utilisateurs de l'objet référentielUser
    // (vraisemblablement un référentiel pour les entités User) et les renvoie sous la forme d'une
    // ArrayList d'objets User.
    public ArrayList<User> getAll(){
        return this.user.findAll();
    }
    /**
     * La fonction ci-dessus est un point de terminaison POST dans une application Java Spring qui
     * ajoute un nouvel utilisateur à la base de données.
     * 
     * @param user L'annotation `@PostMapping` est utilisée pour mapper les requêtes HTTP POST à une
     * méthode de gestionnaire spécifique dans une classe de contrôleur. Dans ce cas, la méthode du
     * gestionnaire est mappée au point de terminaison « /user/add ».
     */
    @PostMapping("/user/add")
    @ResponseBody
    public void setUser(@RequestBody User user){
        this.user.save(user);
    }

    /**
     * Cette fonction supprime un utilisateur par son identifiant s'il existe dans la base de données.
     * 
     * @param id Le paramètre `id` dans la méthode `delUser` est une variable de chemin extraite de
     * l'URL. Il est utilisé pour identifier l'utilisateur qui doit être supprimé du système.
     */
    @DeleteMapping("/user/delete/{id}")
    public void delUser(@PathVariable(name = "id") String id){
        User user = this.user.findById(Integer.parseInt(id));
        if(user!=null) {
            this.user.delete(user);
        }
    }


   /**
    * Cette fonction Java récupère un utilisateur par son identifiant à partir d'une source de données
    * et renvoie l'objet utilisateur.
    * 
    * @param id Le paramètre `id` dans l'annotation `@GetMapping` représente la valeur dynamique qui
    * fait partie du chemin de l'URL. Dans ce cas, il est spécifié comme variable de chemin en
    * utilisant `@PathVariable(name = "id")`. Cela signifie que lorsqu'une requête est adressée au
    * point final `/user/{id
    * @return La méthode getById() renvoie un objet User avec l'ID spécifié. L'objet User est récupéré
    * du référentiel utilisateur en appelant la méthode findById() avec la valeur entière analysée du
    * paramètre ID.
    */
    @GetMapping("/user/{id}")
    @ResponseBody
    public User getById(@PathVariable(name = "id") String id){
        return this.user.findById(Integer.parseInt(id));
    }

    /**
     * Cette fonction Java récupère un utilisateur par son mot de passe et son email à partir d'une
     * base de données.
     * 
     * @param pass Le paramètre `pass` dans la méthode `getByPasswordAdndEmail` est une variable String
     * qui représente le mot de passe d'un utilisateur. Il est extrait de la variable de chemin nommée
     * « mot de passe » dans l'URL de la requête.
     * @param email Le paramètre `email` dans la méthode `getByPasswordAndEmail` est une variable de
     * chemin qui représente l'adresse e-mail de l'utilisateur que vous souhaitez récupérer. Il est
     * transmis dans le chemin de l'URL dans le cadre de la requête.
     * @return Une instance de la classe User qui correspond au mot de passe et à l'adresse e-mail
     * fournis.
     */
    @GetMapping("/user/each/{password}/{email}")
    public User getByPasswordAdndEmail(@PathVariable(name = "password") String pass,@PathVariable(name = "email") String email){
        return  this.user.findByPasswrodAndEmail(pass,email);
    }
}
