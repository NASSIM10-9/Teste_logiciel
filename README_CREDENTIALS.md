# Credentials Saucedemo.com

## Utilisateurs Disponibles

Tous les utilisateurs utilisent le même mot de passe : **`secret_sauce`**

### Utilisateurs Valides

1. **standard_user** - Utilisateur standard, fonctionne normalement
   - Username: `standard_user`
   - Password: `secret_sauce`

2. **performance_glitch_user** - Utilisateur avec problèmes de performance
   - Username: `performance_glitch_user`
   - Password: `secret_sauce`
   - Note: Les pages peuvent prendre plus de temps à charger

3. **visual_user** - Utilisateur avec problèmes visuels
   - Username: `visual_user`
   - Password: `secret_sauce`
   - Note: Peut avoir des problèmes d'affichage

### Utilisateurs pour Tests d'Erreurs

4. **locked_out_user** - Utilisateur verrouillé
   - Username: `locked_out_user`
   - Password: `secret_sauce`
   - Note: La connexion échouera avec le message "Sorry, this user has been locked out"

5. **problem_user** - Utilisateur avec problèmes
   - Username: `problem_user`
   - Password: `secret_sauce`
   - Note: Peut avoir des comportements anormaux

6. **error_user** - Utilisateur avec erreurs
   - Username: `error_user`
   - Password: `secret_sauce`
   - Note: Peut générer des erreurs lors des interactions

## Utilisation dans les Tests

Les credentials sont configurés dans `src/main/resources/config.properties` et peuvent être utilisés dans les scénarios Cucumber.

### Exemple d'utilisation :

```gherkin
Scenario: Connexion avec utilisateur standard
  Given Je suis sur la page de connexion
  When Je saisis le nom d'utilisateur "standard_user"
  And Je saisis le mot de passe "secret_sauce"
  And Je clique sur le bouton de connexion
  Then La connexion doit être réussie
```

## Notes Importantes

- **locked_out_user** est spécifiquement conçu pour tester les cas d'échec de connexion
- **standard_user** est recommandé pour la plupart des tests fonctionnels
- Tous les utilisateurs partagent le même mot de passe pour simplifier les tests

