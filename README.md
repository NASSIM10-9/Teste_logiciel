# Projet d'Examen - Test Logiciel Automatisé

Ce projet implémente un framework de test automatisé complet utilisant Selenium WebDriver, Cucumber, et Extent Reports selon les spécifications de l'examen.

## 📋 Fonctionnalités Implémentées

### ✅ Outils et Bonnes Pratiques

- **Page Object Model (POM)** : Structure organisée avec des classes de pages séparées
- **Cucumber** : Scénarios de test définis en langage naturel (Gherkin)
- **Extent Reports** : Génération de rapports détaillés avec captures d'écran
- **Captures d'écran automatiques** : En cas d'échec de test
- **Explicit Waits** : Utilisation systématique d'attentes explicites pour la robustesse

### 🧪 Scénarios de Test

Le projet contient **4 scénarios principaux** avec cas valides et non valides :

1. **Recherche et validation de résultats** (`Search.feature`)
   - ✅ Cas valide : Recherche avec résultats
   - ❌ Cas invalide 1 : Recherche avec terme vide
   - ❌ Cas invalide 2 : Recherche avec caractères spéciaux invalides

2. **Gestion du panier d'achat** (`Cart.feature`)
   - ✅ Cas valide : Ajout d'un article au panier
   - ❌ Cas invalide 1 : Suppression d'un article d'un panier vide
   - ❌ Cas invalide 2 : Suppression avec index invalide

3. **Connexion utilisateur** (`Login.feature`)
   - ✅ Cas valide : Connexion avec identifiants valides
   - ❌ Cas invalide 1 : Connexion avec mot de passe incorrect
   - ❌ Cas invalide 2 : Connexion avec champs vides

4. **Gestion du profil utilisateur** (`Profile.feature`)
   - ✅ Cas valide : Mise à jour avec données valides
   - ❌ Cas invalide 1 : Mise à jour avec email invalide
   - ❌ Cas invalide 2 : Mise à jour avec téléphone invalide

5. **Processus de paiement** (`Checkout.feature`) - Bonus
   - ✅ Cas valide : Paiement avec informations valides
   - ❌ Cas invalide 1 : Paiement avec numéro de carte invalide
   - ❌ Cas invalide 2 : Paiement avec date d'expiration expirée

## 🏗️ Structure du Projet

```
test-automation-exam/
├── pom.xml
├── README.md
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/testautomation/
│   │   │       ├── pages/          # Page Object Model
│   │   │       │   ├── BasePage.java
│   │   │       │   ├── HomePage.java
│   │   │       │   ├── CartPage.java
│   │   │       │   ├── LoginPage.java
│   │   │       │   ├── ProfilePage.java
│   │   │       │   └── CheckoutPage.java
│   │   │       └── utils/          # Utilitaires
│   │   │           ├── DriverManager.java
│   │   │           ├── ScreenshotUtil.java
│   │   │           └── WaitUtil.java
│   │   └── resources/
│   │       └── extent.properties
│   └── test/
│       ├── java/
│       │   └── com/testautomation/
│       │       ├── runners/
│       │       │   └── TestRunner.java
│       │       └── stepdefinitions/
│       │           ├── Hooks.java
│       │           ├── SearchStepDefinitions.java
│       │           ├── CartStepDefinitions.java
│       │           ├── LoginStepDefinitions.java
│       │           ├── ProfileStepDefinitions.java
│       │           └── CheckoutStepDefinitions.java
│       └── resources/
│           ├── features/           # Fichiers Cucumber
│           │   ├── Search.feature
│           │   ├── Cart.feature
│           │   ├── Login.feature
│           │   ├── Profile.feature
│           │   └── Checkout.feature
│           └── extent-config.xml
└── screenshots/                    # Captures d'écran (généré)
└── test-output/                    # Rapports (généré)
```

## 🚀 Installation et Configuration

### Prérequis

- Java 11 ou supérieur
- Maven 3.6+
- Navigateur Chrome (ou Firefox/Edge)

### Installation

1. Cloner ou télécharger le projet
2. Installer les dépendances Maven :
```bash
mvn clean install
```

3. WebDriverManager téléchargera automatiquement les drivers nécessaires

## ▶️ Exécution des Tests

### Exécuter tous les tests

```bash
mvn test
```

### Exécuter un scénario spécifique

Modifier les tags dans `TestRunner.java` :
```java
tags = "@Search"  // Pour exécuter uniquement les tests de recherche
```

### Exécuter via l'IDE

Exécuter la classe `TestRunner.java` directement depuis votre IDE.

## 📊 Rapports

### Extent Reports

Après l'exécution, le rapport Extent Reports sera généré dans :
```
test-output/ExtentReport.html
```

Le rapport inclut :
- ✅ Résultats détaillés de tous les scénarios
- 📸 Captures d'écran pour les cas échoués
- 📝 Statuts des tests (succès/échec)
- ⏱️ Temps d'exécution

### Cucumber Reports

Rapports Cucumber disponibles dans :
```
target/cucumber-reports/
```

## 🔧 Configuration

### Changer de navigateur

Définir la propriété système :
```bash
mvn test -Dbrowser=firefox
# ou
mvn test -Dbrowser=edge
```

Par défaut, Chrome est utilisé.

### Modifier l'URL de test

Modifier les URLs dans les step definitions selon votre application à tester.

## 📝 Notes Importantes

1. **Explicit Waits** : Tous les tests utilisent des attentes explicites via `WaitUtil` pour éviter les tests flaky
2. **Captures d'écran** : Automatiquement prises en cas d'échec et intégrées au rapport
3. **Page Object Model** : Chaque page a sa propre classe pour maintenir la maintenabilité
4. **Séparation des préoccupations** : Utilitaires, pages, et step definitions sont séparés

## 🎯 Points Clés de l'Implémentation

- ✅ **Robustesse** : Utilisation systématique d'explicit waits
- ✅ **Maintenabilité** : Structure POM claire et organisée
- ✅ **Lisibilité** : Scénarios Cucumber en langage naturel
- ✅ **Rapportage** : Rapports détaillés avec captures d'écran
- ✅ **Couverture** : 4+ scénarios avec cas valides et invalides

## 📚 Technologies Utilisées

- **Selenium WebDriver 4.15.0** : Automatisation du navigateur
- **Cucumber 7.14.0** : BDD framework
- **Extent Reports 5.1.1** : Génération de rapports
- **WebDriverManager 5.6.2** : Gestion automatique des drivers
- **JUnit 5** : Framework de test
- **Maven** : Gestion des dépendances

## 👨‍💻 Adaptation à Votre Application

Pour adapter ce projet à votre application :

1. Modifier les URLs dans les step definitions
2. Ajuster les sélecteurs dans les Page Objects selon votre application
3. Adapter les scénarios Cucumber à vos besoins spécifiques
4. Personnaliser les messages d'erreur et de succès

## 📄 Licence

Ce projet est créé dans le cadre d'un examen académique.

---

**Bonne chance pour votre examen ! 🎓**

