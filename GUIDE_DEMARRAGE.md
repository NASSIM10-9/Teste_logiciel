# Guide de Démarrage Rapide

## 🚀 Installation

1. **Installer Java 11+**
   - Vérifier : `java -version`

2. **Installer Maven**
   - Vérifier : `mvn -version`

3. **Compiler le projet**
   ```bash
   mvn clean install
   ```

## ▶️ Exécution des Tests

### Première exécution
```bash
mvn test
```

### Exécuter un scénario spécifique
Modifier les tags dans `TestRunner.java` :
```java
tags = "@Search"  // Pour exécuter uniquement les tests de recherche
```

## 📊 Consulter les Rapports

### Extent Reports
Ouvrir dans le navigateur :
```
test-output/ExtentReport.html
```

### Cucumber Reports
```
target/cucumber-reports/index.html
```

## 🔧 Configuration

### Changer l'URL de test
Modifier `src/main/resources/config.properties` :
```properties
base.url=https://votre-application.com
```

### Changer de navigateur
```bash
mvn test -Dbrowser=firefox
# ou
mvn test -Dbrowser=edge
```

## ⚠️ Notes Importantes

1. **Adaptation nécessaire** : Les sélecteurs dans les Page Objects doivent être adaptés à votre application web
2. **URLs de test** : Modifier les URLs dans les step definitions selon votre application
3. **Sélecteurs** : Les sélecteurs actuels sont des exemples - à remplacer par ceux de votre application

## 📁 Structure des Fichiers Importants

- **Page Objects** : `src/main/java/com/testautomation/pages/`
- **Step Definitions** : `src/test/java/com/testautomation/stepdefinitions/`
- **Feature Files** : `src/test/resources/features/`
- **Configuration** : `src/main/resources/config.properties`

## 🐛 Résolution de Problèmes

### Erreur : "Driver not found"
WebDriverManager télécharge automatiquement les drivers. Si problème persiste :
```bash
mvn clean install -U
```

### Tests échouent
1. Vérifier que l'application web est accessible
2. Adapter les sélecteurs dans les Page Objects
3. Vérifier les URLs dans les step definitions

### Erreurs de compilation
```bash
mvn clean compile
```

