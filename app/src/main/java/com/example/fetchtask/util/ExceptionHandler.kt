package com.example.fetchtask.util


//custom exception class
class APIException(val error: String, val errorCode: Int) : Exception() {
}