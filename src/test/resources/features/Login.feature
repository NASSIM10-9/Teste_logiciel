@Login
Feature: Connexion utilisateur
  En tant qu'utilisateur
  Je veux pouvoir me connecter à mon compte
  Afin d'accéder à mes fonctionnalités personnalisées

  @Valid @LoginValid
  Scenario: Connexion avec identifiants valides
    Given Je suis sur la page de connexion
    When Je saisis le nom d'utilisateur "standard_user"
    And Je saisis le mot de passe "secret_sauce"
    And Je clique sur le bouton de connexion
    Then La connexion doit être réussie
    And Un message de succès doit être affiché

  @Invalid @LoginInvalid1
  Scenario: Connexion avec mot de passe incorrect
    Given Je suis sur la page de connexion
    When Je saisis le nom d'utilisateur "standard_user"
    And Je saisis le mot de passe "wrong_password"
    And Je clique sur le bouton de connexion
    Then La connexion doit échouer
    And Un message d'erreur doit être affiché
    And Le message doit contenir "Username and password do not match"

  @Invalid @LoginInvalid2
  Scenario: Connexion avec champs vides
    Given Je suis sur la page de connexion
    When Je saisis le nom d'utilisateur ""
    And Je saisis le mot de passe ""
    And Je clique sur le bouton de connexion
    Then La connexion doit échouer
    And Un message d'erreur doit être affiché
    And Le message doit contenir "Username is required"

  @Invalid @LoginInvalid3
  Scenario: Connexion avec utilisateur verrouillé
    Given Je suis sur la page de connexion
    When Je saisis le nom d'utilisateur "locked_out_user"
    And Je saisis le mot de passe "secret_sauce"
    And Je clique sur le bouton de connexion
    Then La connexion doit échouer
    And Un message d'erreur doit être affiché
    And Le message doit contenir "Sorry, this user has been locked out"

