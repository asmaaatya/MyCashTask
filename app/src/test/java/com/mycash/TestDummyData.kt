package com.mycash

import com.mycash.domain.models.requests.HomeRequest
import com.mycash.domain.models.requests.LogInRequest
import com.mycash.domain.models.requests.SingUpRequest

object TestDummyData {
    val name = "asmmaa"
    val email = "asmaa@gmail.com"
    val password = "12345678"
    val phone = "012134567896556"

    val user = SingUpRequest(name, email, password, phone)
    val loggedInUser= LogInRequest(name, email)
    val latitude: Double=1.5
    val longitude: Double=2.5
    val filter: Int=1
    val homeRequestData= HomeRequest(latitude, longitude, filter)
}