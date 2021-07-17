# Linked-Queue
An implementation of a customer service queue as a LinkedList. The program models a group of customers and a service-counter on a typical day from 9am to 5pm, using queues.

Input: 

--> customersfile.txt  :  has the customers’ information, such that each customer has one "paragraph" of two lines, with at least one blank line between one paragraph and the next. The format of the paragraph of a customer is:

ID-NUMBER: a unique integer customer id 
ARRIVAL-TIME: hh:mm:ss

The first line of customersfile.txt is an unsigned positive integer, representing the constant service time per customer, in seconds.

--> queriesfile.txt  :  will have a sequence of queries, one query per line, where the queries can be:

WAITING-TIME-OF customer-id // measured in seconds NUMBER-OF-CUSTOMERS-SERVED
LONGEST-BREAK-LENGTH // measured in seconds 
TOTAL- IDLE-TIME // measured in seconds MAXIMUM-NUMBER-OF-PEOPLE-IN-QUEUE-AT-ANY-TIME

call it from the command-line like this:
>> Program2 customersfile.txt queriesfile.txt
