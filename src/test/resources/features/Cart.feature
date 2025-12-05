@Cart
Feature: Gestion du panier d'achat
  En tant qu'utilisateur connecté
  Je veux pouvoir ajouter et supprimer des articles du panier
  Afin de gérer mes achats

  @Valid @CartValid
  Scenario: Ajout d'un article au panier
    Given Je suis connecté et sur la page des produits
    When J'ajoute un article au panier
    Then L'article doit être ajouté au panier
    And Le nombre d'articles dans le panier doit être "1"

  @Invalid @CartInvalid1
  Scenario: Suppression d'un article d'un panier vide
    Given Je suis sur la page du panier
    And Le panier est vide
    When J'essaie de supprimer un article
    Then Le panier doit rester vide
    And Aucun article ne doit être supprimé

  @Invalid @CartInvalid2
  Scenario: Suppression d'un article avec index invalide
    Given Je suis connecté et sur la page des produits
    And J'ajoute "2" articles au panier
    And Je vais sur la page du panier
    When J'essaie de supprimer l'article à l'index "5"
    Then Le nombre d'articles doit rester "2"

