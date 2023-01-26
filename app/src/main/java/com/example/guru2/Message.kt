package com.example.guru2

data class Message(
    var message:String?,
    var sendId:String?
){
    constructor():this("", "")
}
