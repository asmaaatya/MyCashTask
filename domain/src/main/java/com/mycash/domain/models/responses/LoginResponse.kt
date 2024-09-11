package com.mycash.domain.models.responses

import com.mycash.domain.models.beans.login.Login

data class LoginResponse(val data: Login) : BaseResponse()