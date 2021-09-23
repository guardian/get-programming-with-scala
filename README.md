Baseline code for the Capstone Lesson 33 in Unit 5 of the book "Get Programming with Scala" by Daniela Sfregola

## About this Capstone Project
In this capstone, you’ll analyze data for more than 45,000 movies. 
The information is a subset of a popular and publicly accessible dataset called “The Movies Dataset” by Rounak Banik.
https://www.kaggle.com/rounakbanik/the-movies-dataset

For this capstone, you’ll focus on a subset of its data contained in a file called movies_ metadata.csv.
You will interrogate the data set to discover information about these movies. 

## Overview of the Codebase
The src/main/resources/movies_metadata.csv is the resource containing the movie data you’ll analyze.
It provides many fields for each movie, but you’ll only focus on some of them. 
Finally, the src/scala folder contains some useful implementations:
- org.example.movies.MoviesDataset uses an external dependency, called scala-csv, to read the file and parse each line into a Movie instance by invoking the org.example.movies.entities.Movie.parse function.
- org.example.movies.entities.Parsers contains several functions to parse string values. Each parse function tries to convert some text into a specific type value. It returns None in case of failure, and it wraps the parsed value into a Some otherwise. These parser functions take advantage of the Map and Try types and the circe library to parse JSON objects, which are topics you’ll learn about in subsequent units of the book.
- org.example.movies.entities.Movie provides the structure of a movie and its fields you should consider in your analysis. You will provide an implementation for its parse function by combining the provided base parse functions.

### Movie.scala
The scala-csv library will convert each line of the movies_metadata.csv file into a dictionary-like structure called Map, 
in which it associates each movie feature with its corresponding string value. 
You’ll now implement the function Movie.parse, which converts the row parameter into a Movie instance. 
It will rely on the parse functions already implemented in Parsers.

These functions will try to find the value associated with a given key/feature and create an instance for their specific type. 
If a value exists and is compatible with the expected type, it will produce an instance of that type wrapped into a Some. 
For all the other cases, it will return None. A few example usages are the following:
```scala
parseInt(Map("id" -> "1"), "id")
// returns Some(1)
 
parseInt(Map("id" -> "1"), "myId")
// returns None because a value for key myId does not exist
 
parseInt(Map("id" -> "test"), "id")
// returns None because the string “test” cannot be converted to an Int
```
Although the data set documents that every movie has a unique identifier under the label ID, this is missing for three films in the CSV file. 
Rather than failing at runtime, your program gracefully handles it by logging a warning and skipping the row.

### How to Query the dataset
Add your queries to `MovieApp.scala` and use `sbt run` to run the project to see the response.
`PrintResultHelpers.scala` prints human-readable messages containing the question you asked and its answer. 
When you run the project, you should see the following:
```bash
$sbt run
[info] running org.example.movies.MovieApp 
21:11:10.645 [run-main-0] INFO org.example.movies.MoviesDataset - Processing file movies_metadata.csv...
21:11:14.120 [run-main-0] INFO org.example.movies.MoviesDataset - Completed processing of file movies_metadata.csv! 45466 records loaded
21:11:15.550 [run-main-0] WARN org.example.movies.entities.Movie$ - Skipping malformed movie row
21:11:15.764 [run-main-0] WARN org.example.movies.entities.Movie$ - Skipping malformed movie row
21:11:15.905 [run-main-0] WARN org.example.movies.entities.Movie$ - Skipping malformed movie row
[success] Total time: 21 s, completed 20-Jan-2021 21:11:16
```

Notice three expected warnings in the logs; the three movies without a value for ID are causing them. 






