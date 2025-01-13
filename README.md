# Developer in Test Take Home Assignment
Created an application that will 
- Read in provided data from an external text file. 
- Group, sort, and order the data. 
- Display the output to the screen. 

## Application Design
#### Read and Write Considerations
With the application reading in data from an external text file and outputting data in a formatted manner to the console, I wanted to make sure that the script would be able to handle changes to the data quickly. For this reason, I used a Hashmap of Lists to have constant time lookup of the household itself, while also being able to edit the members in the household easily through the use of a Arraylist. 

#### Data format
When looking at the provided data example, I found that each datum was enclosed within quotation marks, had different capitalization, and had typos. To format data to be usable in the script, I chose to make all addresses uppercase so that they could be used for the key in the hashmap, while retaining original casing for when the households were displayed. 
Since the data seemed to be separated by commas, I did choose to initially split the strings based on commas however, I found that there could be typos within the data that may include a comma which would lead to incorrect data splitting and therefore, would result in incorrect read of the data. By creating a method that retrieved the substring within the quotation marks, I am able to process all data within the quotation marks correctly. 


#### Questions
Although the data seemed to be formatted in an automated way with all datum being enclosed in Quotation marks, I was wondering if that was a correct assumption or if I had overlooked a detail. 
    - Initially, I had used split commas to convert the lines of data into String[] however, I found there to be a typo (a misplaced comma) in one the lines which would split the data incorrectly.
    - In order to allow for the data to include typos, I decided that since it looked like all data was enclosed in quotation marks, to use that as the basis for where true data was included. 


