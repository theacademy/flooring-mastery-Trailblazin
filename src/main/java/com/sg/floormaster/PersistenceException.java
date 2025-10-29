package com.sg.floormaster;


//Class for our custom exceptions
// Remember that when we extend Exception, our new exception will be a checked exception.
// We MUST handle it.
public class PersistenceException extends Exception {

    //Constuctors simply use the matching constructor for Exception class

    //This is for a validation-type exception - this would not break runtime
    public PersistenceException(String message) {
        super(message);
    }

    //This is for a logic-type exception - this would break runtime
    public PersistenceException(String message, Throwable cause) {
        super(message, cause);
    }
}


