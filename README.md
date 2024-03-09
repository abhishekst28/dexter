# Hackathon 2021

Here is one of the ideas for hackathon 2021 - Interactive payment gateway for blind people who have incapability to see things. 

Description - In today’s world everyone is moving to digitalization and people are getting rid of physical banknotes. For a normal person there are many options available like GPAY, PAYTM etc.
But there is no such application there is market which can be used by the people who have incapability to see things. 
Our solution target to such people and how we can bring in digitalisation in making payments.

Operations –

1.	Initiate a payment – 
  a.	it could be done by pressing a device button.
  b.	pronouncing a special phrase.  -> we should go for this.
2.	Prompt for options –
  a.	App would response back with available options
    i.	Make a payment.  -> We will go only for this option now.
    ii.	Get a payment.
    iii.	Generate my random QR code. 
3.	Select an option say “Option i”
4.	Prompt for number
  a.	User can respond either with phone number. -> we go only for this.
  b.	Or person can move around scanning device around the QR code of payment receiver.
5.	Prompt for amount
6.	Prompt for place finger on fingerprint scanner.
7.	Application talks with the payment gateway and perform actions.
  a.	We can have a dummy payment gateway which will send Success or failure message back.
8.	Application prompt back to user about notification of payment gateway.

Notes:

1.	We should try to use the speech recogniser API provided by GCP
2.	Try to use good UX provide by GCP
3.	Java dev will need to write Controller logic
4.	Dummy payment gateway can be used for now.

Important points:

1.	We will be outside the DB network on those days.
2.	Competition starts at 1:00 PM GMT on Thursday and end on 1:00 on Friday
3.	We will get 1000 Euros to use the resources provided by GCP
4.	Come out with a catchy name for team and app 


Demo: https://youtu.be/Sf3WCWWKbvs?si=VDGoCtccEibKpKki



