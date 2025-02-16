package com.creative.weather_app.data

class DataofException<T,Boolean, E:Exception> (
    var data : T? = null,
    var loading : Boolean? = null,
    var exception : E? = null

)