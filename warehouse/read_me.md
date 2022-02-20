This project is data warehouse. We can store deals details: currency to convert from, and currency to convert to, deal stamp, and amount.
The system is validating the inputs. You can not enter empty values in the record and you can not enter currency code more than three letters. Also,
you can not enter numerical currency codes.

After submitting the deal, you can not add any other deal with same stamp, the system will not save more than one deal with every stamp.
Also, after submitting the deal, the form will be reloaded again with empty inputs.

<h2>Running app Instructions<h2>

you can pull this repository to your local machine and use Docker to run this app as there is a jar generated and docker file.
Follow the steps bellow to run the app with docker terminal:

1- Navigate to the project folder on your machine.
2- Enter: "docker build -t warehouse-jar.jar" to create the docker image.
3- Enter: "docker run -p PORT_YOU_WANT:8080 warehouse-jar.jar"
4- Hit your localhost using any browser and you will see the application running.
