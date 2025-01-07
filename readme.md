# Histoire d'apprendre Kafka
Imaginons une application de banque sur laquelle pour faire un virement d'argent on doit passer par les étapes suivantes :
- Validation des comptes (Expéditeur + Destinataire + Montant)
- Traitement du virement (Débit sur le compte expéditeur + Crédit sur le compte destinataire)
- Notification des comptes

Chaque traitement va prendre 3s à 5s, alors pour ne pas bloquer l'utilisateur nous allons utiliser kafka.