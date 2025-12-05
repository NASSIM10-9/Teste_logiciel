@Filter
Feature: Filtrage et tri des produits
  En tant qu'utilisateur connecté
  Je veux pouvoir filtrer et trier les produits
  Afin de trouver rapidement ce que je cherche

  @Valid @FilterValid
  Scenario: Tri des produits par nom (A-Z)
    Given Je suis connecté et sur la page des produits
    When Je trie les produits par "az"
    Then Les produits doivent être triés par nom de A à Z
    And Le premier produit doit commencer par "Sauce Labs"

  @Invalid @FilterInvalid1
  Scenario: Tri avec option invalide
    Given Je suis connecté et sur la page des produits
    When J'essaie de trier avec une option invalide
    Then Les produits ne doivent pas changer d'ordre

  @Invalid @FilterInvalid2
  Scenario: Tri avec valeur null
    Given Je suis connecté et sur la page des produits
    When J'essaie de trier avec une valeur null
    Then Les produits doivent rester dans leur ordre initial

