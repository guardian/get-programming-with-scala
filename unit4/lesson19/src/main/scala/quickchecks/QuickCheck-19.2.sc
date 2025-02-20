// What happens to the implementation of the function welcome if you declare Person
// as a regular class rather than a case class – that is, you remove the case keyword from its declaration?


// ANSWER

// The function welcome no longer compiles. The compiler complains that it cannot find a value Person:
// a companion object with the name Person containing an implementation for the unapply function no longer
// exists because you have declared Person as a regular class rather than a case class.
// Also, its fields name and age are no longer publicly accessible.

class Person(val name: String, val age: Int)

def welcome(person: Person): String = person match {
  case Person("Tom", _) => "Hello Mr Tom!"
  case Person(name, age) if age > 18 => s"Good to see you $name"
  case p @ Person(_, 18) => s"${p.name}, welcome!"
  case Person(_, _) => "Hi bro!"
}
