# Case: Account API

Dette er en delvis løsning til Bank Datas code case for potentielle nye ansatte.

# Tech stack

API'en bruger Spring boot: v3.2.5 backend og Angular CLI: v17.3.5 frontend (bygget med Maven).
Data indtastet af brugeren sendes til en lokal MySQL database (accounts_db)med to tabeller.
(users_table & transactions_table).

# Konfiguration

I filen accountmanager_backend\src\main\resources\application.properties skal forbindelsen
til MySQL databasen konfigureres. Ændrer parametrene:

spring.datasource.url=jdbc:mysql://localhost:3306/DIN_DATABASE
spring.datasource.username=DIT_BRUGERNAVN
spring.datasource.password=DIT_PASSWORD

# Egen kode

Spring Initializr blev brugt til at generere en skabelon to backend mappen: 'accountmanager_backend'.
Angulars ng new command blev brugt til at genererer en skabelon to frontend mappen: 'accountmanager_frontend'

Egen kode skal derfor primært findes findes i

# accountmanager_backend/src/java/com/accountmanager:

Definition a classes og methods. De primære classes er User.java og Transaction.java. De primære metoder er defineret
i UserController.java der tillader oprettelse a konti og overførsler. Metoderne kaldes fra Angular frontend.
JPA bliver benyttet til at implementere hibernate som interfaces mellem MySQL og applikationen. Interfaces 
defineres i UserRepository.java and TransactionRepository.java.

# accountmanager_frontend/src/app:

Hovedkomponenter i frontend defineres her med tilhørende styles. Wep-adresserne styres fra app-routing.module.ts.
Relevant databinding mellem Angular frontend til/fra Springboot backend til/fra MySQL database ses tydeligst i 
user.service.ts og transaction.service.ts der gør databasen tilgængelig som web-API.

# Addressering af use casesd


| Operation     | Description                                                       | 
| --------------|-------------------------------------------------------------------|
| `CREATE`      | create an account                                                 |
| `LIST`        | list accounts                                                     |
| `TRANSFER`    | transfer a specified amount from one account to another account   |


Applikationen starter på frontpage hvor brugeren har mulighed for at navigere via baren i toppen til:

"Forside" (tilbage til forside), "Konti" (se liste med oprettede konti), "Opret Konto" (opret ny konto med givent beløb), "Overfør" (overfør mellem oprettede konti), og "Historik (se liste over overførsler og detaljer)".
Alle de basale funktioner er mulgie, men appen er meget simpel og mangler visse af nedenstående considerations.

## Addressing Considerations 
The following list of considerations are things that might be relevant when implementing the above API. You do not need to address all of these concerns in your code, but ideally you have considered how you would address them.
1. **Error handling**: What if an account has insufficient funds for a transfer? What if a specified account does not exist?

  Der kan ikke overføres flere penge end på en given konti og overførslen fejler hvis det forsøges. Der er dog ikke endnu implementeret en advarsel sunlig for brugeren (logges i konsol).
  Der kan ikke overføres til og fra ikke eksisterende konti, da konti i databasen findes baseret på konto-nr.

3. **Security**: How to ensure accounts can only be accessed by their registered owners?

Ideelt ville en login-page have været i brug, hvor brugeren så kun kan tilgå og overføre fra de konti de selv har oprettet.
Overførsel til konti ikke eget af brugeren kunne dog være muligt. Til det skulle der oprettes et login input i frontend der 
binder til backend og checker om det givne brugernavn/kode er korrekt. Eventuelt skulle et admin-login være muligt hvor konti
kan redigeres.
  
5. **Auditing**: How to make sure the transaction history is available for auditing?

En tabel med alle transaktioner er oprettet med egen PRIMARY KEY og kan kobles til en konto
gennem kontonumrene der fungerer som FOREIGN KEY.  

7. **Observability**: How to monitor the health of this application?

En meget simpel test implementeret som standard af de benyttede frameworks. Her testes udelukkende om applikationen kan bygges
og kompileres uden fejl. 

## Optional Features
1. **Persistence**: include a database CHECK
2. **Frontend**: provide a frontend that allows users to interact with the API CHECK
3. **Deployment**: deploy your application in a public cloud hosting service NOT ACHIEVED
The API must implement the following operations:
