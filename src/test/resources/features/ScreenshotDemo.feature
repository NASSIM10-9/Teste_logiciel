@ScreenshotDemo
Feature: Démonstration des captures d'écran sur échec
  En tant que testeur
  Je veux vérifier que les captures d'écran sont prises lors des échecs

  Background:
    Given Je suis sur la page de connexion

  Scenario: Echec 1 - Connexion valide attendue comme échec
    When Je saisis le nom d'utilisateur "standard_user"
    And Je saisis le mot de passe "secret_sauce"
    And Je clique sur le bouton de connexion
    Then La connexion doit échouer

  Scenario: Echec 2 - Connexion utilisateur verrouillé attendue comme succès
    When Je saisis le nom d'utilisateur "locked_out_user"
    And Je saisis le mot de passe "secret_sauce"
    And Je clique sur le bouton de connexion
    Then La connexion doit être réussie

  Scenario: Echec 3 - Connexion mauvais mot de passe attendue comme succès
    When Je saisis le nom d'utilisateur "standard_user"
    And Je saisis le mot de passe "wrong_password"
    And Je clique sur le bouton de connexion
    Then La connexion doit être réussie

  Scenario: Echec 4 - Connexion utilisateur inconnu attendue comme succès
    When Je saisis le nom d'utilisateur "unknown_user"
    And Je saisis le mot de passe "secret_sauce"
    And Je clique sur le bouton de connexion
    Then La connexion doit être réussie
