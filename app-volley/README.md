# Gestion des étudiants

## Aperçu

Ce dépôt contient deux dossiers principaux : un pour l'application frontend Android Studio et un autre pour le code backend PHP. Suivez les instructions ci-dessous pour configurer et exécuter les deux composants.

## Structure des dossiers

```
/app-volley
├── TP5/
│   └── (fichiers du projet Android Studio)
└── php_volley/
    └── (fichiers PHP pour le backend)
```

### 1. Frontend Android

Le dossier frontend-android contient le projet Android Studio pour l'application mobile. Ce projet est construit en Java et suit les pratiques standard de développement Android.

Pour exécuter l'application Android, suivez ces étapes :

1. **Ouvrez Android Studio**.
2. **Importez le projet** :
   - Sélectionnez "Ouvrir un projet existant".
   - Naviguez jusqu'au dossier frontend-android et sélectionnez-le.
3. **Compilez et exécutez** :
   - Assurez-vous d'avoir un émulateur Android ou un appareil physique connecté.
   - Cliquez sur le bouton Exécuter ou appuyez sur Maj + F10 pour compiler et lancer l'application.

### 2. Backend PHP

Le dossier backend-php contient le code PHP pour les services backend. Pour tester le backend, vous pouvez utiliser XAMPP ou tout autre environnement de serveur local.

Voici comment le configurer avec XAMPP :

1. **Téléchargez et installez XAMPP** :
   - Si vous n'avez pas XAMPP installé, téléchargez-le depuis [Apache Friends](https://www.apachefriends.org/index.html) et suivez les instructions d'installation.
2. **Déplacez les fichiers PHP dans le répertoire XAMPP** :
   - Copiez le contenu du dossier backend-php.
   - Collez-le dans le répertoire htdocs de votre installation XAMPP (généralement situé à C:\xampp\htdocs sous Windows).
3. **Démarrez le serveur Apache** :
   - Ouvrez le panneau de contrôle XAMPP.
   - Cliquez sur le bouton Démarrer à côté d'Apache.
4. **Accédez au backend** :
   - Ouvrez votre navigateur web et naviguez vers http://localhost/[nom-de-votre-dossier] pour accéder aux fichiers PHP.
   - Remplacez [nom-de-votre-dossier] par le nom réel du dossier, le cas échéant.

## Test de l'application

- Assurez-vous que le frontend Android et le backend PHP sont en cours d'exécution.
- Vous pouvez tester la connexion entre le frontend et le backend en utilisant l'application mobile, qui effectuera des requêtes API vers les services backend.

## Conclusion

Ce dépôt fournit les composants nécessaires pour exécuter une application mobile Android avec un backend PHP. Si vous avez des questions ou des problèmes, n'hésitez pas à créer une issue dans ce dépôt.
