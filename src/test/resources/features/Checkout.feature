@Checkout
Feature: Processus de checkout
  En tant qu'utilisateur avec des articles dans le panier
  Je veux pouvoir finaliser ma commande
  Afin de compléter mon achat

  @Valid @CheckoutValid
  Scenario: Checkout avec informations valides
    Given Je suis connecté avec des articles dans le panier
    And Je suis sur la page de checkout
    When Je saisis le prénom "Jean"
    And Je saisis le nom "Dupont"
    And Je saisis le code postal "75001"
    And Je continue vers l'étape suivante
    And Je finalise la commande
    Then La commande doit être complétée avec succès
    And Un message de confirmation doit être affiché

  @Invalid @CheckoutInvalid1
  Scenario: Checkout avec prénom vide
    Given Je suis connecté avec des articles dans le panier
    And Je suis sur la page de checkout
    When Je saisis le prénom ""
    And Je saisis le nom "Dupont"
    And Je saisis le code postal "75001"
    And Je continue vers l'étape suivante
    Then Le checkout doit échouer
    And Un message d'erreur doit être affiché
    And Le message doit contenir "First Name is required"

  @Invalid @CheckoutInvalid2
  Scenario: Checkout avec code postal invalide
    Given Je suis connecté avec des articles dans le panier
    And Je suis sur la page de checkout
    When Je saisis le prénom "Jean"
    And Je saisis le nom "Dupont"
    And Je saisis le code postal "ABC"
    And Je continue vers l'étape suivante
    Then Le checkout doit échouer
    And Un message d'erreur doit être affiché

