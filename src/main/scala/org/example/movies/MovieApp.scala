package org.example.movies

import PrintResultHelpers._

object MovieApp extends App {

  val dataset = new MoviesDataset("movies_metadata.csv")
  val movies = dataset.movies

  private val unknown = "--"

  // How many films are there in the dataset?
  // 45463
  printResult(
    question = "How many movies are there in the dataset?",
    answer = {
      val totCount = movies.size
      s"$totCount movies"
    }
  )

  // How many films were released in 1987?
  // 462
  printResult(
    question = "How many movies were released in 1987?",
    answer = {
      val countFrom1987 = movies.count(
        _.releaseDate.exists(_.getYear == 1987))
      s"$countFrom1987 movies"
    }
  )

  // Let’s find the five films with the highest vote average and count.
  // However, you want to penalize those with a low vote count,
  // so discard all movies with less than 50 votes.
  //- [AVG: 9.5, COUNT: 50.0] Planet Earth II
  //- [AVG: 9.1, COUNT: 661.0] Dilwale Dulhania Le Jayenge
  //- [AVG: 8.8, COUNT: 176.0] Planet Earth
  //- [AVG: 8.7, COUNT: 68.0] Sansho the Bailiff
  //- [AVG: 8.6, COUNT: 98.0] Human
  printResult(
    question = "TOP 5 movies per vote average and count",
    answers = {
      val topPerVote =
        movies.filter(_.voteCount >= 50)
          .sortBy { movie =>
            (- movie.voteAverage, - movie.voteCount)
          }.take(5)
      topPerVote.map { movie =>
        s"[AVG: ${movie.voteAverage}, COUNT: ${movie.voteCount}] " +
          s"${movie.title}"
      }
    }
  )

  // Let’s now compute a movie ranking per popularity and select the top five.
  // - [POPULARITY: 547.4883] Minions
  //- [POPULARITY: 294.33704] Wonder Woman
  //- [POPULARITY: 287.25366] Beauty and the Beast
  //- [POPULARITY: 228.03275] Baby Driver
  //- [POPULARITY: 213.84991] Big Hero 6
  printResult(
    question = "TOP 5 movies per popularity",
    answers = {
      val topPerPopularity =
        movies.sortBy { movie =>
          -movie.popularity.getOrElse(0f)
        }.take(5)
      topPerPopularity.map { movie =>
        s"[POPULARITY: ${movie.popularity.getOrElse(unknown)}] " +
          s"${movie.title}"
      }
    }
  )

  // Are there any movies that are not in English?
  // Select 5 non-English movies.
  // - [LANG: fr, RELEASE DATE: 1995-05-16] The City of Lost Children (La Cité des Enfants Perdus)
  //- [LANG: zh, RELEASE DATE: 1995-04-30] Shanghai Triad (摇啊摇，摇到外婆桥)
  //- [LANG: fr, RELEASE DATE: 1996-09-18] Wings of Courage (Guillaumet, les ailes du courage)
  //- [LANG: it, RELEASE DATE: 1994-01-01] Lamerica (Lamerica)
  //- [LANG: it, RELEASE DATE: 1994-09-22] The Postman (Il postino)
  printResult(
    question = "5 non-english movies",
    answers = {
      val topNonEnglishPerPopularity =
        movies.filterNot(_.originalLanguage == "en")
          .take(5)
      topNonEnglishPerPopularity.map { movie =>
        s"[LANG: ${movie.originalLanguage}, " +
          s"RELEASE DATE: ${movie.releaseDate.getOrElse(unknown)}] " +
          s"${movie.title} (${movie.originalTitle})"
      }
    }
  )

  //find which movie the most profit.
  // Although profit is not a property in our movie data set,
  // you can derive it from its revenue and budget.
  // - [PROFIT: USD 1,823,223,624] Star Wars: The Force Awakens
  printResult(
    question = "Which movie made the most profit?",
    answer = {
      val mostProfit = movies.maxBy(
        movie => movie.revenue - movie.budget)
      val formattedProfit = {
        val formatter = java.text.NumberFormat.getInstance()
        formatter.format(mostProfit.revenue - mostProfit.budget)
      }
      s"[PROFIT: USD $formattedProfit] ${mostProfit.title}"
    }
  )

  // Additional queries:
  // List all the genres available in the data set.
  // Which movie is the most recent one?
  // What is the duration of the longest film?
  // Which of them made the smallest profit?

}
