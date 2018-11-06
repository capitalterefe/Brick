Feature: Manage Pet Owners Information From The Dashboard
@test
Scenario: Register A new Pet Owner
Given Admin Is on Pet Clinic Home Page 
When Admin Register A New Owner 
|firstName|lastName|address|city|telephone	|
|Tst	  |lst	   |125	   |lxt	|202		|
Then Admin Should Able To Search A Registered Owner with FirstName 
|firstName|
|Tst	  |


