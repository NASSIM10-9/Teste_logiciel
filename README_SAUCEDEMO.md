# Projet d'Examen - Test Automatisé pour Saucedemo.com

Ce projet a été adapté pour tester le site **https://www.saucedemo.com**, un site de démonstration e-commerce.

## 🎯 Scénarios de Test Adaptés

### 1. **Connexion Utilisateur** (`Login.feature`)
- ✅ Cas valide : Connexion avec `standard_user` / `secret_sauce`
- ❌ Cas invalide 1 : Connexion avec mot de passe incorrect
- ❌ Cas invalide 2 : Connexion avec champs vides

### 2. **Gestion du Panier** (`Cart.feature`)
- ✅ Cas valide : Ajout d'un article au panier
- ❌ Cas invalide 1 : Suppression d'un article d'un panier vide
- ❌ Cas invalide 2 : Suppression avec index invalide

### 3. **Filtrage et Tri des Produits** (`Search.feature` → `Filter`)
- ✅ Cas valide : Tri des produits par nom (A-Z)
- ❌ Cas invalide 1 : Tri avec option invalide
- ❌ Cas invalide 2 : Tri avec valeur null

### 4. **Navigation et Menu** (`Profile.feature` → `Navigation`)
- ✅ Cas valide : Navigation vers la page About via le menu burger
- ❌ Cas invalide 1 : Navigation avec menu fermé
- ❌ Cas invalide 2 : Navigation vers une page inexistante

### 5. **Processus de Checkout** (`Checkout.feature`)
- ✅ Cas valide : Checkout avec informations valides (prénom, nom, code postal)
- ❌ Cas invalide 1 : Checkout avec prénom vide
- ❌ Cas invalide 2 : Checkout avec code postal invalide

## 🔧 Page Objects Adaptés

### LoginPage
- Sélecteurs réels de saucedemo.com :
  - `#user-name` pour le username
  - `#password` pour le password
  - `#login-button` pour le bouton de connexion
  - `[data-test='error']` pour les messages d'erreur

### ProductPage (nouvelle)
- Page des produits après connexion
- Gestion des produits, panier, tri, menu burger

### CartPage
- Sélecteurs adaptés : `.cart_item`, `[data-test='remove']`, `[data-test='checkout']`

### CheckoutPage
- Formulaire en 2 étapes :
  - Step 1 : First name, Last name, Postal code
  - Step 2 : Overview et finalisation

## 🚀 Exécution

```bash
# Compiler le projet
mvn clean install

# Exécuter tous les tests
mvn test

# Exécuter un scénario spécifique
# Modifier les tags dans TestRunner.java
```

## 📊 Credentials Saucedemo

- **Utilisateur valide** : `standard_user` / `secret_sauce`
- **Utilisateur verrouillé** : `locked_out_user` / `secret_sauce`
- **Utilisateur problème** : `problem_user` / `secret_sauce`

## ⚠️ Notes Importantes

1. Les sélecteurs ont été adaptés aux éléments réels de saucedemo.com
2. Le site utilise des `data-test` attributes pour certains éléments
3. Le checkout se fait en 2 étapes (information puis overview)
4. Le menu burger nécessite d'être ouvert avant de cliquer sur les liens

## 📁 Structure Adaptée

- **Page Objects** : Tous adaptés avec les sélecteurs réels
- **Step Definitions** : Mises à jour pour saucedemo.com
- **Feature Files** : Scénarios adaptés aux fonctionnalités réelles
- **Configuration** : URL mise à jour dans `config.properties`

