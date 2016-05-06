Le mode d'emploi se trouve dans le rapport.

organisation des fichiers :

UCLove : dossier principal de l'application
| app : contient tous les fichier sources
  | src : contient tous les fichiers sources
    | androidTest : permet de lancer des tests sur l'app. N'est pas utilisé dans notre cas
    | test : permet de lancer des tests sur l'app. N'est pas utilisé dans notre cas
    | main : contient les fichiers sources
      | AndroidManifest : contient les méta-données de l'application
      | res : contient toutes les données (images, text, layout
        | drawable : contient les images
        | layout : contient les layout
        | values : contient les fichiers de valeur, tels que string ou colors. Si suivit de en, indique qu'il s'agit des valeurs anglaises
      | java/com/lsinf/uclove : contient tous les fichiers sources de l'application
