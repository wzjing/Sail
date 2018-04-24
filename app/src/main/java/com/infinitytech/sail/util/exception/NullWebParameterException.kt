package com.infinitytech.sail.util.exception

/**
 * Exception: Web request not set parameters
 */
public class NullWebParameterException: Exception {

    public constructor(): super("request parameter not set")
    public constructor(message: String): super(message)

}