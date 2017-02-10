# Master

## Cloner ce projet a local

- ssh `$git clone git@github.com:TheOriginalsESIPE/Master.git`   
- http `$https://github.com/TheOriginalsESIPE/Master.git`   

## Remote add ce projet
N'oublie pas initialiser un git dans votre projet. `$git init`   

`$git remote add name_respositry git@github.com:TheOriginalsESIPE/Master.git`   

## Creer et changer votre branch

`$git checkout -b prenom.nom`   

## Mise en ligne votre branch 
`$git push origin(le nom de votre connection a distance) nom_branch:master`   

## Mise a jour votre projet a master
`$git add nom_fichier`   
`$git commit -m "votre_email"`   
`$git push origin(le nom de votre connection a distance) master`   

## Merger une branche avec la branche actuelle

`git merge nom_branch`   

## Processus pour commencer
1. cloner ce projet dans ton respositry local avec `$git clone addr.git`   
2. changer le repertoire dans Master de ce projet `$cd Master`   
3. verifier si tous sont correcte `$git remote show origin`   
4. creer votre propre branche `$git -b checkout nomprenom`   
5. editer votre branche en ajoutant les fichiers `$git add *.java; $git commit -m "xxx@etu.u-pec.fr"`   
6. push votre branche sur celle branche de github `$git push origin nomprenom`   

