Feature: Manage Pets Information From The Dashboard
@test
Scenario: Add A New Pet To Owners Information 
Given Admin Is on Pet Clinic Home Page 
And Admin Should Able To Search A Registered Owner with FirstName 
|firstName |
|George	 	 |
And Admin Should Be Able To Add New Pet To Owner
|petName|petBirthday|petType|
|pepe	|12/12/2015 |dog	|
Then Admin Should be Able To Add a new Visit To A Pet
|petName|petBirthday|petType|visitDiscription|
|pepe	|12/12/2015 |dog	|regular checkup |

