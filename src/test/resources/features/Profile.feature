@Navigation
Feature: Navigation et menu utilisateur
  En tant qu'utilisateur connecté
  Je veux pouvoir naviguer dans l'application
  Afin d'accéder aux différentes fonctionnalités

  @Valid @NavigationValid
  Scenario: Navigation vers la page About via le menu
    Given Je suis connecté et sur la page des produits
    When J'ouvre le menu burger
    And Je clique sur "About"
    Then Je dois être redirigé vers la page About
    And L'URL doit contenir "saucelabs.com"

  @Invalid @NavigationInvalid1
  Scenario: Navigation avec menu fermé
    Given Je suis connecté et sur la page des produits
    When J'essaie de cliquer sur un élément du menu sans l'ouvrir
    Then Le menu ne doit pas s'ouvrir
    And Je dois rester sur la page des produits

  @Invalid @NavigationInvalid2
  Scenario: Navigation vers une page inexistante
    Given Je suis connecté et sur la page des produits
    When J'essaie d'accéder à une URL invalide
    Then Je dois rester sur la page actuelle
    And Un message d'erreur peut être affiché

