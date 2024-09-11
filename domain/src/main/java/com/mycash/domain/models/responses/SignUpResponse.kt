package com.mycash.domain.models.responses

import com.mycash.domain.models.beans.signUp.SignUp

data class SignUpResponse(val data: SignUp) : BaseResponse()