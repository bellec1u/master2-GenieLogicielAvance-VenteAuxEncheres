# master2-GenieLogicielAvance-VenteAuxEncheres

## Equipe :

 - LÃ©opold BELLEC
 - Juozas DAUZVARDIS
 - Brice PETERS
 - Jean-Marc DEBICKI

## JMS Configuration

Pour pouvoir lancer l'application, il faut configurer les ressources JMS sur le serveur Glassfish.
Deux solutions sont disponibles :

### Depuis la console admin glassfish

Allez sur localhost:4848

Dans la barre de navigation à gauche, cliquez sur JMS Resources -> Destination Resources
Cliquez sur "New..."

Entrez : "jms/VenteAuxEncheresQueue" dans "JNDI Name", "VenteAuxEncheresQueue" dans "Physical Destination Name" et choisissez "javax.jms.Queue" dans "Resource Type"
Cliquez sur "OK"

Dans la barre de navigation à gauche, cliquez sur JMS Resources -> Connection Factories
Cliquez sur "New..."

Entrez : "jms/VenteAuxEncheresQueueFactory" dans "Pool Name" et choisissez "javax.jms.QueueConnectionFactory" dans "Resource Type"
Cliquez sur "OK"

La configuration est terminée

### En ligne de commande

Dans un terminal, naviguez vers le dossier du glassfish que vous utilisez pour lancer le projet.

Naviguez dans ./bin

Vous trouverez, entre autres, le binaire "asadmin"

D'ici, lancez les deux commandes suivantes

```
    asadmin create-jms-resource --restype javax.jms.Queue --property Name=VenteAuxEncheresQueue jms/VenteAuxEncheresQueue
    
    asadmin create-jms-resource --restype javax.jms.QueueConnectionFactory --description "connection factory for jms/VenteAuxEncheresQueue" jms/VenteAuxEncheresQueueFactory
```

La configuration est terminée
