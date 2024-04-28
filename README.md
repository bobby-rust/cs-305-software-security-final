# Final project for CS-305: Software Security

## Briefly summarize your client, Artemis Financial, and their software requirements. Who was the client? What issue did they want you to address?
Artemis Financial is a financial consulting company that develops savings, investments, retirement, and insurance plans for their customers. Artemis Financial recently modernized their operations, which included upgrading their web interface for their clients. Their web interface is public and they are looking implement the best security practices to protect their customer's data and financial information. They wanted to add a checksum for data transcfers to ensure secure communications.

## What did you do very well when you found your client’s software security vulnerabilities? Why is it important to code securely? What value does software security add to a company’s overall wellbeing?
There were many software security vulnerabilities in the client's project. To find these vulnerabilities, static testing using the OWASP dependency check tool was used. There were a lot of outdated dependencies being used that needed to be updated as they contained known vulnerabilities that could be easily exploited. Conducting a manual review also helped fix the code itself by adjusting and incorporating robust security best practices. Having secure technology is extremely important for a company's wellbeing as it prevents security incidents which alter the company's image and trust of their customers, damaging business.

## What part of the vulnerability assessment was challenging or helpful to you?
Learning to use the OWASP dependency check tool was eye opening when finding how many vulnerabilities exist in versions of software that are just one year old. Understanding this made me see how important it is to regularly update dependencies and check for new vulnerablities.

## How did you increase layers of security? In the future, what would you use to assess vulnerabilities and decide which mitigation techniques to use?
Implementing rate-limiting using Bucket4j, using JWTs during server communications as an efficient way to increase security efficiently, and ensuring that any sensitive server data was kept hidden away in environment variables, hashed, and signed before transmission.

## How did you make certain the code and software application were functional and secure? After refactoring the code, how did you check to see whether you introduced new vulnerabilities?
Running vulnerability tests prior to refactoring the code base allowed me to see what needed to be changed. After refactoring, it is important to check for vulnerablities to ensure that new ones were't introduced. 

## What resources, tools, or coding practices did you use that might be helpful in future assignments or tasks?
Using security best practice flowcharts to have some guidance and prevent forgetting certain aspects of security will be helpful along with the other tools previously mentioned that I used. 

## Employers sometimes ask for examples of work that you have successfully completed to show your skills, knowledge, and experience. What might you show future employers from this assignment?
Showing the certificate generation used to enable HTTPS on the server is a useful skill in creating any production level web application. My knowledge and understanding of hashing algorithms, use of JWTs, rate-limiting, API security, and general code security best practices. 
