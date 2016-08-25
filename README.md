# SLang2
The successor language to SLang, this time it supports proper runtime classes with OOP (coming soon)

## Features
* Strings
* Functions
  * All functions are anonymous, they are simply assigned to variables
* Every thing is a SlangInstance
  * Types define functions in the scope of the instance
  * These are then accessed in one of two ways:
    * Access with function call: myvar.func()
    * Operator like invocation: myvar + otherVar
      * This is equivalent to myvar.+(otherVar)
* Variables/Methods with names with special chars (+,-,/,\,*, etc...)
* Instantiation with the `new` keyword


## Coming Soon
* Types that pre-define members for instances
* Custom classes defined at runtime
* A better currying syntax
* Ints, Floats, Lists

## Roadmap
* IMPORTANT - Inheritance
* Booleans
* Numbers
* Custom classes

## Expected issues
* Order of operations

## TODO

##MAYBE
* Allow for classes to be redefined?