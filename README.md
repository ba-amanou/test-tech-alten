# Boutique Alten Shop

Ce projet a été réalisé dans le cadre d'un test technique pour Alten. Il a été complété en une semaine et consistait à enrichir un site e-commerce basique existant avec de nouvelles fonctionnalités, tout en implémentant un backend.

## Fonctionnalités

### Front et Back
- Gestion des produits (CRUD)
- Gestion du panier utilisateur (ajout, suppression, modification de quantité)
- Authentification JWT (création de compte + login)
- Gestion basique de la différence admin / user
- Architecture RESTful

### Front
- Formulaire de contact avec validations
- Filtres et paginations sur listes produits
- Messages de validation/erreurs

### Back
- Documentation Swagger
- Tests d'API avec [Bruno](https://www.usebruno.com/)

<br/>

## Technologies utilisées

### Front
- Angular v18 ( imposé )
  - Feature-based architecture
  - Reactive Forms
  - Routing Lazy-loaded
  - Signals
  - Interceptors
  - Guards
- primeNg v17 ( imposé )


### Back
- Java 21
- Spring Boot 3.5.3
- Spring Security (JWT)
- Hibernate & JPA
- H2 database
- Swagger / OpenAPI
- Bruno (pour tester l’API)

<br/>

## Installation

### Prérequis

- Node.js ≥ 18.x

- Angular CLI ≥ 18.x

- Java 21

- Maven ≥ 3.8

- Git

#### Lancer le frontend (Angular 18)
```
# 1. Cloner le repo
git clone <url-du-repo>
cd front

# 2. Installer les dépendances
npm install

# 3. Lancer l'application
ng serve
```
Accessible sur: http://localhost:4200

#### Lancer le backend (Spring Boot 3.5.3 - Java 21)
```
# Se placer dans le dossier backend
cd back

# Lancer l'application Spring Boot
./mvnw spring-boot:run
```
Le backend écoute par défaut sur : http://localhost:8080

#### Comptes de connexion

Pour démonstration, deux comptes sont disponibles à la connexion
- admin -> Email : admin@admin.com / Password : admin
- user -> Email : user@user.com / Password : user



## Screenshots

### Liste des produits en tant qu'utilisateur
![Image de l'appli en tant qu'utilisateur sur liste des produits](/docs/img/appli-user.jpg)
<br/>

### Exemple de tests avec bruno pour le login utilisant la ref de swagger
![Exemple de tests bruno pour login utilisant la ref swagger](/docs/img/test-bruno-api.jpg)

<br/>
<br/>
<br/>

# Les Consignes

- Vous êtes développeur front-end : vous devez réaliser les consignes décrites dans le chapitre [Front-end](#Front-end)

- Vous êtes développeur back-end : vous devez réaliser les consignes décrites dans le chapitre [Back-end](#Back-end) (*)

- ✅ Vous êtes développeur full-stack : vous devez réaliser les consignes décrites dans le chapitre [Front-end](#Front-end) et le chapitre [Back-end](#Back-end) (*)

(*) Afin de tester votre API, veuillez proposer une stratégie de test appropriée.

## Front-end

Le site de e-commerce d'Alten a besoin de s'enrichir de nouvelles fonctionnalités.

### Partie 1 : Shop

- ✅ Afficher toutes les informations pertinentes d'un produit sur la liste
- ✅ Permettre d'ajouter un produit au panier depuis la liste 
- ✅ Permettre de supprimer un produit du panier
- ✅ Afficher un badge indiquant la quantité de produits dans le panier
- ✅ Permettre de visualiser la liste des produits qui composent le panier

### Partie 2

- ✅ Créer un nouveau point de menu dans la barre latérale ("Contact")
- ✅ Créer une page "Contact" affichant un formulaire
- ✅ Le formulaire doit permettre de saisir son email, un message et de cliquer sur "Envoyer"
- ✅ Email et message doivent être obligatoirement remplis, message doit être inférieur à 300 caractères
- ✅ Quand le message a été envoyé, afficher un message à l'utilisateur : "Demande de contact envoyée avec succès"

### Bonus : 

- ✅ Ajouter un système de pagination et/ou de filtrage sur la liste des produits
- ✅ On doit pouvoir visualiser et ajuster la quantité des produits depuis la liste et depuis le panier 

## Back-end

### Partie 1

Développer un back-end permettant la gestion de produits définis plus bas.
Vous pouvez utiliser la technologie de votre choix parmi la liste suivante :

- Node.js/Express
- ✅ Java/Spring Boot
- C#/.net Core
- PHP/Symphony : Utilisation d'API Platform interdite

<br/>
✅ Le back-end doit gérer les API suivantes : 

    | Resource           | POST                  | GET                            | PATCH                                    | PUT | DELETE           |
    | ------------------ | --------------------- | ------------------------------ | ---------------------------------------- | --- | ---------------- |
    | **/products**      | Create a new product  | Retrieve all products          | X                                        | X   |     X            |
    | **/products/:id**  | X                     | Retrieve details for product 1 | Update details of product 1 if it exists | X   | Remove product 1 |

Un produit a les caractéristiques suivantes : 

``` typescript
class Product {
  id: number;
  code: string;
  name: string;
  description: string;
  image: string;
  category: string;
  price: number;
  quantity: number;
  internalReference: string;
  shellId: number;
  inventoryStatus: "INSTOCK" | "LOWSTOCK" | "OUTOFSTOCK";
  rating: number;
  createdAt: number;
  updatedAt: number;
}
```

✅ Le back-end créé doit pouvoir gérer les produits dans une base de données SQL/NoSQL ou dans un fichier json.

### Partie 2

✅ Imposer à l'utilisateur de se connecter pour accéder à l'API.
  La connexion doit être gérée en utilisant un token JWT.  
✅ Deux routes devront être créées :
  * [POST] /account -> Permet de créer un nouveau compte pour un utilisateur avec les informations fournies par la requête.   
    Payload attendu : 
    ```
    {
      username: string,
      firstname: string,
      email: string,
      password: string
    }
    ```
  * [POST] /token -> Permet de se connecter à l'application.  
    Payload attendu :  
    ```
    {
      email: string,
      password: string
    }
    ```
✅ Une vérification devra être effectuée parmi tout les utilisateurs de l'application afin de connecter celui qui correspond aux infos fournies. Un token JWT sera renvoyé en retour de la reqûete.  
✅ Faire en sorte que seul l'utilisateur ayant le mail "admin@admin.com" puisse ajouter, modifier ou supprimer des produits. Une solution simple et générique devra être utilisée. Il n'est pas nécessaire de mettre en place une gestion des accès basée sur les rôles.  
✅ Ajouter la possibilité pour un utilisateur de gérer un panier d'achat pouvant contenir des produits.  
❌ Ajouter la possibilité pour un utilisateur de gérer une liste d'envie pouvant contenir des produits.  

_Note : La gestion de la liste d'envie paraît redondante avec celle du panier, les fonctionnalités attendues étant similaires._

## Bonus

✅ Vous pouvez ajouter des tests Postman ou Swagger pour valider votre API