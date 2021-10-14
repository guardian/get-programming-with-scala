package org.example.movies

import PrintResultHelpers._

object MovieApp extends App {

  val dataset = new MoviesDataset("movies_metadata.csv")
  val movies = dataset.movies

  private val unknown = "--"

  // How many films are there in the dataset?
  // Use the printResult function in PrintResultHelpers.scala to do this
  printResult("How many films are there in the dataset?", s"${movies.length}")

  // How many films were released in 1987?
  if(movies.releaseDate.exists(releaseDate => releaseDate.getYear == 1987)) {

  }
  printResult("How many films were released in 1987?")

  // Let’s find the five films with the highest vote average and count.
  // However, you want to penalize those with a low vote count,
  // so discard all movies with less than 50 votes.
  val filteredMovies = movies.filter(_.voteCount >= 50)
  val result = filteredMovies.sortBy(movie => (- movie.voteAverage, - movie.voteCount)).take(5)
  val newResult = result.map { movie =>
    s"${movie.title}, ${movie.voteCount}, ${movie.voteAverage}\n"
  }
  printResult("Most popular movies", s"$newResult")

  // Let’s now compute a movie ranking per popularity and select the top five.

  // Are there any movies that are not in English? Select 5 non-English movies.

  //find which movie the most profit.
  // Although profit is not a property in our movie data set,
  // you can derive it from its revenue and budget.

  // Additional queries:
  // List all the genres available in the data set.
  // Which movie is the most recent one?
  // What is the duration of the longest film?
  // Which of them made the smallest profit?
}
