@Filter
Feature: Filtrage et tri des produits
  En tant qu'utilisateur connecté
  Je veux pouvoir filtrer et trier les produits
  Afin de trouver rapidement ce que je cherche

  Background:
     Given Je suis sur la page de connexion
     When Je saisis le nom d'utilisateur "standard_user"
     And Je saisis le mot de passe "secret_sauce"
     And Je clique sur le bouton de connexion
     Then La connexion doit être réussie

  @Valid @FilterValid
  Scenario: Tri des produits par nom (A-Z)
    When Je trie les produits par "az"
    Then Les produits doivent être triés par nom de A à Z
    And Le premier produit doit commencer par "Sauce Labs"

  @Invalid @FilterInvalid1
  Scenario: Tri avec option invalide
    When J'essaie de trier avec une option invalide
    Then Les produits ne doivent pas changer d'ordre

  @Invalid @FilterInvalid2
  Scenario: Tri avec valeur null
    When J'essaie de trier avec une valeur null
    Then Les produits doivent rester dans leur ordre initial

